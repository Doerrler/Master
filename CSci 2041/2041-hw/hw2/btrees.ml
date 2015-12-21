(* Types for binary search trees. *)
type 'a btree = Node of 'a * 'a btree * 'a btree | Empty

(* A comparison function cmp should have the following behavior:
   - cmp x y < 0 if x is less than y
   - cmp x y = 0 if x is equal to y
   - cmp x y > 0 if x is greater than y *)
type 'a compare = 'a -> 'a -> int

(* A binary search tree is a binary tree where every element in the 
   left subtree of Node(x,left,right) is less than x, 
   and every element in the right subtree is greater than x. *)
type 'a bstree = { tree : 'a btree ; cmp : 'a compare }

(* just a helper function for building trees *)		   
let leaf x = Node(x,Empty,Empty)
		 
let search { tree ; cmp } v =
  let rec tsearch t = 
    match t with
    | Empty -> false
    | Node (v',lt,rt) ->
       match (cmp v v') with
       | 0 -> true
       | s when s < 0 -> tsearch lt
       | _ -> tsearch rt
  in tsearch tree

let insert { tree ; cmp } v =
  let rec tinsert t =
    match t with
    | Empty -> (leaf v)
    | Node (v',lt,rt) ->
       match (cmp v v') with
       | 0 -> t
       | s when s < 0 -> Node(v', (tinsert lt), rt)
       | _ -> Node(v', lt, (tinsert rt)) in
  { tree = (tinsert tree) ; cmp }


let rec treeMin tree cmp = 
  let rec tMin min tree = match tree with
    | Empty -> Some min
    | Node (v, Empty, rightt) -> if min > v then tMin v rightt else tMin min rightt
    | Node (v, leftt, Empty) -> if min > v then tMin v leftt else tMin min leftt
    | Node (v, Empty, Empty) -> Some v
    | Node (v, leftt, rightt) -> match (cmp leftt rightt) with
      | 0 -> if (tMin min leftt) <= (tMin min rightt) then (tMin v leftt) 
	else (tMin v rightt)
      | s when s < 0 -> tMin v leftt
      | _ -> tMin v rightt
  in match tree with
  | Empty -> None
  | Node(v, leftt, rightt) -> (tMin v tree)

let treeMax tree cmp = treeMin tree (fun x y -> (-cmp x y))

