package com.vidyakalkendra.petsapp;

public class Pet {

    private int id;
    private String name;
    private String breed;
    private String type;
    private double weight;
    private String gender;

    public Pet(String name, String breed, double weight, String type, String gender) {
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.type = type;
        this.gender = gender;
    }

    public Pet(int id, String name, String breed, String type, double weight, String gender) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.type = type;
        this.weight = weight;
        this.gender = gender;
    }

    public Pet(int id, String name, String breed, double weight){
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.weight = weight;
    }

    public Pet(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
