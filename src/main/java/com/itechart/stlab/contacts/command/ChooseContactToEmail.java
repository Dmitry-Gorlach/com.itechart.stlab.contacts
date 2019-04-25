package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;
import com.itechart.stlab.contacts.model.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChooseContactToEmail implements Command {
    private static final String PAGE_SEND_EMAIL = "page.send.email";
    private ContactLogic contactLogic;

    public ChooseContactToEmail(ContactLogic contactLogic) {
        this.contactLogic = contactLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException, IOException, ServletException {
        String[] contacts = request.getParameterValues("selected_contacts");
        List<Optional<Contact>> contactList = new ArrayList<>();

        if(contacts != null) {
            for (String contactId : contacts) {
                Optional<Contact> contact = contactLogic.findContactById(Integer.parseInt(contactId));
                contactList.add(contact);
            }
        }

        request.setAttribute("contacts", contactList);

        Router router = new Router();
        router.setPage(ConfigurationManager.PAGE_PATH.getProperty(PAGE_SEND_EMAIL));
        return router;
    }
}
