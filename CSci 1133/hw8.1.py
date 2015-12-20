def squareroot(x):
    guess = x/2
    while abs(guess*guess-x) > 1e-4:
        guess = (guess + (x/guess))/2
    return guess


def stdev(vlist):
    total = 0
    iterator = 0
    b = 0
    while iterator < len(vlist):
        total += eval(vlist[iterator])
        iterator += 1
    mean = total/(len(vlist)+1)
    for num2 in vlist:
        a = (eval(num2) - mean)**2
        b += a
    popstdev = squareroot(b/(len(vlist)+1))
    return popstdev


def median(vlist):
    mlist = vlist
    iterator = 0
    while iterator < len(mlist):
        mlist[iterator] = int(mlist[iterator])
        iterator +=1
    mlist.sort()
    a = len(mlist)
    if len(mlist)/2 == len(mlist)//2:
        median= ((mlist[a//2])+(mlist[(a//2)-1]))/2
    else:
        median= float(mlist[a//2])
    return median


def openfile():
    attempts= 0
    filename= input("Enter a file name: ")
    while attempts <= 2:
        try:
            file = open(filename, 'r')
            return file
        except:
            attempts +=1
            if attempts == 3:
                print("File cannot be found.")
                return ""
            print("File not found.")
            filename= input("Please Re-enter: ")


def readheader(ifile):
    line = ifile.readline()
    header = line.split(",")
    header[len(header)-1] = header[len(header)-1].replace("\n","")
    #header list established
    lines = ifile.readlines()
    lstdata = lines[2].split(",")
    lstdata[len(lstdata)-1] = lstdata[len(lstdata)-1].replace("\n","")
    #data list established
    return header,lstdata


def fieldindex(ifile):
    attempts= 0
    column = 0
    lowerheadlist= []
    headerfile= ifile
    headerlst,datalist = readheader(headerfile)
    fieldname1= input("Enter a fieldname: ")
    fieldname= fieldname1
    fieldname= fieldname.lower().replace(" ","")
    for element in headerlst:
        lowerheadlist.append(element.lower().replace(" ",""))
    while attempts <= 2:
        for word in lowerheadlist:
            if fieldname == word:
                iterator = 0
                while iterator < len(headerlst):
                    if fieldname == lowerheadlist[iterator]:
                        column = iterator
                        return column,headerlst[column]
                        break
                    else:
                        iterator +=1
            elif fieldname not in lowerheadlist:
                attempts +=1
                if attempts > 2:
                    print("Too many failed attempts. Program Terminated.")
                    return -1,""
                print(fieldname1, "does not match any field.")
                fieldname1= input("Please Re-enter: ")
                fieldname= fieldname1
                fieldname= fieldname.lower().replace(" ","")
                break
            else:
                pass
    if attempts > 2:
        print("Too many failed attempts. Program Terminated.")
        return -1,""



datalist= []
chosenlist= []
sumchosen = 0
testingnum = 0
file= openfile()
if file == "":
    indx = -1
else:
    indx,fieldname= fieldindex(file)
if indx == -1:
    pass
else:
    file.seek(0)
    line1= file.readline()
    lines= file.readlines()
    for data in lines:
        datalist.append(data.split(","))
    for row in datalist:
        row[len(row)-1] = row[len(row)-1].replace("\n","")
        chosenlist.append(row[indx])
    try:
        for element in chosenlist:
            element = eval(element)
            sumchosen += element
    except NameError:
        print(fieldname, "is not a numeric field.")
        print("Program Terminated")
    if sumchosen != 0:
        chosenlist2= chosenlist
        numscores= len(chosenlist)
        mean= sumchosen/numscores
        standarddev= stdev(chosenlist)
        medianval= median(chosenlist2)
        mini= min(chosenlist)
        maxi= max(chosenlist)
        print()
        print(fieldname, "Summary Data")
        print("-----------------------")
        print("   Number of scores: ",numscores)
        print("   Mean: ","%.2f"%mean)
        print("   Standard Deviation: ","%.2f"%standarddev)
        print("   Median: ","%.2f"%medianval)
        print("   Min: ",mini)
        print("   Max: ",maxi)
        print()

#fix fieldname print numeric field error DONE
#standard deviation off by .3 on Midterm 1****
#fix third filename/fieldname errors DONE
#test with other excel file





