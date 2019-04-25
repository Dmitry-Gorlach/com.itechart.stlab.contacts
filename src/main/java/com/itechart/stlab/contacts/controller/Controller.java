package com.itechart.stlab.contacts.controller;

import com.itechart.stlab.contacts.command.ActionFactory;
import com.itechart.stlab.contacts.command.Command;
import com.itechart.stlab.contacts.exception.CommandException;
import com.itechart.stlab.contacts.exception.LogicException;
import com.itechart.stlab.contacts.manager.ConfigurationManager;
import com.itechart.stlab.contacts.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.itechart.stlab.contacts.util.Constants.*;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
@MultipartConfig(fileSizeThreshold=MEMORY_THRESHOLD,
                maxFileSize=MAX_FILE_SIZE,
                maxRequestSize=MAX_REQUEST_SIZE)
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Router router;
        try {
            Command command = ActionFactory.defineCommand(request.getParameter("command"));
            router = command.execute(request);
        }catch (CommandException | LogicException e){
            router = new Router();
            router.setPage(ConfigurationManager.PAGE_PATH.getProperty("page.error"));
            router.setRoute(Router.RouteType.REDIRECT);
            LOGGER.log(Level.ERROR, e.getMessage(),e);
        }

        switch (router.getRoute()){
            case FORWARD:
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(router.getPage());
                dispatcher.forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + router.getPage());
                break;
        }

    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
