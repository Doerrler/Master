def bsearch(lst,key,start,length):
    print(start)
    if key == start:
        print(length//2)
    elif key <= start:
        return bsearch(lst,key,lst[length//4],length//2)
    elif key >= start:
        return bsearch(lst,key,lst[length//],length)





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
    bsearch(petlist,wantedpet,petlist[int(len(petlist)/2)],len(petlist))
