package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DeleteContact implements Command {
    private static final String PAGE_CONTACTS_LIST = "page.contacts.list";
    private ContactLogic contactLogic;

    public DeleteContact(ContactLogic contactLogic) {
        this.contactLogic = contactLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException, IOException {
        Router router = new Router();
        try {
            request.getParts();

            String selectedContacts= request.getParameter("selected_contacts");
            String delimeter = ",";
            String[] contactId;
            contactId = selectedContacts.split(delimeter);

            if(contactId!=null) {
                for (String id : contactId) {
                    contactLogic.delete(Integer.valueOf(id));
                }
            }

        } catch (ServletException  e) {
            e.printStackTrace();
        }

        return refreshRedirect(ConfigurationManager.PAGE_PATH.getProperty(PAGE_CONTACTS_LIST));
    }
}
