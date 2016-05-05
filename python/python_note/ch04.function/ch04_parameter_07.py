test = lambda a, b = 0, *args, **kwargs: sum([a*10, b*10] + list(args) + kwargs.values())

# 1 + 2 + 3 + 4 + 5 + 6 = 10 + 11 = 21
# 1*10 + 2*10 + 3 + 4 + 5 + 6 = 30 + 18 = 48
print test(1, *[2,3,4], **{"x": 5, "y": 6})
