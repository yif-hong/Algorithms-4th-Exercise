package exercise.chapter3_5;

import exercise.chapter1_3.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchSET<Key extends Comparable<Key>> implements Iterable<Key> {
    private Key[] keys;
    private int size;

    public BinarySearchSET(int capacity) {
        keys = (Key[]) new Comparable[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException();
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int comp = key.compareTo(keys[mid]);
            if (comp == 0) {
                return mid;
            } else if (comp > 0) {
                lo = mid + 1;
            } else if (comp < 0) {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public void add(Key key) {
        if (key == null) throw new IllegalArgumentException();
        int i = rank(key);
        if (i < size && key.compareTo(keys[i]) == 0) {
            keys[i] = key;
            return;
        }
        for (int j = size; j > i; j--) {
            keys[j] = keys[j - 1];
        }
        keys[i] = key;
        size++;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return;
        }
        int i = rank(key);
        if (i < size && key.compareTo(keys[i]) == 0) {
            for (int j = i; j < size - 1; j++) {
                keys[j] = keys[j + 1];
            }
            keys[size] = null;
            size--;
            if (size > 0 && size == keys.length / 4) {
                resize(keys.length / 2);
            }
        }
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return keys[0];
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return keys[size - 1];
    }

    public Key select(int i) {
        if (i >= size && i < 0) throw new NoSuchElementException();
        return keys[i];
    }

    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int i = rank(key);
        return i == size ? null : keys[i];
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int i = rank(key);
        if (i < size && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        return i == 0 ? null : keys[i - 1];
    }

    public Iterable<Key> keys() {
        return keys(keys[0], keys[size - 1]);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null) {
            throw new IllegalArgumentException();
        }
        Queue<Key> queue = new Queue<>();
        if (lo.compareTo(hi) > 0) {
            return queue;
        }
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }

    public boolean contains(Key key) {
        int lo = 0, hi = size;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int comp = key.compareTo(keys[mid]);
            if (comp == 0) {
                return true;
            } else if (comp > 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Key[] tempK = (Key[]) (new Comparable[capacity]);
        for (int i = 0; i < size; i++) {
            tempK[i] = keys[i];
        }
        keys = tempK;
    }

    @Override
    public Iterator<Key> iterator() {
        return keys().iterator();
    }
}
