def median(vlist):
    mlist = vlist
    mlist.sort()
    print(mlist)
    a = len(mlist)
    print(a)
    if len(mlist)/2 == len(mlist)//2:
        median= ((mlist[a//2]+mlist[(a//2)-1])/2)
    else:
        median= mlist[a//2]
    return median

datalist = eval(input("Enter a list of numbers: "))
dlist = []
for value in datalist:
    dlist.append(value)
test= median(dlist)
print(test)
