package org.ar.trackable;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for Trackable Array List.
 */
public class TrackableTreeSetTest {

  @Test
  public void testAllConsumers() {
    //GIVEN
    TreeSet<String> mirrorSet= new TreeSet<>();

    TreeSet<String> targetSet = TrackableObjects.<String>treeSet()
        .whenAdd(mirrorSet::add)
        .whenRemove(mirrorSet::remove)
        .whenAddAll(mirrorSet::addAll)
        .whenRemoveAll(mirrorSet::removeAll)
        .build();

    //WHEN
    targetSet.add("test");
    targetSet.add("test");
    targetSet.addAll(Arrays.asList("test2", "test3"));
    targetSet.remove("test2");
    targetSet.removeAll(Collections.singletonList("test3"));

    //THEN
    assertEquals(mirrorSet, targetSet);
  }
}
