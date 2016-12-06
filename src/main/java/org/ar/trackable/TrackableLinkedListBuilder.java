package org.ar.trackable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * {@link TrackableLinkedListBuilder} Trackable LinkedList builder. Build simple {@link java.util.LinkedList}
 * object that use provided consumers to extend simple operation behavior.
 */
public class TrackableLinkedListBuilder<T> {

  private Consumer<T> addConsumer;
  private BiConsumer<Integer, T> addByIndexConsumer;
  private BiConsumer<Integer, Collection<? extends T>> addAllByIndexConsumer;
  private Consumer<T> addFirstConsumer;
  private Consumer<T> addLastConsumer;
  private Consumer<Collection<?>> removeAllConsumer;
  private Consumer<Integer> removeByIndexConsumer;
  private Consumer<Object> removeConsumer;
  private Consumer<Object> removeLastOccurrenceConsumer;

  TrackableLinkedListBuilder() {}

  public TrackableLinkedListBuilder<T> whenAdd(Consumer<T> whenAddConsumer) {
    this.addConsumer = whenAddConsumer;
    return this;
  }

  public TrackableLinkedListBuilder<T> whenAddByIndex(BiConsumer<Integer, T> whenAddByIndexConsumer) {
    this.addByIndexConsumer = whenAddByIndexConsumer;
    return this;
  }

  public TrackableLinkedListBuilder<T> whenAddAllByIndex(BiConsumer<Integer, Collection<? extends T>> whenAddAllByIndexConsumer) {
    this.addAllByIndexConsumer = whenAddAllByIndexConsumer;
    return this;
  }

  public TrackableLinkedListBuilder<T> whenAddFirst(Consumer<T> whenAddFirstConsumer) {
    this.addFirstConsumer = whenAddFirstConsumer;
    return this;
  }

  public TrackableLinkedListBuilder<T> whenAddLast(Consumer<T> whenAddLastConsumer) {
    this.addLastConsumer = whenAddLastConsumer;
    return this;
  }

  public TrackableLinkedListBuilder<T> whenRemoveAll(Consumer<Collection<?>> whenRemoveAllConsumer) {
    this.removeAllConsumer = whenRemoveAllConsumer;
    return this;
  }

  public TrackableLinkedListBuilder<T> whenRemoveByIndex(Consumer<Integer> whenRemoveByIndexConsumer) {
    this.removeByIndexConsumer = whenRemoveByIndexConsumer;
    return this;
  }

  public TrackableLinkedListBuilder<T> whenRemove(Consumer<Object> whenRemoveConsumer) {
    this.removeConsumer = whenRemoveConsumer;
    return this;
  }

  public TrackableLinkedListBuilder<T> whenRemoveLastOccurrence(Consumer<Object> whenRemoveLastOccurrenceConsumer) {
    this.removeLastOccurrenceConsumer = whenRemoveLastOccurrenceConsumer;
    return this;
  }

  public LinkedList<T> build(){
    return new LinkedList<T>(){
      @Override
      public boolean add(T t) {
        if (addConsumer != null) {
          addConsumer.accept(t);
        }
        return super.add(t);
      }

      @Override
      public void add(int index, T element) {
        if (addByIndexConsumer != null) {
          addByIndexConsumer.accept(index, element);
        }
        super.add(index, element);
      }

      @Override
      public boolean addAll(int index, Collection<? extends T> c) {
        if (addAllByIndexConsumer != null) {
          addAllByIndexConsumer.accept(index, c);
        }
        return super.addAll(index, c);
      }

      @Override
      public void addFirst(T t) {
        if (addFirstConsumer != null) {
          addFirstConsumer.accept(t);
        }
        super.addFirst(t);
      }

      @Override
      public void addLast(T t) {
        if (addLastConsumer != null) {
          addLastConsumer.accept(t);
        }
        super.addLast(t);
      }

      @Override
      public boolean removeAll(Collection<?> c) {
        if (removeAllConsumer != null) {
          removeAllConsumer.accept(c);
        }
        return super.removeAll(c);
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
      public boolean removeLastOccurrence(Object o) {
        if (removeLastOccurrenceConsumer != null) {
          removeLastOccurrenceConsumer.accept(o);
        }
        return super.removeLastOccurrence(o);
      }
    };
  }

}
