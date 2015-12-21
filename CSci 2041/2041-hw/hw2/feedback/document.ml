type entity =
  Title of entity list
  | Heading of entity list 
  | Text of string
  | List of listEntity list
  | Anchor of anchor
  and listEntity = entity list
  and anchor = Named of string * (entity list) | HRef of string * (entity list)

type document = { head : entity list ; body : entity list }

(* a few example documents *)
let d1 = { head = [(Title [(Text "cs2041.org")])] ;
	   body = [ (Heading [(Text "CS 2041 Document")]) ;
		    (Text "A short document") ;
		    (Anchor (Named ("here", []))) ;
		    (Text "A little more stuff") ;
		    (Anchor (HRef ("#here", [(Text "Click this to go back")]))) ] }

let d2 = { head = [(Title [(Text "github.umn.edu/cs2041-f15/labs2041-f15/lab2")])] ;
	   body = [ (Heading [(Text "Lab 2: Types, Patterns and Recursion")]) ;
		    (Text "Due dates and stuff.") ;
		    (Heading [(Text "Ground rules")]) ;
		    (Text "Work with a partner, and so on.") ;
		    (Heading [(Text "Goals for this lab")]) ;
		    (List [[(Text "+ apply type inference knowledge")]; 
			   [(Text "+ see pattern matching examples")]; 
			   [(Text "+ write recursive functions")]]) ;
		    (Heading [(Text "Types and Type inference")]) ;
		    (Text "The rest of the lab gets boring quickly...") ] }

let d_err1 = { head = [(Anchor (Named ("notgood", [])))] ;
	       body = [(Text "But sort of boring.")] }

let d_err2 = { head = [] ;
	       body = [(Title [(Text "The Title doesn't go in the body!")])] }

let d_err3 = { head = [(Title [(Text "Title's where it goes but...")])] ;
	       body = [(Anchor (Named ("evenwose", [(Anchor (Named ("nested anchor!", [])))])))] }
			    
(* Example of computing on a document *)	      
let check_rules { head ; body } =
  let rec check_head el = match el with
    | [] -> true
    | (Title el')::t | (Heading el')::t -> (check_head el') && (check_head t)
    | (Text _)::t -> check_head t 
    | (List lst)::t -> let rec list_helper lst = match lst with
      |[]-> true
      |a::b -> if (check_head a) then (list_helper b) else false
		     in if (list_helper lst) then check_head t else false
    | (Anchor _)::t -> false in
  let rec check_body el nest = match el with (* nest = are we inside an Anchor element? *)
    | [] -> true
    | (Title el')::t -> false
    | (Text _)::t -> check_body t nest
    | (Heading el')::t -> (check_body el' nest) && (check_body t nest)
    | (List lst)::t -> let rec list_helper lst = match lst with
      |[]-> true
      |a::b -> if (check_head a) then (list_helper b) else false
		     in if (list_helper lst) then check_head t else false
    (* When we check the elements inside this anchor, set nest to true, but not in the tail*)
    | (Anchor (Named (_,el')))::t
    | (Anchor (HRef (_,el')))::t -> if nest then false 
      else (check_body el' true) && (check_body t nest) in
  (* Initially, nest = false... *)
  (check_body body false) && (check_head head)

let rec headings_helper body =  match body with
  |[]-> []
  |h::t-> match h with
    |Heading _-> h::(headings_helper t)
    |_ -> headings_helper t

let find_headings doc = headings_helper doc.body


let rec extract_text { head ; body } =
  let extract a = match a with
    | (Text text)::[] -> text
    | _ -> ""
  in let rec ext_helper body strx = (match body with
  | [] -> strx
  | (Text h)::t -> ext_helper t (strx^h^" ")
  | (Heading h)::t | (Title h)::t -> ext_helper t (strx^(extract h)^" ")
  | (Anchor (Named (_,h)))::t | (Anchor (HRef (_,h)))::t -> ext_helper t (strx^(extract h)^" ")
  | (List h)::t -> let rec lst_helper lst = (match lst with
    | [] -> ext_helper t ""
    | a::b -> ext_helper a "" ^ lst_helper b)
		   in strx^(lst_helper h))
     in ext_helper body ""

(* TA COMMENT(halto004): 
	-use mutual recursion to handle entity and list seperately
	-listEntity should have a constructor
	7/10

*)
