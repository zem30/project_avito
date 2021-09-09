package com.amr.project.service.email;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.Mail;
import com.amr.project.model.entity.User;
import com.amr.project.util.EmailVerificationTokenGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EmailVerificationService {

    private UserDao userDao;
    private EmailSenderService senderService;

    @Transactional
    public String sendVerificationEmail(User createdUser) {
        createdUser.setActivationCode(EmailVerificationTokenGenerator.generate());
        Mail verificationMessage = new Mail();
        verificationMessage.setTo(createdUser.getEmail());
        verificationMessage.setMessage("<a href=http://localhost:8888/regitrationConfirm?email=" +
                createdUser.getEmail() + "&token=" + createdUser.getActivationCode() +
                ">Verify email and activate account</a>");
        verificationMessage.setSubject("Verify new account with e-mail: " + createdUser.getEmail());
        userDao.update(createdUser);
        return senderService.sendEmailWithHTML(verificationMessage);
    }

    @Transactional
    public String checkActivationCode(String email, String activationCode) {
        String result = "";
        User checkedUser = userDao.getByEmail(email);
        if (checkedUser != null) {
            if (!checkedUser.isActivate()) {
                if (checkedUser.getActivationCode().equals(activationCode)) {
                    checkedUser.setActivate(true);
                    userDao.update(checkedUser);
                    result = "Email " + email + " successfully confirm!!! You can log in your account";
                } else {
                    result = "Activation code incorrect!!!";
                }
            } else {
                result = "Email " + email + " already verified and account already activated!!!";
            }
        } else {
            result = "User with email " + email + " not found!!!";
        }
        return result;
    }
}
