package org.ar.trackable;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Trackable Array List.
 */
public class TrackableHashMapTest {

  @Test
  public void testAllConsumers() {
    //GIVEN
    Map<String, String> mirrorMap = new HashMap<>();

    Map<String, String> targetMap = TrackableObjects.<String, String>hashMap()
        .whenGet(mirrorMap::get)
        .whenPut(mirrorMap::put)
        .whenRemove(mirrorMap::remove)
        .whenGetOrDefault(mirrorMap::getOrDefault)
        .whenPutAll(mirrorMap::putAll)
        .whenPutIfAbsent(mirrorMap::putIfAbsent)
        .whenRemoveByKeyValue(mirrorMap::remove)
        .whenReplace(mirrorMap::replace)
        .build();

    //WHEN
    targetMap.put("test", "value");
    String value = mirrorMap.get("test");

    targetMap.remove("test");
    String nullableValue = mirrorMap.get("test");

    targetMap.put("test", "value");
    targetMap.replace("test", "value2");

    String value2 = mirrorMap.get("test");

    //THEN
    assertEquals("value", value);
    assertEquals(null, nullableValue);
    assertEquals("value2", value2);
  }
}
