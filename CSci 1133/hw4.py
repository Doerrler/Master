n = eval(input("Enter a number > 1: "))
if n <= 1:
    print("Error, value given is not greater than 1.")
    quit()
nlist = list(range(2,n+1))
iterator = 0
while iterator < len(nlist):
    m = nlist[iterator]
    value = 2
    firstmultiple = (m * value)
    while (firstmultiple not in nlist) and (value <= m):
        value += 1
        firstmultiple = (m * value)
    while (firstmultiple in nlist):
        nlist.remove(firstmultiple)
        value += 1
        firstmultiple = (m * value)
        if firstmultiple not in nlist:
            value += 1
            firstmultiple = (m * value)
    iterator += 1
if iterator > 0:
    print(nlist)
    
    
