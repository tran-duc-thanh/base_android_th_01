package com.example.baseandroidth1.dbFake;

import com.example.baseandroidth1.model.Item;
import com.example.baseandroidth1.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

public class TblItem {
    private static List<Item> data = new ArrayList<>();

    public static List<Item> getData() {
        return data;
    }

    public static void addData (Item item) {data.add(item);}

    public static void updateData (Item item) {
        data.forEach(d -> {if (d.getId() == item.getId()) data.set(data.indexOf(d), item);});
    }

    public static void deleteData (int id) {
        if (DataUtils.isNullOrEmpty(getItemById(id))) return;
        data.remove(getItemById(id));
    }

    public static Item getItemById (int id) {
        for (Item item : data) {
            if (item.getId() == id) return item;
        }
        return null;
    }
}
