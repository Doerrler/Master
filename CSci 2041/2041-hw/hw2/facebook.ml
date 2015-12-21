(* A hypothetical scaled-down "database" structure for a social network. *)
type user_id = UID of int
type user = { id: user_id ; name : string ; private_data : string ; friends : user_id list }
type post_id = PID of int
type wall_post = { pid : post_id ; uid : user_id ; contents: string }
type database = { users : user list ; wall_posts : wall_post list }

let harry = { id = (UID 1) ; name = "Harry Potter" ; private_data = "The Chosen One" ;
	      friends = [ (UID 2) ; (UID 3) ] }
let hermione = { id = (UID 2) ; name = "Hermione Granger" ; private_data = "Hogwarts: A History" ;
		 friends = [ (UID 1) ; (UID 3) ] }
let ron = { id = (UID 3) ; name = "Ronald Weasley" ; private_data = "Er, what now?" ;
	    friends = [ (UID 1) ; (UID 2) ] }
let viktor = { id = (UID 4) ; name = "Viktor Krum" ; private_data = "Wronksi Feint" ;
	       friends = [ (UID 2) ; (UID 5) ] }
let igor = { id = (UID 5) ; name = "Igor Karkaroff" ; private_data = "Former Death Eater" ;
	     friends = [ (UID 4) ] }

let hermiones_post = { pid = (PID 1); uid = (UID 2) ; contents = "I read about it in Hogwarts: A History" }
let harrys_post = { pid = (PID 2) ; uid = (UID 1) ; contents = "Horcruxes are hard to find!" }
let viktors_post = { pid = (PID 3) ; uid = (UID 4) ; contents = "Quidditch is better than football." }
let rons_post = { pid = (PID 4) ; uid = (UID 3) ; contents = "Magic is cool." }
let harrys_second_post = { pid = (PID 5) ; uid = (UID 1) ; contents = "I dunno, I kinda miss he-who-shall-not-be-named." }

let hw = { users = [harry; hermione; ron] ; wall_posts = [hermiones_post ; harrys_post ; rons_post ; harrys_second_post ] }
let ds = { users = [viktor ; igor ] ; wall_posts = [ viktors_post ] }
	   

(*
 Checks the referential integrity of wall_post by comparing 
 its uid to the id's in the user list
*)
let rec check_wp wpost ulst = match ulst with
  |[] -> false
  |h::t -> if h.id = wpost.uid then true else (check_wp wpost t)


(*
 Helper Function for check_friends
-takes in friends which is a uid list and ulist which if .id is used is a uid list
-this gets arround the issue of having a user as an input rather than the friendslist
-goes through each uid  of friendslist and compares it to those in ulist until a match
 is located at which time it proceeds with the next uid in friendslist
*)
let rec compare_uids friendslist ulist = match friendslist with 
  |[] -> true
  |h::t -> match ulist with
    |[] -> false
    |h2::t2 -> if h = h2.id then (compare_uids t ulist) else (compare_uids friendslist t2)

let rec check_friends usr ulst = compare_uids usr.friends ulst


(*
 Helper Functions for check_db
-each one just uses the respective function (check_wp or check_friends) and applies it to
the appropriate list
-then the actual function just has to check that they are both true
*)

let rec check_wpostlist wpostlist userlist = match wpostlist with
  |[] -> true
  |h::t -> if (check_wp h userlist) then (check_wpostlist t userlist) else false

let rec check_friendslist friendslist userlist = match friendslist with
  |[] -> true
  |h::t -> if (check_friends h userlist) then (check_friendslist t userlist) else false

let rec check_db db = ((check_wpostlist db.wall_posts db.users) &&
    (check_friendslist db.users db.users))
