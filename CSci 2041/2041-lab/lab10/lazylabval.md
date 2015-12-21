Write the step-by-step lazy evaluation of each of the following _`lazyCaml`_ expressions; or if the expression does not terminate with a normal form, state why:
```
take 2 (dfs (crazytree 2 "run"))
```
take 2 (dfs (Node(crazytree 1 "arun", crazytree 2 "buffalorun")))

take 2 ((dfs (crazytree 1 "arun")) @ (dfs (crazytree 2 "buffalorun")))

take 2 ((dfs (Node(crazytree 0 "aarun", crazytree 1 "buffaloarun"))) @ (dfs (crazytree 2 "buffalorun")))

take 2 (((dfs (crazytree 0 "aarun")) @ (dfs (crazytree 1 "buffaloarun"))) @ (dfs (crazytree 2 "buffalorun")))

take 2 (((dfs (Leaf "aarun")) @ (dfs (Node(crazytree 0 "abuffaloarun", crazytree 1 "buffalobuffaloarun")))) @ (dfs (crazytree 2 "buffalorun")))

take 2 ((["aarun"] @ (dfs (Node(crazytree 0 "abuffaloarun", crazytree 1 "buffalobuffaloarun")))) @ (dfs (crazytree 2 "buffalorun")))

["aarun"]::(take 1 (((dfs (Node(crazytree 0 "abuffaloarun", crazytree 1 "buffalobuffaloarun"))) @ (dfs (crazytree 2 "buffalorun")))))

["aarun"]::(take 1 (((dfs (Leaf "abuffaloarun")) @ (dfs crazytree 1 "buffalobuffaloarun")) @ (dfs (crazytree 2 "buffalorun"))))

["aarun"]::(take 1 ((["abuffaloarun"] @ (dfs crazytree 1 "buffalobuffaloarun")) @ (dfs (crazytree 2 "buffalorun"))))

["aarun"]::["buffaloarun"]::(take 0 ((dfs crazytree 1 "buffalobuffaloarun") @ (dfs (crazytree 2 "buffalorun"))))

["aarun"]::["buffaloarun"]::[]

```
treefind (crazytree 1 "?") "abuffalo?"
```
treefind (Node(crazytree 0 ("a?"), crazytree 1 ("buffalo?")) "abuffalo?"
```
treefind (Node(Leaf "a?", crazytree 1 ("buffalo?")) "abuffalo?"
```
(treefind (Leaf "a?") "abuffalo?") || treefind (crazytree 1 ("buffalo?")) "abuffalo"
```
treefind (crazytree 1 ("buffalo?")) "abuffalo"
```
treefind (Node(crazytree 0 ("abuffalo?"), crazytree 1 ("buffalobuffalo?"))) "abuffalo?"
```
treefind (Leaf "abuffalo") "abuffalo" || treefind (crazytree 1 ("buffalobuffalo?")) "abuffalo?"
```
true
```
treefind (crazytree 1 "!") "buffalobuffalo!"
```
This function will never terminate. Since no "!" will be added to the string and treefind function will never find buffalo! Thus, the function crazytree will add buffalo to the string again and again. 
