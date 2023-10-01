package com.tomfran.lsm.memtable;

import com.tomfran.lsm.sstable.SSTable;
import com.tomfran.lsm.types.Item;

public class Memtable {

    static final int DEFAULT_SSTABLE_SAMPLE_SIZE = 1 << 10;

    SkipList list;

    public Memtable() {
        list = new SkipList();
    }

    public Memtable(int numElements) {
        list = new SkipList(numElements);
    }

    public void add(Item item) {
        list.add(item);
    }

    public Item get(byte[] key) {
        return list.get(key);
    }

    public void remove(byte[] key) {
        list.add(new Item(key, null));
    }

    public int size() {
        return list.size();
    }

    public SSTable flush(String filename) {
        return new SSTable(filename, list, DEFAULT_SSTABLE_SAMPLE_SIZE, list.size());
    }

}
