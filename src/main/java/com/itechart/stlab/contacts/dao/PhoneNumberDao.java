package com.itechart.stlab.contacts.dao;


import com.itechart.stlab.contacts.exception.DaoException;
import com.itechart.stlab.contacts.model.PhoneNumber;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PhoneNumberDao extends  CrudDao<PhoneNumber>{
    @Override
    void create(PhoneNumber entity) throws DaoException;

    @Override
    List<PhoneNumber> getAll() throws DaoException;

    @Override
    Optional<PhoneNumber> getById(Integer id) throws DaoException;

    Set<PhoneNumber> getByContactId(Integer contactId) throws DaoException;
    Set<PhoneNumber> getAllPhoneNumbers() throws DaoException;

    @Override
    void delete(Integer id) throws DaoException;
}
