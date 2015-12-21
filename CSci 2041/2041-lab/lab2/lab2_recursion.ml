(*1 unzip*)
let rec unzip lst =
  match lst with 
  |[]->([],[])
  |(x,y)::tl-> let left, right = unzip tl in (x::left,y::right)
(*The type of this function is ('a * 'b) list -> 'a list * 'b list
Fron the match, we see that the thing we take as input should be a tuple list which we defined as ('a*'b) list
From the second match situation, we know that we finally return 2 lists that seperate 'a and 'b which then form
two lists: 'a list and 'b list. Thus, the type should be ('a * 'b) list -> 'a list * 'b list.
And Ocaml gives us the type: val unzip : ('a * 'b) list -> 'a list * 'b list = <fun>*)

(*2* list_cat*)
let rec list_cat lst =
  match lst with
  |[]->""
  |hd::tl->hd ^ (list_cat tl)
(*The type of this function is string list -< string
And the function takes 1 string list as arguement and then returns one string that contains all the strings inside the 
list.*)

(*3 list_deriv*)
let rec list_deriv lst = 
  match lst with
  |[]->[]
  |[a]->[]
  |x::h::tl->(h-x)::(list_deriv (h::tl))
(*The type of this function is int list-> int list
And this function takes the "derivative" of a list of integers by taking n integers and returning the list of n-1 
differences between the consecutive integers*)
