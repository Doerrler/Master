def imatrix(n):
    matrixlist = []
    rownum= 0
    while rownum <n:
        alist = [0] * n
        alist[rownum] = 1
        matrixlist.append(alist)
        rownum +=1
    return matrixlist

print(imatrix(5))

