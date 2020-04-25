package edu.java.test;

import java.util.List;
import java.util.Map;

public class Man {

    private String name;
    private int age;
    private List<String> favoriteBooks;
    private Map<String, String> map;
    private Address address;
    private Man man;

    public Man(String name, int age, List<String> favoriteBooks, Address address, Map<String, String> map) {
        this.name = name;
        this.age = age;
        this.favoriteBooks = favoriteBooks;
        this.address = address;
        this.map = map;
    }

    public Man() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(List<String> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }

//    @Override
//    public String toString() {
//        return "Man{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                ", favoriteBooks=" + favoriteBooks +
//                ", address=" + address +
//                ", man=" + man.getName() +
//                '}';
//    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", favoriteBooks=" + favoriteBooks +
                ", map=" + map +
                ", address=" + address +
                ", man=" + man.getName() +
                '}';
    }
}
