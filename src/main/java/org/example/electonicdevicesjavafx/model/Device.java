package org.example.electonicdevicesjavafx.model;

public class Device {
    private DeviceType type;
    private String name;
    private double price;
    private double weight;

    public Device(DeviceType type, String name, double price, double weight) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return type + ": " + name + " - $" + price + ", " + weight + "kg";
    }

    public enum DeviceType {
        SMARTPHONE, LAPTOP, TABLET
    }
}