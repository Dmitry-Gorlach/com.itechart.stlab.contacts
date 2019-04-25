package com.itechart.stlab.contacts.model;

import java.time.*;
import java.util.*;

public class Contact extends Entity{

    private String firstName;
    private String surname;
    private String lastname;
    private LocalDate birthday;
    private Gender gender;
    private String nationality;
    private MaritalStatus maritalStatus;
    private String company;
    private String webSite;
    private String email;
    private Set<PhoneNumber> phoneNumbers = new HashSet<>();
    private List<Attachment> attachments = new ArrayList<>();
    private String photo;
    private String country;
    private String city;
    private String street;
    private String house;
    private String flat;
    private String postcode;

    public Contact(Integer id, String firstname, String surname, String lastname, LocalDate birthday, Gender gender, String nationality, MaritalStatus maritalStatus, String company, String website, String email, String country, String city, String street, String house, String flat, String postcode) {
    }


    public enum Gender {

        MALE, FEMALE

    }

    public enum MaritalStatus {
        SINGLE, MARRIED, DIVORCED
    }

    public Contact() {
    }


    public Contact(String firstName, String surname, String lastname, LocalDate birthday, Gender gender, String nationality, MaritalStatus maritalStatus, String company, String webSite, String email,
                   String country, String city, String street, String house, String flat, String postcode) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.company = company;
        this.webSite = webSite;
        this.email = email;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.postcode = postcode;
    }

    public Contact(Integer id) {
        super(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Contact contact = (Contact) o;
        return firstName.equals(contact.firstName) &&
                surname.equals(contact.surname) &&
                Objects.equals(lastname, contact.lastname) &&
                Objects.equals(birthday, contact.birthday) &&
                gender == contact.gender &&
                Objects.equals(nationality, contact.nationality) &&
                maritalStatus == contact.maritalStatus &&
                Objects.equals(company, contact.company) &&
                Objects.equals(webSite, contact.webSite) &&
                Objects.equals(email, contact.email) &&
                Objects.equals(phoneNumbers, contact.phoneNumbers) &&
                Objects.equals(attachments, contact.attachments) &&
                Objects.equals(photo, contact.photo) &&
                Objects.equals(country, contact.country) &&
                Objects.equals(city, contact.city) &&
                Objects.equals(street, contact.street) &&
                Objects.equals(house, contact.house) &&
                Objects.equals(flat, contact.flat) &&
                Objects.equals(postcode, contact.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, surname, lastname, birthday, gender, nationality, maritalStatus, company, webSite, email, phoneNumbers, attachments, photo, country, city, street, house, flat, postcode);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", nationality='" + nationality + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", company='" + company + '\'' +
                ", webSite='" + webSite + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", attachments=" + attachments +
                ", photo='" + photo + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", flat='" + flat + '\'' +
                ", postcode=" + postcode +
                '}';
    }
}
