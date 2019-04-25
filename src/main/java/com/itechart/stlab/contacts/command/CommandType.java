package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.command.search.*;
import com.itechart.stlab.contacts.logic.AttachmentLogic;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.logic.PhoneNumberLogic;

public enum CommandType {

    ADD_CONTACT(new AddContact(new ContactLogic())),
    GET_ALL_CONTACTS(new GetAllContact(new ContactLogic())),
    GET_CONTACT_BY_ID(new GetContactById(new ContactLogic(), new PhoneNumberLogic(), new AttachmentLogic())),
    GET_CONTACT_BY_FIRSTNAME(new GetContactByFirstname(new ContactLogic())),
    GET_CONTACT_BY_SURNAME(new GetContactBySurname(new ContactLogic())),
    GET_CONTACT_BY_LASTNAME(new GetContactByLastname(new ContactLogic())),
    GET_CONTACT_BY_GENDER(new GetContactByGender(new ContactLogic())),
    GET_CONTACT_BY_MARITAL_STATUS(new GetContactByMaritalStatus(new ContactLogic())),
    GET_CONTACT_BY_NATIONALITY(new GetContactByNationality(new ContactLogic())),
    GET_CONTACT_BY_BIRTHDAY_ASC(new GetContactByBirthdayAsc(new ContactLogic())),
    GET_CONTACT_BY_BIRTHDAY_DESC(new GetContactByBirthdayDesc(new ContactLogic())),
    UPDATE_CONTACT(new UpdateContact(new ContactLogic(), new PhoneNumberLogic(), new AttachmentLogic())),
    GET_PHONE_NUMBERS_BY_CONTACT_ID(new GetPhoneNumbersByContactId(new PhoneNumberLogic())),
    GET_ALL_PHONE_NUMBERS(new GetAllPhoneNumbers(new PhoneNumberLogic())),
    DELETE_CONTACT(new DeleteContact(new ContactLogic())),
    DELETE_PHONE(new DeletePhone(new PhoneNumberLogic())),
    DELETE_ATTACHMENT(new DeleteAttachment(new AttachmentLogic())),
    CHOOSE_CONTACT_TO_EMAIL(new ChooseContactToEmail(new ContactLogic())),
    SEND_EMAIL(new SendEmail(new ContactLogic())),
    OPEN_ADD_CONTACT_PAGE(new OpenAddContactPage()),
    OPEN_SEARCH_CONTACT_PAGE(new OpenSearchContactPage()),
    LANGUAGE(new LanguageCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }

}
