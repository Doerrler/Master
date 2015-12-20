def triangle(num):
    if num <= 1:
        return 1
    else:
        return triangle(num-1) + num

#not tail-end recursion
