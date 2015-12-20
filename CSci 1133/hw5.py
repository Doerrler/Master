def gameState(movesmade):
    a=0
    b=0
    c=0
    while a < 3:
        if (movesmade[a][0]==movesmade[a][1]==movesmade[a][2] and movesmade[a][1]!=""):
            return (movesmade[a][0], 'Wins')
            quit()
        a += 1
    while b < 3:
        if (movesmade[0][b]==movesmade[1][b]==movesmade[2][b] and movesmade[1][b]!=""):
            return (movesmade[1][0], 'Wins')
            quit()
        b += 1
    if (movesmade[0][0]==movesmade[1][1]==movesmade[2][2] and movesmade[1][1] != "") or (movesmade[0][2]==movesmade[1][1]==movesmade[2][0] and movesmade[1][1] != ""):
            return (movesmade[1][1], 'Wins')
            quit()
    elif movesmade[c][0]!='' and movesmade[c][1] != '' and movesmade[c][2] != '':
            c += 1
            if movesmade[c][0]!='' and movesmade[c][1] != '' and movesmade[c][2] != '':
                c += 1
                if movesmade[c][0]!='' and movesmade[c][1] != '' and movesmade[c][2] != '':
                    return ('Draw')
    else:
        return ("No Win, No Draw")
    
