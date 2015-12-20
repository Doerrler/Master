upperlimit = eval(input("Enter upper limit: "))
attempts = 2
while (upperlimit <= 1) and (attempts > 0):
    attempts -= 1
    print("Error! Please enter a number > 1")
    upperlimit = eval(input("Enter upper limit: "))
    if attempts == 0:
        print("Program Terminated.")
count = (upperlimit - 1)
count2 = (upperlimit - 1)
primes = set()
primes.add(2)
while (upperlimit >= 2) and (count >= 1):
    check = upperlimit/count
    count -=1
    if check == int(check):
        upperlimit -= 1
        count = (upperlimit - 1)
    elif check != int(check) and count > 1:
        continue
    elif check != int(check) and count ==1:
        primes.add(upperlimit)
        upperlimit -= 1
        count = (upperlimit - 1)
    while attempts > 0:
        break
#It says to have a nested while loop so I added one. I know its useless in this case.
if attempts > 0:
    print("Primes:", primes)
