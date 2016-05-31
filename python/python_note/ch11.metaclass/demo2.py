Data = type("Data", (object,), {"x": 1})
print(Data.__class__)
print(Data.__base__)
print(Data.x)