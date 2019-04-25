package com.itechart.stlab.contacts.command.search;

import com.itechart.stlab.contacts.command.Command;
import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;
import com.itechart.stlab.contacts.model.Contact;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class GetContactByBirthdayDesc implements Command {
    private static final String PAGE_CONTACTS_LIST = "page.contacts.list";
    private ContactLogic contactLogic;

    public GetContactByBirthdayDesc(ContactLogic contactLogic) {
        this.contactLogic = contactLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException {

    String birthday = request.getParameter("birthday");
    List<Contact> contacts = contactLogic.findContactByBirthdayDesc(LocalDate.parse(birthday));
        request.setAttribute("contacts", contacts);

    Router router = new Router();
        router.setPage(ConfigurationManager.PAGE_PATH.getProperty(PAGE_CONTACTS_LIST));
        return router;
    }
}

