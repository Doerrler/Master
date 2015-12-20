def bsearch(lst,key,start,length):
    if key == lst[start]:
        print(start)
    elif key <= lst[start]:
        bsearch(lst,key,start//2,length//2)
    elif key >= lst[start]:
        bsearch(lst,key,((3*start)//2),length//2)


petlist = ['boa','cat','dog','eel','fish','gerbil','hamster','pig','tarantula']
wantedpet = input("Enter a pet: ")
attempts=2
while wantedpet not in petlist and attempts > 0:
        print(wantedpet, "is not in the list!")
        wantedpet = input("Enter a pet: ")
        attempts -= 1
if attempts == 0:
    print("Too many failed attempts...")
else:
    bsearch(petlist,wantedpet,5,9)

