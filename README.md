# trackable
Java trackable object. Functionality to track interaction with Java object and 
add additional functionality into their behavior. 

### Use case

Java has a lot of objects that developers mostly use. For example ArrayList is the main implementation for
collection. It cover almost all cases of collection usage and a lot of people use it everyday.

But sometimes we need to check is some element inside and do some logic then. Or we have to iterate thru array list and
when we find some element then do something. All this operations give some complexity into code and decrease code redability.

This tiny library give you comfortable interface to build trackable objects. So now you can define what you have to do 
when som specified element adding into collection. Or you can predefine some logic when some element have to be removed 
from collection. All mostly used method can be extended in comfortable way when you create instance of this Collection.

### Supported Objects

- ArrayList
- LinkedList
- HashMap
- HashSet
- TreeSet

### Examples

Create collection using `TrackableObjects` class : 

```
List<String> testList = TrackableObjects.<String>arrayList().build();

Map<String, String> targetMap = TrackableObjects.<String, String>hashMap().build();

Set<String> targetSet = TrackableObjects.<String>hashSet().build();

LinkedList<String> testList = TrackableObjects.<String>linkedList().build();

TreeSet<String> targetSet = TrackableObjects.<String>treeSet().build();

```

Use `Trackable<?>Builder` to declare extra logic into default object behavior :

```
List<String> testList = TrackableObjects.<String>arrayList()
        .whenAdd(element -> { //DO SOMETHING })
        .whenAddAll(collection -> { //DO SOMETHING })
        .whenAddAllByIndex((index, collection) -> { //DO SOMETHING })
        .whenAddByIndex((index, element) -> { //DO SOMETHING })
        .whenRemove(element -> { //DO SOMETHING })
        .whenRemoveAll(collection -> { //DO SOMETHING })
        .whenRemoveByIndex((index, element) -> { //DO SOMETHING })
        .build();
```
