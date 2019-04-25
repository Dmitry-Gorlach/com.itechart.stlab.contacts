package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.manager.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Command {

    Router execute(HttpServletRequest request) throws LogicException, IOException, ServletException;

    default Router refreshForward(String page) {
        Router router = new Router();
        router.setPage(page);
        return router;
    }

    default Router refreshRedirect(String page){
        Router router = new Router();
        router.setPage(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }

    default Router redirectIndexPage(){
        Router router = new Router();
        router.setPage(ConfigurationManager.PAGE_PATH.getProperty("page.index"));
        return router;
    }

}
