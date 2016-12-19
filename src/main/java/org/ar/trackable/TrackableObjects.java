package org.ar.trackable;

/**
 * This class provide static methods for builders to build different Java default object with
 * extra behavior.
 *
 * Supported objects :
 * - {@link java.util.ArrayList}
 * - {@link java.util.LinkedList}
 * - {@link java.util.HashMap}
 * - {@link java.util.HashSet}
 */
public class TrackableObjects {

  /**
   * Create builder of {@link java.util.ArrayList} trackable implementation.
   *
   * @param <T> type of {@link java.util.ArrayList}
   * @return Trackable {@link java.util.ArrayList}
   */
  public static <T> TrackableArrayListBuilder<T> arrayList() {
    return new TrackableArrayListBuilder<>();
  }

  /**
   * Create builder of {@link java.util.LinkedList} trackable implementation.
   *
   * @param <T> type of {@link java.util.LinkedList}
   * @return Trackable {@link java.util.LinkedList}
   */
  public static <T> TrackableLinkedListBuilder<T> linkedList() {
    return new TrackableLinkedListBuilder<>();
  }

  /**
   * Create builder of {@link java.util.HashMap} trackable implementation.
   *
   * @param <K> type of {@link java.util.HashMap} key
   * @param <V> type of {@link java.util.HashMap} value
   * @return Trackable {@link java.util.HashMap}
   */
  public static <K, V> TrackableHashMapBuilder<K, V> hashMap() {
    return new TrackableHashMapBuilder<>();
  }

  /**
   * Create builder of {@link java.util.HashSet} trackable implementation.
   *
   * @param <T> type of {@link java.util.HashSet}
   * @return Trackable {@link java.util.HashSet}
   */
  public static <T> TrackableHashSetBuilder<T> hashSet() {
    return new TrackableHashSetBuilder<>();
  }

  /**
   * Create builder of {@link java.util.TreeSet} trackable implementation.
   *
   * @param <T> type of {@link java.util.TreeSet}
   * @return Trackable {@link java.util.TreeSet}
   */
  public static <T> TrackableTreeSetBuilder<T> treeSet() {
    return new TrackableTreeSetBuilder<>();
  }
}
