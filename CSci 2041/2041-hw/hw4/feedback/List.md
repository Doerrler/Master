##Induction on Lists

##Lemma 1:

want to show for all l1 ∈ 'a list and for all l2 ∈ 'a list, 

	length l1@l2 = (length l1) + (length l2)
	
for all l ∈ 'a list, P(l) = for all l2 ∈ 'a list, length l@l2 = length l + l2

###Base Case:

P([]): length[]@l2 = (length []) + (length l2)

length l2 = 0 + length l2 //by definition of @ (left) and evaluation of length (right)

length l2 = length l2 //simplification
	
###Inductive Case:

want to show for all l1 ∈ 'a list and for all x ∈ 'a, P(l) -> P(x::lst), 

**Inductive Hypothesis:** for all l ∈ 'a list and for all l2 ∈ 'a list, length l@l2 = (length l) + (length l2)

length (x::l)@l2 = (length x::l) + (length l2)

length (x::l)@l2 = (1 + length l) + (length l2)  //evaluation of length

length x::(l@l2) = (1 + length l) + (length l2)  //definition of :: and associativity of @

1 + length l@l2 = 1 + (length l) + (length l2)  //evaluation of length

1 + (length l) + (length l2) = 1 + (length l) + (length l2)  // by I.H.

###Conclusion:

Therefore by induction, for all l ∈ 'a list, and for all l2 ∈ 'a list,

	length l1@l2 = (length l1) + (length l2)

##Lemma 2: 

//this code was modified from class and Nicholas Hopper is the original author

want to show for all l1 'a list and for all l2 'a list,

	tail_rev l1 l2 = (l1^R)@l2
	
for all l ∈ 'a list, P(l) = for all l2 ∈ 'a list, tail_rev l l2 = l<sup>R</sup>@l2

###Base Case:

P([]): tail_rev [] l2 = []<sup>R</sup>@l2 = []@l2 = l2 //definition of an empty list and simplification

###Inductive Case:

want to show for all l ∈ 'a list and for all x ∈ 'a, P(l) -> P(x::l) 

**Inductive Hypothesis:** for all l ∈ int list and for all l2 ∈ int list,  tail_rev l l2 = l<sup>R</sup>@l2

tail_rev x::l l2 = (x::l)<sup>R</sup>@l2

tail_rev l x::l2 = (x::l)<sup>R</sup>@l2 = l<sup>R</sup>@[x]@l2 // evaluation of tail_rev (right) and properties of lists (left)

l<sup>R</sup>@(x::l2) = l<sup>R</sup>@(x::l2) //by I.H. (right) and properties of lists (left)

###Conclusion:

Therefore by principle induction of lists, for all l1 ∈ 'a list, and for all l2 ∈ 'a list,

	tail_rev l1 l2 = (l1^R)@l2

##Lemma 3:

want to show for all l ∈ int list and for all acc ∈ int, 

	tsum l n = n + sum(l)
	
for all l int ∈ int list, P(l) = for all n ∈ int, tsum l n = n + sum(l)
  
###Base Case:

P([]): tsum [] n = n + sum([])

n = n + 0		//by evaluation of tsum and the definition of an empty list

###Inductive Case:

want to show for all l ∈ int list and for all n ∈ int, P(l) -> P(x::l) 

**Inductive Hypothesis:** for all l ∈ int list and for all n ∈ int, tsum l n = n + sum(l)

tsum (x::l) n = n + sum(x::l)

tsum l (x+n) = n + x + sum(l)		//evaluation of tsum and definition of sum

x + n + sum(l) = x + n + sum(l)		//by I.H. and associativity of integer addition

###Conclusion:

Therefore by induction, for all l ∈ int list, and for all n ∈ int,

	tsum l n = n + sum(l)

##Proof 1

###Property: 

for all l ∈ int list, 
	
	length l = length (reverse l)

for all l int ∈ list, P(l): length l = length (reverse l)

###Base Case:

P([]): length [] = length (reverse [])

length [] = length (tail_rev [] [])		//by evaluation of reverse

length [] = length ([]<sup>R</sup>@[])		// by lemma 2

0 = 0 + 0				    	//by evaluation of length and lemma 1

###Inductive Case:

want to show that, for all l ∈ 'a list -> P(l) -> P(x::l) 

**Inductive Hypothesis:** for all l ∈ int list, length l = length (reverse l)

length x::l = length (reverse x::l)

length x::1 = length (tail_rev x::l [])		//by evaluation of reverse

length x::l = length (tail_rev l [x])		//by evaluation of tail_rev

length x::l = length (l<sup>R</sup>@[x])	//by lemma 2

(length l) +1 = (length l) +1 			//by evaluation of length and lemma 1

---
**TA COMMENT(moham775)**: 12/12
---

###Conclusion:

Therefore, by the principle of structural induction on lists for all l of type int list, 
	
	length l = length (reverse l)

##Proof 2

###Property:

for all l ∈ int list, 
	
	tail_sum l = tail_sum (reverse l)

for all l int ∈ list, P(l): tail_sum l = tail_sum (reverse l)

###Base Case:

P([]): tail_sum [] = tail_sum (reverse [])

tail_sum [] = tail_sum (tail_rev [] [])			//by evaluation of reverse

tail_sum [] = tail_sum ([]<sup>R</sup>@[])		//by lemma 2

tail_sum [] = tail_sum ([]@[])				//by definition of an empty list

tail_sum [] = tail_sum []				//by definition of @

###Inductive Case:

want to show that, for all l ∈ 'a list -> P(l) -> P(x::l) 

**Inductive Hypothesis:** for all l ∈ int list, tail_sum l = tail_sum (reverse l)

tail_sum x::l = tail_sum (reverse x::l)

tsum (x::l) 0 = tsum (reverse x::l) 0			//by evaluation of tail_sum

tsum (x::1) 0 = tsum (tail_rev x::l []) 0		//by evaluation of reverse

tsum l x = tsum (tail_rev l [x]) 0			//by evaluation of tsum (left) and of tail_rev (right)

tsum l x = tsum (l<sup>R</sup>@[x]) 0			//by lemma 2

tsum 1 x = tsum l<sup>R</sup> x				//by associativity of @ and evaluation of tsum

x + sum(l) = x + sum(l<sup>R</sup>)			//by lemma 3

x + sum(l) = x + sum(1)					//by mathamatical definition of sum (associativity)

###Conclusion: 

Therefore, by the principle of structural induction on lists for all l of type int list
	
	tail_sum l = tail_sum (reverse l)



### TA Comment(meye2058) tail_sum Feedback: /12
