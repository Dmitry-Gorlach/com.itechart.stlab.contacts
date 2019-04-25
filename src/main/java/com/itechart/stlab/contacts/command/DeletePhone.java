package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.PhoneNumberLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.itechart.stlab.contacts.util.DataValidator.validateSelectedPhones;

public class DeletePhone implements Command {
    private final static String PAGE_CONTACT="page.contact";
    private PhoneNumberLogic phoneNumberLogic;

    public DeletePhone(PhoneNumberLogic phoneNumberLogic) {
        this.phoneNumberLogic = phoneNumberLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException, IOException {
        Router router = new Router();
        try {
            request.getParts();

            String selectedPhones= request.getParameter("selected_phones");
            if(validateSelectedPhones(selectedPhones)) {
                String delimeter = ",";
                String[] phoneId;
                phoneId = selectedPhones.split(delimeter);

                for (String id : phoneId) {
                    phoneNumberLogic.delete(Integer.valueOf(id));
                }
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return refreshRedirect(ConfigurationManager.PAGE_PATH.getProperty(PAGE_CONTACT));
    }
}
