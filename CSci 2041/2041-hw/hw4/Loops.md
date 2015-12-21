##lsearch

###State: (i,(f i))

###flsearch

      let flsearch (f: int -> int) (n:int) = {
            let   (i, fi) = (0, (f 0))
            let rec loop (i, fi) n0 =
                  if (i <= n0) and (f1 <= 0) then
                        loop (i+1, (f i+1)) n0;
                  else (i, fi);
            let result,val = loop (i, fi) n;
            result;
      }


      let flsearch f n = let loop_helper i fi = if ((i <= n) && (fi < 0)) then (loop_helper (i+1) (f i)) else i 
            in loop_helper 0 (f 0)

##Proof 1

###Property:

for all f such that type(f) = int -> int, for all n ∈ int, 

      if (f n) > 0 then f (lsearch f n) > 0
      
      if (f n) > 0 then f (flsearch f 0) > 0

If (f n) > 0 is false then the statement is true regardless, so we only need to prove the case where (f n) > 0 is ture.

###Base Case:

(f 0) > 0 -> f (lsearch f 0) > 0

(f 0) > 0 -> (f 0) > 0

When n = 0 the loop would never run, leaving f 0 > 0 and f (lsearch f 0) > 0 so assuming f 0 > 0 we only must show that (lsearch f 0) >= 0. Because the loop does not run a single time (because f(0) > 0) the function f is only applied to i=0 in the lsearch function, returning 0. Thus proving that if (f 0) > 0 then f (lsearch f 0) > 0 by substituting 0 in for (lsearch f 0) and we know that (f 0) = (f 0) >= 0.

###Inductive Case:

need to prove that if (f (n+1)) > 0 then f (lsearch f (n+1)) > 0

**Inductive Hypothesis:** if (f n) > 0 then f (lsearch f n) > 0

if (f (n+1)) > 0 -> f (lsearch f (n+1)) > 0

if (f (n+1)) > 0 -> f (f (lsearch f n)) > 0 //evaluation of lsearch

if (f n) > 0 -> f (lsearch f n) > 0 //by I.H.

so f (f (lsearch f n)) must also be greater than 0

###Conclusion:

Therefore by substitution and structural induction for all f such that type(f) = int -> int, for all n ∈ int, 

      if (f n) > 0 then f (lsearch f n) > 0

##Proof2

###Property: 

for all f such that type(f) = int -> int, if (f 0) < 0 then for all n ∈ int, 

      if i < (lsearch f n) then (f i) < 0

###Base Case:

if i < (lsearch f n) then (f i) < 0

When n = 0 the loop wouldn’t run and i would also be 0 so if (f 0) < 0 and i < (lsearch f n) would become 0 < (lsearch f 0) and (lsearch f 0) would be (f (f 0)) so 0 is indeed less than (f (f 0)) and thus (f 0) < 0.

###Inductive Case:

want to prove that for all f, if (f 0) < 0 then for all n, if i < (lsearch f n+1) then (f i) < 0

**Inductive Hypothesis:** for all f such that type(f) = int -> int, if (f 0) < 0 then for all n ∈ int, 

if i < (lsearch f n) then (f i) < 0</b>

if (f 0) < 0, then i < (lsearch f (n+1))

i < f (lsearch f n))

###Conclusion:

Therefore by substitution and structural induction for all f such that type(f) = int -> int, for all n ∈ int, 

      if (f n) > 0 then f (lsearch f n) > 0
