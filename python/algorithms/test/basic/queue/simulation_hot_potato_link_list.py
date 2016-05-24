class Node:
    def __init__(self, value):
        self.value = value
        self.up = None
        self.down = None

    def up(self, up):
        self.up = up

    def down(self, down):
        self.down = down

    def __str__(self):
        return self.value


class LinkedList:
    def __init__(self):
        a = Node('A')
        b = Node('B')
        c = Node('C')
        d = Node('D')
        e = Node('E')
        f = Node('F')

        a.up = f
        a.down = b

        b.up = a
        b.down = c

        c.up = b
        c.down = d

        d.up = c
        d.down = e

        e.up = d
        e.down = f

        f.up = e
        f.down = a

        self.head = a

    def go(self, num):
        # print
        xs = []
        head = self.head
        xs.append(head)
        while head.down != self.head:
            head = head.down
            xs.append(head)
        print(' '.join([str(x) for x in xs]))

        #
        if self.head.down == self.head:
            return self.head

        #
        for i in range(num):
            self.head = self.head.down

        #
        print('evict ' + str(self.head))

        #
        self.head.up.down = self.head.down
        self.head.down.up = self.head.up    # forget!
        self.head = self.head.down
        return self.go(num)

linkList = LinkedList()
print('the result is:' + str(linkList.go(3)))
