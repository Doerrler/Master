(* data type to represent an infinite data object in a "lazy" fashion *)
type 'a stream = Cons of 'a * (unit -> 'a stream)

(* Some utility functions for streams *)				
let hd (Cons(h,t)) = h
let tl (Cons(h,t)) = t ()

let rec take_s n s = match (n,s) with
| (0,_) -> []
| (_,Cons(h,t)) -> h::(take_s (n-1) (t ()))

let rec map_s f (Cons(h,t)) = Cons(f h, fun () -> map_s f (t ()))			
			
let rec merge s1 s2 = Cons(hd s1, fun () -> Cons(hd s2, fun () -> merge (tl s1) (tl s2)))

let rec filter_s p (Cons(h,t)) =
  if (p h) then Cons(h, fun () -> filter_s p (t ()))
  else filter_s p (t ())
			  
let double s = merge s s 

(* Some streams we have seen in lecture *)		     
let rec nats n = Cons(n, fun () -> nats (n+1))
let fibs = let rec fib_help f0 f1 = Cons(f0, fun () -> fib_help f1 (f0+f1)) in fib_help 0 1
let factorials = let rec fact_help n a = Cons(n*a, fun () -> fact_help (n+1) (n*a)) in fact_help 1 1

(* one more helpful function *)
let rec gcd a b =
  if a=0 then b
  else if b < a then gcd b a
  else gcd (b mod a) a
	   
(* Your solutions for problem 3 go here: *)
let rec natpairs (x,y) = Cons ((x,y), (fun () -> match (x,y) with | (c,0) -> natpairs (0,c+1) | (a,b) -> natpairs (a+1,b-1)))

let py_triple (x,y) = (x,y, (int_of_float (sqrt (float_of_int (x*x + y*y)))))

let py_check (x,y,z) = if (((x>0)&&(y>0)) && (x<y) && (x*x + y*y == z*z) && ((gcd x y) == 1)) then true else false

let pytrips = filter_s (fun x -> py_check(x)) (map_s (fun x -> py_triple x) (natpairs(0,0)))

(* TA COMMENT(moham775) natpairs: 6/6 *)
(* TA COMMENT(moham775) py_triple: 3/3 *)
(* TA COMMENT(moham775) check_py: 3/3 *)
(* TA COMMENT(moham775) pytrips: 3/3 *)	

let str_rev xstr = let rec rev_helper n = if (n >= String.length xstr) then "" else (rev_helper (n+1))^(String.make 1 xstr.[n]) 
		   in rev_helper 0

let pal_check xstr = if ((String.length xstr mod 2) = 0) then ((String.lowercase(String.sub xstr 0 ((String.length xstr)/2))) = 
  (str_rev (String.lowercase(String.sub xstr ((String.length xstr)/2) ((String.length xstr)/2))))) else
    ((String.lowercase(String.sub xstr 0 ((String.length xstr)/2))) = 
  (str_rev (String.lowercase(String.sub xstr (((String.length xstr)/2)+1) ((String.length xstr)/2))))) 

let kleene_star ls =
  let rec helper l1 l2 l3 l4 l5 = match (l1,l2) with
    | ([],h::t) -> helper l4 t l3 l4 l5
    | (_, []) -> helper l5 l3 l3 l5 []
    | (h1::t1, h2::t2) -> Cons((h1^h2), (fun () -> helper t1 l2 l3 l4 (l5@[h1^h2])))
  in Cons("",(fun () -> (helper ls [""] ls ls [])))

let palindromes ls = filter_s (fun x -> pal_check x) (kleene_star ls)


(* TA COMMENT(zhan4136) pal_check: 4/4 *)

(* TA COMMENT(zhan4136) kleene_star: 8/8 *)

(* TA COMMENT(zhan4136) palindromes: 3/3 *)
