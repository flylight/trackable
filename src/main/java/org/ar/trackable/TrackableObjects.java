package org.ar.trackable;

/**
 * This class provide static methods for builders to build different Java default object with
 * extra behavior.
 */
public class TrackableObjects {

  public static <T> TrackableArrayListBuilder<T> arrayList() {
    return new TrackableArrayListBuilder<T>();
  }

}
