import random

class Carddeck:
    def __init__(self):
        self.cards = []
        self.__value =['2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A']
        self.__suit = ['C', 'S', 'D', 'H']
        for values in self.__value:
            for suits in self.__suit:
                card = Card(suits, value)
                self.deck.append(card)
        self.deck = [str(a)+str(b) for a in "23456789TJQKA" for b in "CSDH"]
        
                
    def __repr__(self):
        return str(self.card)
    def shuffle(self):
        return random.shuffle(self.__card)
    def dealcard(self):
        return self.card[0]
    def dealhand(self):
        Pokerhand = self.card[:5]
        return Pokerhand

class Pokerhand:
    def __init__(self, pokerhand):
        self.Pokerhand = [pokerhand]
    def __repr__():
        return str(self)
    def rank():
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


c = Carddeck
c.shuffle(c)
valuelst= []
handvalue = {'High card':0,'One pair':1,'Two pair':2,'Three of a kind':3,'Straight':4,\
             'Flush':5,'Fullhouse':6,'Four of a kind':7,'Straight flush':8}
for num in range(0,100):
    if len(c) < 5:
        while c.dealcard != "":
            Pokerhand += c.dealcard
        c = Cardeck
        c.shuffle()
        while len(Pokerhand) < 5:
            Pokerhand += c.dealcard
        print(Pokerhand)
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



