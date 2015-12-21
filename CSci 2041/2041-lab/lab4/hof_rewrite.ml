let rec append l1 l2 = match l1 with
  | [] -> l2 
  | (h::t) -> h::(append t l2)

let rec append1 = fun l1 -> fun l2 ->
 match l1 with
  | [] -> l2 
  | (h::t) -> h::(append1 t l2)

let rec append2 = fun l1 ->
 match l1 with
  | [] -> fun l2 ->l2 
  | (h::t) -> fun l2 ->h::(append2 t l2)

let rec append3 = fun l1 ->
 match l1 with
  | [] -> fun l2 ->l2 
  | (h::t) -> fun l2 ->h::(let append_1 = (append3 t) in append_1 l2)

let rec append4 = fun l1 ->
 match l1 with
  | [] -> fun l2 ->l2 
  | (h::t) -> fun l2 -> let append_2 = (append4 t) in match l2 with
    |[]->l1
    |(h'::t')->h:: append_2 (h'::t')

let rec append_final = fun l1 ->
 match l1 with
  | [] -> fun l2 ->l2 
  | (h::t) ->  let append_3 = (append_final t) in 
    fun l2 ->match l2 with
    |[]->l1
    |(h'::t')->h:: append_3 (h'::t')
(*This is the final rewrite of the append function.*)


let rec take_until lst s = match lst with
  | [] -> []
  | (h::t) -> if h = s then [] else h::(take_until t s)

let rec take_until1= fun lst-> fun s->
        match lst with
        |[]->[]
        |(h::t) -> if h = s then [] else h::(take_until1 t s)

let rec take_until2= fun lst->
        match lst with
        |[]->fun s ->[]
        |(h::t) -> fun s ->if h = s then [] else h::(take_until2 t s)

let rec take_until3= fun lst->
        match lst with
        |[]->fun s ->[]
        |(h::t) -> fun s ->if h = s then [] else h::(let take1 = (take_until) t in take1 s)

let rec take_until4= fun lst->
        match lst with
        |[]->fun s ->[]
        |(h::t) -> fun s ->let take3 = take_until4 t in if h = s then [] else take3 s 

let rec take_until_final= fun lst->
        match lst with
        |[]->fun s ->[]
        |(h::t) -> let take2 = take_until_final t in fun s ->if h = s then [] else take2 s
(*This is the final version of rewrite of the function take until*)
