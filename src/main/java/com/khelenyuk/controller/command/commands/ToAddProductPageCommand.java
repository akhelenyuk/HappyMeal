package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToAddProductPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.addproduct");
        return page;
    }
}
