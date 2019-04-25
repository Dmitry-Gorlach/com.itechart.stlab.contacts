package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.PhoneNumberLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;
import com.itechart.stlab.contacts.model.PhoneNumber;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class GetAllPhoneNumbers implements Command {
    private static final String PAGE_CONTACT = "page.contact";
    private PhoneNumberLogic phoneNumberLogic;

    public GetAllPhoneNumbers(PhoneNumberLogic phoneNumberLogic) {
        this.phoneNumberLogic = phoneNumberLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException {
        Set<PhoneNumber> phoneNumbers = phoneNumberLogic.findAllPhoneNumber();
        request.setAttribute("phoneNumbers", phoneNumbers);

        Router router = new Router();
        router.setPage(ConfigurationManager.PAGE_PATH.getProperty(PAGE_CONTACT));
        return router;
    }
}
