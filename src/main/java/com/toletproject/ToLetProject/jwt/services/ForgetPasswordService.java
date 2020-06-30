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

            String emailBody1 = "" +
                    "<tr style=\"border-collapse:collapse\">\n" +
                    "    <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                    "        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                    "            <tbody>\n" +
                    "            <tr style=\"border-collapse:collapse\">\n" +
                    "                <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                    "\n" +
                    "            </tr>\n" +
                    "            </tbody>\n" +
                    "        </table>\n" +
                    "        <table class=\"es-header\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:#7C72DC;background-repeat:repeat;background-position:center top\">\n" +
                    "            <tbody>\n" +
                    "            <tr style=\"border-collapse:collapse\">\n" +
                    "                <td style=\"padding:0;Margin:0;background-color:#7C72DC\" bgcolor=\"#7c72dc\" align=\"center\">\n" +
                    "                    <table class=\"es-header-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#7C72DC;width:600px\">\n" +
                    "                        <tbody>\n" +
                    "                        <tr style=\"border-collapse:collapse\">\n" +
                    "                            <td align=\"left\" style=\"Margin:0;padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:20px\">\n" +
                    "                                <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                                    <tbody>\n" +
                    "                                    <tr style=\"border-collapse:collapse\">\n" +
                    "                                        <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:580px\">\n" +
                    "                                            <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                                                <tbody>\n" +
                    "                                                <tr style=\"border-collapse:collapse\">\n" +
                    "                                                    <td align=\"center\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:36px;font-family:'comic sans ms', 'marker felt-thin', arial, sans-serif;line-height:54px;color:#FFFFFF\"><strong>Basakhujun.com</strong></p></td>\n" +
                    "                                                </tr>\n" +
                    "                                                </tbody>\n" +
                    "                                            </table></td>\n" +
                    "                                    </tr>\n" +
                    "                                    </tbody>\n" +
                    "                                </table></td>\n" +
                    "                        </tr>\n" +
                    "                        </tbody>\n" +
                    "                    </table></td>\n" +
                    "            </tr>\n" +
                    "            </tbody>\n" +
                    "        </table>\n" +
                    "        <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                    "            <tbody>\n" +
                    "            <tr style=\"border-collapse:collapse\">\n" +
                    "                <td style=\"padding:0;Margin:0;background-color:#7C72DC\" bgcolor=\"#7c72dc\" align=\"center\">\n" +
                    "                    <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                    "                        <tbody>\n" +
                    "                        <tr style=\"border-collapse:collapse\">\n" +
                    "                            <td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                    "                                <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                                    <tbody>\n" +
                    "                                    <tr style=\"border-collapse:collapse\">\n" +
                    "                                        <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                    "                                            <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#FFFFFF;border-radius:4px\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" role=\"presentation\">\n" +
                    "                                                <tbody>\n" +
                    "                                                <tr style=\"border-collapse:collapse\">\n" +
                    "                                                    <td align=\"center\" style=\"Margin:0;padding-bottom:5px;padding-left:30px;padding-right:30px;padding-top:35px\"><h1 style=\"Margin:0;line-height:58px;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:48px;font-style:normal;font-weight:normal;color:#111111\">Hello," + userOptional.get().getName() + "&nbsp;<br></h1><h1 style=\"Margin:0;line-height:58px;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:48px;font-style:normal;font-weight:normal;color:#111111\">Trouble signing in?</h1></td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr style=\"border-collapse:collapse\">\n" +
                    "                                                    <td bgcolor=\"#ffffff\" align=\"center\" style=\"Margin:0;padding-top:5px;padding-bottom:5px;padding-left:20px;padding-right:20px;font-size:0\">\n" +
                    "                                                        <table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                                                            <tbody>\n" +
                    "                                                            <tr style=\"border-collapse:collapse\">\n" +
                    "                                                                <td style=\"padding:0;Margin:0px;border-bottom:1px solid #FFFFFF;background:#FFFFFFnone repeat scroll 0% 0%;height:1px;width:100%;margin:0px\"></td>\n" +
                    "                                                            </tr>\n" +
                    "                                                            </tbody>\n" +
                    "                                                        </table></td>\n" +
                    "                                                </tr>\n" +
                    "                                                </tbody>\n" +
                    "                                            </table></td>\n" +
                    "                                    </tr>\n" +
                    "                                    </tbody>\n" +
                    "                                </table></td>\n" +
                    "                        </tr>\n" +
                    "                        </tbody>\n" +
                    "                    </table></td>\n" +
                    "            </tr>\n" +
                    "            </tbody>\n" +
                    "        </table>\n" +
                    "        <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                    "            <tbody>\n" +
                    "            <tr style=\"border-collapse:collapse\">\n" +
                    "                <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                    "                    <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                    "                        <tbody>\n" +
                    "                        <tr style=\"border-collapse:collapse\">\n" +
                    "                            <td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                    "                                <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                                    <tbody>\n" +
                    "                                    <tr style=\"border-collapse:collapse\">\n" +
                    "                                        <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                    "                                            <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" role=\"presentation\">\n" +
                    "                                                <tbody>\n" +
                    "                                                <tr style=\"border-collapse:collapse\">\n" +
                    "                                                    <td class=\"es-m-txt-l\" bgcolor=\"#ffffff\" align=\"left\" style=\"Margin:0;padding-bottom:15px;padding-top:20px;padding-left:30px;padding-right:30px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:18px;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#666666\">Resetting your password is easy. Enter the forget password key to your app screen &amp; reset your password.</p></td>\n" +
                    "                                                </tr>\n" +
                    "                                                </tbody>\n" +
                    "                                            </table></td>\n" +
                    "                                    </tr>\n" +
                    "                                    </tbody>\n" +
                    "                                </table></td>\n" +
                    "                        </tr>\n" +
                    "                        <tr style=\"border-collapse:collapse\">\n" +
                    "                            <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:20px;padding-left:30px;padding-right:30px\">\n" +
                    "                                <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                                    <tbody>\n" +
                    "                                    <tr style=\"border-collapse:collapse\">\n" +
                    "                                        <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:540px\">\n" +
                    "                                            <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                                                <tbody>\n" +
                    "                                                <tr style=\"border-collapse:collapse\">\n" +
                    "                                                    <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:28px;font-family:'comic sans ms', 'marker felt-thin', arial, sans-serif;line-height:42px;color:#666666;text-align:center\"><strong>" + random_int + "<br><h2>Thanks, From <b>BasaKhujun Team</b></h2></strong></p></td>\n" +
                    "                                                </tr>\n" +
                    "                                                </tbody>\n" +
                    "                                            </table></td>\n" +
                    "                                    </tr>\n" +
                    "                                    </tbody>\n" +
                    "                                </table></td>\n" +
                    "                        </tr>\n" +
                    "                        </tbody>\n" +
                    "                    </table></td>\n" +
                    "            </tr>\n" +
                    "            </tbody>\n" +
                    "        </table>\n";

            String emailSubject = "Password Reset Code";

            sendMail(userOptional.get().getEmail(), userOptional.get().getName(), emailSubject, emailBody1);

            return userOptional.get().getEmail();
        } else {
            return "User Not Found";
        }
    }

    public String sendMail(String sendTo, String name, String subject, String body) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("basakhujunteam@gmail.com", "Basa Khujun Team");

        helper.setTo(sendTo);

        helper.setSubject(subject);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // helper.setText(body, true);
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
