package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.service.IPageService;
import com.khelenyuk.model.User;
import com.khelenyuk.controller.service.IUserService;
import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.controller.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAdminPageCommand implements ActionCommand {
    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * select all users from DB and forwards to admin page
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.admin");

        pageService.updateAdminPageData(request);
        return page;
    }
}
