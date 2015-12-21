(* Your inferred type:'a -> ('a -> 'b) -> 'b
   Explanation of type: Firstly, we can see that the function takes 2 arguments, we assume they 
are 'a and 'b. And in later function, we see that (f x) which means that if x has type 'a, then
the function will take x as input and output another type output, which means that f is a function
that takes 'a as input and return a 'b output. Thus the type should be 'a -> ('a -> 'b) -> 'b
   Annotate the definition below: This function will do the function which is f on the elment x 
and return the result after the operation on x.*)
let (|>) = fun x f -> (f x)


(* Your inferred type: ('a -> bool) -> 'a list -> 'a list * 'a list
   Explanation of type:In second match situation, we can see that p takes an arguement and then
return an output if that is true. Thus, we know that the type of p should be 'a -> bool. After
that, when we look at the first match case, lst is a list, and since p takes head of the list
as inputs, we know that lst has type 'a list. And at last, when we see the output of the function,
we see that the result that returned should be a tuple list, when the first half is 'a list and 
second half is 'a list as well.
   Annotate the definition below: This function will take an list and return 2 lists in a tuple
that the first half is the list that all the elments inside it are those who satisfy the 
condition(function) p and the second half should just have elements that don't satisfy the 
condtion.*)
let rec partition p lst =
  match lst with
  | [] -> ([],[])
  | h::t -> let (l1,l2) = (partition p t) in
	    if (p h) then (h::l1, l2) else (l1,h::l2)

					     
(* Your inferred type:This function has error since exists take 2 arguments but in second match
case it only takes one argument. And I fixed it by adding the argument p.
   Then the type should be ('a -> bool) ->'a list -> bool
   Explanation of type: When we look at the second match situation, we can see that p takes an
argument and then return a result which should have same type that false has. Then, we know that
the type of p should be 'a -> bool. And since the second argument of the function is matched to 
list and the function p takes each head of the list to do the operation, we know that the second 
argument of the function should be 'a list. And as we see the result that returned by this 
function, we can see that it returns a boolean. Then the type should be 
('a -> bool) -> 'a list -> bool
   Annotate the definition below: This function will take a list and check whether this list 
has elements that satisfy the condition(function) p.*)
let rec exists = fun p ->
  function
  | [] -> false
  | (h::t) -> (p h) || (exists p t)
