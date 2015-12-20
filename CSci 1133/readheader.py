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
    print(header)
    lines = ifile.readlines()
    lstdata = lines[2].split(",")
    lstdata[len(lstdata)-1] = lstdata[len(lstdata)-1].replace("\n","")
    print(lstdata)
    #check relative position

readheader(openfile())
