import random

class Carddeck:
    def __init__(self,deck,cardindex=0):
        self.__deck = deck
        self.__index = 0
    def __repr__(self):
        return str(self.__deck)
    def shuffle(self, deck):
        random.shuffle(self.__deck)
        self.__index = 0
    def dealcard(self):
        return self.__deck[index]
    def dealhand(self):
        Pokerhand = self.__deck[:5]
        return Pokerhand

class Pokerhand():
    def __init__(self, pokerhand):
        self.__Pokerhand = [pokerhand]
    def __repr__(self):
        return str(self.__Pokerhand)
    def rank():
        suits= set()
        cvals=[]
        straightnum = 0
        test = 0
        test1 = 0
        handvalue = {'highcard':0,'onepair':1,'twopair':2,'threeofakind':3,'straight':4,'flush':5,'fullhouse':6,'fourofakind':7,'straightflush':8}
        valuelst = []
        for c in Pokerhand:
                suits.add(c[1])
        if len(suits) == 1:
            valuelst.append('flush')
        cvals=[]
        for c in hand:
            cvals.append("_23456789TJQKA".index(c[0]))
        if 13 in cvals:
            cvals.append(0)
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

deck =[str(a)+str(b) for a in "23456789TJQKA" for b in "CSDH"]
c = Carddeck(deck,0)
c.shuffle(deck)
valuelst= []
handvalue = {'High card':0,'One pair':1,'Two pair':2,'Three of a kind':3,'Straight':4,\
             'Flush':5,'Fullhouse':6,'Four of a kind':7,'Straight flush':8}
phand= Pokerhand(c.dealhand())
print(phand)
for num in range(0,100):
    if len(phand) < 5:
        while c.dealcard != "":
            phand += c.dealcard
        c = Cardeck
        c.shuffle()
        while len(phand) < 5:
            phand += c.dealcard
        print(phand)
    else:
        Pokerhand= c.dealhand()
        print(Pokerhand)
    valuelst.append(Pokerhand.rank())
    for card in Pokerhand:    
        c.remove(card)
print(valuelst)
for handval in handvalue:
    print(handval.keys(),":",valuelst.count(handval.values()))
print("Straight Flush   :",valuelst.count(8))
print("Four of a kind   :",valuelst.count(7))
print("Full house       :",valuelst.count(6))
print("Flush            :",valuelst.count(5))
print("Straight         :",valuelst.count(4))
print("Three of a kind  :",valuelst.count(3))
print("Two pair         :",valuelst.count(2))
print("One pair         :",valuelst.count(1))
print("High Card        :",valuelst.count(0))



