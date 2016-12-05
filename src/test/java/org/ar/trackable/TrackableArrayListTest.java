package org.ar.trackable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Trackable Array List.
 */
public class TrackableArrayListTest {

  @Test
  public void testAllConsumers() {
    //GIVEN
    List<String> mirrorList = new ArrayList<>();

    List<String> testList = TrackableObjects.<String>arrayList()
        .whenAdd(mirrorList::add)
        .whenAddAll(mirrorList::addAll)
        .whenAddAllByIndex(mirrorList::addAll)
        .whenAddByIndex(mirrorList::add)
        .whenRemove(mirrorList::remove)
        .whenRemoveAll(mirrorList::removeAll)
        .whenRemoveByIndex(index -> mirrorList.remove((int)index))
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

    testList.remove(3);
    testList.remove(dataSet.get(7));
    testList.removeAll(Arrays.asList(dataSet.get(8), dataSet.get(9), dataSet.get(4)));

    //THEN

    assertEquals(testList, mirrorList);

    assertEquals(String.join(" ", mirrorList), expectedString);
  }
}
