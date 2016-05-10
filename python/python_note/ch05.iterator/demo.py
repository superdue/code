from itertools import *

# chain
print(list(chain(xrange(3), "abc")))

# combinations
print(list(combinations("abcd", 2)))
print(list(combinations(xrange(4), 2)))
print(list(combinations_with_replacement("abc", 2)))

# compress
print(list(compress("abcde", [1, 0, 1, 1, 0])))

# count


def c():
    for x in count(10, step=2):
        print x
        if x > 17: break

c()

# cycle
for i, x in enumerate(cycle("abc")):
    print x
    if i > 7: break

# dropwhile
print(list(dropwhile(lambda i: i < 4, [2, 1, 4, 1, 3])))
print(list(takewhile(lambda i: i < 4, [2, 1, 4, 1, 3])))

# groupby
print([list(k) for k, g in groupby('AAABBBCCDAABBCCDD')])
print([list(g) for k, g in groupby('AAAABBBCCDAABBCCDD')])

# ifilter

print(list(ifilter(lambda x: x % 2, xrange(10))))
print(list(ifilterfalse(lambda x: x % 2, xrange(10))))

# imap
print(list(imap(lambda x, y: x + y, (2, 3, 10), (5, 2, 3))))

# islice
print(list(islice(xrange(10), 3)))
print(list(islice(xrange(10), 3, 5)))
print(list(islice(xrange(10), 3, 9, 2)))

# izip
print(list(izip("abc", [1, 2])))
print(list(izip_longest("abc", [1, 2], fillvalue=0)))

# permutations
print(list(permutations("abc", 2)))

# product
print(list(product("abc", [0, 1])))

# repeat
print(list(repeat("a", 3)))

# starmap
print(list(starmap(lambda x, y: x + y, [(1, 2), (10, 20)])))

# tee
for it in tee(xrange(5), 3):
    print list(it)

