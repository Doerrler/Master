(* Pattern matching examples: try to predict what the result of each expression below will be.
   You can use utop to check. *)

(* tuples *)      
let (a,b) = (3,4) in a*b
(*This pattern matching will return an integer which is the result of a*b. And the value of it is 12*)
let c,d = 1,2
(*This pattern matching will return 2 integers. 1 is binded to c and 2 is binded to integer d.*)

(* list patterns *)	      
let (h::t) = [1;2;3]
(*This pattern matching will return an int and an int list. 1 is binded to the head of the list and 
int list [2;3] is binded to the tl. However, this pattern matching is not exhaustive when the (h::t) is
a list with only one element.*)
(*The way to fix it should be let (h::t) = *)

(*let (x::y::z) = [1] *)
let (x::y::z) = [2;3;4]
(*This pattern can't be correct because there are at least 2 values inside of the function but the list
binded to this list only has 1 element*)
(*The way to fix it should be let (x::y::z) = [2;3;4]*)
let (_::rest) = [1;2]		  
(*This pattern should just return a int list [2] since in this pattern, only the rest is called and it 
would be the return part of the patter. However, this function is not exhaustive as well*)
(*The way to fix it should be *)

(* "as" patterns *)		  
let ((a1,b1) as c1) = (2,3)
(*This patter should return 3 values. a1 is int 2, b1 is in 3, and a3 is tuple consist of a1 and a2 (2,3)*)
let ((a2,b2) as c2, d2) = ((2,3),4)
(*This patter should return 4 values. a2 is int 2, b2 is int 3, d2 is int 4, and c2 is tuple consist of a2 
and b2 (2,3)*)

(* OR patterns *)

(* This or pattern works... *)			
let rec make_pairs = function
  | ([] | _::[]) -> []
  | a::b::t -> (a,b) :: make_pairs t

(* but this one doesn't.  Why?  Fix it.*)				   
let rec singleton_or_empty_list = function
  | []  -> true
  | h::[] -> true
  | _ -> false
(*This function doesn't work because that if we have 2 things that are similar and we want them return the 
same result, this similar things must have the exact same set of identifiers. However, in this function, 
an empty list has 0 identifiers but the list that contains 1 element has one identifiers. 
Thus, we just need to seperate in to two cases and then we fixed it.*)


(* This pattern won't work, due to the *linearity* restriction.  It can be
fixed with "pattern guards" as in Hickey, though that's overkill here. *)   
let twins p = match p with
  | (s,t) -> if s = t then true else false
(*This function doen't work because that each identifier must appear exactly once in patter but s appears 
twice in first match situation. It's easy for us to fix it. We just need to use if ... else ... to decide 
which is true and which is false.*)
			
