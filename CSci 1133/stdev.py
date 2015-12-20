def squareroot(x):
    guess = x/2
    while abs(guess*guess-x) > 1e-6:
        guess = (guess + (x/guess))/2
    return guess

def stdev(vlist):
    total = 0
    iterator = 0
    b = 0
    print(len(vlist))
    while iterator < len(vlist):
        total += vlist[iterator]
        iterator += 1
    mean = total/(len(vlist)+1)
    print(mean)
    for num2 in vlist:
        a = (num2 - mean)**2
        b += a
    popstdev = squareroot(b//(len(vlist)+1))
    return popstdev

datalist = eval(input("Enter a list of numbers: "))
dlist = []
for value in datalist:
    dlist.append(value)
test= stdev(dlist)
print(test)
