package com.toletproject.ToLetProject.jwt.services;

import com.toletproject.ToLetProject.jwt.dto.request.GenerateOTPRequest;
import com.toletproject.ToLetProject.jwt.dto.request.GenerateOTPRequest1;
import com.toletproject.ToLetProject.jwt.dto.request.GenerateOTPRequest2;
import com.toletproject.ToLetProject.jwt.model.User;
import com.toletproject.ToLetProject.jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Optional;


@Service
@AllArgsConstructor

public class ForgetPasswordService {

    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    PasswordEncoder encoder;

    public String generateOTP(GenerateOTPRequest generateOTPRequest) throws IOException, MessagingException {
        Optional<User> userOptional = userRepository.findByEmail(generateOTPRequest.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            int random_int = (int) (Math.random() * (999999 - 100000 + 1) + 100000);

            user.setGeneratedOTP(random_int);
            user.setPasswordReset(true);
            userRepository.save(user);

            String emailBody = "" +
                    "<h3>Hello <b>" + userOptional.get().getName() + "</b>,</h3>" +
                    "<h2>The password reset code is <b>" + random_int + "</b> . </h2>" +
                    "<br><h3>Thank You </h3>";

            String emailSubject = "Password Reset Code";

            sendMail(userOptional.get().getEmail(), userOptional.get().getName(), emailSubject, emailBody);

            return userOptional.get().getEmail();
        } else {
            return "User Not Found";
        }
    }

    public String sendMail(String sendTo, String name, String subject, String body) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("contact@aalhabib01.xyz", "Abdullah AL Habib");

        helper.setTo(sendTo);

        helper.setSubject(subject);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        helper.setText(body, true);

        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));


        javaMailSender.send(msg);
        return "Send to " + sendTo;
    }

    public String verifyOTP(GenerateOTPRequest1 generateOTPRequest) {
        Optional<User> userOptional = userRepository.findByEmail(generateOTPRequest.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getGeneratedOTP() == generateOTPRequest.getOtp() && user.isPasswordReset()) {
                return "Matched";
            } else {
                return "Not Matched";
            }
        } else {
            return "Not Matched";
        }
    }

    public String forgetPassChange(GenerateOTPRequest2 generateOTPRequest) throws IOException, MessagingException {
        Optional<User> userOptional = userRepository.findByEmail(generateOTPRequest.getEmail());


        User user = userOptional.get();
        if (user.getGeneratedOTP() == generateOTPRequest.getOtp() && user.isPasswordReset()) {

            user.setPassword(encoder.encode(generateOTPRequest.getNewPassword()));
            user.setGeneratedOTP(0);
            user.setPasswordReset(false);

            userRepository.save(user);

            String emailBody = "" +
                    "<h3>Hello <b>" + userOptional.get().getName() + "</b>,</h3>" +
                    "<h2>Your password has been changed successfully! </h2>" +
                    "<br><h3>Thank You </h3>";

            String emailSubject = "Password Changed";

            sendMail(userOptional.get().getEmail(), userOptional.get().getName(), emailSubject, emailBody);

            return "Password Changed";
        } else {
            return "Not Matched";
        }

    }
}
