def test():
    class Data(object): pass
    return Data

Data = test()

print(Data.__name__)
print(type(Data))

print(Data())
