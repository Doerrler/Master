(* lab 9 examples to convert to CPS *)
let rec map f lst = match lst with
  | [] -> []
  | h::t -> (f h)::(map f t)

let rec append l1 l2 = match l1 with
  | [] -> l2
  | h::t -> h::(append t l2)

let rec assoc_update k v lst = match lst with
  | [] -> [(k,v)]
  | (k',_)::t when k'=k -> (k,v)::t
  | kv::t -> kv::(assoc_update k v t)

type 'a btree = Node of 'a * 'a btree * 'a btree | Empty

let rec treeMin t =
  match t with
  | Empty -> None
  | Node(v,l,r) ->
     match (treeMin l, treeMin r) with
     | (None,None) -> Some v
     | (None, Some v') | (Some v', None) ->  Some (min v v')
     | (Some vl, Some vr) -> Some (min (min v vl) vr)

(* CPS versions go here: *)
let map_k f lst =
  let rec kmap lst k = match lst with
    | [] -> k []
    | h::t -> kmap t (fun result -> k ((f h)::result)) in
  kmap lst (fun x -> x)

let append_k l1 l2 =
  let rec kappend lst1 k = match lst1 with
    | [] -> k l2
    | h::tl -> kappend tl (fun result -> k (h::result)) in
  kappend l1 (fun x -> x)

let assoc_update_k k v lst = 
  let rec k_assoc f l = match l with
    |[] -> f [(k,v)]
    |(k', _)::t when k' = k ->(fun result -> f ((k,v)::result)) t
    |kv::t -> k_assoc (fun result -> f (kv::result)) t
  in k_assoc (fun x -> x) lst

type 'a option = None | Some of 'a

let treeMin_k t = 
  let rec k_treeMin tree k =
    match tree with
    |Empty -> k None
    |Node(v,lt,rt)-> k_treeMin rt (fun ll -> k_treeMin lt (fun rr -> k (min (min ll rr)  v)))
  in k_treeMin t (fun x -> x)

