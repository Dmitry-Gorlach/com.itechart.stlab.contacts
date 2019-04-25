package com.itechart.stlab.contacts.model;

import java.util.Objects;

public class PhoneNumber extends Entity {

    private String countryCode;
    private String phoneCode;
    private String number;
    private TypeCode type;
    private String comment;
    private Integer contactId;

    public PhoneNumber() {
    }

    public PhoneNumber(Integer id, String countryCode, String phoneCode, String number, TypeCode type, String comment, Integer contactId) {
        super(id);
        this.countryCode = countryCode;
        this.phoneCode = phoneCode;
        this.number = number;
        this.type = type;
        this.comment = comment;
        this.contactId = contactId;
    }

    public enum TypeCode{
        HOME, MOBILE
    }

    public PhoneNumber(Integer id) {
        super(id);
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TypeCode getType() {
        return type;
    }

    public void setType(TypeCode type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(phoneCode, that.phoneCode) &&
                Objects.equals(number, that.number) &&
                type == that.type &&
                Objects.equals(comment, that.comment) &&
                contactId.equals(that.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countryCode, phoneCode, number, type, comment, contactId);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "countryCode='" + countryCode + '\'' +
                ", phoneCode='" + phoneCode + '\'' +
                ", number='" + number + '\'' +
                ", type=" + type +
                ", comment='" + comment + '\'' +
                ", contactId=" + contactId +
                '}';
    }
}



   

