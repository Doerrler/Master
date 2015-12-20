def findvowel(word):
    vowels= ['a','e','i','o','u','A','E','I','O','U']
    dashtest= 0
    if word[0] in vowels:
        word += 'w'  
    for l in word:
        if l not in vowels and l != "-":
            word += l
            word = word[1:]
        if l not in vowels and l == "-":
            word += l
            word = word[1:]
            dashtest= 1
        elif l in vowels:
            if dashtest != 0:
                word = word.replace("-","")
                word = "-" + word
            word += "ay"
            return word
            break
    for a in vowels:
         if dashtest != 0 and a not in word:
            word.remove("-")
            word += "-"
            word = word[1:]
            word += "ay"
            return word
         elif a not in word:
            word += "ay"
            return word

def igpay(phrase):
    phrase= phrase.replace("-"," -")
    wordlist= phrase.split(" ")
    iterator= 0
    iterator2= 0
    iterator3= 0
    iterator4= 0
    finaliterator= 0
    punctuation= [',','.','?','!']
    newlist= []
    while iterator < len(wordlist):
        newlist.append(findvowel(wordlist[iterator]))
        iterator += 1
    while iterator3 < len(newlist):
        newlist[iterator3]= newlist[iterator3].lower()
        iterator3 += 1
    while iterator2 < len(wordlist):
        if wordlist[iterator2][0].isupper():
            newlist[iterator2]= newlist[iterator2][0].upper() + newlist[iterator2][1:]
            iterator2 += 1
        else:
            iterator2 += 1
    for words in newlist:
        for p in punctuation:
            if p in words:
                a = newlist.index(words)
                words = words.replace(words[words.index(p)],"")
                words = words + (p)
                newlist[a] = words
    if "-" in newlist:
        indexdash = newlist.index("-")
        newlist = newlist.remove(newlist.index(indexdash-1))
    print("Pig Latin: ", end="")
    while finaliterator < len(newlist):
        if finaliterator+1 <= len(newlist)-1 and "-" in newlist[finaliterator+1]:
                print(newlist[finaliterator], end="")
                finaliterator += 1
        else:
            print(newlist[finaliterator], end=" ")
            finaliterator += 1


w = input("Enter a phrase in English: ")
igpay(w)
print()
cont = input("Continue? (y/n): ")
cont = cont.lower()
while cont == "y":
    w = input("Enter a phrase in English: ")
    igpay(w)
    print()
    cont = input("Continue? (y/n): ")
    cont = cont.lower()

