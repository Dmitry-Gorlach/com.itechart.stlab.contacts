package com.itechart.stlab.contacts.logic;

import com.itechart.stlab.contacts.dao.impl.ContactDaoImpl;
import com.itechart.stlab.contacts.exception.DaoException;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.model.Contact;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ContactLogic {
    private ContactDaoImpl contactDaoImpl = new ContactDaoImpl();

    public Contact add(String firstName, String surname, String lastName, LocalDate birthday,
                       Contact.Gender gender, String nationality, Contact.MaritalStatus maritalStatus, String company,
                       String website, String email, String country, String city, String street, String house,
                       String flat, String postcode) throws LogicException {
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setSurname(surname);
        contact.setLastname(lastName);
        contact.setBirthday(birthday);
        contact.setGender(gender);
        contact.setNationality(nationality);
        contact.setMaritalStatus(maritalStatus);
        contact.setCompany(company);
        contact.setWebSite(website);
        contact.setEmail(email);
        contact.setCountry(country);
        contact.setCity(city);
        contact.setStreet(street);
        contact.setHouse(house);
        contact.setFlat(flat);
        contact.setPostcode(postcode);


        try {
            contactDaoImpl.create(contact);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return contact;
    }

    public Contact edit(Contact contact) throws LogicException {

        try {
            contactDaoImpl.update(contact);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return contact;
    }

    public void delete(Integer id) throws  LogicException{
        try{
             contactDaoImpl.delete(id);
        }catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    public void editContact(Integer id, String firstName, String lastname, String surname, LocalDate birthday,
                             Contact.Gender gender, String nationality, Contact.MaritalStatus maritalStatus,
                             String company, String website, String email, String photo_uri, String country,
                             String city, String street, String house, String flat, String postcode) throws LogicException{
        try{
            contactDaoImpl.updateContact(id, firstName, lastname, surname, birthday, gender, nationality, maritalStatus,
                    company, website, email, photo_uri, country, city, street, house, flat, postcode);
        }catch (DaoException e){
            throw new LogicException(e);
        }

    }

    public void editContactWithoutPhoto(Integer id, String firstName, String lastname, String surname, LocalDate birthday,
                            Contact.Gender gender, String nationality, Contact.MaritalStatus maritalStatus,
                            String company, String website, String email, String country, String city, String street, String house,
                                        String flat, String postcode) throws LogicException{
        try{
            contactDaoImpl.updateContactWithoutPhoto(id, firstName, lastname, surname, birthday, gender, nationality, maritalStatus,
                    company, website, email, country, city, street, house, flat, postcode);
        }catch (DaoException e){
            throw new LogicException(e);
        }

    }

    public List<Contact> findAll() throws LogicException{

        try{
            return contactDaoImpl.getAll();
        }catch (DaoException e){
            throw new LogicException("Error in ContactLogic",e);
        }

    }
    public Optional<Contact> findContactById(Integer id) throws LogicException{

        try{
            return contactDaoImpl.getById(id);
        }catch (DaoException e){
            throw new LogicException("Error in ContactLogic",e);
        }

    }

    public List<Contact> findContactByFirstName(String firstName) throws LogicException{
        try{
            return contactDaoImpl.getByFirstName(firstName);
        }catch (DaoException e){
            throw new LogicException("Error in ContactLogic");
        }
    }

    public List<Contact> findContactBySurname(String surname) throws LogicException{
        try{
            return contactDaoImpl.getBySurname(surname);
        }catch (DaoException e){
            throw new LogicException("Error in ContactLogic");
        }
    }
    public List<Contact> findContactByLastName(String lastName) throws LogicException{
        try{
            return contactDaoImpl.getByLastName(lastName);
        }catch (DaoException e){
            throw new LogicException("Error in ContactLogic");
        }
    }
    public List<Contact> findContactByGender(Contact.Gender gender) throws LogicException{
        try{
            return contactDaoImpl.getByGender(gender);
        }catch (DaoException e){
            throw new LogicException("Error in ContactLogic");
        }
    }
    public List<Contact> findContactByMaritalStatus(Contact.MaritalStatus maritalStatus) throws LogicException{
        try{
            return contactDaoImpl.getByMaritalStatus(maritalStatus);
        }catch (DaoException e){
            throw new LogicException("Error in ContactLogic");
        }
    }

    public List<Contact> findContactByNationality(String nationality) throws LogicException{
        try{
            return contactDaoImpl.getByNationality(nationality);
        }catch (DaoException e){
            throw new LogicException("Error in ContactLogic");
        }
    }
    public List<Contact> findContactByBirthdayAsc(LocalDate birthday) throws LogicException{
        try{
            return contactDaoImpl.getBirthdayAsc(birthday);
        }catch (DaoException e){
            throw new LogicException("Error in ContactLogic");
        }
    }
    public List<Contact> findContactByBirthdayDesc(LocalDate birthday) throws LogicException{
        try{
            return contactDaoImpl.getBirthdayDesc(birthday);
        }catch (DaoException e){
            throw new LogicException("Error in ContactLogic");
        }
    }


}
