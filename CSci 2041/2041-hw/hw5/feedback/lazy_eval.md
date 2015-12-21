###take 3 (squares 3)

Will reach a normal form in a finite number of steps.

take 3 (squares 3)

take 3 (9::(squares 4))

9::(take 2 (squares 4))

9::(take 2 (16::(squares 5)))

9::16::(take 1 (squares 5))

9::16::(take 1 (25::(squares 5)))

9::16::25::(take 0 (squares 6))

9::16::25::[]

###fold_right (&&) (map ((<) 0) (squares 2)) true

Will never reach a normal form under lazy evaluation because the squares will never result in a value less than 0.

###fold_right (||) (map (fun n -> n mod 8 = 0) (factorials ())) false

Will reach a normal form in a finite number of steps.

fold_right (||) (map (fun n -> n mod 8 = 0) (factorials ())) false

fold_right (||) (((fun n -> n mod 8 = 0) 1)::(map (fun n -> n mod 8 = 0) (fac_acc 2 1))) false

(||) false (fold_right (||) (map (fun n -> n mod 8 = 0) (fac_acc 2 1)) false)

(||) false (fold_right (||) (((fun n -> n mod 8 = 0) 2)::(map (fun n -> n mod 8 = 0) (fac_acc 3 2)) false))
(||) false ((||) false (fold_right (||) (map (fun n -> n mod 8 = 0) (fac_acc 3 2)) false))

(||) false ((||) false (fold_right (||) (((fun n -> n mod 8 = 0) 6)::(map (fun n -> n mod 8 = 0) (fac_acc 4 6))) false))

(||) false ((||) false ((||) false (fold_right (||) (map (fun n -> n mod 8 = 0) (fac_acc 4 6))) false))

(||) false ((||) false ((||) false (fold_right (||) (((fun n -> n mod 8 = 0) 24)::(map (fun n -> n mod 8 = 0) (fac_acc 5 24))) false)))

(||) false ((||) false ((||) false ((||) true (fold_right (||) (map (fun n -> n mod 8 = 0) (fac_acc 5 24))) false)))

This is equivalent to evaluating (false || false || false || true || ...) but by definition of the or operator we know that this would evaluate to true and the lazy evaluation would not need to proceed any further.

###take (sum_list (squares 1)) (factorials ())

Will never reach a normal form under lazy evaluation because the expression (sum_list (squares 1)) will approach infinity and thus it will not stop calculating factorials. 


(* TA Comment(meye2058) lazy_eval Feedback: 20/20
 *)