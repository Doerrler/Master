def fieldindex(ifile):
    attempts= 0
    validentry = false
    validlist = ['midterm1','midterm2','final']
    fieldname= input("Enter a fieldname: ")
    fieldname= fieldname.lower().replace(" ","")
    print(fieldname)
    while validentry == false:
        try:
            if fieldname in validlist:
                validentry = true
        except IOError:
            while attempts < 2 and fieldname in ifile:
                print(fieldname, "is not a numeric field.")
                fieldlname= input("Please Re-enter:5")
                attempts +=1
            while attempts < 2 and fieldname not in ifile:
                print(fieldname, "does not match any field.")
                fieldname= input("Please Re-enter: ")
                attempts +=1
            if attempts >= 2:
                print("Program Terminated...")
                return -1,""
    ifile.find(fieldname)
    ordinal= ifile[fieldname]
    print(ordinal)
    #d = {'midterm1':'Midterm 1','midterm2':'Midterm 2','final':'Final'}
    #return ordinal, fieldname

fieldindex(hw8.csv)
