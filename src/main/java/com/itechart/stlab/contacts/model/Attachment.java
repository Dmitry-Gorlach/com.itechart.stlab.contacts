package com.itechart.stlab.contacts.model;


import java.time.*;
import java.util.Objects;

public class Attachment extends Entity {

    private String nameAttachment;
    private LocalDate dateUpload;
    private String comment;
    private Integer contactId;

    public Attachment() {
    }

    public Attachment(Integer id, String nameAttachment, LocalDate dateUpload, String comment, Integer contactId) {
        super(id);
        this.nameAttachment = nameAttachment;
        this.dateUpload = dateUpload;
        this.comment = comment;
        this.contactId = contactId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Attachment(Integer id) {
        super(id);
    }

    public String getNameAttachment() {
        return nameAttachment;
    }

    public void setNameAttachment(String nameAttachment) {
        this.nameAttachment = nameAttachment;
    }

    public LocalDate getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(LocalDate dateUpload) {
        this.dateUpload = dateUpload;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(nameAttachment, that.nameAttachment) &&
                Objects.equals(dateUpload, that.dateUpload) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameAttachment, dateUpload, comment);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "nameAttachment='" + nameAttachment + '\'' +
                ", dateUpload=" + dateUpload +
                ", comment='" + comment + '\'' +
                '}';
    }
}
