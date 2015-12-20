class Vehicle():
    def __init__(self, make, year, model, mileage, price):
        self.__make = make
        self.__model = model
        self.__year = year
        self.__mileage = mileage
        self.__price = price
    def __str__(self):
            return "Make:    %s \nModel:   %s \nYear:    %s \nMileage: %s \nPrice:   %s" \
                   %(self.__make,self.__model,self.__year,self.__mileage,self.__price)
    def Display(self):
        print(self)
    def getmake(self):
        return self.__make
    def setmake(self, make):
        self.__make = make
    def getmodel(self):
        return self.__model
    def setmodel(self, model):
        self.__model = model
    def getyear(self):
        return self.__year
    def setyear(self, year):
        self.__year = year
    def getmileage(self):
        return self.__mileage
    def setmileage(self,mileage):
        self.__mileage = mileage
    def getprice(self):
        return self.__price
    def setprice(self, price):
        self.__price = price

class Car(Vehicle):
    def __init__(self, make, year, model, mileage, price, numdoors):
        super().__init__(make, year, model, mileage, price)
        self.__numdoors = numdoors
    def getnumdoors(self):
        return self.__numdoors
    def setnumdoors(self):
        self.__numdoors = numdoors
    def __str__(self):
        return "Inventory Unit: Car \nMake:    %s \nModel:   %s \nYear:    %s \nMileage: %s \nPrice:   %s \nNumber of Doors: %s" \
               %(super().getmake(), super().getmodel(), super().getyear(), super().getmileage(), super().getprice(), self.__numdoors)
    def Display(self):
        super().Display()

class Truck(Vehicle):
    def __init__(self, make, year, model, mileage, price, drivetype):
        super().__init__(make, year, model, mileage, price)
        self.__drivetype = drivetype   
    def getdrivetype(self):
        return self.__drivetype
    def setdrivetype(self, drivetype):
        self.__drivetype = drivetype
    def __str__(self):
        return "Inventory Unit: Truck \nMake:    %s \nModel:   %s \nYear:    %s \nMileage: %s \nPrice:   %s \nDrive Type: %s" \
               %(super().getmake(), super().getmodel(), super().getyear(), super().getmileage(), super().getprice(), self.__drivetype)
    def Display(self):
        super().Display()

class SUV(Vehicle):
    def __init__(self, make, year, model, mileage, price, passengercap):
        super().__init__(make, year, model, mileage, price)
        self.__passengercap = passengercap
    def getpassengercap(self):
        return self.__passengercap
    def setpassengercap(self, passengercap):
        self.__passengercap = passengercap
    def __str__(self):
        return "Inventory Unit: SUV \nMake:    %s \nModel:   %s \nYear:    %s \nMileage: %s \nPrice:   %s \nPassenger Capacity: %s" \
               %(super().getmake(), super().getmodel(), super().getyear(), super().getmileage(), super().getprice(), self.__passengercap)
    def Display(self):
        super().Display()

class Inventory():
    def __init__(self):
        self.__inventory = []
    def addVehicle(self, vehicle):
        self.__inventory.append(vehicle)
    def Display(self):
        for vehicle in self.__inventory:
            vehicle.Display()
            print()

def main():
    cont = "y"
    appropriatetypes= ["Car", "Truck", "SUV"]
    inventory = Inventory()
    while cont in "Yy":
        vehicletype = str(input("Enter vehicle type (Car, Truck, SUV): "))
        if vehicletype not in appropriatetypes:
            print(vehicletype," Not recognized. Please retype vehicle type exactly as shown.")
            vehicletype = str(input("Enter vehicle type (Car, Truck, SUV): "))
            if vehicletype not in appropriatetypes:
                  print("Vehicletype still not recognized, program terminated.")
        else:
            if vehicletype == appropriatetypes[0]:
                inventoryunit = "Car"
                make = str(input("Enter the Make: "))
                year = str(input("Enter the Year: "))
                model = str(input("Enter the Model: "))
                mileage = input("Enter the Mileage: ")
                price = input("Enter the Price: ")
                numdoors = input("Enter the Number of Doors: ")
                x = Car(make, year, model, mileage, price, numdoors)
                inventory.addVehicle(x)
            elif vehicletype == appropriatetypes[1]:
                inventoryunit = "Truck"
                make = str(input("Enter the Make: "))
                year = str(input("Enter the Year: "))
                model = str(input("Enter the Model: "))
                mileage = input("Enter the Mileage: ")
                price = input("Enter the Price: ")
                drivetype = input("Enter the Drive Type: ")
                y = Truck(make, year, model, mileage, price, drivetype)
                inventory.addVehicle(y)
            elif vehicletype == appropriatetypes[2]:
                inventoryunit = "SUV"
                make = str(input("Enter the Make: "))
                year = str(input("Enter the Year: "))
                model = str(input("Enter the Model: "))
                mileage = input("Enter the Mileage: ")
                price = input("Enter the Price: ")
                passengercap = input("Enter the Passenger Capacity: ")
                z = SUV(make, year, model, mileage, price, passengercap)
                inventory.addVehicle(z)
        cont = str(input("Continue adding inventory (y/n): "))
    inventory.Display()

#c = SUV("Ford", 2006, "Explorer", 10000, 8000, 4)
#c.Display()
main()
