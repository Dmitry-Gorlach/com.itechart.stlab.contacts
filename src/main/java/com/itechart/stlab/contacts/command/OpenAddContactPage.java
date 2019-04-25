package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.manager.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class OpenAddContactPage implements Command {
    private static final String PAGE_ADD_CONTACT= "page.add.contact";

    @Override
    public Router execute(HttpServletRequest request) throws LogicException, IOException, ServletException {

        Router router = new Router();
        router.setPage(ConfigurationManager.PAGE_PATH.getProperty(PAGE_ADD_CONTACT));
        return router;
    }
}
