def openfile():
    attempts= 0
    filename= input("Enter a file name: ")
    while attempts < 2:
        try:
            file = open(filename, 'r')
            line = file.readline()
            return file
        except:
            print("File not found.")
            filename= input("Please Re-enter: ")
            attempts +=1
            if attempts > 1:
                print("File cannot be found.")
                return ""

          
file= openfile()

