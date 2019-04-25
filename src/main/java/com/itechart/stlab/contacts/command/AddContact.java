package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;
import com.itechart.stlab.contacts.model.Contact;
import com.itechart.stlab.contacts.model.Contact.Gender;
import com.itechart.stlab.contacts.util.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.itechart.stlab.contacts.util.DataValidator.validateAddContactParameters;

public class AddContact implements Command {

    private static final String PARAM_FIRSTNAME = "first_name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_LASTNAME = "last_name";
    private static final String PARAM_BIRTHDAY = "birthday";
    private static final String PARAM_GENDER = "gender";
    private static final String PARAM_NATIONALITY = "nationality";
    private static final String PARAM_MARITAL_STATUS = "marital_status";
    private static final String PARAM_COMPANY = "company";
    private static final String PARAM_WEBSITE = "website";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_COUNTRY = "country";
    private static final String PARAM_CITY = "city";
    private static final String PARAM_STREET = "street";
    private static final String PARAM_HOUSE = "house";
    private static final String PARAM_FLAT = "flat";
    private static final String PARAM_POSTCODE = "postcode";
    private static Logger LOGGER = LogManager.getLogger(SendEmail.class);

    private final static String PAGE_MAIN="page.index";
    private static final String PAGE_ADD_CONTACT= "page.add.contact";
    private static final String PAGE_SUCCESS_ADD_CONTACT= "page.add.contact.extra";
    private ContactLogic contactLogic;

    public AddContact(ContactLogic contactLogic) {
        this.contactLogic = contactLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException {
       String firstname = request.getParameter(PARAM_FIRSTNAME);
       String surname = request.getParameter(PARAM_SURNAME);
       String lastname = request.getParameter(PARAM_LASTNAME);
       String date = request.getParameter(PARAM_BIRTHDAY);

        if(!DataValidator.validateDate(date)){
            request.setAttribute("messageAddContactError", ConfigurationManager.LOCALE_EN.getProperty("message.add.contact.error"));
            return refreshForward(ConfigurationManager.PAGE_PATH.getProperty(PAGE_ADD_CONTACT));
        }
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       LocalDate birthday = LocalDate.parse(request.getParameter(PARAM_BIRTHDAY),formatter);
        Gender gender = Gender.valueOf(request.getParameter(PARAM_GENDER));
       String nationality = request.getParameter(PARAM_NATIONALITY);
       Contact.MaritalStatus maritalStatus = Contact.MaritalStatus.valueOf(request.getParameter(PARAM_MARITAL_STATUS));
       String company = request.getParameter(PARAM_COMPANY);
       String website = request.getParameter(PARAM_WEBSITE);
       String email = request.getParameter(PARAM_EMAIL);
       String country = request.getParameter(PARAM_COUNTRY);
       String city = request.getParameter(PARAM_CITY);
       String street = request.getParameter(PARAM_STREET);
       String house = request.getParameter(PARAM_HOUSE);
       String flat = request.getParameter(PARAM_FLAT);
       String postcode = request.getParameter(PARAM_POSTCODE);

        if(validateAddContactParameters(firstname,surname,birthday, gender, maritalStatus)) {
            Contact contact = contactLogic.add(firstname, surname, lastname, birthday, gender, nationality, maritalStatus,
                    company, website, email, country, city, street, house, flat, postcode);
        }
        LOGGER.log(Level.INFO, "Contact was created successfully.");
        String successMessage = "Successfully! New contact was created!";
        HttpSession session = request.getSession();
        session.setAttribute("successMessage", successMessage);

        return refreshRedirect(ConfigurationManager.PAGE_PATH.getProperty(PAGE_SUCCESS_ADD_CONTACT));
    }
}
