package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SimpleEmailService simpleEmailService;

    private final static String SUBJECT = "Once a day mail.";

    @Scheduled(fixedDelay = 10000/*, cron = "0 0 0 10 * *"*/)
    public void sendInformationEmail(){
    long size = taskRepository.count();
    StringBuilder task = new StringBuilder();
    task.append("task");
    if (size > 1) {
        task.append("s");
    }
    simpleEmailService.send(new Mail(adminConfig.getAdminMail(),
            "",
            SUBJECT,
            "currently you have " + size + " " + task + " stored in db."));
    }

}
