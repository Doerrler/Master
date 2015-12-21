(* this line makes the lwt bind infix operator available in scope *)
open Lwt

(* the code to handle connection ending goes here: *)
exception Quit
let close_channels ch1 ch2 =
	Lwt_io.close ch1 >>= fun() ->
	Lwt_io.close ch2 >>= fun () ->
	Lwt.fail Quit

       
let echo_handler (inp,outp) =  
  let handle_input s = (match Str.string_before s 2 with
    | "/q" -> close_channels inp outp
    | _ -> Lwt_io.write_line outp s) in
  (* Here, before main_loop, is where we'll add the input line handler: *)
  let rec main_loop () =
    Lwt_io.read_line inp >>= fun l ->
    handle_input l >>= main_loop in
  Lwt.async (fun () -> Lwt.catch main_loop (fun e -> Lwt.return ()))
								 	    
let s = Lwt_io.establish_server (Unix.ADDR_INET(Unix.inet_addr_any, 16384)) echo_handler
let _ = Lwt_main.run (fst (Lwt.wait ()))
