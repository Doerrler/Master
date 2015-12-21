1. P(C0) and P (C1) and (P(b) -> P (L0 b)) and (P(b) -> P (L1 b))
2.
Property:	for all bl∈bitlist, bitweight bl <= bitlen bl
Base Case:
		Case 1: When bl = C0
			bitweight C0 <= bitlen C0
			0 <= bitlen C0					//LHS by definition of bitweight
			0 <= 1 						//RHS by definition of bitlen
			True
		Case 2: When bl = C1
			bit weight C1 <= bitlen C1
			1 <= bitlen C1					//LHS by definition of bitweight
			1 <= 1						//RHS by definition of bitlen
			True
		Since both Case1 and Case2 are true, then base case holds.
	  Inductive steps:
		Inductive Hypothesis: for all bl∈bitlist, bitweight bl <= bitlen bl
		Case 1:
			Need to prove that: for all bl∈bitlist, and for all L1∈bitlist,
				 bitweight (L1 (bl)) <= bitlen (L1 (bl))
			bitweight L1 b1 <= bitlen L1 b1
			1 + (bitweight b1) <= bitlen L1 b1 		//LHS by definition of bitweight
			1 + (bitweight b1) <= 1 + (bitlen b1)		//RHS by definition of bitlen
			bitweight b1 <= bitlen b1			//simplification
			True						//By inductive hypothesis
		Case 2:
			Need to prove that: for all bl∈bitlist, and for all L0∈bitlist,
				 bitweight (L0 (bl)) <= bitlen (L0 (bl))
			bitweight L0 b1 <= bitlen L0 b1
			bitweight b1 <= bitlen L0 b1 			//LHS by definition of bitweight
			bitweight b1 <= 1 + (bitlen b1)			//RHS by definition of bitlen
			bitweight b1 <= bitlen b1 <= 1 + (bitlen b1)	//simplification
			True						//By inductive hypothesis
		Since both Case 1 and Case 2 are true, then inductive hypothesis holds.
	Since both Base case and Inductive Case are true, then the property: for all bl∈bitlist, bitweight bl <= bitlen bl holds
