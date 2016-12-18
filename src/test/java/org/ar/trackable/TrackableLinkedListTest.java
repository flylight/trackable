package org.ar.trackable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Trackable Array List.
 */
public class TrackableLinkedListTest {

  @Test
  public void testAllConsumers() {
    //GIVEN
    LinkedList<String> mirrorList = new LinkedList<>();

    LinkedList<String> testList = TrackableObjects.<String>linkedList()
        .whenAdd(mirrorList::add)
        .whenAddAllByIndex(mirrorList::addAll)
        .whenAddByIndex(mirrorList::add)
        .whenAddFirst(mirrorList::addFirst)
        .whenAddLast(mirrorList::addLast)
        .whenRemove(mirrorList::remove)
        .whenRemoveAll(mirrorList::removeAll)
        .whenRemoveByIndex(index -> mirrorList.remove((int) index))
        .whenRemoveLastOccurrence(mirrorList::removeLastOccurrence)
        .build();

    List<String> dataSet = Arrays
        .asList("Hello,", "my name is", "Duke !", "I like", "Java ", "code!",
            "This", "is", " cool", "library!");

    String expectedString = "Hello, my name is Duke ! I like code!";
    //WHEN
    testList.add(dataSet.get(1));
    testList.add(0, dataSet.get(0));
    testList.addAll(dataSet.subList(2, 6));
    testList.addAll(3, dataSet.subList(6, 10));
    testList.addFirst(dataSet.get(0));
    testList.addLast(dataSet.get(5));

    testList.remove(4);
    testList.remove(dataSet.get(7));
    testList.removeAll(Arrays.asList(dataSet.get(8), dataSet.get(9), dataSet.get(4)));
    testList.removeFirstOccurrence(dataSet.get(0));
    testList.removeLastOccurrence(dataSet.get(5));

    //THEN

    assertEquals(testList, mirrorList);

    assertEquals(expectedString, String.join(" ", mirrorList));
  }
}
