package com.example.myapplication.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Items")
public class Item extends Model {
    // If name is omitted, then the field name is used.
    @Column(name = "Name", index = true)
    public String name;

    @Column(name = "Category", index = true)
    public Category category;

    public Item() {
        super();
    }

    public Item(String name, Category category) {
        super();
        this.name = name;
        this.category = category;
    }

    public static Item getRandom() {
        return new Select().from(Item.class).orderBy("RANDOM()").executeSingle();
    }

    public static Item getRandom(Category category) {
        return new Select()
                .from(Item.class)
                .where("Category = ?", category.getId())
                .orderBy("RANDOM()")
                .executeSingle();
    }

    public static List<Item> getAll(Category category) {
        return new Select()
                .from(Item.class)
                .where("Category = ?", category.getId())
                .orderBy("Name ASC")
                .execute();
    }
}