package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.AttachmentLogic;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.logic.PhoneNumberLogic;
import com.itechart.stlab.contacts.model.Attachment;
import com.itechart.stlab.contacts.model.Contact;
import com.itechart.stlab.contacts.model.PhoneNumber;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;

import static com.itechart.stlab.contacts.manager.ConfigurationManager.PAGE_PATH;

public class GetContactById implements Command {
    private final static String PAGE_CONTACT="page.contact";
    private ContactLogic contactLogic;
    private PhoneNumberLogic phoneNumberLogic;
    private AttachmentLogic attachmentLogic;

    public GetContactById(ContactLogic contactLogic, PhoneNumberLogic phoneNumberLogic, AttachmentLogic attachmentLogic) {
        this.contactLogic = contactLogic;
        this.phoneNumberLogic = phoneNumberLogic;
        this.attachmentLogic = attachmentLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Optional<Contact> contact = contactLogic.findContactById(id);
        request.setAttribute("contact", contact);

        Set<PhoneNumber> phoneNumbers = phoneNumberLogic.findPhoneNumberByContactId(id);
        request.setAttribute("phoneNumbers", phoneNumbers);

        Set<Attachment> attachments = attachmentLogic.findAttachmentByContactId(id);
        request.setAttribute("attachments", attachments);

        Router router = new Router();
        router.setPage(PAGE_PATH.getProperty(PAGE_CONTACT));
        return router;
    }
}
