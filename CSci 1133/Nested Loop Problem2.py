n = int(input("Enter an interger > 1: "))
iterator = 1
iterator1 = 1
while iterator <= n:
    while iterator1 <= n:
        a = iterator * iterator1
        print(a, end=" ")
        iterator1 += 1
    iterator += 1
    iterator1 = 1
    print()
