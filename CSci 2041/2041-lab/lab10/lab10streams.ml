type 'a stream = Cons of 'a * (unit -> 'a stream)
type 'a lazylist = End | Lz of 'a * 'a lazylist lazy_t
				
let rec take_s n s = match (n,s) with
  | (0,_) -> []
  | (_,Cons(h,t)) -> h::(take_s (n-1) (t ()))

let rec lztake n ll = match(n,ll) with
  | (0,_) | (_,End) -> []
  | (_,Lz(h,t)) -> h::(lztake (n-1) (Lazy.force t))
	  
(* your definition of ustrings goes here: *)
let ustring_s s = 
  let rec help st acc =
    Cons (acc,fun()->help st (st^acc))
  in help s ""

(* Add definitions for drop_while_s and take_until_s here: *)
let take_until_s str f = 
  let rec help st acc = 
    match st with
    |Cons (h,tl)-> if (f h) = true then acc else help (tl()) (acc@[h])  
  in help str []

(* now add lz_ustring and lztake_until here: *)
let lz_ustring st = 
  let rec help st acc =
    Lz (acc,lazy(help st (st^acc)))
  in help st ""

let lztake_until lzlst f =
  let rec help lz acc =
    match lz with
    |Lz (h,tl) -> if (f h) = true then acc else (help  (Lazy.force tl) (acc@[h]))
    |_->failwith "Can't do it !"
  in help lzlst []
