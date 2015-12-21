(* hostinfo, the "name" of a computer connected to the Internet *)
type hostinfo = IP of int*int*int*int | DNSName of string

(* Here's where your definition of tld goes: *)
let tld hinfo = match hinfo with
  |IP (a1,a2,a3,a4) -> None
  |DNSName n -> Some (String.sub n ((String.rindex n '.')+1) 
			((String.length n) - ((String.rindex n '.') + 1)));;

