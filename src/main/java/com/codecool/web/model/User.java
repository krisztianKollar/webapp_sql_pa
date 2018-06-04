package com.codecool.web.model;

public class User extends AbstractModel {

    private String companyName;
    private String contactName;
    private String contactTitle;

    public User(String id, String companyName, String contactName, String contactTitle) {
        super(id);
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }
}
