package com.itechart.stlab.contacts.command.search;

import com.itechart.stlab.contacts.command.Command;
import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;
import com.itechart.stlab.contacts.model.Contact;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
public class GetContactByMaritalStatus implements Command {

    private final static String PAGE_CONTACTS_LIST = "page.contacts.list";
    private ContactLogic contactLogic;

    public GetContactByMaritalStatus(ContactLogic contactLogic) {
        this.contactLogic = contactLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException {
        Router router = new Router();
        String maritalStatus = request.getParameter("marital_status");
        if(!maritalStatus.isEmpty()){
        List<Contact> contacts = contactLogic.findContactByMaritalStatus(Contact.MaritalStatus.valueOf(maritalStatus));
        request.setAttribute("contacts", contacts);
        } else {
            return refreshForward(ConfigurationManager.PAGE_PATH.getProperty("page.contact.search"));
        }

        router.setPage(ConfigurationManager.PAGE_PATH.getProperty(PAGE_CONTACTS_LIST));
        return router;
    }
}
