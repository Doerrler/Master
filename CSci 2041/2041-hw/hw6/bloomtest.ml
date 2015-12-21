open Bloom

module BloomSparseInt =  BloomFilter(SparseSet)(IntHash)
module BloomBitInt =  BloomFilter(BitSet)(IntHash)

let insert_list = let rec il_helper i lst = match i with
  | 200 -> lst
  | _ -> il_helper (i+1) ((Random.int ((int_of_float (2. ** 30.)) -1))::lst)
		  in il_helper 0 []

let test_list = let rec il_helper i lst = match i with
  | 1000000 -> lst
  | _ -> il_helper (i+1) ((Random.int ((int_of_float (2. ** 30.)) -1))::lst)
		  in il_helper 0 []

(*BloomSparseInt Build*)
let bsit =
  let start = Sys.time () in
  let result = BloomSparseInt.from_list insert_list in
  let finish = Sys.time () in
  Printf.printf "SparseInt: build time = %f seconds " (finish-.start); result

(*BloomSparseInt Test*)
let tsit =
  let start = Sys.time () in
  let rec testfp lst acc = match lst with
    | [] -> let finish = Sys.time () in 
	    Printf.printf "test time = %f seconds false positives = %i \n" (finish -. start) acc
    | h::t -> if BloomSparseInt.mem h bsit then testfp t (acc+1) else testfp t acc
	    in testfp test_list 0

(*BloomBitInt Build*)
let bbit =
  let start = Sys.time () in
  let result = BloomBitInt.from_list insert_list in
  let finish = Sys.time () in
  Printf.printf "BitInt: build time = %f seconds " (finish-.start); result

(*BloomBitInt Test*)
let tbit =
  let start = Sys.time () in
  let rec testfp lst acc = match lst with
    | [] -> let finish = Sys.time () in 
	    Printf.printf "test time = %f seconds false positives = %i \n" (finish -. start) acc
    | h::t -> if BloomBitInt.mem h bbit then testfp t (acc+1) else testfp t acc
	    in testfp test_list 0
  

module BloomSparseString =  BloomFilter(SparseSet)(StringHash)
module BloomBitString =  BloomFilter(BitSet)(StringHash)

let read_input_lines filename =
  let in_file = open_in filename in
  let rec loop acc =
    let next_line = try Some (input_line in_file) with End_of_file -> None in
    match next_line with
    | (Some l) -> loop (l::acc)
    | None -> acc
  in let lines = try List.rev (loop []) with _ -> []
     in let () = close_in in_file 
	in lines

let insert_list = read_input_lines "top-2k.txt"
let test_list = read_input_lines "top-1m.txt"

(*BloomSparseString*)
let bsst =
  let start = Sys.time () in
  let result = BloomSparseString.from_list insert_list in
  let finish = Sys.time () in
  Printf.printf "SparseString: build time = %f seconds " (finish-.start); result

let tsst =
  let start = Sys.time () in
  let rec testfp lst acc = match lst with
    | [] -> let finish = Sys.time () in 
	    Printf.printf "test time = %f seconds false positives = %i \n" (finish -. start) acc
    | h::t -> if BloomSparseString.mem h bsst then testfp t (acc+1) else testfp t acc
	    in testfp test_list 0

(*BloomBitString*)
let bbst =
  let start = Sys.time () in
  let result = BloomBitString.from_list insert_list in
  let finish = Sys.time () in
  Printf.printf "BitString: build time = %f seconds " (finish-.start); result

let tbst =
  let start = Sys.time () in
  let rec testfp lst acc = match lst with
    | [] -> let finish = Sys.time () in 
	    Printf.printf "test time = %f seconds false positives = %i \n" (finish -. start) acc
    | h::t -> if BloomBitString.mem h bbst then testfp t (acc+1) else testfp t acc
	    in testfp test_list 0
