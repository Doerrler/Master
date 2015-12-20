def getcapital(name,capitals):
    iterator = 0
    while iterator <= len(capitals):
        if capitals[iterator][0] == name:
            capname = capitals[iterator][1]
            return capname
        else:
            iterator += 1
    while iterator > len(capitals):
        notfound = ""
        return notfound

caplist = [['Minnesota','St. Paul'],['Iowa','Des Moines'],['Wisconsin','Madison']]
print(getcapital('Minnesota',caplist))
print(getcapital('Iowa',caplist))
