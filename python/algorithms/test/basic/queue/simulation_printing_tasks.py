# To model this situation we need to use some probabilities.
# students may print a paper from 1 to 20 pages in length.
# This means that there is equal chance of any length from 1 to 20 appearing.

# If there are 10 students in the lab and each prints twice, then there are 20 print tasks per hour on average.
# What is the change that at any given second, a print task is going to be created?

# For every second we can simulate the chance that a print task occurs by generating a random number between 1 and 180
# inclusive. If the number is 180, we say a task has been created.

from pythonds.queue import Queue
import random

class Printer:
    def __int__(self, ppm):
        self.pagerate = ppm
        self.currentTask = None
        self.timeRemaining = 0

    def tick(self):
        if self.currentTask != None:
            self.timeRemaining = self.timeRemaining - 1
            if self.timeRemaining <= 0:
                self.currentTask = None

    def busy(self):
        return self.currentTask != None

    def startNext(self, newtask):
        self.currentTask = newtask
        self.timeRemaining = newtask.getPages() * 60 / self.pagerate

class Task:
    def __init__(self, time):
        self.timestamp = time
        self.pages = random.randrange(1, 21)

    def waitTime(self, currenttime):
        return currenttime - self.timestamp

def simulation(numSeconds, pagesPerMinute):

    labprinter = Printer(pagesPerMinute)
    printQueue = Queue()
    waitingtimes = []

    for currentSecond in range(numSeconds):

        if newPrintTask():
            task = Task(currentSecond)
            printQueue.enqueue(task)

        if (not labprinter.busy()) and (not printQueue.isEmpty()):
            nexttask = printQueue.dequeue()
            waitingtimes.append(nexttask.waitTime(currentSecond))
            labprinter.startNext(nexttask)

        labprinter.tick()

    averageWait = sum(waitingtimes)/len(waitingtimes)
    print("Average Wait %6.2f secs %3d tasks remaining." % (averageWait, printQueue.size()))

def newPrintTask():
    num = random.randrange(1, 181)
    return num == 180

# main
for i in range(10):
    simulation(3600, 5)
