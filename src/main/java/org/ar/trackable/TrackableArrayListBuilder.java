package org.ar.trackable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * {@link TrackableArrayListBuilder} Trackable Array List builder. Build simple java {@link ArrayList}
 * that use predefined consumers to extend simple operation behavior..
 */
public class TrackableArrayListBuilder<T> {

  private BiConsumer<Integer, T> whenAddByIndexConsumer;
  private Consumer<T> whenAddConsumer;
  private BiConsumer<Integer, Collection<? extends T>> whenAddAllByIndexConsumer;
  private Consumer<Collection<? extends T>> whenAddAllConsumer;
  private Consumer<Object> whenRemoveConsumer;
  private Consumer<Integer> whenRemoveByIndexConsumer;
  private Consumer<Collection<?>> whenRemoveAllConsumer;

  TrackableArrayListBuilder() {}

  public TrackableArrayListBuilder<T> whenAddByIndex(BiConsumer<Integer, T> whenAddByIndexConsumer) {
    this.whenAddByIndexConsumer = whenAddByIndexConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenAdd(Consumer<T> whenAddConsumer) {
    this.whenAddConsumer = whenAddConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenAddAllByIndex(BiConsumer<Integer, Collection<? extends T>> whenAddAllByIndexConsumer) {
    this.whenAddAllByIndexConsumer = whenAddAllByIndexConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenAddAll(Consumer<Collection<? extends T>> whenAddAllConsumer) {
    this.whenAddAllConsumer = whenAddAllConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenRemove(Consumer<Object> whenRemoveConsumer) {
    this.whenRemoveConsumer = whenRemoveConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenRemoveByIndex(Consumer<Integer> whenRemoveByIndexConsumer) {
    this.whenRemoveByIndexConsumer = whenRemoveByIndexConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenRemoveAll(Consumer<Collection<?>> whenRemoveAllConsumer) {
    this.whenRemoveAllConsumer = whenRemoveAllConsumer;
    return this;
  }

  /**
   * Build trackable {@link ArrayList} with predefined capacity.
   * @return ArrayList.
   */
  public ArrayList<T> build() {
    return build(10);
  }

  /**
   * Build trackable {@link ArrayList} with predefined capacity.
   * @param initialCapacity capacity.
   * @return ArrayList.
   */
  public ArrayList<T> build(int initialCapacity) {
    return new ArrayList<T>(initialCapacity) {
      @Override
      public void add(int index, T element) {
        if (whenAddByIndexConsumer != null) {
          whenAddByIndexConsumer.accept(index, element);
        }
        super.add(index, element);
      }

      @Override
      public boolean add(T element) {
        if (whenAddConsumer != null) {
          whenAddConsumer.accept(element);
        }
        return super.add(element);
      }

      @Override
      public boolean addAll(int index, Collection<? extends T> c) {
        if (whenAddAllByIndexConsumer != null) {
          whenAddAllByIndexConsumer.accept(index, c);
        }
        return super.addAll(index, c);
      }

      @Override
      public boolean addAll(Collection<? extends T> c) {
        if (whenAddAllConsumer != null) {
          whenAddAllConsumer.accept(c);
        }
        return super.addAll(c);
      }

      @Override
      public T remove(int index) {
        if (whenRemoveByIndexConsumer != null) {
          whenRemoveByIndexConsumer.accept(index);
        }
        return super.remove(index);
      }

      @Override
      public boolean remove(Object o) {
        if (whenRemoveConsumer != null) {
          whenRemoveConsumer.accept(o);
        }
        return super.remove(o);
      }

      @Override
      public boolean removeAll(Collection<?> c) {
        if (whenRemoveAllConsumer != null) {
          whenRemoveAllConsumer.accept(c);
        }
        return super.removeAll(c);
      }
    };
  }
}
