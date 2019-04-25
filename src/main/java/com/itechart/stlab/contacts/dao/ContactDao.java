package com.itechart.stlab.contacts.dao;

import com.itechart.stlab.contacts.exception.DaoException;
import com.itechart.stlab.contacts.model.Attachment;
import com.itechart.stlab.contacts.model.Contact;

import java.time.LocalDate;
import java.util.List;

public interface ContactDao extends CrudDao<Contact> {

     List<Contact> getByFirstName(String firstName) throws DaoException;
     List<Contact> getBySurname(String surname) throws DaoException;
     List<Contact> getByLastName(String lastName) throws DaoException;
     List<Contact> getBirthdayAsc(LocalDate birthday) throws DaoException;
     List<Contact> getBirthdayDesc(LocalDate birthday) throws DaoException;
     List<Contact> getByGender(Contact.Gender gender) throws DaoException;
     List<Contact> getByMaritalStatus(Contact.MaritalStatus MaritalStatus) throws DaoException;
     List<Contact> getByNationality(String nationality) throws DaoException;
     void updateContact(Integer id, String firstName, String lastname, String surname, LocalDate birthday,
                        Contact.Gender gender, String nationality, Contact.MaritalStatus maritalStatus,
                        String company, String website, String email, String photo_uri, String country,
                       String city, String street, String house, String flat, String postcode) throws DaoException;
     void updateContactWithoutPhoto(Integer id, String firstName, String lastname, String surname, LocalDate birthday,
                        Contact.Gender gender, String nationality, Contact.MaritalStatus maritalStatus,
                        String company, String website, String email, String country,
                        String city, String street, String house, String flat, String postcode) throws DaoException;
     void addAttachment(Attachment attachment) throws DaoException;


}
