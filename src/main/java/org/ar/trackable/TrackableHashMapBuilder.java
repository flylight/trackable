package org.ar.trackable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * {@link TrackableHashMapBuilder} Trackable Hash Map builder produce {@link HashMap} instance with
 * extended default methods by provided consumers.
 */
public class TrackableHashMapBuilder<K, V> {
  private BiConsumer<K, V> putConsumer;
  private Consumer<Map<K, V>> putAllConsumer;
  private BiConsumer<K, V> putIfAbsentConsumer;
  private Consumer<Object> getConsumer;
  private BiConsumer<Object, V> getOrDefaultConsumer;
  private BiConsumer<Object, Object> removeByKeyValueConsumer;
  private Consumer<Object> removeConsumer;
  private BiConsumer<K, V> replaceConsumer;

  public TrackableHashMapBuilder<K, V> whenPut(BiConsumer<K, V> putConsumer) {
    this.putConsumer = putConsumer;
    return this;
  }

  public TrackableHashMapBuilder<K, V> whenPutAll(Consumer<Map<K, V>> putAllConsumer) {
    this.putAllConsumer = putAllConsumer;
    return this;
  }

  public TrackableHashMapBuilder<K, V> whenPutIfAbsent(BiConsumer<K, V> putIfAbsentConsumer) {
    this.putIfAbsentConsumer = putIfAbsentConsumer;
    return this;
  }

  public TrackableHashMapBuilder<K, V> whenGet(Consumer<Object> getConsumer) {
    this.getConsumer = getConsumer;
    return this;
  }

  public TrackableHashMapBuilder<K, V> whenGetOrDefault(BiConsumer<Object, V> getOrDefaultConsumer) {
    this.getOrDefaultConsumer = getOrDefaultConsumer;
    return this;
  }

  public TrackableHashMapBuilder<K, V> whenRemoveByKeyValue(BiConsumer<Object, Object> removeByKeyValueConsumer) {
    this.removeByKeyValueConsumer = removeByKeyValueConsumer;
    return this;
  }

  public TrackableHashMapBuilder<K, V> whenRemove(Consumer<Object> removeConsumer) {
    this.removeConsumer = removeConsumer;
    return this;
  }

  public TrackableHashMapBuilder<K, V> whenReplace(BiConsumer<K, V> replaceConsumer) {
    this.replaceConsumer = replaceConsumer;
    return this;
  }

  public HashMap<K, V> build() {
    return new HashMap<K, V>() {
      @Override
      public V put(K key, V value) {
        if (putConsumer != null) {
          putConsumer.accept(key, value);
        }
        return super.put(key, value);
      }

      @Override
      public void putAll(Map m) {
        if (putAllConsumer != null) {
          putAllConsumer.accept(m);
        }
        super.putAll(m);
      }

      @Override
      public V putIfAbsent(K key, V value) {
        if (putIfAbsentConsumer != null) {
          putIfAbsentConsumer.accept(key, value);
        }
        return super.putIfAbsent(key, value);
      }

      @Override
      public V get(Object key) {
        if (getConsumer != null) {
          getConsumer.accept(key);
        }
        return super.get(key);
      }

      @Override
      public V getOrDefault(Object key, V defaultValue) {
        if (getOrDefaultConsumer != null) {
          getOrDefaultConsumer.accept(key, defaultValue);
        }
        return super.getOrDefault(key, defaultValue);
      }

      @Override
      public boolean remove(Object key, Object value) {
        if (removeByKeyValueConsumer != null) {
          removeByKeyValueConsumer.accept(key, value);
        }
        return super.remove(key, value);
      }

      @Override
      public V remove(Object key) {
        if (removeConsumer != null) {
          removeConsumer.accept(key);
        }
        return super.remove(key);
      }

      @Override
      public V replace(K key, V value) {
        if (replaceConsumer != null) {
          replaceConsumer.accept(key, value);
        }
        return super.replace(key, value);
      }
    };
  }
}
