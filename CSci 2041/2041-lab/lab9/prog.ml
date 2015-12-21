type expr =
  Int of int | Bool of bool
  | Add of expr*expr
  | Seq of expr list
  | If of expr*expr*expr
  | Gt of expr*expr
  | Set of string*expr
  | Name of string
  | While of expr*expr

type result = IntR of int | BoolR of bool | UnitR

let rec assign n v st = match st with
  | [] -> [(n,v)]
  | (nm,_)::t when nm=n -> (n,v)::t
  | nv::t -> nv::(assign n v t)
	
exception NeedIntR of string
exception NeedBoolR of string
exception UnboundName of string
exception InvalidAddInSet of string
exception InvalidGtInSet of string
exception InvalidIfInSet of string
exception InvalidWhileInSet of string
			      
let rec eval expr st = match expr with
  | Int i -> (IntR i, st)
  | Bool b -> (BoolR b, st)
  | Add(e1,e2) -> evalAdd e1 e2 st
  | Seq el -> evalSeq el st
  | If(c,thn,els) -> evalIf c thn els st
  | Gt(e1,e2) -> evalGt e1 e2 st
  | Set(nm,vexp) -> let (v,s') = eval vexp st in (try (UnitR, assign nm v s') with
    | NeedIntR "In Add both v1 and v2 should be IntR something!" -> 
      raise (InvalidAddInSet "Need two valid Ints for Add in the Set expression!")
    | NeedIntR "In Gt both v1 and v2 should be IntR something!" ->
      raise (InvalidGtInSet "Need two valid Ints for Gt in the Set expression!")
    | NeedBoolR "In If the first argument should be a BoolR something!" -> 
      raise (InvalidIfInSet "Need the first argument of If to be Bool in the Set expression!")
    |NeedBoolR "In While the first argument should be a BoolR something!" -> 
      raise (InvalidIfInSet "Need the first argument of While to be Bool in the Set expression!"))
  | Name nm -> (try (List.assoc nm st, st) with 
    | _ -> raise (UnboundName ("The Name, "^nm^", is unbound!")))
  | While(c,b) -> evalWhile c b st
and evalAdd e1 e2 st =
  let (v1,st1) = eval e1 st in
  let (v2,st2) = eval e2 st1 in
  match (v1,v2) with
  | (IntR i1, IntR i2) -> (IntR (i1+i2), st2)
  | _ -> raise (NeedIntR "In Add both v1 and v2 should be IntR something!")
and evalSeq el st = match el with
  | [] -> (UnitR, st)
  | [e] -> eval e st
  | e1::el1 -> let (_,st1) = eval e1 st in evalSeq el1 st1
and evalIf c thn els st = match eval c st with
  | (BoolR true, st') -> eval thn st'
  | (BoolR false, st') -> eval els st'
  | _ -> raise (NeedBoolR "In If the first argument should be a BoolR something!")
and evalGt e1 e2 st =
  let (v1,st1) = eval e1 st in
  let (v2,st2) = eval e2 st1 in
  match (v1,v2) with
  | (IntR i1, IntR i2) -> (BoolR (i1 > i2), st2)
  | _ -> raise (NeedIntR "In Gt both v1 and v2 should be IntR something!")
and evalWhile c b st = match (eval c st) with
  | (BoolR false, st') -> (UnitR, st')
  | (BoolR true, st') -> let (_,st2) = eval b st' in evalWhile c b st2
  | _ -> raise (NeedBoolR "In While the first argument should be a BoolR something!")

let runProg ex = eval ex []

(*UnboundName, "x" unbound for Gt call*)
let p0 = Seq [ Set ("y", Seq []);
	       While (Gt (Name "x", Name "y"),
		      Seq [ Set("y", Add (Name "x", Int (-1)));
			    Set("x", Add (Name "y", Int 1))]) ;
	       Name "y"]

(*InvalidSet, because wrong elements in Add*)
let p1 = Set ("x", Add (Seq [], Bool true))

(*InvalidIf, not sure about this, probably issue with y If*)
let p2 = While (If (Int 1, Bool false, Bool true),
		Seq [ Set ("x", Gt (Name "x", Name "x"));
		      Set ("y", If(Name "x", Name "y", Bool false)) ])

(*InvalidGt, Int compared to bool*)						       
let p3 = Seq [ Set ("x", Int 0);
	       While (Gt (Name "x", Bool false), Set ("x", Add (Name "x", Int (-1)))) ;
	       If (Int 1, Name "y", Seq []) ]

(*UnboundName, think this is an infinite loop too*)
let p4 = While(Int 1,
	       If (Gt(Name "x", Name"y"),
		   Set ("x", Add(Name "x", Int(-1))),
		   Set ("y", Add(Name "y", Int(-1)))))
