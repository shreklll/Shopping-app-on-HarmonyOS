package com.xkd.myapplication.beans;

public class Product {
    private String id;
    private String price;
    private String name;
    private String introduce;
    private String area;

    public Product(String id, String price, String name, String introduce, String area) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.introduce = introduce;
        this.area = area;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", area='" + area + '\'' +
                '}';
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
