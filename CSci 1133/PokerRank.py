import random

def rank(pokerhand):    
    suits= set()
    cvals=[]
    straightnum = 0
    possiblelist= [highcard,onepair,twopair,threeofakind,straight,flush,fullhouse,fourofakind,straightflush]
    for item in possiblelist:
        item= 'false'
    for c in hand:
            suits.add(c[1])
    if len(suits) == 1:
        flush = 'true'
    else:
        flush = 'false'
    cvals=[]
    for c in hand:
        cvals.append("_23456789TJQKA".index(c[0]))
        cvals.sort()
    print(cvals)
    for card in cvals:
        if card == cvals[cvals.index(card)+1] and cvals.index(card)+1 <= len(cvals):
            if cvals[cvals.index(card)+1] == cvals[cvals.index(card)+2] and cvals.index(card)+2 <= len(cvals):
                if cvals[cvals.index(card)+2] == cvals[cvals.index(card)+3] and cvals.index(card)+3 <= len(cvals):
                    fourofakind = 'true'
                else:
                    threeofakind = 'true'
            else:
                onepair = 'true'
        while (card + 1) == cvals[cvals.index(card)+1] and cvals.index(card)+1 <= len(cvals):
            straightnum +=1
        if straightnum == 5:
            straight = 'true'
        else:
            pass
    if threeofakind == 'true' and onepair == 'true':
        fullhouse = 'true'
        threeofakind = 'false'
        onepair = 'false'
    for item in possiblelist:
        if item == 'true':
            print(item)
        #need to make sure it isnt counting one twice, 3ofkind has a pair
    if flush == 'true' and straight == 'true':
        straightflush = 'true'
        flush = 'false'
        straight = 'false'

deck=[str(a)+str(b) for a in "23456789TJQKA" for b in "CSDH"]
random.shuffle(deck)
hand= deck[:5]
print(hand)
rank(hand)
