(* Bloom Filter implementation.  This file will not compile as is. *)
module type memset = sig
  type elt (* type of values stored in the set *)
  type t (* abstract type used to represent a set *)
  val mem : elt -> t -> bool 
  val empty : t
  val is_empty : t -> bool
  val add : elt -> t -> t
  val from_list : elt list -> t
  val union : t -> t -> t
  val inter : t -> t -> t
end

(* Define the hashparam signature here *)		       
module type hashparam = sig
  type t (* abstract type used to represent a set *)
  val hashes : t -> int list
end

(* Define SparseSet module here, using the Set.Make functor *)		
module SetHelper = struct
  type t = int 
  let compare = Pervasives.compare
end

module SparseSet:memset with type elt = int = struct
  include Set.Make(SetHelper)
  let from_list lst = (List.fold_left (fun a b -> add b a) empty lst)
end

(* Fill in the implementation of the memset signature here.  You'll need to expose the elt type *)
module BitSet:memset with type elt = int = struct
  type elt = int
  type t = string
    (* Some helper functions... bitwise &, bitwise | of two char values: *)
  let (&*) c1 c2 = String.make 1 (Char.chr ((Char.code c1) land (Char.code c2)))
  let (|*) c1 c2 = String.make 1 (Char.chr ((Char.code c1) lor (Char.code c2)))
    (* bitwise and of two strings: *)
  let rec (&@) s1 s2 = match (s1,s2) with
    | ("", s) | (s, "") -> ""
    | _ -> (s1.[0] &* s2.[0]) ^ ((Str.string_after s1 1) &@ (Str.string_after s2 1))
    (* bitwise or of two strings: *)
  let rec (|@) s1 s2 = match (s1,s2) with
    | ("",s) | (s, "") -> s
    | _ -> (s1.[0] |* s2.[0]) ^ ((Str.string_after s1 1) |@ (Str.string_after s2 1))
    (* single-character string with bit i set: *)
  let strbit i = String.make 1 (Char.chr (1 lsl (i land 7)))
    (* make a string set *)
  let rec make_str_t x s = let rec ms_help len acc = match len with
    | x when x <=0 -> acc
    | x -> ms_help (x-1) (acc^(String.make 1 (Char.chr 0)))
			   in let result = (ms_help (x/8) "")^(strbit (x mod 8))
			      in result |@ s
  let empty = ""
  let is_empty s = match s with 
    | "" -> true 
    | _ -> false
  let mem x s = if x/8 > (String.length s -1) then false else
      let mem_help = (strbit x).[0] 
      in (s.[x/8] &* mem_help) = (mem_help &* mem_help)
  let add x s = (make_str_t x s)
  let union s1 s2 = s1 |@ s2
  let inter s1 s2 = s1 &@ s2
  let from_list lst = List.fold_left (fun a b -> add b a) empty lst
end

(* Fill in the implementation of a BloomFilter, matching the memset signature, here. *)
(* You will need to add some sharing constraints to the signature below. *)
module BloomFilter(S : memset with type elt = int)(H : hashparam):memset with type elt = H.t = struct
    type elt = H.t
    type t = S.t
    (* Implement the memset signature: *)
    let empty = S.empty
    let is_empty s = match s with 
      | empty -> true 
      | _ -> false
    let mem e s = let rec mem_helper e s = match e with
      | [] -> true
      | h::t -> if (S.mem h s) then (mem_helper t s) else false
		      in mem_helper (H.hashes e) s
    let add e s = let rec add_helper e s = match e with
      | [] -> s
      | h::t -> if (S.mem h s) then (add_helper t s) else (add_helper t (S.add h s)) 
		      in add_helper (H.hashes e) s
    let union s1 s2 = S.union s1 s2
    let inter s1 s2 = S.inter s1 s2
    let from_list elist =  List.fold_left (fun x y -> add y x) empty elist
			 
end
								
(* A hashparam module for strings... *)
module StringHash = struct
    type t = string (* I hash values of type string *)
    let hlen = 15
    let mask = (1 lsl hlen) - 1
    let hashes s =
      let rec hlist n h = if n = 0 then [] else (h land mask)::(hlist (n-1) (h lsr hlen)) in
      hlist 4 (Hashtbl.hash s) 
end

(* Add the IntHash module here *)
module IntHash = struct
  type t = int
  let h1 n = (795*n + 962) mod 1031
  let h2 n = (386*n + 517) mod 1031
  let h3 n = (937*n + 693) mod 1031
  let hashes n = [(h1 n); (h2 n); (h3 n)]
end
