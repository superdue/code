scala.collection

scala.collection.mutable
scala.collection.immutable
scala.collection.generic

scala.collection.IndexedSeq[T]

scala.collection.immutable.IndexedSeq[T]
scala.collection.mutable.indexedSeq[T]

scala.collection.mutable.Set
scala.collection.mutable.Iterable

Traversable(1, 2, 3)
Iterable("x", "y", "z")
Map("x" -> 24, "y" -> 25, "z" -> 26)
Set(Color.Red, Color.Green, Color.Blue)
SortedSet("hello", "world")
Buffer(x, y, z)
IndexedSeq(1.0, 2.0)
LinearSeq(a, b, c)

List(1, 2, 3)
HashMap("x" -> 24, "y" -> 25, "z" -> 26)

```
Traversable
  Iterable
    Seq
      IndexedSeq
        Vector
        ResizableArray
        GenericArray
      LinearSeq
        MutableList
        List
        Stream
      Buffer
        ListBuffer
        ArrayBuffer
    Set
      SortedSet
        TreeSet
      HashSet (mutable)
      LinkedHashSet
      HashSet (immutable)
      BitSet
      EmptySet, Set1, Set2, Set3, Set4
    Map
      SortedMap
        TreeMap
      HashMap (mutable)
      LinkedHashMap (mutable)
      HashMap (immutable)
      EmptyMap, Map1, Map2, Map3, Map4
```

Table 24.1 - Operations in trait Traversable

   What it is                              What it does
----------------------------------------+--------------------------
Abstract method:
  xs foreach f
Addition:
  xs ++ ys
Maps:
  xs map f
  xs flatMap f
  xs collect f
Conversions:
  xs.toArray
  xs.toList
  xs.toInterable
  xs.toSeq
  xs.toIndexedSeq
  xs.toStream
  xs.toSet
  xs.toMap
Copying:
  xs copyToBuffer buf
  xs copyToArray(arr, s, len)
Size info:
  xs.isEmpty
  xs.nonEmpty
  xs.size
  xs.hasDefiniteSize
Element retrieval:
  xs.head
  xs.headOption
  xs.last
  xs.lastOption
  xs find p
Subcollections:
  xs.tail
  xs.init
  xs slice (from, to)
  xs take n
  xs drop n
  xs takeWhile p                           The longest prefix of elements in the collection that all satisfy p.
  xs dropWhile p                           The collection whitout the longest prefix of elemetns that all satisfy p.
  xs filter p
  xs withFilter p                          A non-strict filter of this collection. All operations on the resulting filter will only apply to those elements of xs for which the condition p is true.
  xs filterNot p
Subdivisions:
  xs splitAt n
  xs span p
  xs partition p
  xs groupBy f
Element conditions:
  xs forall p
  xs exists p
  xs count p
Folds:
  (z /: xs)(op)
  (xs :\ z)(op)
  xs.foldLeft(z)(op)
  xs.foldRight(z)(op)
  xs reduceLeft op
  xs reduceRight op
Specific folds:
  xs.sum
  xs.product
  xs.min
  xs.max
Strings:
  xs addString (b, start, sep, end)
  xs mkString (start, sep, end)
  xs.stringPrefix
Views:
  xs.view
  xs view (fro, to)


Table 24.2 - Operations in trait Iterable

   What it is                              What it does
----------------------------------------+--------------------------
Abstract method:
  xs.iterator
Other iterators:
  xs grouped size
  xs sliding size
Subcollections:
  xs takeRight n
  xs dropRight n
Zippers:
  xs zip ys
  xs zipAll (ys, x, y)
  xs.zipWithIndex
Comparison:
  xs sameElements ys


Table 24.3 - Operations in trait Seq

   What it is                              What it does
----------------------------------------+--------------------------
Indexing and length:
  xs(i)
  xs isDefinedAt i
  xs.length
  xs.lengthCompare ys
  xs.indices
Index search:
  xs indexOf x
  xs lastIndexOf x
  xs indexOfSlice ys
  xs lastIndexOfSlice ys
  xs indexWhere p
  xs segmentLength (p, i)
  xs prefixLength p
Additions:
  x +: xs
  xs :+ x
  xs padTo(len, x)
Updates:
  xs patch (i, ys, r)
  xs updated (i, x)
  xs(i) = x
Sorting:
  xs.sorted
  xs sortWith lessThan
  xs sortBy f
Reversals:
  xs.reverse
  xs.reverseIterator
  xs reverseMap f
Comparisons:
  xs startsWith ys
  xs endsWith ys
  xs contains x
  xs containsSlice ys
  (xs corresponds ys)(p)
Multiset operations:
  xs intersect ys
  xs diff ys
  xs union ys
  xs.distinct



Table 24.4 - Operations in trait Buffer

   What it is                              What it does
----------------------------------------+--------------------------
Additions:
  buf += x
  buf += (x, y, z)
  buf ++= xs
  x +=: buf
  xs ++=: buf
  buf insert (i, x)
  buf insertAll (i, xs)
Removals:
  buf -= x
  buf remove i
  buf remove (i, n)
  buf trimStart n
  buf trimEnd n
  buf.clear()
Cloning:
  buf.clone


Table 24.5 - Operations in trait Set

   What it is                              What it does
----------------------------------------+--------------------------
Tests:
  xs contains x
  xs(x)
  xs subsetOf ys
Additions:
  xs + x
  xs + (x, y, z)
  xs ++ ys
Removals:
  xs - x
  xs - (x, y, z)
  xs -- ys
  xs.empty
Binary operations:
  xs & ys
  xs intersert ys
  xs | ys
  xs union ys
  xs &~ ys
  xs diff ys


Table 24.6 - Operations in trait mutable.Set

   What it is                              What it does
----------------------------------------+--------------------------
Additions:
  xs += x
  xs += (x, y, z)
  xs ++= ys
  xs add x
Removals:
  xs -= x
  xs -= (x, y, z)
  xs --= ys
  xs remove x
  xs retain p
  xs.clear()
Update:
  xs(x) = b
Cloning:
  xs.clone


Table 24.7 - Operations in trait Map

   What it is                              What it does
----------------------------------------+--------------------------
Lookups:
  ms get k
  ms(k)
  ms getOrElse (k, d)
  ms contains k
  ms isDefinedAt k
Additions and udpates:
  ms + (k -> v)
  ms + (k -> v, l -> w)
  ms ++ kvs
  ms updated (k, v)
Removals:
  ms - k
  ms - (k, l, m)
  ms -- ks
Subcollections:
  ms.keys
  ms.keySet
  ms.keysIterator
  ms.values
  ms.valuesIterator
Transformation:
  ms filterKeys p
  ms mapValues f



Table 24.7 - Operations in trait mutable.Map

   What it is                              What it does
----------------------------------------+--------------------------
Additions and updates:
  ms(k) = v
  ms += (k -> v)
  ms += (k -> v, l -> w)
  ms ++= kvs
  ms put (k, v)
  ms getOrElseUpdate (k, d)
Removals:
  ms -= k
  ms -= (k, l, m)
  ms --= ks
  ms remove k
  ms retain p
  ms.clear()
Tranformation and cloning:
  ms transform f
  ms.clone









Concrete immutable collection classes

Lists
Streams
Vectors
Immutable stacks
Immutable queues
Ranges
Hash tries
Red-black trees
Immutable bit sets
List maps

Concrete mutable collection classes

Array buffers
List buffers
String builders
Linked lists
Double linked lists
Mutable lists
Queues
Array sequences
Stacks
Array stacks
Hash tables
Weak hash maps
Concurrent Maps
Mutable bit sets




Table 24.12 - Operations in trait Iterator

   What it is                              What it does
----------------------------------------+--------------------------
Abstract methods:
  it.next()
  it.hasNext
Variations:
  it.buffered
  it grouped size
  xs sliding size
Copying:
  it copyToBuffer buf
  it copyToArray(arr, s, l)
Duplication:
  it.duplicate
Additions:
  it ++ jt
  it padTo (len, x)
Maps:
  it map f
  it flatMap f
  it collect f
Conversions:
  it.toArray
  it.toList
  it.toIterable
  it.toSeq
  it.toIndexedSeq
  it.toStream
  it.toSet
  it.toMap
Size info:
  it.isEmpty
  it.nonEmpty
  it.size
  it.length
  it.hasDefiniteSize
Element retrieval index search:
  it find p
  it indexOf x
  it indexWhere p
Subiterators:
  it take n
  it drop n
  it slice (m, n)
  it takeWhile p
  it dropWhile p
  it filter p
  it withFilter p
  it filterNot p
Subdivisions:
  it partition p
Element conditions:
  it forall p
  it exists p
  it count p
Folds: 
  (z /: it)(op)
  (it :\ z)(op)
  it.foldLeft(z)(op)
  it.foldRight(z)(op)
  it reduceLeft op
  it reduceRight op
Specific folds:
  it.sum
  it.product
  it.min
  it.max
Zippers:
  it zip jt
  it zipAll (jt, x, y)
  it.zipWithIndex
Update:
  it patch (i, jt, r)
Comparison:
  it sameElements jt
Strings:
  it addString (b, start, sep, end)
  it mkString(start, sep, end)


Table 24.13 - Factory method fors sequences

   What it is                              What it does
----------------------------------------+--------------------------
S.empty
S(x, y, z)
S.concat(xs, ys, zs)
S.fill(m, n)(e)
S.tabulate(n)(f)
S.tabulate(m, n)(f)
S.range(start, end)
S.range(start, end, step)
S.iterate(x, n)(f)











