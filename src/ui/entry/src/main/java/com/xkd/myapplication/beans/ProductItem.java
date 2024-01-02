package com.xkd.myapplication.beans;

public class ProductItem {
    private String name;
    private String id;
    private String price;
    private String attribute;
    private String area;

    public ProductItem(String name, String id, String price, String attribute, String area) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.attribute = attribute;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", price='" + price + '\'' +
                ", attribute='" + attribute + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
