package com.example.baseandroidth1.dbFake;

import com.example.baseandroidth1.model.Item;
import com.example.baseandroidth1.utils.DataUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TblItem {
    private static List<Item> data = new ArrayList<>(
            Arrays.asList(
//                    new Item("name", "content", "14/11/1996", true),
//                    new Item("name1", "content", "14/11/1996", true),
//                    new Item("name2", "content", "14/11/1996", true),
//                    new Item("name3", "content", "14/11/1996", true),
//                    new Item("name4", "content", "14/11/1996", true),
//                    new Item("name5", "content", "14/11/1996", true),
//                    new Item("name6", "content", "14/11/1996", true),
//                    new Item("name7", "content", "14/11/1996", true),
//                    new Item("name8", "content", "14/11/1996", true),
//                    new Item("name9", "content", "14/11/1996", true),
//                    new Item("name10", "content", "14/11/1996", true),
//                    new Item("name11", "content", "14/11/1996", true),
//                    new Item("name12", "content", "14/11/1996", true),
//                    new Item("name13", "content", "14/11/1996", true),
//                    new Item("name14", "content", "14/11/1996", true),
//                    new Item("name15", "content", "14/11/1996", true),
//                    new Item("name16", "content", "14/11/1996", true),
//                    new Item("name17", "content", "14/11/1996", true),
//                    new Item("name18", "content", "14/11/1996", true),
//                    new Item("name19", "content", "14/11/1996", true),
//                    new Item("name20", "content", "14/11/1996", true),
//                    new Item("name21", "content", "14/11/1996", true),
//                    new Item("name22", "content", "14/11/1996", true),
//                    new Item("name23", "content", "14/11/1996", true),
//                    new Item("name24", "content", "14/11/1996", true),
//                    new Item("name25", "content", "14/11/1996", true)
                    )
    );

    public static List<Item> getData() {
        return data;
    }

    public static void addData (Item item) {data.add(0, item);}

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
