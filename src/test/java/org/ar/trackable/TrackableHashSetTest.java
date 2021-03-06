package org.ar.trackable;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for Trackable Array List.
 */
public class TrackableHashSetTest {

  @Test
  public void testAllConsumers() {
    //GIVEN
    Set<String> mirrorSet= new HashSet<>();

    Set<String> targetSet = TrackableObjects.<String>hashSet()
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
    assertTrue(mirrorSet.size() == 1);
    assertEquals(mirrorSet, targetSet);
  }
}
