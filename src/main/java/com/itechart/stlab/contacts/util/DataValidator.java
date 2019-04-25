package com.itechart.stlab.contacts.util;

import com.itechart.stlab.contacts.model.Contact;

import javax.servlet.http.Part;
import java.time.LocalDate;
import java.util.List;

public class DataValidator {

    private static final String NAME_REGEX = "[A-Z]{1}[a-z]{1,19}|[А-ЯЁ]{1}[а-яё]{1,19}";
    private static String DATE_PATTERN =
            "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

    public static boolean validateDate(String date){
            return date != null && date.matches(DATE_PATTERN);
    }

    public static boolean validateAddContactParameters(
            String firstname, String surname, LocalDate birthday, Contact.Gender gender, Contact.MaritalStatus maritalStatus){
        return firstname != null && surname != null && birthday != null && gender != null && maritalStatus!= null;
    }

    public static boolean validateSelectedPhones(String selectedPhones) {
        return !selectedPhones.equals("") && selectedPhones != null;
    }

    public static boolean validateSelectedAttachments(String selectedAttachments) {
        return !selectedAttachments.equals("") && selectedAttachments != null;
    }

    public static boolean validatePhoneParameters(String[] countryCodes, String[] phoneCodes, String[] numbers,
                                                  String[] typeCodes, String[] selectedAttachment){
        return  countryCodes != null && phoneCodes != null && numbers != null
                && typeCodes != null && selectedAttachment != null;
    }

    public static boolean validateAttachmentParameters(List<Part> fileParts, String[] uploadDate, String[] comments){
        return  fileParts.size()!=0 && uploadDate != null && comments != null;
    }

    public static boolean validateEmailParameters(String[] contacts){
        return  contacts != null;
    }
}
