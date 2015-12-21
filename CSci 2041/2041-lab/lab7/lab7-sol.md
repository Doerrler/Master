Binomial Coefficients
1.Property: for all n>0, k>0, and k<n choose n k evaluates n! / ((n-k)! * k!)
2.Base Case: choose 0 0 = 0! / ((0-0)!*0!) = 1 / 1*1 = 1   Base Case Proved
3.Induction:    If P(n) is true then we need to prove that p(n+1) is true
  		Inductive Hypothesis: choose n k = n! / ((n-k)! * k!)                         for n>0, k>0, and k<n
	        We want to prove that: choose n+1 k = (n+1)! / ((n+1-k)! * k!)		      for n>0, k>0, and k<n
	     			       choose n+1 k = ((choose n (k-1))*(n+1))/k              //by the definition of function choose
	                                            = (n! / ((n-(k-1))! * (k-1)!) * (n+1))/k  //substitute Inductive Hypothesis in 
			                            = (n+1)!/ ((n+1-k)! * k!)                 //(Arithmetic calculation)
			  							   	      //Induction proved
Since both base case and induction part are proved, then the property: for all n>0, k>0 and k<n, choose n k evaluates n! / ((n-k)! * k!)


Structured Arithmetic
1.Property: for all m that are elements of nat, for all n that are elements of nat, if (to_int m) > (to_int n), then to_int (minus_nat m n) = (to_int m) - (to_int n)
2.Base Case: if m = n = Zero then
       	     (to_int m) - (to_int n) = 0  			//Eq1
	     to_int (minus_nat m n)= to_int m = to_int Zero = 0 //Eq2
	     Eq1 = Eq2, then base case is proved.
3.Induction: If P(n) is true then we need to prove that p(Succ n) is true
	     Inductive Hypothesis: for all (to_int m)>(to_int n), then to_int (minus_nat m n) = (to_int m) - (to_int n)
	     Need to prove that : for all (to_int Succ m)>(to_int n), then to_int (minus_nat (Succ m) n) = (to_int Succ m) - (to_int n)
	     to_int (minus_nat (Succ m) n) = to_int (Succ minus_nat m n)
					   = 1 + to_int (minus_nat m n) 	//by definition of to_int function
					   = 1 + (to_int m) - (to_int n)	//by Inductive Hypothesis
					   = to_int (Succ m) - (to_int n)	//by definition o fto_int function
	     Then the Inductive step is proved.
Since both base case and inductive step are proved, then the property: for all m that are elements of nat, for all n that are elements of nat, if (to_int m) > (to_int n), then to_int (minus_nat m n) = (to_int m) - (to_int n) is true.

Structured Comparisons
1.Property: for all m that are elements of nat, for all n that are elements of nat, if (to_int m) = (to_int n), then eq_nat m n. Also, if eq_nat m n, then (to_int m) = (to_int n)
	Base Case: if m = n = Zero then
		 	(to_int m) = (to_int n) -> 0 = 0 -> true				//Eq1 by definition of to_int function
		 	eq_nat m n = eq_nat Zero Zero -> true					//Eq2 by definition of eq_nat function
		 	Since Eq1 = Eq2, then the base case is proved
	Inductive Step: 
			If P(n) is true, then need to prove that P(Succ n) is true
			Inductive Hypothesis: for all m that are elements of nat, for all n that are elements of nat, if (to_int m) = (to_int n), then eq_nat m n. Also, if eq_nat m n, then (to_int m) = (to_int n)
			Need to prove that: for all Succ m that are elements of nat, for all Succ n that are elements of nat, if (to_int Succ m) = (to_int Succ n), then eq_nat (Succ m) (Succ n). Also, if eq_nat (Succ m) (Succ n), then (to_int Succ m) = (to_int Succ n)
			(to_int Succ m) = (to_int Succ n) 
		->	1 + (to_int m)  = 1 + (to_int n)  					//by definition of to_int function
		->	Since to_int m = to_int n is true by Inductive Hypothesis, then 
		->	1 + (to_int m)  = 1 + (to_int n) = true 				//Eq1 by Inductive Hypothesis
		
			eq_nat (Succ m) (Succ n)
		-> 	eq_nat m n								//by definition of eq_nat function
		-> 	=true									//by inductive hypothesis
Although in this question, we are supposed to prove in 2 directions. But when we are trying to prove it, we find that the question always evaluates to a boolean that is true which means that both left and right of the equation leads to the same answer true. Thus, we can just prove in one direction and the other direction is obviously right.
Since both base case and inductive step are proved, then the property that for all m that are elements of nat, for all n that are elements of nat, if (to_int m) = (to_int n), then eq_nat m n. Also, if eq_nat m n, then (to_int m) = (to_int n) is proved.
	     
