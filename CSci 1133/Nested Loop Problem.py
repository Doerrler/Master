n = int(input("Enter an interger > 0: "))
iterator1 = 1
iterator2 = 1
while iterator1 < n+1:
    while iterator2 < n+1:
        x = iterator1 * iterator2
        print(x,end=" ")
        iterator2 +=1
    while iterator2 >= n+1:
        iterator2 = 1
        iterator1 +=1
        print()
