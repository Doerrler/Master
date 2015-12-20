class A():
    def __init__(self, var1, var2):
        self.__var1 = var1
        self.__var2 = var2
    def getvar1(self):
        return self.__var1
    def __repr__(self):
        return "%s, %s" %(self.__var1,self.__var2)
    def Display(self):
        print(self)

class B(A):
    def __init__(self, var1, var2, var3):
        super().__init__(var1, var2)
        self.__var3 = var3
    def __repr__(self):
        return "%s, %s, %s" %(super(A, self).getvar1(),self.__var3)
    def Display(self):
        super(A, self).Display()

c = A('test', 'test2')
c.Display()
c = B('test', 'test2', 'test3')
c.Display()
