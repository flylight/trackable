package org.ar.trackable;

/**
 * This class provide static methods for builders to build different Java default object with
 * extra behavior.
 *
 * Supported objects :
 * - {@link java.util.ArrayList}
 * - {@link java.util.LinkedList}
 *
 */
public class TrackableObjects {

  public static <T> TrackableArrayListBuilder<T> arrayList() {
    return new TrackableArrayListBuilder<>();
  }

  public static <T> TrackableLinkedListBuilder<T> linkedList() {
    return new TrackableLinkedListBuilder<>();
  }
}
