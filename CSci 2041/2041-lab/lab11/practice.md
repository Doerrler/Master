2. Induction
A. Principle of structural induction:
		P(Empty) and P(Leaf a) and (P(a) and P(b) -> P(Node(a,b)) and P(a) -> P(Linked (v,a)))		

B. Prove that for all t, for all x, stree t x = stree (mirror t) x
		Basis:  a. stree Empty x 
			-> false			//by definition of function stree
			stree (mirror Empty) x
			-> stree Empty x		//by definition of function mirror
			-> false 			//by definition of function stree
			b. stree (Leaf y) x 
			-> x=y				//by definition of function stree
			stree (mirror (Leaf y)) x			
			-> stree (Leaf y) x		//by definition of function mirror
			-> x=y				//by definition of function stree 
	Both Induction and basis are true, then true.
	Thus, base case is true.
		Induction step:
	Case1: 
		Inductive hypothesis: for all t, for all x, stree t x = stree (mirror t) x
	Need to prove that 
		for all t, for all x, stree Link (y,t) x = stree (mirror (Link (y,t))) x
		stree (mirror (Link (y,t))) x
		-> stree (Link (y, mirror t))		//by definition of function mirror
		-> x=y || stree (mirror t) x		//by definition of function stree
		-> x=y || stree t x 			//by Inductive hypothesis
		-> stree (Link (y,t)) x 		//by definition of function stree
	Case2:  
		Inductive hypothesis: for all l, for all x, stree l x = stree (mirror l) x
		for all r, for all x, stree r x = stree (mirror r) x
	Need to prove that: 
		for all l, for all r, for all x, stree (Node(l,r)) x = stree (mirror (Node(l,r))) x
		stree (mirror (Node(l,r))) x 
		-> stree (Node (mirror l, mirror r)) x			//by definition of mirror
		-> stree (mirror l) x || stree (mirror r) x		//by definition of stree
		-> stree l x || stree r x				//by inductive hypothesis
		stree (Node (l.r))
		-> stree l x || stree r x				//by definition of stree
	Case1 and Case2 are proved, then Inductive is true.
Since both Basis and Inductive case are true, then the property:for all t, for all x, stree t x = stree (mirror t) x holds
