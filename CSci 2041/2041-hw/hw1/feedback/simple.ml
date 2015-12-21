(*Combines a and b while separating them with a comma*)
let csvify (a,b) = a^", "^b
(*Essentially just removes rounding with overflows*)
let (++) a b = if a+b> 32767 then 32767 else if a+b< -32768 then -32768 else a+b
(*Adds two vectors*)
let vec_add (a,b) (c,d) = (a+.c,b+.d)
(*Finds the dot product of two vectors*)
let dot (a,b) (c,d) = (a*.c)+.(b*.d)
(*Determines if two vectors are orthogonal*)
let perp (a,b) (c,d) = if (a*.c)+.(b*.d) = 0. then true else false
(*I am not sure how we are supposed to comment these since they are pretty straight forward*)


(* TA COMMENT(halto004):
  Looks good.
  20/20

*)
