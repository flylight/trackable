package org.ar.trackable;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * {@link TrackableHashSetBuilder} Trackable Hash Set builder produce {@link HashSet} instance with
 * extended default methods by provided consumers.
 */
public class TrackableHashSetBuilder<T> {

  private Consumer<T> addConsumer;
  private Consumer<Object> removeConsumer;
  private Consumer<Collection<? extends T>> addAllConsumer;
  private Consumer<Collection<?>> removeAllConsumer;

  public TrackableHashSetBuilder<T> whenAdd(Consumer<T> addConsumer) {
    this.addConsumer = addConsumer;
    return this;
  }

  public TrackableHashSetBuilder<T> whenRemove(Consumer<Object> removeConsumer) {
    this.removeConsumer = removeConsumer;
    return this;
  }

  public TrackableHashSetBuilder<T> whenAddAll(Consumer<Collection<? extends T>> addAllConsumer) {
    this.addAllConsumer = addAllConsumer;
    return this;
  }

  public TrackableHashSetBuilder<T> whenRemoveAll(Consumer<Collection<?>> removeAllConsumer) {
    this.removeAllConsumer = removeAllConsumer;
    return this;
  }

  public HashSet<T> build() {
    return new HashSet<T>() {
      @Override
      public boolean add(T t) {
        if (addConsumer != null) {
          addConsumer.accept(t);
        }
        return super.add(t);
      }

      @Override
      public boolean remove(Object o) {
        if (removeConsumer != null) {
          removeConsumer.accept(o);
        }
        return super.remove(o);
      }

      @Override
      public boolean addAll(Collection<? extends T> c) {
        if (addAllConsumer != null) {
          addAllConsumer.accept(c);
        }
        return super.addAll(c);
      }

      @Override
      public boolean removeAll(Collection<?> c) {
        if (removeAllConsumer != null) {
          removeAllConsumer.accept(c);
        }
        return super.removeAll(c);
      }
    };
  }
}
