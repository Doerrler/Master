##Lemma 1

###Property: 

for all a1 ∈ polyList, for all a2 ∈ polyList,
	
    List.length (list_add a1 a2) = max (List.length a1, List.length a2))

for all a ∈ polyList, P(a): for all a2 ∈ polyList, List.length (list_add a1 a2) = max (List.length a1, List.length a2)

###Base Case:

P([]): List.length (list_add [] a2) = max (List.length [], List.length a2))

List.length a2 = max (0, List.length a2))		//by evaluation of list_add and definition of an empty list

List.length a2 = List.length a2                 //by evaluation of max

###Inductive Case:

want to show that for all a ∈ polyList, P(a) -> P(x::a) 

**Inductive Hypothesis:** for all a ∈ polyList and for all a2 ∈ polyList, List.length (list_add a a2) = max (List.length a, List.length a2))

List.length (list_add x::a a2) = max (List.length x::a, List.length a2)

//let a2a be the first element of a2 and let b2 be a2 without a2a

List.length ((x+a2a) list_add a b2) = max (List.length x::a, List.length a2) //evaluation of list_add

1 + List.length (list_add a b2) = max ((1 + List.length a), List.length a2) //evaluation of length

**Case 1** if List.length b2 > a:

1 + List.length b2 = List.length a2         //evalluation of list_add and max

List.length a2 = List.length a2             //simplification

**Case 2** if List.length b2 =< a:

1 + List.length a = 1 + List.length a       //evalluation of list_add and max

###Conclusion:

Therefore, by the principle of structural induction on lists for all a1 ∈ polyList, for all a2 ∈ polyList,
	
	 List.length (list_add a1 a2) = max (List.length a1, List.length a2))

##Proof 1

###Property: 

for all a1 ∈ polyList, for all a2 ∈ polyList,
	
	list_deg (list_add a1 a2) = deg (Add (to_poly a1, to_poly a2))

for all a ∈ polyList, P(a): for all a2 ∈ polyList, list_deg (list_add a a2) = deg (Add (to_poly a, to_poly a2))

###Base Case:

P([]): list_deg (list_add [] a2) = deg (Add (to_poly [], to_poly a2))

list_deg (a2) = deg (Add (0, to_poly a2))	    	//by evaluation of list_add and to_poly

deg (to_poly a2) = deg (Add (0, to_poly a2))		//by proof provided from text

deg (to_poly a2) = deg (to_poly a2)                 //by evaluation of Add

###Inductive Case:

want to show that, for all a ∈ polyList and for all x ∈ polyExpr, P(a) -> P(x::a) 

**Inductive Hypothesis:** for all a ∈ polyList and for all a2 ∈ polyList, 

**IH (continued)** list_deg (list_add a a2) = deg (Add (to_poly a, to_poly a2))

P(x::a): list_deg (list_add x::a a2) = deg (Add (to_poly x::a, to_poly a2))

list_deg (list_add x::a a2) = max (deg (to_poly x::a), deg (to_poly a2)) //evaluation of deg

list_deg (list_add x::a a2) = max (deg (to_poly x::a), deg (to_poly a2)) //by lemma 1 and evaluation of list_deg

list_deg (list_add x::a a2) = max ((list_deg x::a), (list_deg a2))  //by proof provided in text

list_deg (list_add x::a a2) = max ((List.length x::a)-1, (list.length a2)-1) //by evaluation of list_deg

max (List.length x::a, List.length a2)) -1 = max ((List.length x::a)-1, (list.length a2)-1) // by evaluation of list_deg and lemma 1

max (List.length x::a, List.length a2)) -1 = max (List.length x::a, List.length a2)) -1 //simplification

###Conclusion:

Therefore, by the principle of structural induction on lists for all a1 ∈ polyList, for all a2 ∈ polyList,
	
	list_deg (list_add a1 a2) = deg (Add (to_poly a1, to_poly a2))
	
##Proof 2

###Property: 

for all p1 ∈ polyExpr, for all p2 ∈ polyExpr,
	
	deg (compose p1 p2) = (deg p1)*(deg p2)

for all p ∈ polyList, P(p): for all p2 ∈ polyList, deg (compose p p2) = (deg p)*(deg p2)

###Base Case:

**For P(Int a):**

P(Int a): deg (compose (Int a) p2) = (deg (Int a))*(deg p2)

deg (Int a) = (deg (Int a))*(deg p2)	    //by evaluation of compose

0 = (0)*(deg p2)	                    	//by evaluation of deg

0 = 0                                       //simplification

**For P(X):**

P(X): deg (compose X p2) = (deg X)*(deg p2)

deg p2 = 1*(deg p2)                         //by evaluation of compose and deg

deg p2 = deg p2                             //simplification

###Inductive Case:

**For P(Add (e,e2)):**

want to show that, for all e ∈ polyExpr and for all e2 ∈ polyExpr, P(e) -> P(Add (e,e2)) 

**Inductive Hypothesis:** for all p1 ∈ polyExpr, for all p2 ∈ polyExpr, deg (compose p1 p2) = (deg p1)*(deg p2)

P(Add (e,e2)): deg (compose (Add (e,e2) p2) = (deg (Add (e,e2))*(deg p2)

deg (Add ((compose e, p2), (compose e2 p2))) = (max (deg e) (deg e2))*(deg p2) //evaluation of compose and deg

max (deg (compose e, p2)) (deg (compose e2, p2)) = (max (deg e) (deg e2))*(deg p2) //evaluation of deg

max ((deg e)*(deg p2)) ((deg e2)*(deg p2)) = (max (deg e) (deg e2))*(deg p2) //by I.H.

max ((deg e)*(deg p2)) ((deg e2)*(deg p2)) = max ((deg e)*(deg p2)) ((deg e2)*(deg p2)) //simplification

**For P(Mul (e,e2)):**

want to show that, for all e ∈ polyExpr and for all e2 ∈ polyExpr, P(e) -> P(Mul (e,e2)) 

**Inductive Hypothesis:** for all p1 ∈ polyExpr, for all p2 ∈ polyExpr, deg (compose p1 p2) = (deg p1)*(deg p2)

P(Mul (e,e2)): deg (compose (Mul (e,e2) p2) = (deg (Mul (e,e2))*(deg p2)

deg (Mul ((compose e, p2), (compose e2 p2))) = ((deg e)+(deg e2))*(deg p2) //evaluation of compose and deg

deg (compose e, p2) + deg (compose e2 p2) = ((deg e)+(deg e2))*(deg p2) //evaluation deg

((deg e)+(deg p2) + (deg e2)+(deg p2)) = ((deg e)+(deg e2))*(deg p2) //by I.H.

((deg e)+(deg p2) + (deg e2)+(deg p2)) = ((deg e)+(deg p2) + (deg e2)+(deg p2)) //simplification

###Conclusion:

Therefore, by the principle of induction for all p1 ∈ polyExpr, for all p2 ∈ polyExpr,

	deg (compose p1 p2) = (deg p1)*(deg p2)

##Proof 3

###Property: 

for all p ∈ polyExpr,
	
	deg (simplify p) <= deg p

for all p ∈ polyList, P(p): deg (simplify p) <= deg p

###Base Case:

**For P(Int a):**

P(Int a): deg (simplify Int a) <= deg (Int a)

deg (Int a) = (deg (Int a)	                 //by evaluation of simplify

**For P(X):**

P(X): deg (simplify X) <= deg X

deg X = deg X                               //by evaluation of simplify

**For P(Add (e,e2)):**

want to show that, for all e ∈ polyExpr and for all e2 ∈ polyExpr, P(e) -> P(Add (e,e2))

**Inductive Hypothesis:** for all p ∈ polyExpr, P(p): deg (simplify p) <= deg p

P(Add (e,e2)): deg (simplify Add(e,e2)) <= deg (Add (e,e2))

deg (sim_add (simplify e, simplify e2)) <= max (deg e) (deg e2) //evaluation of simplify and deg

max (deg (simplify e)) (deg (simplify e2)) <= max (deg e) (deg e2) //evaluation of sim_add

max (deg e) (deg e2) <= max (deg e) (deg e2) //by I.H.

**For P(Mul (e,e2)):**

want to show that, for all e ∈ polyExpr and for all e2 ∈ polyExpr, P(e) -> P(Mul (e,e2)) 

**Inductive Hypothesis:** for all p ∈ polyExpr, P(p): deg (simplify p) <= deg p

P(Mul (e,e2)): deg (simplify Mul(e,e2)) <= deg (Mul (e,e2))

deg (sim_mul (simplify e, simplify e2)) <= (deg e) + (deg e2) //evaluation of simplify and deg

(deg (simplify e)) + (deg (simplify e2)) <= (deg e) + (deg e2) //evaluation of sim_mul

(deg e) + (deg e2) <= (deg e) + (deg e2) //by I.H.

###Conclusion:

Therefore, by the principle of induction for all p ∈ polyExpr,

	deg (simplify p) <= deg p
