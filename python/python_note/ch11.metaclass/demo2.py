Data = type("Data", (object,), {"x": 1})  # class 的实际行为
print(Data.__class__)
print(Data.__base__)
print(Data.x)