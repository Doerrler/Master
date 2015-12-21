(* Type inference examples.  For each  *)

(* Intended type of pairwith:'a -> 'b list -> ('a* 'b ) list
   Actual type: 'a -> 'b list -> ('a* 'b ) list
   Explanation: The function takes in an unknown type x and then pairs it as a tuple 
   with each element in a list until it has been added to every element. It returns a 
   list of tuples of unkown types because no type specific operations were used on x
   or the list during the process. Because of this x has type 'a and lst has type 'b list, resulting
   in a list of tuples of the form ('a * 'b).
let rec pairwith x lst =
  match lst with
  | [] -> []
  | (h::t) -> (x,h) :: pairwith x t

(* Intended type of has_any: 'a -> 'a list -> bool
   Actual type: Type error due to expecting 'a but actually getting 'a list for the recursive call
   Explanation: From the name declaration line of the function, we know that the function takes 2 arguements 
   as inputs which we assume x's type is 'a and lst type is 'b list. Then we find that the function is trying
   to return a boolean. Thus the type intended is 'a -> 'b list -> bool. However, when we see the second match 
   situation, we find that when x does not equal h it raises a type error because the recursive call isn't 
   given the value to check for, x. Once the recursive call is given its proper arguments the function works 
   properly.
 *)
let rec has_any x lst =
  match lst with
  | [] -> false
  | (h::t) -> x=h || has_any x t

(* Intended type of lookup: 'a -> ('a * string) list-> string
   Actual type: 'a -> ('a * string) list-> string
   Explanation: From the name lane of the function, we know that lookup function takes two arguements as 
   inputs and we assume key's type is 'a and lst's type is 'b list. Then we see the first match situation, 
   we can see the result of the first match situation is a string which means that the result's type should 
   be a string. But the type of the list is still unkown. Then we move to the second match situation. We see 
   that the head of the lst is a tuple which consists 2 elements. Thus, we can write the 'b list to ('b * 'c) 
   list. Then we see the result of the match case. We see that k might equal to key, when key has the type 'a. 
   Then we know that k's type should be the same as key's type. And when k is equal to key, the result is v.
   However, from the first match situation, we know that the result should be a string, thus we know that v 
   is a string as well. Now the input list's type can be changed from ('b * 'c) list  to ('a * string) list.
 *)
let rec lookup key lst =
  match lst with
  | [] -> "No match"
  | (k,v)::t -> if k=key then v else lookup key t
			


let rec fool a = bar a
and  bar a = fool a 	

(* Intended type of first_of_first : 'a list list -> 'a
   Actual type: 'a list list -> 'a
   Explanation: from the name lane of the function we can see that we take an list as the arguement.
We assume it's 'a list. And then we see the second lane which is another function inside the function.
and we find that the arguement this function takes is also a list. Thus, we know that the argument of
the first_of_first function is at least a nested-list. Thus, we can change 'a list to 'a list list.
And in the first match situation of the fst function, we can see that the result of that function is the
element of the list which is in the nested list. And the last lane of the function which is the returning 
result is the result of fst function. Thus, we know that the result of this function is just 'a.
However, I think it's more important to know what this function does before we define the type of it. And 
I found that this whole function is trying to get the first element of the first list in the nested list.
And this helps us understand the type of the function better.
 *)	
			
let first_of_first lst =
  let rec fst l = match l with
    | h::t -> h
    | _->raise (Invalid_argument "No first element here.")
  in let f1 = fst lst in
  fst f1
