#List Induction
##Property:

	for all l1 ∈ int list, for all l2 ∈ int list, length (list_add l1 l2) = max (length l1) (length l2)
##Base Case:

###	When length l1 > length l2:

	length(list_add l1 []) 	= max (length l1) (length [])

			       	= max (length l1) 0           	//evaluation of length

			       	= (legnth l1)			//evaluation of max

###	When length l1 <= length l2:

	legnth(list_add [] l2) 	= max (length []) (length l2)

			       	= max 0 (length l2)           	//evaluation of length

			       	= (legnth l2)			//evaluation of max

	Since both cases in the base case are true then the base case is true

##Inductive Case:

	Inductive Hypothesis: length (list_add x::l1 l2) = max (length x::l1) (length l2)

###	When length l1 > length l2:

	length(list_add X::l1 l2) = max (length x::l1) (length l2) 	//By I.H.

				  = max ((length l1)+1) (length l2) 	//by list property of appending

				  = (length l1) +1			//evaluation of max
###	When length l1 <= length l2:

	length(list_add l1 x::l2) = max (length l1) (length x::l2) 	//By I.H.

				  = max (length l1) ((length l2)+1)	//by list property of appending

				  = (length l2) +1			//evaluation of max

	therefore by the principle of induction for list,

	for all l1 ∈ int list, for all l2 ∈ int list, length (list_add l1 l2) = max (length l1) (length l2)


#Polynomials
##Property: 

for all p1 that are polyExpr, deg p1 = deg (simplify p1)

##Base Case: 
###	    1. P(Int a)

		deg (Int a) = 0				//by evaluation of deg function

		deg (simplify (Int a))

	      = deg (Int a) 				//by evaluation of simplify function

	      = 0					//by evaluation of deg function

		Thus, the first case of base case is true.

###	    2. P (X)

		deg (X) = 1				//by evaluation of deg function

		deg (simplify (X))

	      = deg (X)					//by evaluation of simplify function

	      = 1					//by evaluation of deg function

		Thus the second case of base case is true.

	Since both case in base case is true, then the base case is true.
##Inductive Case: 
###	    1. Add Situation:
		P(e) -> P(Add (e,e2))

		Inductive Hypothesis: 

			for all p that are polyExpr, deg p1 = deg (simplify p1)

		Need to prove:

			for all p that are polyExpr, deg Add(p1,e2) = deg (simplify (Add (p1,e2)))

		deg Add(p1,e2)

	      = max (deg p1) (deg e2)			//by evaluation of deg function

	      = 0					//if deg p1 = Int i1 and deg e2 = Int i2

		deg (simplify Add (p1,e2))

###	      Subcase A:

		= deg (Int (i1+i2)) 			//if simplify p1 = Int i1 and simplify e2 = Int i2

		= 0

###	      Subcase B:

		= deg (Add (simplify p1,simplify e2))			//by evaluation of simplify function

		= deg (Add (deg p1, deg e2))				//by Inductive Hypothesis

		= max (deg e1) (deg e2)					//by evaluation of deg function


	Since 0=0 if simplify p1 = Int i1 and simplify e2 = Int i2 and max (deg e1) (deg e2) if simplify p1 != Int i1, Then the 
inductive step of first add situation is proved.
###		2. Mul Situation:

		P(e) -> P(Mul (e,e2))

		Inductive Hypothesis:

			for all e that are polyExpr, deg e = deg (simplify e)

		Need to prove:

			for all e that are polyExpr, deg Mul(e,e2) = deg (simplify (Mul (e,e2)))

		deg Mul(e,e2)

	      = (deg e1) + (deg e2)					//by evaluation of deg function

	      = 0 + 0 = 0						//if deg e1 = Int i1 and deg e2 = Int i2


		deg (simplify (Mul (e,e2)))

###	     Subcase A:

	      = deg (Int (i1*i2))					//if simplify e= Int i1 and simplify e2 = Int i2

	      = deg (Int m)						//General Calculation

	      = 0							//by evaluation of deg function

###	     Subcase B:

	      = deg (Mul (simplify e1, simplify e2))			//by evaluation of simplify function

	      = deg (Mul (deg e1, deg e2))				//by Inductive Hypothesis

	      = (deg e1) + (deg e2)					//by evaluation of deg function


	Since 0 = 0 if simplify e1 = Int i1 and simplify e2 = Int i2 and (deg e1) + (deg e2) = deg (e1) + (deg e2) in sencond situation, then the inductive step of Mul situation is proved.

In All, since both base case and inductive case are proved, then the property: for all p1 that are polyExpr, deg p1 = deg (simplify p1) is true.

#Binomial Coefficients
##1.

State: (r,d,nn) 
##2.

```let rec choose_loop(r,nn,d,k) = if (d<=k) then choose_loop(((r*nn)/d),(nn-1),(d+1),k)
##3. Proof by Induction

if (r',nn',d',k') = choose_loop(r,nn,d) then r' = r*nn/d = n choose d = n! / ((n-d)! * d!
###Base Case:
when k=0, n = 1 -> Since d = 1 > k, then escape the loop. r = 1
		choose_loop(1,1,1,0)
		= 1!/ ((1-1)!*1!) 
		= 1
Since 1 = 1, then the base case is proved.
###Inductive Step:
###Inductive Hypothesis:
for all n, r = n choose d = n! / ((n-d)! * d!
###Need to prove:
for all n+1, r = (n+1) choose d = (n+1)! / (n+1-d)! *d!
###Proof:
		choose_loop(n+1,n+1,d,k)
		= choose_loop(((((n+1)*(n+1))/d),(n+1-d),(d+1),k)				//by evaluation of choose_loop function
		= (n+1) choose d								//by evaluation of choose_loop
Thus, inductive step is proved.

Since both base case and inductive case is proved, then the property that: if (r',nn',d',k') = choose_loop(r,nn,d) then r' = r*nn/d = n choose d = n! / ((n-d)! * d! is true.

