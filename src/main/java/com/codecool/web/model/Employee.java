package com.codecool.web.model;

public class Employee extends AbstractModel {

    private String lastName;
    private String firstName;
    private String title;

    public Employee(String id, String lastName, String firstName, String title) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
    }
}
