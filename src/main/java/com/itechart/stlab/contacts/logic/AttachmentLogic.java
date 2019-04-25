package com.itechart.stlab.contacts.logic;

import com.itechart.stlab.contacts.dao.impl.AttachmentDaoImpl;
import com.itechart.stlab.contacts.exception.DaoException;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.model.Attachment;

import java.time.LocalDate;
import java.util.Set;

public class AttachmentLogic {
    private AttachmentDaoImpl attachmentDaoImpl = new AttachmentDaoImpl();

    public Attachment add(String attachmentName, LocalDate dateUpload, String comment,
                          Integer contactId) throws LogicException {
        Attachment attachment = new Attachment();
        attachment.setNameAttachment(attachmentName);
        attachment.setDateUpload(dateUpload);
        attachment.setComment(comment);
        attachment.setContactId(contactId);

        try {
            attachmentDaoImpl.create(attachment);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return attachment;
    }

    public Attachment update(String attachmentName, LocalDate dateUpload, String comment,
                             Integer contactId) throws LogicException {
        Attachment attachment = new Attachment();
        attachment.setNameAttachment(attachmentName);
        attachment.setDateUpload(dateUpload);
        attachment.setComment(comment);
        attachment.setContactId(contactId);
        try {
            attachmentDaoImpl.update(attachment);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return attachment;
    }

    public Set<Attachment> findAttachmentByContactId(Integer contactId) throws LogicException{
        try{
            return attachmentDaoImpl.getByContactId(contactId);
        }catch (DaoException e){
            throw new LogicException("Error in AttachmentLogic");
        }
    }

    public void delete(Integer id) throws  LogicException{
        try{
            attachmentDaoImpl.delete(id);
        }catch (DaoException e) {
            throw new LogicException(e);
        }

    }
}
