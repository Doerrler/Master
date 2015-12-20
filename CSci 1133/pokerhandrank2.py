import random

def rank(pokerhand):    
    suits= set()
    cvals=[]
    straightnum = 0
    test = 0
    test1 = 0
    handvalue = {'highcard':0,'onepair':1,'twopair':2,'threeofakind':3,'straight':4,'flush':5,'fullhouse':6,'fourofakind':7,'straightflush':8}
    valuelst = []
    for c in hand:
            suits.add(c[1])
    if len(suits) == 1:
        valuelst.append('flush')
    cvals=[]
    for c in hand:
        cvals.append("_23456789TJQKA".index(c[0]))
        cvals.sort()
    print(cvals)
    for card in cvals:
        if cvals.index(card)+1 < len(cvals)-1 and card == cvals[cvals.index(card)+1]:
            if cvals.index(card)+2 < len(cvals) and cvals[cvals.index(card)+1] == cvals[cvals.index(card)+2]:
                if cvals.index(card)+3 < len(cvals) and cvals[cvals.index(card)+2] == cvals[cvals.index(card)+3]:
                    if 'fourofakind' not in valuelst:
                        valuelst.append('fourofakind')
                else:
                    if 'threeofakind' not in valuelst:
                        valuelst.append('threeofakind')
            else:
                if 'onepair' not in valuelst and test == 0:
                    valuelst.append('onepair')
                    match1= cvals.index(card)
                    test += 1
                elif cvals[cvals.index(card)] != cvals[match1] and test ==1:
                    valuelst.append('twopair')
                    valuelst.remove('onepair')
                    test += 1
        if cvals.index(card)+1 < len(cvals) and (card + 1) == cvals[cvals.index(card)+1]:
            straightnum +=1
        if straightnum == 4 and test1 == 0:
            valuelst.append('straight')
            test1 += 1
        else:
            pass
    if 'threeofakind' in valuelst and 'onepair' in valuelst:
        valuelst.append('fullhouse')
        valuelst.remove('threeofakind')
        valuelst.remove('onepair')
    elif 'flush' in valuelst and 'straight' in valuelst:
        valuelst.append('straightflush')
        valuelst.remove('flush')
        valuelst.remove('straight')
    elif valuelst == []:
        valuelst.append('highcard')
    a = valuelst[0]
    return handvalue[a]

deck=[str(a)+str(b) for a in "23456789TJQKA" for b in "CSDH"]
random.shuffle(deck)
for a in range(0,10):
    if len(deck) < 5:
        deck=[str(a)+str(b) for a in "23456789TJQKA" for b in "CSDH"]
        random.shuffle(deck)
    hand= deck[:5]
    print(hand)
    rank(hand)
    for card in hand:    
        deck.remove(card)

#hand= ['TS','2S','8S','KS','9S']
#rank(hand)
