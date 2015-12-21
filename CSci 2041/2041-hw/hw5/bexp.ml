(* boolexpr parsing with continuations and descriptive exceptions *)
type boolExpr =
  Bool of bool
  | And of boolExpr*boolExpr
  | Or of boolExpr*boolExpr
  | Not of boolExpr
  | Var of string

type token = AND | OR | NOT | LIT of bool | OP | CL | NM of string

let rec lex str = 
  let splitlist = Str.full_split (Str.regexp "\\b\\|(\\|)") str in
  let rec tok_splits = function
    | [] -> []
    | (Str.Delim "(")::t -> OP::(tok_splits t)
    | (Str.Delim ")")::t -> CL::(tok_splits t)
    | (Str.Delim _)::t -> tok_splits t
    | (Str.Text s)::t ->
       match String.trim s with
       | "" -> tok_splits t
       | "not" -> NOT::(tok_splits t) | "and" -> AND::(tok_splits t) | "or" -> OR::(tok_splits t)
       | "true" -> (LIT true)::(tok_splits t) | "false" -> (LIT false)::(tok_splits t)
       | "(" -> OP::(tok_splits t) | ")" -> CL::(tok_splits t)
       | _ as s -> (NM s)::(tok_splits t) in
  tok_splits splitlist
				 
let rparse tlist =
  let rec phelp tlist =
    match tlist with
    | (LIT b)::t -> (Bool b, t)
    | (NM s)::t -> (Var s, t)
    | OP::NOT::t -> let (e,t') = phelp t in begin match t' with CL::t'' -> (Not e, t'') | _ -> failwith "unclosed not" end
    | OP::AND::t ->
       let (e1, t1) = phelp t in
       let (e2, t2) = phelp t1 in
       begin match t2 with CL::t' -> (And (e1,e2), t') | _ -> failwith "unclosed and" end
    | OP::OR::t ->
       let (e1,t1) = phelp t in
       let (e2,t2) = phelp t1 in
       begin match t2 with CL::t' -> (Or (e1,e2), t') | _ -> failwith "unclosed or" end
    | _ -> failwith "unexpected token" in
  match phelp tlist with
  | (e,[]) -> e
  | _ -> failwith "tokens beyond end of expression"

(* evaluate a boolExpr (bExp), assuming that only the variables in the (string) list tvars are true *)
let rec reval bExp tvars = match bExp with
  | Bool b -> b
  | And (e1,e2) -> (reval e1 tvars) && (reval e2 tvars)
  | Or (e1,e2) -> (reval e1 tvars) || (reval e2 tvars)
  | Not e -> not (reval e tvars)
  | Var s -> List.mem s tvars

(* try this in utop: reval (rparse (build_deep_not (1 lsl 18))) [] *)		      
let build_deep_not n =
  let rec build_close_str n acc = if n=0 then acc else build_close_str (n-1) (CL::acc) in
  let rec build_not_str n acc = if n=0 then acc else build_not_str (n-1) (OP::(NOT::acc))
  in build_not_str n ((LIT true)::build_close_str n [])

		   
(* Here's where you build the continuations & descriptive exceptions-based versions *)		   
exception Unclosed of int*int
exception Unused of int
exception SyntaxError of int


let rec keval bExp tvars k = match bExp with
  | Bool b -> k b
  | And(e1,e2) -> keval e1 tvars (fun eval2 -> keval e2 tvars (fun evaland -> k (evaland && eval2)))
  | Or (e1,e2) -> keval e1 tvars (fun eval2 -> keval e2 tvars (fun evaland -> k (evaland || eval2)))
  | Not e -> not (keval e tvars k)
  | Var s -> k (List.mem s tvars)

let eval bExp tvars = keval bExp tvars (fun x -> x)

let rec kparse tlist = 
  let rec k_helper tlist i k = match tlist with
  | (LIT b)::t -> k (Bool b) t (i+1)
  | (NM s)::t -> k (Var s) t (i+1)
  | OP::NOT::t -> k_helper t (i+2) (fun e t' x -> 
    begin match t' with CL::t'' -> k (Not e) t'' (i+1+x) | _ -> raise (Unclosed (i,i+1+x)) end)
  | OP::AND::t -> k_helper t (i+2) (fun e1 t1 x -> 
    k_helper t1 i (fun e2 t2 x' -> begin match t2 with CL::t' -> k (And (e1,e2)) t' (i+1+x+x') | _ -> 
      raise (Unclosed (i,i+1+x+x')) end))
  | OP::OR::t -> k_helper t (i+2) (fun e1 t1 x -> 
    k_helper t1 i (fun e2 t2 x' -> begin match t2 with CL::t' -> k (Or (e1,e2)) t' (i+1+x+x') | _ -> 
      raise (Unclosed (i,i+1+x+x')) end))
  | _ -> raise (SyntaxError i)
in k_helper tlist 0

let parse tlist = kparse tlist (fun e t i -> begin match t with [] -> e | _ -> raise (Unused i) end)

let query xstr slist = try (print_string ("Expression: "^xstr^" Result: "^(string_of_bool (eval (parse(lex xstr)) slist)))) with
  | Unclosed (i,i2) -> print_string ("Unclosed expression between "^(string_of_int i)^"and expected close at "^(string_of_int i2)^"!")
  | Unused i -> print_string ("Unused token in index "^(string_of_int i)^"!")
  | SyntaxError i -> print_string ("Unexpected token in index "^(string_of_int i)^"!")

