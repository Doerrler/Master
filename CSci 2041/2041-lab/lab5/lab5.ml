let rec map f lst =
  match lst with
  | [] -> []
  | h::t -> (f h)::(map f t)

(* fold_left in Ocaml *)
let rec fold f acc lst =
  match lst with
  | [] -> acc
  | h::t -> fold f (f acc h) t

(* fold_right in Ocaml *)
let rec reduce f lst init =
  match lst with
  | [] -> init
  | h::t -> f h (reduce f t init)

(*Append*)
let append l1 l2 = reduce (fun x y -> x::y) l1 l2

(*filter*)
let filter pred lst = reduce (fun x acc -> if pred x then x::acc else acc) lst [] 

(*fold*)
let list_cat lst = fold (fun x y -> x^y) "" lst

(*map*)
let list_fst lst = map (fun (a,b)->a) lst

let mem x lst = reduce (fun a b-> if a != b then true else false) (map (fun z -> if z=x then true else false) lst) false

let count_intersection lst1 lst2 = fold (fun x y->if (mem y lst1) then x+1 else x) 0 lst2

let check_set lst = match lst with
  |[]->true
  |h::tl->fold (fun x y -> if (mem y tl) then false else x) true tl

type 'a option = None | Some of 'a 

let help (k,v) (k1,v1) = if v1>v then k else k1

let assoc_max lst = 
  match lst with
  |[]->raise (Invalid_argument "No value to match!")
  |[(k,v)]->Some k
  |(k,v)::h1::tl-> Some (fold (fun acc (k,v) -> (help (k,v) h1)) k tl)

(* TA COMMENT (leid0065): 1/1 *)
