package org.ar.trackable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * {@link TrackableArrayListBuilder} Trackable Array List builder. Build simple java {@link ArrayList}
 * object that use provided consumers to extend simple operation behavior.
 */
public class TrackableArrayListBuilder<T> {

  private BiConsumer<Integer, T> addByIndexConsumer;
  private Consumer<T> addConsumer;
  private BiConsumer<Integer, Collection<? extends T>> addAllByIndexConsumer;
  private Consumer<Collection<? extends T>> addAllConsumer;
  private Consumer<Object> removeConsumer;
  private Consumer<Integer> removeByIndexConsumer;
  private Consumer<Collection<?>> removeAllConsumer;

  TrackableArrayListBuilder() {}

  public TrackableArrayListBuilder<T> whenAddByIndex(BiConsumer<Integer, T> whenAddByIndexConsumer) {
    this.addByIndexConsumer = whenAddByIndexConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenAdd(Consumer<T> whenAddConsumer) {
    this.addConsumer = whenAddConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenAddAllByIndex(BiConsumer<Integer, Collection<? extends T>> whenAddAllByIndexConsumer) {
    this.addAllByIndexConsumer = whenAddAllByIndexConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenAddAll(Consumer<Collection<? extends T>> whenAddAllConsumer) {
    this.addAllConsumer = whenAddAllConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenRemove(Consumer<Object> whenRemoveConsumer) {
    this.removeConsumer = whenRemoveConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenRemoveByIndex(Consumer<Integer> whenRemoveByIndexConsumer) {
    this.removeByIndexConsumer = whenRemoveByIndexConsumer;
    return this;
  }

  public TrackableArrayListBuilder<T> whenRemoveAll(Consumer<Collection<?>> whenRemoveAllConsumer) {
    this.removeAllConsumer = whenRemoveAllConsumer;
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
        if (addByIndexConsumer != null) {
          addByIndexConsumer.accept(index, element);
        }
        super.add(index, element);
      }

      @Override
      public boolean add(T element) {
        if (addConsumer != null) {
          addConsumer.accept(element);
        }
        return super.add(element);
      }

      @Override
      public boolean addAll(int index, Collection<? extends T> c) {
        if (addAllByIndexConsumer != null) {
          addAllByIndexConsumer.accept(index, c);
        }
        return super.addAll(index, c);
      }

      @Override
      public boolean addAll(Collection<? extends T> c) {
        if (addAllConsumer != null) {
          addAllConsumer.accept(c);
        }
        return super.addAll(c);
      }

      @Override
      public T remove(int index) {
        if (removeByIndexConsumer != null) {
          removeByIndexConsumer.accept(index);
        }
        return super.remove(index);
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
    };
  }
}
