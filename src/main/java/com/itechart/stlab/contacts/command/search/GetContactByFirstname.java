package com.itechart.stlab.contacts.command.search;

import com.itechart.stlab.contacts.command.Command;
import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;
import com.itechart.stlab.contacts.model.Contact;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetContactByFirstname implements Command {
    private static final String PAGE_CONTACTS_LIST = "page.contacts.list";
    private ContactLogic contactLogic;

    public GetContactByFirstname(ContactLogic contactLogic) {
        this.contactLogic = contactLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException {
        String firstName = request.getParameter("first_name");
        List<Contact> contacts = contactLogic.findContactByFirstName(firstName);
        request.setAttribute("contacts", contacts);

        Router router = new Router();
        router.setPage(ConfigurationManager.PAGE_PATH.getProperty(PAGE_CONTACTS_LIST));
        return router;
    }
}
