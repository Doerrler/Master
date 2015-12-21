(* The only explicit recursion in this file should be in this pre-defined function *)
let file_lines fname = 
  let in_file = open_in fname in
  let rec loop acc =
    let next_line = try Some (input_line in_file) with End_of_file -> None in
    match next_line with
    | (Some l) -> loop (l::acc)
    | None -> acc
  in
  let lines = try List.rev (loop []) with _ -> [] in
  let () = close_in in_file in
  lines

let file_as_string fname = String.concat "\n" (file_lines fname)
			 
let split_words = Str.split (Str.regexp "\\b") 

(* Your code goes here: *)
(* Read the list of representative text files *)
let flist = file_lines Sys.argv.(1)

(* Read	the contents of each text file: *)		
let read_text = List.map file_as_string flist

(* Read the contents of the target text file *)
let read_target_text = file_as_string Sys.argv.(2)
				   
(* Define the function that converts a string into a list of words *)			
let words xstr = 
  let preprocess = 
    String.map (fun x -> if (('z'>=x && x>='a') || ('Z'>=x && x>='A')) then x else ' ') xstr
  in let splitprocess = 
       split_words preprocess
     in  List.filter (fun y -> if (String.contains y ' ') then false else true) splitprocess
					   
(* Store the list of words from each representative *)
let rlist = List.map words read_text
			    
(* Convert the target text file into a list of words *)
let target_text = words read_target_text

(* Use Stemmer.stem to stem all of the words in the input, but only if I can make stemmer work. *)
let rlist_stem = List.map (fun x -> List.map Stemmer.stem x) rlist
let target_stem = List.map Stemmer.stem target_text

(* Define a function to convert a list into a set *)
let to_set lst =  List.fold_left 
  (fun alist e -> if (List.mem e alist) then alist else alist @ [e]) [] lst 

(* Convert all of the stem lists into stem sets *)				
let rlist_set = List.map to_set rlist_stem
let target_set = to_set target_stem
		
(* Define the similarity function between two sets: size of intersection / size of union *)	    
let intersection_size set1 set2 = if (List.length set1 > List.length set2) 
then (List.fold_left (fun acc e1 -> if (List.mem e1 set2) then acc+1 else acc) 0 set1)
else (List.fold_left (fun acc e2 -> if (List.mem e2 set1) then acc+1 else acc) 0 set2)

let union_size set1 set2 = (List.length set1) + (List.length set2) - (intersection_size set1 set2)

let similarity set1 set2 = 
  float_of_int (intersection_size set1 set2) /. float_of_int (union_size set1 set2)

(* Find the most similar representative file *)
let simlist = List.map (fun text -> (similarity text target_set)) rlist_set

let combolist = List.map2 (fun a b -> (a,b)) simlist flist

let max = List.fold_left (fun highest x -> if (x > highest) then x else highest) 0. simlist	  

(* print the result *)
let () = print_endline ("The most similar file to "^Sys.argv.(2)^" was "^(List.assoc max combolist))
let () = print_endline ("Similarity: "^(string_of_float max))

(* this last line just makes sure the output prints before the program exits *)			    
let () = flush stdout


(* TA COMMENT(zhan4136) Reading the File List: 4/4 *)

(* TA COMMENT(zhan4136) Splitting into Words: 5/5 *)

(* TA COMMENT(zhan4136) Canonicalization: 4/4 *)

(* TA COMMENT(zhan4136) Converting to Sets: 5/5 *)

(* TA COMMENT(zhan4136) Define the Similarity Function: 12/12 *)
