P105:
Fields are also known as `instance variables`.
Public is Scala's default access level.

P106:
In the absence of any explicit return statement, a Scala method returns the last value computed by the method.

P107:
Another shorthand for methods is that you can leave off the curly braces if a method computes only a single result expression.

P111:
If you are a Java programmer, one way to think of singleton objects is as the home for any static methods you might have written in Java.

singleton objects extend a superclass and can mix in traits. Given each singleton object is an instance of its superclasses and mixed-in traits.

a singleton object is initialized the first time some code accesses it.

P112:
Each singleton object is implemented as an instance of a `synthetic class` referenced from a static variable, so they have the same initialization semantics as Java statics`.

P115:
So you should inherit from `Application` only when your program is relatively simple and single-threaded.

P137:
"bills !*&^%~ code!" => "(bills.!*&^%~(code)).!()"

P141:
NOTE: Immutable object trade-offs

P157:
but please bear in mind that with power comes responsibility.

P163:
because the wile loop results in no value, it is often left out of pure functional languages. Such languages have expressions, not loops. Scala includes the while loop nonetheless, because somethimes an imperative soluation can be more readable, especially to programmers with a predominantly imperative background.
...
In general, we recommend you challenge while loops in your code in the same way you challenge vars. In fact, while loops and vars often go hand in hand.

P190:
The Scala compiler knows that x must be an integer, because it sees that you are immediately using the function to filter a list of integers (referred to by someNumbers). This is called target typing, because the targeted usage of an expression—in this case an argument to someNumbers.filter()—is allowed to influence the typing of that expression—in this case to determine the type of the x parameter. The precise details of target typing are not important to study. You can simply start by writing a function literal without the argument type, and, if the compiler gets confused, add in the type. Over time you’ll get a feel for which situations the compiler can and cannot puzzle out.

A second way to remove useless characters is to leave out parentheses around a parameter whose type is inferred. In the previous example, the parentheses around x are unnecessary:

P194:
If you are writing a partially applied function expression in which you
leave off all parameters, such as println _ or sum _, you can express it more concisely by leaving off the underscore if a function is required at that point in the code. For example, instead of printing out each of the numbers in someNumbers (defined on page 189) like this:

P197:
The function value (the object) that’s created at runtime from this function literal is called a closure. 

P267:
Beware that the Ordered trait does not define equals for you, because it is unable to do so. The problem is that implementing equals in terms of compare requires checking the type of the passed object, and because of type erasure, Ordered itself cannot do this test. Thus, you need to define equals yourself, even if you inherit Ordered. You'll find out how to go about this in Chapter 30.

use of traits:
1. turning a thin interface into a rich one.
2. providing stackable modifications to classes.

P273:
As hinted previously, the answer is linearization, When you instantiate a class with new, scala takes the class and all of its inherited classes adn traits and puts them in a single, linear order. Then whenever you call super inside one of those classes, the invoked method is the next one up the chain. If all of the methods but the last call super, the net result is stackable behavior.

P276:
If efficiency is very important, lean towards using a class. Most Java runtimes make a virtual method invocation of a class member a faster operation than an interface method invocation.

They are a fundamental unit of code that is reusable through inheritance. Because of this nature, many experienced Scala programmers start with traits when they are at the early stages of implementation. Each trait can hold less than an entire concept, a mere fragment of a concept. As the design solidifies, the fragments can be combined into more complete concepts through trait mixin.

P288:
Access to protected members is also a bit more restrictive than in Java. In Scala, a protected member is only accessible from subclasses of the class in which the member is defined. In Java such accesses are also possible from other classes in the same package. 


P424:

没看懂：This means you can "charge" each of these `n` tail operations with one n'th of the complexity of `mirror`, which means a constant amount of work. Assuming that head, tail, and enqueue operations appear with abount the same frequency, the amoritized complexity is hence constant operation.

P427:
Private constructors and private members are one way to hide the initialization and representation of a class. Another, more radical way is to hide the class itself and only export a trait that reveals the public interface of the class.

P438:
This observation is also the main reason that Scala prefers declaration-site variance over use-site variance as it is found in Java's wildcards.

This is called the `Liskov Substitution Principle`.

P486:
That's why in Scala Int values can be stored in variables of type Double. There's no special rule in the type system for this; it's just an implicit conversion that gets applied.

P483:
In fact, it is common for libraries to include a Preamble object including a number of useful implicit conversions.

P509:
One shortcoming of this program pattern is that it is not tail recursive. Note that the recursive call to `incAll` above occurs inside a :: operation. Therefore each recursive call requires a new stack frame. On today's virtual machines this means that you cannot apply `incAll` to lists of much more than aboutn 30,000 to 50,000 elements. This is a pity.

P514:
The design of Scala's List and ListBuffer is quite similar to what's done in Java's pair of classes String and StringBuffer. 
Usually, :: lends itself well to recursive algorithms in the divide-and-conquer style. List buffers are often used in a more traditional loop-based style.

P550:
Each Seq trait has two subtraits, LinearSeq and IndexedSeq. These do not add any new operations, but each offers different performance characteristics. A linear sequence has efficient head and tail opeartions, whereas an indexed sequence has efficient apply, length, and (if mutable) update operations. List is a frequently used linear sequence, as is Stream. Two freequently used indexed sequences are Array and ArrayBuffer. The Vector class provides an interesting compromise between indexed and linear access.
 It has both effectively constant time indexing overhead and constant time linear access overhead. Because if this, vectors are a good foundation for mixed access patterns where both indexed and linear accesses are used.

P555:
The current default implementation of a mutable set uses a hash table to store the set's elements. The default implementation of an immutable set uses a representation that adapts to the number of elements of the set.

P566:
Vection的实现原理

P568:
Immutable stacks are used rarely in Scala programs because their functionality is subsumed by lists: A push on an immutable stack is the same as a :: on a list, and a pop on a stack is the same a tail on a list.

P670:
One approach to solving this problem is `dependency injection`, a technique supported on the Java platform by framework such as Spring and Guice, which are popular in the enterprise Java community. Spring for example, essentially allows you to represent the interface of a module as a Java interface and implementations of the module as Java classes.











































