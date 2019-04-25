package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.AttachmentLogic;
import com.itechart.stlab.contacts.logic.ContactLogic;
import com.itechart.stlab.contacts.logic.PhoneNumberLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;
import com.itechart.stlab.contacts.model.Contact;
import com.itechart.stlab.contacts.model.PhoneNumber;
import com.itechart.stlab.contacts.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.itechart.stlab.contacts.util.Constants.UPLOAD_ATTACHMENT_DIRECTORY;
import static com.itechart.stlab.contacts.util.Constants.UPLOAD_DIRECTORY;
import static com.itechart.stlab.contacts.util.DataValidator.validateAttachmentParameters;
import static com.itechart.stlab.contacts.util.DataValidator.validatePhoneParameters;

public class UpdateContact implements Command {

    private static Logger LOGGER = LogManager.getLogger(UpdateContact.class);
    private static final String PARAM_FIRSTNAME = "first_name";
    private static final String PARAM_LASTNAME = "last_name";
    private static final String PARAM_SURNAME = "surname";
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
    private static final String PARAM_PHOTO = "photo_uri";
    private final static String PAGE_MAIN="page.index";

    private final static String PARAM_COUNTRY_CODE="country_code";
    private final static String PARAM_PHONE_CODE="phone_code";
    private final static String PARAM_NUMBER="number";
    private final static String PARAM_TYPE_PHONE="type_phone";
    private final static String PARAM_COMMENT="comment";

    private final static String PARAM_ATTACHMENT_NAME = "name_attachment";
    private final static String PARAM_ATTACHMENT_DATE_UPLOAD = "date_upload";
    private final static String PARAM_ATTACHMENT_COMMENT = "comment";

    private ContactLogic contactLogic;
    private PhoneNumberLogic phoneNumberLogic;
    private AttachmentLogic attachmentLogic;


    public UpdateContact(ContactLogic contactLogic, PhoneNumberLogic phoneNumberLogic, AttachmentLogic attachmentLogic) {
        this.contactLogic = contactLogic;
        this.phoneNumberLogic = phoneNumberLogic;
        this.attachmentLogic = attachmentLogic;
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return Constants.DEFAULT_FILENAME;
    }


    @Override
    public Router execute(HttpServletRequest request) throws LogicException, IOException, ServletException {
        String firstname = request.getParameter(PARAM_FIRSTNAME);
        String lastname = request.getParameter(PARAM_LASTNAME);
        String surname = request.getParameter(PARAM_SURNAME);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(request.getParameter(PARAM_BIRTHDAY), formatter);
        Contact.Gender gender = Contact.Gender.valueOf(request.getParameter(PARAM_GENDER));
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
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id", id);

        String[] countryCodes = request.getParameterMap().get(PARAM_COUNTRY_CODE);
        String[] phoneCodes = request.getParameterMap().get(PARAM_PHONE_CODE);
        String[] numbers = request.getParameterMap().get(PARAM_NUMBER);
        String[] typeCodes = request.getParameterMap().get(PARAM_TYPE_PHONE);
        String[] comments = request.getParameterMap().get(PARAM_COMMENT);
        String[] selectedPhones= request.getParameterMap().get("selected-phone");
        String[] selectedAttachment= request.getParameterMap().get("selectedAttachment");



        if(validatePhoneParameters(countryCodes,phoneCodes,numbers,typeCodes, selectedPhones)) {
            for (int i = 0; i < countryCodes.length; i++) {
                if (phoneNumberLogic.findPhoneNumberById(Integer.parseInt(selectedPhones[i])) != null && selectedPhones != null ) {
                    phoneNumberLogic.update(countryCodes[i],
                            phoneCodes[i],
                            numbers[i],
                            PhoneNumber.TypeCode.valueOf(typeCodes[i]),
                            comments[i],
                            Integer.parseInt(selectedPhones[i]));
                } else {
                    phoneNumberLogic.add(countryCodes[i],
                            phoneCodes[i],
                            numbers[i],
                            PhoneNumber.TypeCode.valueOf(typeCodes[i]),
                            comments[i],
                            id);
                }
            }
        }

        String uploadPath = "C:\\Users\\User\\IdeaProjects\\contacts\\src\\main\\webapp\\jsp" + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();

        try {
            Part part = request.getPart("upload-photo");
            String fileName = "";
            if(part != null) {
                fileName = UUID.randomUUID().toString() + getFileName(part);
                part.write(uploadPath + File.separator + fileName);
                request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
                String photoPath = fileName;
                contactLogic.editContact(id, firstname, lastname, surname, birthday, gender, nationality, maritalStatus,
                        company, website, email, photoPath, country, city, street, house, flat, postcode);
            }else {
                contactLogic.editContact(id,firstname, lastname, surname, birthday, gender, nationality, maritalStatus,
                        company, website, email,  fileName,country, city, street, house, flat, postcode);
            }
        } catch (FileNotFoundException fne) {
            request.setAttribute("message", "There was an error: " + fne.getMessage());
        }

//        String[] attachmentNames = request.getParameterMap().get(PARAM_ATTACHMENT_NAME);
//        String[] dateUpload = request.getParameterMap().get(PARAM_ATTACHMENT_DATE_UPLOAD);
//        String[] attachmentComments = request.getParameterMap().get(PARAM_ATTACHMENT_COMMENT);
//        String[] selectedAttachment= request.getParameterMap().get("selectedAttachments");
//
//        if(validateAttachmentParameters(attachmentNames, dateUpload, attachmentComments)) {
//            for (int i = 0; i < attachmentNames.length; i++) {
//                if (attachmentLogic.findAttachmentByContactId(Integer.parseInt(selectedPhones[i])) != null && selectedAttachment != null ) {
//                    attachmentLogic.update(attachmentNames[i],
//                            dateUpload[i],
//                            numbers[i],
//                            comments[i],
//                            Integer.parseInt(selectedAttachment[i]));
//                } else {
//                    phoneNumberLogic.add(countryCodes[i],
//                            phoneCodes[i],
//                            numbers[i],
//                            PhoneNumber.TypeCode.valueOf(typeCodes[i]),
//                            comments[i],
//                            id);
//                }
//            }
//        }

        String uploadAttachmentPath = "C:\\Users\\User\\IdeaProjects\\contacts\\src\\main\\webapp\\jsp" + File.separator + UPLOAD_ATTACHMENT_DIRECTORY;
        createUploadDir(uploadAttachmentPath);
        String filename = "";
        final String[] commentAttachment = request.getParameterValues("commentAttachment");
        final String[] dateAttachment = request.getParameterValues("date_upload");

//        final LocalDate dateAttachment = LocalDate.parse(request.getParameter("date_upload"), formatter);
        List<Part> fileParts = request.getParts().stream().filter(part -> "uploadAttachment".equals(part.getName())).collect(Collectors.toList());
        if(validateAttachmentParameters(fileParts, dateAttachment, commentAttachment)) {
            for (int i = 0; i < dateAttachment.length; i++) {
//                if (attachmentLogic.findAttachmentByContactId(Integer.parseInt(selectedAttachment[i])) != null && selectedAttachment != null ) {
//                    for (Part filePart : fileParts) {
//                        filename = UUID.randomUUID().toString() + getFileName(filePart);
//                        filePart.write(uploadAttachmentPath + File.separator + filename);
//                        String attachmentPath = filename;
//                        LocalDate fileDate = LocalDate.parse(dateAttachment[i], formatter);
//                        String comment = commentAttachment[i];
//                        attachmentLogic.update(attachmentPath, fileDate, comment, id);
//                    }
//                }else{
                    for (Part filePart : fileParts) {
                        filename = UUID.randomUUID().toString() + getFileName(filePart);
                        filePart.write(uploadAttachmentPath + File.separator + filename);
                        String attachmentPath = filename;
                        LocalDate fileDate = LocalDate.parse(dateAttachment[i], formatter);
                        String comment = commentAttachment[i];
                        attachmentLogic.add(attachmentPath, fileDate, comment, id);
                    }
//                }
            }
        }


        return  refreshRedirect(ConfigurationManager.PAGE_PATH.getProperty(PAGE_MAIN));
    }

    public File createUploadDir(String uploadPath){
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();
        return uploadDir;
    }

}
