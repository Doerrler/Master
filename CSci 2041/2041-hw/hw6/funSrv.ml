(*I added private message functionality which works by typing /p name with name being the desired user's nickname. There are no additional libraries needed for this implementation. Gives an error message if no nickname is entered.*)
open Lwt

module Server = struct
    (* a list associating user nicknames to the output channels that write to their connections *)
    (* Once we fix the other functions this should change to []*)
    let sessions = ref []	     
    exception Quit
		
    (* replace Lwt.return below with code that uses Lwt_list.iter_p to 
       print sender: msg on each output channel (excluding the sender's)*)
    let rec broadcast (sender:string) (msg:string) =  
      Lwt_list.iter_p (function | (a,_) when a = sender -> Lwt.return () 
      | (_,a) -> Lwt_io.write a (sender^": "^msg^"\n")) !sessions

    (* remove a session from the list of sessions: important so we don't try to 
       write to a closed connection *)
    let remove_session nn = 
      sessions := List.remove_assoc nn !sessions;
      broadcast nn "<left chat>" >>= fun () ->
      Lwt.return ()

    (* Modify to handle the "Quit" case separately, closing the channels before removing the session *)
    let handle_error e nn inp outp = match e with
      | Quit -> Lwt_io.close inp >>= fun () -> (Lwt_io.close outp) >>= fun () -> Lwt.return ()
      | _ -> Lwt.return ()

    (* modify sessions to remove (nn,outp) association, add (new_nn,outp) association.
       also notify other participants of new nickname *)
    let change_nn nn outp new_nn =
      broadcast nn ("<changed nickname to "^new_nn^">") >>=
      fun () -> Lwt.return (sessions := List.remove_assoc nn !sessions;) >>=
      fun () -> Lwt.return (sessions := (new_nn,outp)::!sessions)
	  
    let chat_handler (inp,outp) =
      let nick = ref "" in
      (* replace () below with code to 
         + obtain initial nick(name), 
         + add (nick,outp) to !sessions, and 
         + announce join to other users *) 
      let _ = Lwt_io.write outp "Welcome, please enter a nickname: " >>=
	fun () -> (Lwt_io.read_line inp ) >>=
	fun s -> (Lwt.return (nick := s)) >>=
	fun () -> (Lwt.return (sessions := (!nick,outp) :: !sessions)) >>=
	fun () -> broadcast !nick "<joined>" in
      (* modify handle_input below to detect /q, /n, and /l commands *)
      let handle_input l = match l with
	| "" -> Lwt.return ()
	| x when x.[0] = '/' -> (match x.[1] with
	    | 'q' -> remove_session !nick >>= fun() -> Lwt.fail Quit
	    | 'n' when (String.length l) <= 3 -> Lwt.return()
	    | 'n' -> let nickname = (String.sub x 3 ((String.length l) - 3)) in 
		     change_nn !nick outp nickname >>= 
		     fun () -> Lwt.return (nick := nickname) >>= 
		     fun () -> Lwt.return ()
	    | 'l' -> Lwt_io.write outp "People on the server: \n" >>= 
	      fun() -> Lwt_list.iter_p (fun a -> match a with (a,_) -> Lwt_io.write outp (a ^ "\n")) !sessions
	    | 'p' -> let pmtarget = (String.sub x 3 ((String.length l) - 3)) in
		     match pmtarget with
		     | "" -> Lwt_io.write outp ("Invalid, please enter a nickname! \n") 
		     | _ -> Lwt_io.write outp ("Enter private message for "^pmtarget^": ") >>=
		       fun () -> Lwt_io.read_line inp >>=
		       fun s -> Lwt_list.iter_p (fun (a,b) -> if (a = pmtarget)
			 then Lwt_io.write b (!nick^" sent a PM: "^s^"\n") else Lwt.return()) !sessions
	    | _ -> Lwt_io.write outp "Incorrect Input after '/'")
	| x -> broadcast !nick x in 
    let rec main_loop () =
      Lwt_io.read_line inp >>= handle_input >>= main_loop in
    Lwt.async (fun () -> Lwt.catch main_loop (fun e -> handle_error e !nick inp outp))
  end

let port = if Array.length Sys.argv > 1 then int_of_string (Sys.argv.(1)) else 16384		  
let s = Lwt_io.establish_server (Unix.ADDR_INET(Unix.inet_addr_any, port)) Server.chat_handler	    
let _ = Lwt_main.run (fst (Lwt.wait ()))
let _ = Lwt_io.shutdown_server s (* never executes; you might want to use it in utop, though *)
