A Little Java A Few Patterns

ch01. Modern Toys

The First Bit of Advice
When specifying a collection of data, use abstract classes for datatypes and extended classes for variants.

ch02. Methods to Our Madness

The Second Bit of Advice
When writing a function over a datatype, place a method in each of the variants that make up the datatype. If a field of a variant belongs to the same datatype, the method may call the corresponding method of the field in computing the function.

ch03. What's New?

The Third Bit of Advice
When writing a function that returns values of a datatype, use `new` to create these values.

ch04. Come to Our Carousel

The Fourth Bit of Advice
When writing several functions for the same self-referential datatype, use visitor protocols so that all methods for a function can be found in a single class.

ch05. Objects Are People, Too

ch06. Boring Protocols

       Pie     ---accept--- |PieVisitor
    /       \              /         /
 Bot        Top          Rem      Subst
 
The Sixth Bit of Advice
When the additional consumed values change for a self-referential use of a visitor, don't forget to create a new visitor.

ch07. Oh My!

The Seventh Bit of Advice
When designing visitor protocols for many different types, create a unifying protocol using Object.

ch08. Like Father, Like Son

The Eight Bit of Advice
When extending a class, use overrideing to enrich its functionality

ch09. Be a Good Visitor

The Ninth Bit of Advice
If a datatype may have to be extended, be forward looking and use a constrctor-like (overridable) method so athat visitors can be extended, too.

ch10. The State of Things to Come

The Tenth Bit of Advice
When modifications to bojects are needed, use a class to insulate the operations that modify objects. Otherwise, beware the consequences of your actions.
