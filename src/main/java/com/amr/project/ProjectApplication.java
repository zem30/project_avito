package com.amr.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(exclude = {SessionAutoConfiguration.class, SecurityAutoConfiguration.class})
@EnableAsync
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    /*
    Testing simple and verification email's sending
     */

//    @Autowired
//    private EmailVerificationService verificationService;
//
//    @Autowired
//    private EmailSenderService senderService;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendTestSimpleEmail() throws MessagingException {
//        Mail testMessage = new Mail();
//        testMessage.setTo("zart208@mail.ru");
//        testMessage.setSubject("Testing simple email subject");
//        testMessage.setMessage("Testing simple email message");
//        System.out.println(senderService.sendSimpleEmail(testMessage));
//    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendTestVerificationEmail() throws MessagingException {
//        User user = new User(1L, "zart208@mail.ru", "zart208");
//        user.setPassword("Q123456y");
//        System.out.println(verificationService.sendVerificationEmail(user));
//    }
}
