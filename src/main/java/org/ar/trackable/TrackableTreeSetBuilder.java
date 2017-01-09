package org.ar.trackable;

import java.util.Collection;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * {@link TrackableTreeSetBuilder} Trackable Tree Set builder produce {@link TreeSet} instance with
 * extended default methods by provided consumers.
 */
public class TrackableTreeSetBuilder<T> {

  private Consumer<T> addConsumer;
  private Consumer<Collection<? extends T>> addAllConsumer;
  private Consumer<Object> removeConsumer;
  private Consumer<Collection<?>> removeAllConsumer;
  private Consumer<T> ceilingConsumer;
  private Consumer<T> floorConsumer;
  private Consumer<T> higherConsumer;
  private Consumer<T> lowerConsumer;

  public TrackableTreeSetBuilder<T> whenAdd(Consumer<T> addConsumer){
    this.addConsumer = addConsumer;
    return this;
  }

  public TrackableTreeSetBuilder<T> whenAddAll(Consumer<Collection<? extends T>> addAllConsumer){
    this.addAllConsumer = addAllConsumer;
    return this;
  }

  public TrackableTreeSetBuilder<T> whenRemove(Consumer<Object> removeConsumer){
    this.removeConsumer = removeConsumer;
    return this;
  }

  public TrackableTreeSetBuilder<T> whenRemoveAll(Consumer<Collection<?>> removeAllConsumer){
    this.removeAllConsumer = removeAllConsumer;
    return this;
  }

  public TrackableTreeSetBuilder<T> whenCeiling(Consumer<T> ceilingConsumer){
    this.ceilingConsumer = ceilingConsumer;
    return this;
  }

  public TrackableTreeSetBuilder<T> whenFloor(Consumer<T> floorConsumer){
    this.floorConsumer = floorConsumer;
    return this;
  }

  public TrackableTreeSetBuilder<T> whenHigher(Consumer<T> higherConsumer){
    this.higherConsumer = higherConsumer;
    return this;
  }

  public TrackableTreeSetBuilder<T> whenLower(Consumer<T> lowerConsumer){
    this.lowerConsumer = lowerConsumer;
    return this;
  }

  public TreeSet<T> build() {
    return new TreeSet<T>() {
      @Override
      public boolean add(T t) {
        if (addConsumer != null) {
          addConsumer.accept(t);
        }
        return super.add(t);
      }

      @Override
      public boolean addAll(Collection<? extends T> c) {
        if (addAllConsumer != null) {
          addAllConsumer.accept(c);
        }
        return super.addAll(c);
      }

      @Override
      public boolean remove(Object o) {
        if (removeConsumer != null) {
          removeConsumer.accept(o);
        }
        return super.remove(o);
      }

      @Override
      public boolean removeAll(Collection<?> c) {
        if (removeAllConsumer != null) {
          removeAllConsumer.accept(c);
        }
        return super.removeAll(c);
      }

      @Override
      public T ceiling(T t) {
        if (ceilingConsumer != null) {
          ceilingConsumer.accept(t);
        }
        return super.ceiling(t);
      }

      @Override
      public T floor(T t) {
        if (floorConsumer != null) {
          floorConsumer.accept(t);
        }
        return super.floor(t);
      }

      @Override
      public T higher(T t) {
        if (higherConsumer != null) {
          higherConsumer.accept(t);
        }
        return super.higher(t);
      }

      @Override
      public T lower(T t) {
        if (lowerConsumer != null) {
          lowerConsumer.accept(t);
        }
        return super.lower(t);
      }
    };
  }
}
