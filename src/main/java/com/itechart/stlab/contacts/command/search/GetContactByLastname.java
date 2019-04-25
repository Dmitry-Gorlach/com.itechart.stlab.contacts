package com.itechart.stlab.contacts.command.search;

import com.itechart.stlab.contacts.command.Command;
import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.model.Contact;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.itechart.stlab.contacts.manager.ConfigurationManager.PAGE_PATH;

public class GetContactByLastname implements Command {

    private final static String PAGE_CONTACTS_LIST="page.contacts.list";
    private ContactLogic contactLogic;

    public GetContactByLastname(ContactLogic contactLogic) {
        this.contactLogic = contactLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException {
        String lastName = request.getParameter("last_name");
        List<Contact> contacts = contactLogic.findContactByLastName(lastName);
        request.setAttribute("contacts", contacts);

        Router router = new Router();
        router.setPage(PAGE_PATH.getProperty(PAGE_CONTACTS_LIST));
        return router;
    }
}
