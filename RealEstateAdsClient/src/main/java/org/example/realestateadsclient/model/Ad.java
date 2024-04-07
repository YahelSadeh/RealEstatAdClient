package org.example.realestateadsclient.model;

public class Ad implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private final long id;
    String user;
    String city;
    String street;
    int numOfHome;
    int numOfRooms;
    int numOfFloor;
    int squareOfMeter;
    int Price;
    String date;
    String description;

    public Ad(long id, String user, String city, String street,
              int numOfHome, int numOfRooms, int numOfFloor,
              int squareOfMeter, int price, String date, String description) {
        this.id = id;
        this.user = user;
        this.city = city;
        this.street = street;
        this.numOfHome = numOfHome;
        this.numOfRooms = numOfRooms;
        this.numOfFloor = numOfFloor;
        this.squareOfMeter = squareOfMeter;
        this.Price = price;
        this.date = date;
        this.description = description;
    }
    public long getId() {
        return id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getNumOfHome() {
        return numOfHome;
    }
    public void setNumOfHome(int numOfHome) {
        this.numOfHome = numOfHome;
    }
    public int getNumOfRooms() {
        return numOfRooms;
    }
    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }
    public int getNumOfFloor() {
        return numOfFloor;
    }
    public void setNumOfFloor(int numOfFloor) {
        this.numOfFloor = numOfFloor;
    }
    public int getSquareOfMeter() {
        return squareOfMeter;
    }
    public void setSquareOfMeter(int squareOfMeter) {
        this.squareOfMeter = squareOfMeter;
    }
    public int getPrice() {
        return Price;
    }
    public void setPrice(int price) {
        Price = price;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
