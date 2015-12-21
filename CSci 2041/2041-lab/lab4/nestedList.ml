type 'a nestedlist = End | Cons of 'a myel * 'a nestedlist
and 'a myel = El of 'a | L of 'a nestedlist

let a5 = Cons(El "limbo?", End)
let a6 = Cons(El "my", Cons(El "talisman", End))
let a4 = Cons(El "is", Cons(L a6,  End))
let a3 = Cons(El "I", Cons(El "in", Cons(L a5, End)))
let a2 = Cons(El "where", Cons(L a4, End))
let a1 = Cons(L a2, Cons(El "am", Cons(L a3, End)))
let nl1 = Cons(L a1, End)

let a6 = Cons(El 3, End)
let a5 = Cons(El 4, End)
let a4 = Cons(L a5, Cons(El 3, End))
let a3 = Cons(El 2, Cons(L a6, End))
let a2 = Cons(L a4, Cons(El 2, End))
let a1 = Cons(L a2, Cons(El 1, Cons(L a3, End)))
let nl2 = Cons(L a1, End)

let a3 = Cons(El 3, Cons(El 3, End))
let a2 = Cons(El 2, Cons(L a3, Cons(El 2, End)))
let a1 = Cons(El 1, Cons(L a2, Cons(El 1, End)))
let nl3 = Cons(L a1, End)

(* INITIAL ATTEMPT Without Constuctor

type 'a nestedlist2 El of 'a | L of 'a nestedlist list

let nl1 = L [ L [ El "where"; L [ El "is"; L [ El "my"; El "talisman"]]]; El "am"; L [ El "I"; El "in"; L [ El "Limbo?"]]]

let nl2 = L [ L [ L [ L [ El 4 ]; El 3 ]; El 2 ]; El 1; L [ El 2; L [El 3] ] ]

let nl3 = L [ El 1; L [ El 2; L [El 3; El 3]; El 2]; El 1]
*)


let rec flatten nlst = match nlst with
  |End -> [] 
  |Cons(h,t) -> match h with
    |El h -> h::(flatten t)
    |L h -> (flatten h) @ (flatten t)


(*NOTE: We tried to fix nest_depth for a couple hours after lab and couldn't get it
so we moved on to other sections of the lab*)

let rec nest_depth nlst =  match nlst with
  |End -> 0
  |Cons(h,t) -> match h with
    |El h -> nest_depth t
    |L h -> 1 + nest_depth t

