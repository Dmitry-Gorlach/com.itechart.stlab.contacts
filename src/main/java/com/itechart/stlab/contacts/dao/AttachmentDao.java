package com.itechart.stlab.contacts.dao;

import com.itechart.stlab.contacts.exception.DaoException;
import com.itechart.stlab.contacts.model.Attachment;

import java.util.Set;

public interface AttachmentDao extends CrudDao<Attachment> {

    Set<Attachment> getByContactId(Integer contactId) throws DaoException;

}
