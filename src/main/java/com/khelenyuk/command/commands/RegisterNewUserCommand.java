package com.khelenyuk.command.commands;

import com.khelenyuk.command.ActionCommand;
import com.khelenyuk.entity.User;
import com.khelenyuk.service.LoginRegistrationLogic;
import com.khelenyuk.service.UserLogic;
import com.khelenyuk.servlet.ConfigurationManager;
import com.khelenyuk.servlet.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class RegisterNewUserCommand implements ActionCommand {
    private static final String PARAM_NAME_FIRST_NAME = "first_name";
    private static final String PARAM_NAME_LAST_NAME = "last_name";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PASSWORD_CONFIRM = "password_confirmation";
    private static final String PARAM_NAME_BIRTHDAY = "birthday";
    private static final String PARAM_NAME_WEIGHT = "weight";
    private static final String PARAM_NAME_HEIGHT = "height";
    private static final String PARAM_NAME_SEX = "sex";
    private static final String PARAM_NAME_LIFESTYLE = "lifestyle";

    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.registration");;
        session = request.getSession();

        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String password_confirm = request.getParameter(PARAM_NAME_PASSWORD_CONFIRM);
        String login = request.getParameter(PARAM_NAME_LOGIN);

        if (!LoginRegistrationLogic.confirmPassword(password, password_confirm)) {
            session.setAttribute("errorPassConfirmMessage", MessageManager.getProperty("message.passconfirmerror"));
            return page;
        }

        if (LoginRegistrationLogic.checkLoginExist(login)) {
            session.setAttribute("errorLoginExistMessage", MessageManager.getProperty("message.loginexisterror"));
            return page;
        }
        User user = new User(
                request.getParameter(PARAM_NAME_FIRST_NAME),
                request.getParameter(PARAM_NAME_LAST_NAME),
                login,
                password,
                request.getParameter(PARAM_NAME_EMAIL),
                Date.valueOf(request.getParameter(PARAM_NAME_BIRTHDAY)),
                Integer.valueOf(request.getParameter(PARAM_NAME_SEX)),
                Integer.valueOf(request.getParameter(PARAM_NAME_WEIGHT)),
                Integer.valueOf(request.getParameter(PARAM_NAME_HEIGHT)),
                Integer.valueOf(request.getParameter(PARAM_NAME_LIFESTYLE))
        );
        user.setCalorieNorm(2222);
        if(UserLogic.addUser(user)){
            session.setAttribute("registrationSuccessMessage", MessageManager.getProperty("message.registrationconfirm"));
            page = ConfigurationManager.getProperty("path.page.login");
        } else{
            session.setAttribute("errorRegistrationMessage", MessageManager.getProperty("message.registrationerror"));
        }


        return page;
    }
}