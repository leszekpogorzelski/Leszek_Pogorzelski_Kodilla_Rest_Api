package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks.");
        functionality.add("Provides connection with Trello account.");
        functionality.add("Application allows sending tasks to Trello.");

        List<String> information = new ArrayList<>();
        information.add("This email was generated automatically.");
        information.add("Please do not respond.");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Thank you for using our app!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_email", companyConfig.getCompanyMail());
        context.setVariable("admin_config", adminConfig);
        if (message.contains("currently")) {
            context.setVariable("show_button", false);
            context.setVariable("is_friend", true);
            context.setVariable("application_functionality", information);

        } else {
            context.setVariable("show_button", true);
            context.setVariable("is_friend", false);
            context.setVariable("application_functionality", functionality);
        }
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}

