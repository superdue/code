import sys, importlib

m = importlib.import_module("ch06.add")
print(m)
print(sys.modules.get("ch06.add"))
print("ch06.add" in globals())
print(importlib.import_module(".add", "ch06"))
