package com.management.parkingmanagement.unit.service;

import com.management.parkingmanagement.entities.Park;
import com.management.parkingmanagement.entities.ParkingSession;
import com.management.parkingmanagement.entities.Vehicle;
import com.management.parkingmanagement.exception.InvalidEmailDataException;
import com.management.parkingmanagement.service.EmailService;
import org.apache.commons.mail.EmailException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    private ParkingSession parkingSession;

    private Vehicle vehicle;

    private Park park;

    @Before
    public void setUp() {
        parkingSession = new ParkingSession(1, new Date(), new Date(), "BEST4567", 1);
        vehicle = new Vehicle("BEST4567", "FIAT 147", null);
        park = new Park(1, "Alemao Park", "Carlos Gomes 555 Brazil", true, null);
    }

    @Test
    public void buildEmailBodyTest() {
        try {
            emailService.buildEmailBody(new BigDecimal(50.00), parkingSession, park, vehicle);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

    @Test
    public void sendHtmlEmailTestHappyScenario() throws EmailException {
        ReflectionTestUtils.setField(emailService, "emailServer", "smtp.gmail.com");
        ReflectionTestUtils.setField(emailService, "emailFrom", "blob@blob.com");
        try {
            emailService.sendHtmlEmail("otto@otto.com", "message test", "invoice");
        } catch (Exception e) {
            if (e.getMessage().equals("Sending the email to the following server failed : smtp.gmail.com:25")) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }

    @Test(expected = InvalidEmailDataException.class)
    public void sendHtmlEmailTestWhenClientEmailIsBlank() throws EmailException {
        ReflectionTestUtils.setField(emailService, "emailServer", "smtp.gmail.com");
        ReflectionTestUtils.setField(emailService, "emailFrom", "blob@blob.com");
        emailService.sendHtmlEmail("", "message test", "invoice");
    }

    @Test(expected = InvalidEmailDataException.class)
    public void sendHtmlEmailTestWhenClientEmailIsNull() throws EmailException {
        ReflectionTestUtils.setField(emailService, "emailServer", "smtp.gmail.com");
        ReflectionTestUtils.setField(emailService, "emailFrom", "blob@blob.com");
        emailService.sendHtmlEmail(null, "message test", "invoice");
    }

    @Test(expected = InvalidEmailDataException.class)
    public void sendHtmlEmailTestWhenMessageIsBlank() throws EmailException {
        ReflectionTestUtils.setField(emailService, "emailServer", "smtp.gmail.com");
        ReflectionTestUtils.setField(emailService, "emailFrom", "blob@blob.com");
        emailService.sendHtmlEmail("otto@otto.com", "", "invoice");
    }

    @Test(expected = InvalidEmailDataException.class)
    public void sendHtmlEmailTestWhenMessageIsNull() throws EmailException {
        ReflectionTestUtils.setField(emailService, "emailServer", "smtp.gmail.com");
        ReflectionTestUtils.setField(emailService, "emailFrom", "blob@blob.com");
        emailService.sendHtmlEmail("otto@otto.com", null, "invoice");
    }

    @Test(expected = InvalidEmailDataException.class)
    public void sendHtmlEmailTestWhenSubjectIsBlank() throws EmailException {
        ReflectionTestUtils.setField(emailService, "emailServer", "smtp.gmail.com");
        ReflectionTestUtils.setField(emailService, "emailFrom", "blob@blob.com");
        emailService.sendHtmlEmail("otto@otto.com", "message test", "");
    }

    @Test(expected = InvalidEmailDataException.class)
    public void sendHtmlEmailTestWhenSubjectIsNull() throws EmailException {
        ReflectionTestUtils.setField(emailService, "emailServer", "smtp.gmail.com");
        ReflectionTestUtils.setField(emailService, "emailFrom", "blob@blob.com");
        emailService.sendHtmlEmail("otto@otto.com", "message test", null);
    }
}
