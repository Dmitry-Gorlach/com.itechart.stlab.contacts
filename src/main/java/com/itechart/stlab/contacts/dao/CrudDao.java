package com.itechart.stlab.contacts.dao;

import com.itechart.stlab.contacts.exception.DaoException;
import com.itechart.stlab.contacts.model.Entity;

import java.util.List;
import java.util.Optional;
public interface CrudDao <T extends Entity>{

     void create(T entity) throws DaoException;
     void update(T entity) throws DaoException;
     void delete(Integer id) throws DaoException;
     List<T> getAll() throws DaoException;
     Optional<T> getById(Integer id) throws DaoException;


}

