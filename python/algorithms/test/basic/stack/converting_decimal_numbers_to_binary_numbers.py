from pythonds.basic.stack import Stack

def divideBy2(decNumber):
    remstack = Stack()

    while decNumber > 0:
        rem = decNumber % 2
        remstack.push(rem)
        decNumber = decNumber // 2

    binString = ''
    while not remstack.isEmpty():
        binString = binString + str(remstack.pop())

    return binString


def baseConverter(decNumber, base):
    digits = "0123456789ABCDEF"

    remstack = Stack()

    while decNumber > 0:
        rem = decNumber % base
        remstack.push(rem)
        decNumber = decNumber // base

    newString = ''
    while not remstack.isEmpty():
        newString = newString + digits[remstack.pop()]

    return newString


assert(divideBy2(42) == '101010')
assert(baseConverter(25, 2) == '11001')
assert(baseConverter(25, 16) == '19')