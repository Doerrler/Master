(* OCaml file for lab 1.
   Fix the errors in this file.  *)

<<<<<<< HEAD
let zero = (-2 + 2)
=======
<<<<<<< HEAD
let zero = (-2 + 2)

let myfun x = x
let mybegin s = s.[zero]
let len myval = String.length myval
  
let mult x y = x * y

let or3 a b c = a || b || c

let helloworld = "hello" ^ "world"

let ending s t = let last = len s - 1 in (String.sub s (last - t) t)  

(*let c = beginning ""*)
	       
let () = print_string (ending "Looks like we made it!\n" 9)
  
(*Writing new OCaml Functions*)
let scale a (c,d) = (a*.c,a*.d)

let length (a,b) = sqrt (a*.a+.b*.b)

let unit_vector (a,b) = if sqrt (a*.a +. b*.b)=1.0 then true else false
=======
let zero = (-2 + )
>>>>>>> ef683e1100b50650eee68b743587488d7ed9b4db

let myfun x = x

let mybegin s = s.[zero]
let len myval = String.length myval
  
let mult x y = x * y

let or3 a b c = a || b || c

let helloworld = "hello" ^ "world"

let ending s t = let last = len s - 1 in String.sub s (last - t) t  

(*let c = beginning ""*)
	       
let () = print_string (ending "Looks like we made it!\n" 9)
  
<<<<<<< HEAD
let scale x (y,z) = (x*.y,x*.z)

let length (x,y) = sqrt (x*.x+.y*.y)

let unit_vector x y = if sqrt (x*.x+.y*.y) = 1. then true else false
=======
>>>>>>> d1273eed078b4e0045be4b5f90dad2b6a66d5622
>>>>>>> ef683e1100b50650eee68b743587488d7ed9b4db
