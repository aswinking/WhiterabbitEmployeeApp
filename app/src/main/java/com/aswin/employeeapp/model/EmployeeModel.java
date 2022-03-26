package com.aswin.employeeapp.model;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public class EmployeeModel implements Serializable {

    /**Constructor**/
    public EmployeeModel(String name,
                         String username,
                         String email,
                         String addressStreet,
                         String addressSute,
                         String addressCity,
                         String addressZipcode,
                         String phone,
                         String website,
                         String profileImage,
                         String companyName,
                         String companyCatchPhrase,
                         String companybs) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.addressStreet = addressStreet;
        this.addressSute = addressSute;
        this.addressCity = addressCity;
        this.addressZipcode = addressZipcode;
        this.phone = phone;
        this.website = website;
        this.profileImage = profileImage;
        this.companyName = companyName;
        this.companyCatchPhrase = companyCatchPhrase;
        this.companybs = companybs;
    }

    private String name;
    private String username;
    private String email;
    private String addressStreet;
    private String addressSute;
    private String addressCity;
    private String addressZipcode;
    private String phone;
    private String website;
    private String profileImage;
    private String companyName;
    private String companyCatchPhrase;
    private String companybs;


    /**Getters and Setters**/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String address) {
        this.addressStreet = address;
    }

    public String getAddressSute() {
        return addressSute;
    }

    public void setAddressSute(String addressSute) {
        this.addressSute = addressSute;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressZipcode() {
        return addressZipcode;
    }

    public void setAddressZipcode(String addressZipcode) {
        this.addressZipcode = addressZipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyCatchPhrase() {
        return companyCatchPhrase;
    }

    public void setCompanyCatchPhrase(String companyCatchPhrase) {
        this.companyCatchPhrase = companyCatchPhrase;
    }

    public String getCompanybs() {
        return companybs;
    }

    public void setCompanybs(String companybs) {
        this.companybs = companybs;
    }

}
