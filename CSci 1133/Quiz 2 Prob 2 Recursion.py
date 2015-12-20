def reverse(alist,first,last):
    if alist[first+1] == alist[-1]:
        return alist
    else:
        a = alist[first]
        b = alist[last]
        alist[first]=b
        alist[last]=a
        return reverse(alist,first+1,last-1)

print(reverse([1,3,4,5,'a'],0,-1))
