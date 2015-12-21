let wordlist s =
  let splitlist = Str.full_split (Str.regexp "\\b\\|(\\|)") s in
  let rec filter_splist lst = match lst with
    | [] -> []
    | (Str.Delim "(")::t -> "(" :: (filter_splist t)
    | (Str.Delim ")")::t -> ")" :: (filter_splist t)
    | (Str.Delim _) :: t -> filter_splist t
    | (Str.Text s) :: t -> let s' = String.trim s in
			   let t' = (filter_splist t) in
			   if not (s' = "") then s' :: t' else t' 
  in filter_splist splitlist

type token = 
  OPEN | CLOSE | AND | OR | NOT | TRUE | FALSE | VARI of string

let legal_string str1 = match str1 with
  |"(" -> OPEN
  |")" -> CLOSE
  |"and" -> AND
  |"or" -> OR
  |"not" -> NOT
  |"T" -> TRUE
  |"F" -> FALSE
  |"y" -> VARI "y"
  |"x" -> VARI "x"
  |"z" -> VARI "z"
  |_ -> raise (invalid_arg "Unknown or Invalid Tokens in Input")

let rec tokens_p legal_string strlist = match strlist with
  |[]->[]
  |h::t -> (legal_string h)::(tokens_p legal_string t)

let string_to_char s =
  let rec stc i l =
    if i < 0 then l else stc (i - 1) (s.[i] :: l) in stc (String.length s - 1) []

let rec lowervars strx = let helper charlist = match charlist with
  |[]->true
  |h::t -> if ('a' <= h && h <= 'z') || ('0' <= h && h <= '9') then (lowervars strx) else false 
			 in (helper (string_to_char strx))

let rec tokens s = match s with
  |[] -> []
  |h::t -> if lowervars(h) then h::(tokens t) 
else tokens t

type boolExpr = 
ConstExpr of bool | VarExpr of string | AndExpr of boolExpr * boolExpr
| OrExpr of boolExpr * boolExpr | NotExpr of boolExpr


(* A token list representing a boolean expression is either
   + a CONST token :: <more tokens> 
   + an OPEN PAREN token :: a NOT token :: <a token stream representing a boolean expression> @ (a CLOSE PAREN token :: <more tokens>)
   + an OPEN PAREN token :: an AND token :: <a token list representing a boolean expression> @
                                            <a token list representing a boolen expression> @ a CLOSE PAREN token :: <more tokens>
   + an OPEN PAREN token :: an OR token :: <a token list representing a boolean expression> @
                                           <a token list representing a boolen expression> @ a CLOSE PAREN token :: <more tokens>
   any other list is syntactically incorrect. *)

let rec exp_helper lst = match lst with
  |TRUE::t -> (ConstExpr true, t)
  |FALSE::t -> (ConstExpr false, t)
  (*|VARI a::t -> (VarExpr a, t)*)
  |OPEN::AND::t -> (match (exp_helper t) with
    |(a,t) -> (match exp_helper(t) with 
      |b,(CLOSE::t) -> (AndExpr (a,b), t)))
  |OPEN::OR::t -> (match (exp_helper t) with
    |(a,t) -> (match exp_helper(t) with 
      |b,CLOSE::t -> (AndExpr (a,b), t)))
  |OPEN::NOT::t -> (match (exp_helper t) with
    |(a,t) -> (match exp_helper(t) with 
      |b,CLOSE::t -> (AndExpr (a,b), t)))
  |_ -> raise (invalid_arg "Syntax Error 5, Bad Expression")

let rec parse_bool_exp lst = match lst with
  |TRUE::t -> ConstExpr true
  |FALSE::t -> ConstExpr false
  (*|VARI a -> VarExpr a*)
  |OPEN::AND::t -> (match (exp_helper t) with
    |(a,t) -> AndExpr (a, (parse_bool_exp t))
    |_ -> raise (invalid_arg "Syntax Error With And, Bad Expression"))
  |OPEN::OR::t -> (match (exp_helper t) with
    |a,t -> OrExpr (a,(parse_bool_exp t))
    |_ -> raise (invalid_arg "Syntax Error With Or, Bad Expression"))
  |OPEN::NOT::t -> (match (exp_helper t) with
    |a,t -> NotExpr (a)
    |_ -> raise (invalid_arg "Syntax Error With Not, Bad Expression"))
  |_ -> raise (invalid_arg "Overal Syntax Error, Bad Expression")

(*
  Should evaluate an expression, given a function mapping variable names to boolean values.
*)

let rec eval_bool_exp blexp fu = match blexp with
  |AndExpr(x,y) -> (eval_bool_exp x fu) && (eval_bool_exp y fu)
  |OrExpr(x,y) -> (eval_bool_exp x fu) || (eval_bool_exp y fu)
  |NotExpr(x) -> not(eval_bool_exp x fu)
  |ConstExpr x -> x
  |VarExpr x -> fu x

(*		  			    
let () = if Array.length Sys.argv < 2 then () else
  let (_::sExpr::tlist)  = Array.to_list Sys.argv in
  let bExpr = sExpr |> wordlist |> tokens |> parse_bool_exp in
  let result = eval_bool_exp bExpr (fun v -> List.mem v tlist) in
  let () = print_string ((if result then "True" else "False")^"\n") in
  flush stdout
*)

(* TA COMMENT(zhan4136) Lexing Feedback: 12/12 *)

(* TA Comment(meye2058) Parsing/Evaluation Feedback: 0/18
Syntax/Type error *)
