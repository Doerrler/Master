type number = Int of int
	      |Real of float
let a = Int 7
let b = Int 5
let c = Real 2.0
let d = Real 3.0

let to_int x = match x with
  |Int x -> Some x
  |_ -> None 

let to_float x = match x with
  |Real x-> Some x
  |_-> None

let float_of_number x = match x with
  |Int x -> float_of_int x
  |Real x -> x

let (+>) x y = match (x,y) with
  |Int a, Int b -> Int (a+b)
  |Int a , Real b->  Real ( (float_of_int a) +. b )
  |Real a, Int b-> Real (float_of_int b +. a)
  |Real a, Real b -> Real (a+.b)

let ( *> ) x y = match (x,y) with 
  |Int a, Int b -> Int (a*b)
  |Int a , Real b->  Real ( (float_of_int a) *. b )
  |Real a, Int b-> Real (float_of_int b *. a)
  |Real a, Real b -> Real (a*.b)
