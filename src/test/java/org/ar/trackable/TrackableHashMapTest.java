package org.ar.trackable;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for Trackable Array List.
 */
public class TrackableHashMapTest {

  @Test
  public void testAllConsumers() {
    //GIVEN
    Map<String, String> mirrorMap = new HashMap<>();

    Map<String, String> builder = TrackableObjects.<String, String>hashMap()
        .whenGet(mirrorMap::get)
        .whenPut(mirrorMap::put)
        .build();
  }
}
