package com.itechart.stlab.contacts.command;


import com.itechart.stlab.contacts.controller.Router;
import com.itechart.stlab.contacts.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command{
	private static final String PARAM_LANGUAGE = "locale";

	@Override
	public Router execute(HttpServletRequest request) throws LogicException {
		HttpSession session = request.getSession();
		String locale = request.getParameter(PARAM_LANGUAGE);
		session.setAttribute(PARAM_LANGUAGE, locale);
		return redirectIndexPage();
	}
	
	

}
