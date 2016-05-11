import sys

print(sys.modules.get("zlib"))

print(__import__("zlib"))
print(sys.modules.get("zlib"))
print("zlib" in globals())