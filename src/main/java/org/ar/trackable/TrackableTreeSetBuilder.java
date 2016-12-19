package org.ar.trackable;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.function.Predicate;

/**
 * {@link TrackableTreeSetBuilder} Trackable Hash Set builder produce {@link HashSet} instance with
 * extended default methods by provided consumers.
 */
public class TrackableTreeSetBuilder<T> {



  public TreeSet<T> build() {
    return new TreeSet<T>() {
      @Override
      public boolean add(T t) {
        return super.add(t);
      }

      @Override
      public boolean addAll(Collection<? extends T> c) {
        return super.addAll(c);
      }

      @Override
      public boolean remove(Object o) {
        return super.remove(o);
      }

      @Override
      public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
      }

      @Override
      public boolean removeIf(Predicate<? super T> filter) {
        return false;
      }

      @Override
      public T first() {
        return super.first();
      }

      @Override
      public T ceiling(T t) {
        return super.ceiling(t);
      }

      @Override
      public T floor(T t) {
        return super.floor(t);
      }

      @Override
      public T higher(T t) {
        return super.higher(t);
      }

      @Override
      public T last() {
        return super.last();
      }

      @Override
      public T lower(T t) {
        return super.lower(t);
      }

      @Override
      public T pollFirst() {
        return super.pollFirst();
      }

      @Override
      public T pollLast() {
        return super.pollLast();
      }
    };
  }
}
