def openfile():
    attempts= 0
    filename= input("Enter a file name: ")
    while attempts < 2:
        try:
            file = open(filename, 'r')
            return file
        except:
            print("File not found.")
            filename= input("Please Re-enter: ")
            attempts +=1
            if attempts > 1:
                print("File cannot be found.")
                return ""


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
    headerlst,datalist = readheader(ifile)
    fieldname1= input("Enter a fieldname: ")
    fieldname= fieldname1
    fieldname= fieldname.lower().replace(" ","")
    for element in headerlst:
        lowerheadlist.append(element.lower().replace(" ",""))
    while attempts < 2:
        for word in lowerheadlist:
            if fieldname == word:
                iterator = 0
                while iterator < len(headerlst):
                    if fieldname == lowerheadlist[iterator]:
                        column = iterator
                        if datalist[column].isdigit():
                            print(column, headerlst[column])
                            attempts = 86
                            break
    #only Bad Name get 3 tries, Nonnumeric feilds dont work!!!
                        else:
                            print(fieldname1, "is not a numeric field.")
                            fieldname1= input("Please Re-enter: ")
                            fieldname= fieldname1
                            fieldname= fieldname.lower().replace(" ","")
                            attempts +=1
                            break
                    else:
                        iterator +=1
            elif fieldname not in lowerheadlist:
                print(fieldname1, "does not match any field.")
                fieldname1= input("Please Re-enter: ")
                fieldname= fieldname1
                fieldname= fieldname.lower().replace(" ","")
                attempts +=1
                break
            else:
                pass
    if attempts >= 2 and attempts !=86:
        print("Too many failed attempts. Program Terminated.")
        return -1,""


fieldindex(openfile())
