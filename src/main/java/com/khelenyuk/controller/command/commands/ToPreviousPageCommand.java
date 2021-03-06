package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.controller.service.IPageService;
import com.khelenyuk.controller.service.IUserService;
import com.khelenyuk.controller.service.factory.ServiceFactory;
import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

//TODO I think I need a Factory here.
public class ToPreviousPageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SelectDateCommand.class);

    private IUserService userService = ServiceFactory.getUserService();
    private IPageService pageService = ServiceFactory.getPageService();


    /**
     * checks session for 'previous page' attribute and returns its value
     * @return previous page
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String page = session.getAttribute("previousPage").toString();
        logger.info("Requested 'previous' page: " + page);

        return page;
    }
}
