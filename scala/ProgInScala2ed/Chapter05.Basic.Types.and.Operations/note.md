Table 5.1 - Basic Types and Operations

   Value Type      Range
-------------+------------------------------------------------------------------
Byte           8-bit signed two's complement integer (-2^7 to 2^7-1, inclusive)
Short          16-bit signed two's complement integer (-2^15 to 2^15-1, inclusive)
Int            32-bit signed two's complement integer (-2^31 to 2^31-1, inclusive)
Long           64-bit signed two's complement integer (-2^63 to 2^63-1, inclusive)
Char           16-bit unsigned Unicode character (0 to 2^16-1, inclusive)
String         a sequence of Chars
Float          32-bit IEEE 754 single-precision float
Double         64-bit IEEE 754 double-precision float
Boolean        true or false

Table 5.2 - Special character literal escape sequences

   Literal         Meaning
-------------+------------------------------------
\n             line feed       (\u000A)
\b             backspace       (\u0008)
\t             tab             (\u0009)
\f             form feed       (\u000C)
\r             carriage return (\u000D)
\"             double quote    (\u0022)
\'             single quote    (\u0027)
\\             backslash       (\u005C)

Table 5.3 - Operator precedure

-------------------------------
(all other special characters)
* / %
+ -
:
=!
< >
&
^
|
(all letters)
(all assignment operators)

Table 5.4 - Some rich operations

  Code                Result
--------------------+------------
0 max 5               5
0 min 5               0
-2.7 abs              2.7
-2.7 round            -3L
1.5 isInfinity        false
(1.0/0) isInfinity    true
4 to 6                Range(4,5,6)
"bob" capitalize      "Bob"
"robert" drop 2       "bert"

Table 5.5 - Rich wrapper classes

 Basic type      Rich wrapper
-------------+--------------------
 Byte            scala.runtime.RichByte
 Short           scala.runtime.RichShort
 Int             scala.runtime.RichInt
 Char            scala.runtime.RichChar
 Float           scala.runtime.RichFloat
 Double          scala.runtime.RichDouble
 Boolean         scala.runtime.RichBoolean
 String          scala.collection.immutable.StringOps






















