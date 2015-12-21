let rec forward_search f y dict = match dict with
|[] -> []
|h::t -> if f(h) = y then h :: forward_search f y t else forward_search f y t

(* Open filename, and return a list of lines as strings.  Catches
input_line exceptions, but will raise exceptions caused by open_in *)
let read_input_lines filename = 
    let in_file = open_in filename in
    let rec loop acc =
        let next_line = try Some (input_line in_file) with End_of_file -> None in
        match next_line with
            | (Some l) -> loop (l::acc)
            | None -> acc
        in
    let lines = try List.rev (loop []) with _ -> [] in
    let () = close_in in_file in lines

(*First defines the dictionary through hard coding and then checks for a keyword. It then uses both the dictionary and 
keyword as inputs for the forward_search function. Once the list of matches is complete it checks to see if there is an
element in the list and if so it prints that a match was found and the matching string.*)
let () = let dictionary = read_input_lines "/usr/share/dict/words" in
let keyword = if Array.length Sys.argv >= 2 then Digest.from_hex(Sys.argv.(1)) else raise (invalid_arg "keyword invalid")  in
let outlist = forward_search Digest.string keyword dictionary in
match outlist with
|[] -> print_string ("No match found \n")
|h::t -> print_string ("Found a match: " ^ h ^"\n")



(* TA Comment(meye2058) forward_search Feedback: 14/14 *)
(* TA Comment(leid0065) cmd line Feedback: 6/6 *)
