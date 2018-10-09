package com.management.parkingmanagement.service;

import com.management.parkingmanagement.exception.EmailServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    // @Value("${email.server}")
    private String emailServer;

    // @Value("${email.from}")
    private String emailFrom;

    public String sendHTMLEmail(final String destiny, final String message, final String subject)
            throws EmailServiceException {
        log.info("Sending e-mail to: {}", destiny);

        if (StringUtils.isBlank(destiny) || StringUtils.isBlank(message) || StringUtils.isBlank(subject)) {
            log.error("One or more fields are blank, send HTML e-mail failed, destiny: {}, message: {} and subject: {}",
                    destiny, message, subject);
            throw new EmailServiceException("One or more fields are blank, send HTML e-mail failed");
        }

        String[] destinyArray = StringUtils.split(destiny, ",");

        try {
            HtmlEmail email = new HtmlEmail();
            email.setCharset(org.apache.commons.mail.EmailConstants.UTF_8);
            String mailRelay = emailServer;
            email.setHostName(mailRelay);
            email.addTo(destinyArray);
            email.setFrom(emailFrom);
            email.setSubject(subject);
            email.setHtmlMsg(message);
            email.send();
        } catch (Exception e) {
            log.error("Error to send e-mail", e);
            throw new EmailServiceException(e.getMessage());
        }
        log.info("Send e-mail finished");
        return "SUCCESS";
    }
}
