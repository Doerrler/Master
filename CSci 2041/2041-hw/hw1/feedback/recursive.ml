(* TA COMMENT(zhan4136): 4/20 *)

(* TA COMMENT(zhan4136): 
File Placement - 4/4 

Doesn't compile, no further points awarded.
*)


(*First tests for b being greater than a and if its not it adds the a to the list and runs with a+1*)
let rec range a b = if a-b <= 0 then res else (a::res) range a+1 b
(*Same idea as range but it increments by step instead of 1 and then accounts for the weird occurances
such as negative steps.*)
let range_step a b step = let rec subfun c cumlativelist =
if (c < a && step > 0) || (c < b && step < 0) || (step < 0 && b = c+step) || step = 0 then cumlativelist 
else if step > 0 then  subfun (c-step) (c::cumlativelist) else subfun (c+step) (c::cumlativelist) 
in if  step > 0 then (subfun (b-1) []) else (subfun (a) [])
(*Continues recursively while it checkes if the list is either empty or that n has reached 0.*)
let rec take n lst = match lst with
|[] -> []
|h::t -> if n < 0 then raise (invalid_arg "take: negative argument") else if n=0 then [] else h::take (n-1) t
