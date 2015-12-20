m = eval(input("Enter birthday month: "))
d = eval(input("Enter birthday day: "))
if m <= 0 or m >= 13 or d <= 0 or d >=32:
    print("Error! Invalid date, please try again.")
elif (m == 4 or m == 6 or m == 9 or m == 11 and d >= 31):
    print("Error! Invalid date, please try again.")
elif (m == 2 and d >= 30):
    print("Error! Invalid date, please try again.")
elif (1 == m and d >= 20) or (m == 2 and d <= 18):
    print("Aquarius")
elif (2 == m and d >= 19) or (m == 3 and d <= 20):
    print("Pisces")
elif (3 == m and d >= 21) or (m == 4 and d <= 19):
    print("Aries")
elif (4 == m and d >= 20) or (m == 5 and d <= 20):
    print("Taurus")
elif (5 == m and d >= 21) or (m == 6 and d <= 20):
    print("Gemini")
elif (6 == m and d >= 21) or (m == 7 and d <= 22):
    print("Cancer")
elif (7 == m and d >= 23) or (m == 8 and d <= 22):
    print("Leo")
elif (8 == m and d >= 23) or (m == 9 and d <= 22):
    print("Virgo")
elif (9 == m and d >= 23) or (m == 10 and d <= 22):
    print("Libra")
elif (10 == m and d >= 23) or (m == 11 and d <= 21):
    print("Scorpio")
elif (11 == m and d >= 22) or (m == 12 and d <= 21):
    print("Sagittarius")
elif (12 == m and d >= 22) or (m == 1 and d <= 20):
    print("Capricorn")
