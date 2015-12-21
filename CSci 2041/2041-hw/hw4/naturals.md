##power

###Property: 
for all n ∈  &naturals;, for all x  ∈ float, 

	P(n): power x n = x<sup>n</sup> 
  
###Base Case:

P(0): power x 0 = x<sup>0</sup> = 1.0 					//by evaluation
  
###Inductive Case:

want to show that, for all n ∈ &naturals;, P(n+1) = x<sup>n+1</sup>
	
**Inductive Hypothesis:** power x n = x<sup>n</sup>

power x (n+1) = x <sup>1</sup> *. power x n 			//by evaluation
	
power x (n+1) = x *. x<sup>n</sup> 			      	//by I.H.
	
power x (n+1) = x <sup>n+1</sup> 		      		//by evaluation

###Conclusion:

Therefore, by the principle of natural induction for all n ∈ N, for all x  ∈ float, 

	power x n = x<sup>n</sup>

##pow_nat

###Property: 

for all n ∈ &naturals; , for all x ∈ float, 
	
	P(n): power x (to_int n) = pow_nat x n
  
###Base Case:

P(0): x<sup>0</sup> = 1

power x (to_int Zero) = pow_nat x Zero
  
power x<sup>0</sup> = 1 				//by evaluation of to_int and pow_nat
  
x<sup>0</sup> = 1		                  	//by evaluation
  
###Inductive Case:

want to show that, for all n ∈ &naturals;, P(Succ n): power x (to_int (Succ n)) = pow_nat x (Succ n)

**Inductive Hypothesis:** power x (to_int n) = pow_nat x n

power x (to_int (Succ n)) = pow_nat x (Succ n)

power x ((to_int n)+1) = x * (pow_nat x n)    //by evaluation of to_int (left) and pow_nat (right)

x * power x (to_int n) = x * power x (to_int n)	  //by evaluation of power (left) and I.H. (right)
   
###Conclusion:

Therefore, by the principle of structural induction for all n ∈ nat, for all x ∈ float, 

	power x (to_int n) = pow_nat x n


##less_nat

###Property:

for all n ∈ &naturals;,

	P(n): for all m ∈ &naturals;, less_nat m n <-> (to_int m) < (to_int n)
  
###Base Case:

P(Zero): less_nat m Zero = (to_int m) < (to_int Zero)

false = false   	//by evaluation (left) and by def of structured natural numbers (right)
  
###Inductive Case:

want to show that P(n) implies P(Succ n), less_nat m (Succ n) <-> (to_int m) < (to_int (Succ n))

**Inductive Hypothesis:** for all n ∈ nat, for all m ∈ &naturals;, less_nat m n <-> (to_int m) < (to_int n)

less_nat m (Succ n) <-> (to_int m) < (to_int (Succ n))

**Case 1:** if (to_int m) < (to_int n):

less_nat m n <-> (to_int m) < (1 + (to_int n))

true <-> true

**Case 2:** if (to_int m) >= (to_int n):

less_nat m n <-> (to_int m) < (1 + (to_int n))

false <-> false

###Conclusion:

Since both cases hold, by the principle of structural induction for all n ∈ &naturals;, n.P(Succ n); for all m ∈ &naturals;, 

	less_nat m (Succ n) <-> (to_int m) < (to_int (Succ n))
