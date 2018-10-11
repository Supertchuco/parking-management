package com.management.parkingmanagement.service;

import com.management.parkingmanagement.entities.Park;
import com.management.parkingmanagement.entities.ParkingSession;
import com.management.parkingmanagement.entities.Vehicle;
import com.management.parkingmanagement.exception.EmailServiceException;
import com.management.parkingmanagement.exception.InvalidEmailDataException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Slf4j
@Service
public class EmailService {

    @Value("${email.server}")
    private String emailServer;

    @Value("${email.from}")
    private String emailFrom;

    public String sendHtmlEmail(final String destiny, final String message, final String subject)
            throws EmailServiceException {
        log.info("Sending e-mail to: {}", destiny);

        if (StringUtils.isBlank(destiny) || StringUtils.isBlank(message) || StringUtils.isBlank(subject)) {
            log.error("One or more fields are blank, send HTML e-mail failed, destiny: {}, message: {} and subject: {}",
                    destiny, message, subject);
            throw new InvalidEmailDataException("One or more fields are blank, send HTML e-mail failed");
        }

        String[] destinyArray = StringUtils.split(destiny, ",");

        try {
            HtmlEmail email = new HtmlEmail();
            email.setCharset(org.apache.commons.mail.EmailConstants.UTF_8);
            email.setHostName(emailServer);
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

    public String buildEmailBody(BigDecimal invoice, ParkingSession parkingSession, Park park, Vehicle vehicle) {
        log.info("build e-mail body");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new StringBuilder().append("Hello,<br><br>").append("This your invoice about parking session, see details below:<br>").append("Date started: ")
                .append(dateFormat.format(parkingSession.getStarted())).append(" <br><br>").append("Date finished: ").append(dateFormat.format(parkingSession.getFinished())).append(" <br><br>")
                .append("Park Address: ").append(park.getAddress()).append(" <br><br>").append("Vehicle plate number: ").append(vehicle.getPlateNumber())
                .append(" <br><br>").append("Vehicle model: ").append(vehicle.getModel()).append(" <br><br>").append("Invoice: $: ").append(invoice).append(" <br><br>")
                .append("thanks for cooperate.<br><br>").append("</html>").toString();
    }
}
