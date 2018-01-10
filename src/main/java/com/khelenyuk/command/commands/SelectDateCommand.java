package com.khelenyuk.command.commands;

import com.khelenyuk.command.ActionCommand;
import com.khelenyuk.entity.User;
import com.khelenyuk.service.PageLogic;
import com.khelenyuk.servlet.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class SelectDateCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SelectDateCommand.class);

    private static final String PARAM_NAME_SELECTED_DATE = "chosenDate";


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        session.setAttribute("chosenDateSession", LocalDate.parse(
                request.getParameter(PARAM_NAME_SELECTED_DATE).toString()));

        PageLogic.updatePageData(session, ((User)session.getAttribute("user")).getId());

        return page;
    }

}