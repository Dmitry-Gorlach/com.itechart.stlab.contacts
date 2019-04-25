package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.logic.AttachmentLogic;
import com.itechart.stlab.contacts.manager.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import static com.itechart.stlab.contacts.util.DataValidator.validateSelectedAttachments;

public class DeleteAttachment implements Command {
    private final static String PAGE_CONTACT="page.contact";
    private AttachmentLogic attachmentLogic;

    public DeleteAttachment(AttachmentLogic attachmentLogic) {
        this.attachmentLogic = attachmentLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws LogicException, IOException, ServletException {

        try {
            request.getParts();

            String selectedAttachments= request.getParameter("selected_attachments");
            if(validateSelectedAttachments(selectedAttachments)) {
                String delimeter = ",";
                String[]attachmentId;
               attachmentId = selectedAttachments.split(delimeter);

                for (String id : attachmentId) {
                    attachmentLogic.delete(Integer.valueOf(id));
                }
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return refreshRedirect(ConfigurationManager.PAGE_PATH.getProperty(PAGE_CONTACT));
    }
}
