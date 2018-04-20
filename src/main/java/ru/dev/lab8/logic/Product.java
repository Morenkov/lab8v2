package ru.dev.lab8.logic;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("count")
    private int count;

    @SerializedName("categories")
    private String categories;

    public Product(int id, String name, int count, String categories) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.categories = categories;
    }

    public Product() {
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
