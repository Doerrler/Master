type expr =
  Const of int | Boolean of bool
  | Add of expr * expr
  | Sub of expr * expr
  | Mul of expr * expr
  | Div of expr * expr
  | If of expr * expr * expr
  | Let of string * expr * expr
  | Name of string
  | And of expr * expr
  | Or of expr * expr
  | Not of expr
  | Lt of expr * expr
  | Eq of expr * expr
  | Gt of expr * expr
  | Print of expr

type expType = Int | Bool | Unit 

(* Type to represent a lexical environment of the program, e.g. the current stack of variables and the values they are bound to *)
type envType = (string * result) list
 (* Type to represent a value in the program *)
 and result = IntResult of int | BoolResult of bool | UnitResult 

(* evaluate an expression in a lexical environment *)
let rec eval exp env = match exp with
  | Const n -> IntResult n
  | Boolean b -> BoolResult b
  | Add (e1,e2) -> evalInt (+) e1 e2 env
  | Mul (e1,e2) -> evalInt ( * ) e1 e2 env
  | Sub (e1,e2) -> evalInt (-) e1 e2 env
  | Div (e1,e2) -> evalInt (/) e1 e2 env
  | If (cond,thn,els) -> evalIf cond thn els env
  | Let (nm,vl,exp') -> evalLet nm vl exp' env
  | Name nm -> List.assoc nm env
  | And (e1,e2) -> evalBool (&&) e1 e2 env
  | Or (e1,e2) -> evalBool (||) e1 e2 env
  | Not e -> let (BoolResult b) = eval e env in BoolResult (not b)
  | Lt (e1, e2) -> evalComp (<) e1 e2 env
  | Eq (e1, e2) -> evalComp (=) e1 e2 env
  | Gt (e1, e2) -> evalComp (>) e1 e2 env
  | Print e -> let () = match eval e env with
		 | UnitResult -> print_string "()"
		 | IntResult i -> print_int i
		 | BoolResult b -> if b then print_string "True" else print_string "False" in
	       let () = print_string "\n" in
	       let () = flush stdout in UnitResult
and evalInt f e1 e2 env =
  let (IntResult i1) = eval e1 env in
  let (IntResult i2) = eval e2 env in
  IntResult (f i1 i2)
and evalIf cond thn els env =
  let (BoolResult b) = eval cond env in
  if b then eval thn env else eval els env
and evalLet name vl exp env =
  let r = eval vl env in
  eval exp ((name,r)::env) 
and evalBool f e1 e2 env =
  let (BoolResult b1) = eval e1 env in
  let (BoolResult b2) = eval e2 env in
  BoolResult (f b1 b2)
and evalComp cmp e1 e2 env =
  let (IntResult i1) = eval e1 env in
  let (IntResult i2) = eval e2 env in
  BoolResult (cmp i1 i2)
	     
(* Type checking/inference: Figure out type for an expression.  Fail if the expression is not well-typed.*)    
let rec typeof exp env = match exp with
  | Const _ -> Int
  | Boolean _ -> Bool
  | Add (e1,e2) | Sub (e1,e2) | Mul (e1,e2)
  | Div (e1,e2) ->
     ( match (typeof e1 env, typeof e2 env) with
       | (Int,Int) -> Int
       | _ -> failwith "Arithmetic on non-integer argument(s)")
  | And (e1,e2)
  | Or (e1,e2) ->
     ( match (typeof e1 env, typeof e2 env) with
       | (Bool,Bool) -> Bool
       | _ -> failwith "Boolean operation on non-Bool argument(s)")
  | Not e -> if (typeof e env) = Bool then Bool else failwith "Not of non-Boolean"
  | Lt (e1,e2)
  | Gt (e1,e2) ->
     ( match (typeof e1 env, typeof e2 env) with
       | (Int,Int) -> Bool
       | _ -> failwith "Comparison of non-integer values" )
  | Eq (e1,e2) ->
     ( match (typeof e1 env, typeof e2 env) with
       | (Int,Int) | (Bool,Bool) | (Unit,Unit) -> Bool
       | _ -> failwith "Equality test on incompatible values" )
  | If (cond,thn,els) ->
     if not ((typeof cond env) = Bool) then failwith "If on non-boolean condition" else
       let (t1,t2) = (typeof thn env, typeof els env) in
       if (t1 = t2) then t1 else failwith "Different types for then/else branches"
  | Name name -> (try List.assoc name env with Not_found -> failwith ("Unbound variable "^name))
  | Let (name,vl,e) ->
     let t = typeof vl env in
     typeof e ((name,t)::env)
  | Print e -> let _ = typeof e env in Unit

let e1 = Let("x",Const 3,
	     Let("y", Const 7,
		 If(Gt(Name "y", Name "x"),Print (Boolean true),Print (Boolean false))))

(* Add two well-typed programs below *)
let ex1 = Let("x", Const 3, Let("y", Div(Const 12, Const 4),  Print (Add (Name "x",Name "y"))))

let ex2 = Let("a",Boolean true, 
	      Let("y", Boolean false,
		  If(And(Name "x", Name "y"),Print (Boolean true),Print (Boolean false))))

let badtype1 = Let("x", Mul(Const 7, Boolean true),
		   If (Const 1, Const 3, Print(Boolean false)))

(* Add two programs that will fail to type-check below *)
let failex1 = Let("z", Boolean false, 
		  If((Gt(Name "z", Const 7)), Print (Boolean true), Print (Boolean false)))

let failex2 = Let("x", Div(Boolean true, Const 4), Print (Boolean true))

(* here's where you define find_constants *)
let find_constants ex = 
  let rec helper ex1 acc = 
    match ex1 with
  |Const x -> (Const x)::acc
  |Boolean x-> (Boolean x)::acc
  | Add (e1,e2) | Sub (e1,e2) | Mul (e1,e2)
  | Div (e1,e2) 
  | And (e1,e2)
  | Or (e1,e2) 
  | Lt (e1,e2)
  | Gt (e1,e2)
  | Eq (e1,e2) ->
    (helper e1 acc)@(helper e2 acc)
  | If (cond,thn,els) ->(helper cond acc)@(helper thn acc)@(helper els acc)
  | Not e -> (helper e acc)
  | Let (name,vl,e) -> (helper vl acc)@(helper e acc)
  | Print e-> (helper e acc)
  | _->acc
			in helper ex []
(* here's where you define rm_vars *)

let rm_vars exp = 
  let rec rm exp2 state=
    match exp2 with
    | Add (e1,e2) -> let (a,b) = (rm e1 state,rm e2 state) in Add (a , b )
    | Sub (e1,e2) -> let (a,b) = (rm e1 state,rm e2 state) in Sub (a , b )
    | Mul (e1,e2) -> let (a,b) = (rm e1 state,rm e2 state) in Mul (a , b )
    | Div (e1,e2) -> let (a,b) = (rm e1 state,rm e2 state) in Div (a , b )
    | And (e1,e2) -> let (a,b) = (rm e1 state,rm e2 state) in And (a , b )
    | Or (e1,e2)  -> let (a,b) = (rm e1 state,rm e2 state) in Or (a , b )
    | Lt (e1,e2) -> let (a,b) = (rm e1 state,rm e2 state) in Lt (a , b )
    | Gt (e1,e2) -> let (a,b) = (rm e1 state,rm e2 state) in Gt (a , b )
    | Eq (e1,e2) -> let (a,b) = (rm e1 state,rm e2 state) in Eq (a , b )
    | If (cond,thn,els) ->  let (a,b,c) = (rm cond state,rm thn state,rm els state) in If (a,b,c)
    | Not e -> let a = rm e state in (Not a)
    | Let (name,vl,e) -> Let (name,vl,rm e (let anew = (typeof vl state) in ((name,anew)::state)))
    |Name x-> (match (typeof (Name x) state) with 
      | Int-> Const 0
      | Bool -> Boolean false
      | _->failwith "Invalid")
    | Print e-> Print e
    | _-> failwith "Invaid"
  in rm exp []

