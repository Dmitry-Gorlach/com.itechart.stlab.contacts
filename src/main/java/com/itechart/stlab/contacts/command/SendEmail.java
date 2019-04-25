package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;
import com.itechart.stlab.contacts.model.Contact;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import static com.itechart.stlab.contacts.manager.ConfigurationManager.PROPERTIES;
import static com.itechart.stlab.contacts.util.DataValidator.validateEmailParameters;

public class SendEmail implements Command {
    private final static String PAGE_SUCCESS_SENT_EMAIL="page.send.email.extra";
    private static final String PAGE_SEND_EMAIL = "page.send.email";
    private static final String USERNAME = "mail.username";
    private static final String PASSWORD = "mail.password";
    private static Logger LOGGER = LogManager.getLogger(SendEmail.class);
    private ContactLogic contactLogic;

    public SendEmail(ContactLogic contactLogic) {
        this.contactLogic = contactLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException, IOException, ServletException {
        String subject = request.getParameter("subject");
        String messageText = request.getParameter("message");
        String[] contacts = request.getParameterValues("contactId");
        final StringTemplate helloTemplate = new StringTemplate("Hello, $name$", DefaultTemplateLexer.class);
        final StringTemplate meetingTemplate = new StringTemplate("Dear $name$ invite you to meeting today.", DefaultTemplateLexer.class);
        final String templateName = request.getParameter("template");
        final StringTemplate stringTemplate;
        switch (templateName) {
            case ("Hello"):
                stringTemplate = helloTemplate;
                break;
            case ("Invitation"):
                stringTemplate = meetingTemplate;
                break;
            default:
                stringTemplate = new StringTemplate(messageText, DefaultTemplateLexer.class);
                break;
        }
        if(validateEmailParameters(contacts)) {
            for (String contactId : contacts) {
                Optional<Contact> contact = contactLogic.findContactById(Integer.parseInt(contactId));
                stringTemplate.setAttribute("name", contact.get().getFirstName());
                    Properties properties = new Properties();
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", "smtp.gmail.com");
                    properties.put("mail.smtp.port", "587");
                    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(PROPERTIES.getProperty(USERNAME), PROPERTIES.getProperty(PASSWORD));
                        }
                    });
                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(USERNAME));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(contact.get().getEmail()));
                        message.setSubject(subject);
                        message.setText(stringTemplate.toString());
                        Transport.send(message);
                        LOGGER.log(Level.INFO,"Email sent successfully.");
                        String emailSuccess = "Email sent successfully!!";
                        request.setAttribute("emailSuccess",emailSuccess);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
            }
        }else{
            String infoMessage = "You cannot send email, try choose some contacts";
            request.setAttribute("infoMessage", infoMessage);
            return refreshForward(ConfigurationManager.PAGE_PATH.getProperty(PAGE_SEND_EMAIL));
        }
        stringTemplate.removeAttribute("name");
        String successMessage = "Successfully! Message has been sent!";
        HttpSession session = request.getSession();
        session.setAttribute("successMessage", successMessage);
        return refreshRedirect(ConfigurationManager.PAGE_PATH.getProperty(PAGE_SUCCESS_SENT_EMAIL));
    }
}
