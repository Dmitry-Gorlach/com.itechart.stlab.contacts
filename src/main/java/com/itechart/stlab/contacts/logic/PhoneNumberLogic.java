package com.itechart.stlab.contacts.logic;

import com.itechart.stlab.contacts.dao.impl.PhoneNumberDaoImpl;
import com.itechart.stlab.contacts.exception.DaoException;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.model.PhoneNumber;

import java.util.Optional;
import java.util.Set;

public class PhoneNumberLogic {
    private PhoneNumberDaoImpl phoneNumberDaoImpl = new PhoneNumberDaoImpl();

    public PhoneNumber add(String countryCode, String phoneCode, String number,
                           PhoneNumber.TypeCode typeCode, String comment, Integer contactId) throws LogicException {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setCountryCode(countryCode);
        phoneNumber.setPhoneCode(phoneCode);
        phoneNumber.setNumber(number);
        phoneNumber.setType(typeCode);
        phoneNumber.setComment(comment);
        phoneNumber.setContactId(contactId);

        try {
            phoneNumberDaoImpl.create(phoneNumber);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return phoneNumber;
    }

    public PhoneNumber update(String countryCode, String phoneCode, String number,
                           PhoneNumber.TypeCode typeCode, String comment, Integer phoneId) throws LogicException {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setCountryCode(countryCode);
        phoneNumber.setPhoneCode(phoneCode);
        phoneNumber.setNumber(number);
        phoneNumber.setType(typeCode);
        phoneNumber.setComment(comment);
        phoneNumber.setId(phoneId);
        try {
            phoneNumberDaoImpl.update(phoneNumber);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return phoneNumber;
    }

    public Set<PhoneNumber> findPhoneNumberByContactId(Integer contactId) throws LogicException{
        try{
            return phoneNumberDaoImpl.getByContactId(contactId);
        }catch (DaoException e){
            throw new LogicException("Error in PhoneNumberLogic");
        }
    }

    public Set<PhoneNumber> findAllPhoneNumber() throws LogicException{
        try{
            return phoneNumberDaoImpl.getAllPhoneNumbers();
        }catch (DaoException e){
            throw new LogicException("Error in PhoneNumberLogic");
        }
    }

    public void delete(Integer id) throws  LogicException{
        try{
            phoneNumberDaoImpl.delete(id);
        }catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    public Optional<PhoneNumber> findPhoneNumberById(Integer id) throws LogicException{

        try{
            return phoneNumberDaoImpl.getById(id);
        }catch (DaoException e){
            throw new LogicException("Error in PhoneNumberLogic",e);
        }

    }


}
