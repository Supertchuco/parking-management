package com.management.parkingmanagement.unit.service;

import com.management.parkingmanagement.entities.*;
import com.management.parkingmanagement.exception.ParkingSessionServiceException;
import com.management.parkingmanagement.repository.ParkingSessionRepository;
import com.management.parkingmanagement.service.*;
import com.management.parkingmanagement.vo.FinishingSessionVO;
import com.management.parkingmanagement.vo.StartingSessionVO;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParkingSessionServiceTest {

    @InjectMocks
    private ParkingSessionService parkingSessionService;

    @SuppressWarnings("rawtypes")
    private Class[] parameterTypes;

    private Object[] parameters;

    private Method method;

    @Mock
    private ParkingSessionRepository parkingSessionRepository;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private ParkService parkService;

    @Mock
    private EmailService emailService;

    @Mock
    private ClientService clientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Method buildInvokePrivateMethod(final String privateMethodName, final List<Class> paramClassTypes, final List<Object> parametersInput) throws Exception {
        parameterTypes = new Class[paramClassTypes.size()];
        for (int index = 0; index < paramClassTypes.size(); index++) {
            parameterTypes[index] = paramClassTypes.get(index);
        }

        Method method = parkingSessionService.getClass().getDeclaredMethod(privateMethodName, parameterTypes);
        method.setAccessible(true);

        parameters = new Object[parametersInput.size()];
        for (int index2 = 0; index2 < parametersInput.size(); index2++) {
            parameters[index2] = parametersInput.get(index2);
        }
        return method;
    }


    @Test
    public void getTotalMinutesInParkingTestWith2MinutesDifference() throws Exception {
        Date date1 = new Date();
        Date date2 = DateUtils.addMinutes(date1, 2);
        method = buildInvokePrivateMethod("getTotalMinutesInParking", Arrays.asList(Date.class, Date.class), Arrays.asList(date1, date2));
        assertEquals(2, (int) method.invoke(parkingSessionService, parameters));
    }

    @Test
    public void getTotalMinutesInParkingTestWithNotDifference() throws Exception {
        method = buildInvokePrivateMethod("getTotalMinutesInParking", Arrays.asList(Date.class, Date.class), Arrays.asList(new Date(), new Date()));
        assertEquals(0, (int) method.invoke(parkingSessionService, parameters));
    }

    @Test
    public void checkIfExistSessionOpenedByPlateNumberTestWhenNotExistOpenedSession() throws Exception {
        Mockito.when(parkingSessionRepository.findAllOpenedParkingSessionsByPlateNumber(Mockito.anyString())).thenReturn(new ArrayList<>());
        method = buildInvokePrivateMethod("checkIfExistSessionOpenedByPlateNumber", Arrays.asList(String.class), Arrays.asList("TEST999"));
        method.invoke(parkingSessionService, parameters);
    }

    @Test(expected = InvocationTargetException.class)
    public void checkIfExistSessionOpenedByPlateNumberTestWhenExistOpenedSession() throws Exception {
        Mockito.when(parkingSessionRepository.findAllOpenedParkingSessionsByPlateNumber(Mockito.anyString())).thenReturn(Arrays.asList(new ParkingSession(null, null, 1)));
        method = buildInvokePrivateMethod("checkIfExistSessionOpenedByPlateNumber", Arrays.asList(String.class), Arrays.asList("TEST999"));
        method.invoke(parkingSessionService, parameters);
    }

    @Test
    public void calculateValueToPayTestWith1TimeFrame() throws Exception {
        method = buildInvokePrivateMethod("calculateValueToPay", Arrays.asList(int.class, BigDecimal.class), Arrays.asList(1, new BigDecimal(20)));
        assertTrue(new BigDecimal("20.00").compareTo((BigDecimal) method.invoke(parkingSessionService, parameters)) == 0);
    }

    @Test
    public void calculateValueToPayTestWith3TimeFrames() throws Exception {
        method = buildInvokePrivateMethod("calculateValueToPay", Arrays.asList(int.class, BigDecimal.class), Arrays.asList(45, new BigDecimal(20.05)));
        BigDecimal result = (BigDecimal) method.invoke(parkingSessionService, parameters);
        assertTrue(result.compareTo(new BigDecimal("60.15")) == 0);
    }

    @Test
    public void createParkingSessionTestHappyScenario() {
        Mockito.when(vehicleService.findByPlateNumber(Mockito.anyString())).thenReturn(new Vehicle("TEST123", "Gol", null));
        Mockito.when(parkService.findParkByParkId(1)).thenReturn(new Park(1, "TEST123", "Test", true, null));
        assertTrue(parkingSessionService.createParkingSession(new StartingSessionVO("test444"), "1"));
    }

    @Test(expected = ParkingSessionServiceException.class)
    public void createParkingSessionTestWhenExceptionOccurredDuringSaveOperation() {
        Mockito.when(vehicleService.findByPlateNumber(Mockito.anyString())).thenReturn(new Vehicle("TEST123", "Gol", null));
        Mockito.when(parkService.findParkByParkId(1)).thenReturn(new Park(1, "TEST123", "Test", true, null));
        doThrow(IllegalStateException.class)
                .when(parkingSessionRepository).save(anyObject());
        parkingSessionService.createParkingSession(new StartingSessionVO("test444"), "1");
    }

    @Test
    public void finishParkingSessionTestHappyScenarioWithInvoiceEmail() {
        Mockito.when(parkingSessionRepository.getOpenParkingSessionByParkIdAndPlateNumber(Mockito.anyString(), Mockito.anyInt())).thenReturn(new ParkingSession(new Date(), "test123", 1));
        Mockito.when(vehicleService.findByPlateNumber(Mockito.anyString())).thenReturn(new Vehicle("test123", "Gol", new Client("emailTest", "passtest", null, null)));
        Mockito.when(parkService.findParkByParkId(1)).thenReturn(new Park(1, "TEST123", "Test", true, new Price(1, new BigDecimal("10.00"), null)));
        Mockito.when(emailService.sendHtmlEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn("SUCCESS");
        Mockito.when(clientService.updateDebitFromClientAccountBalance(Mockito.anyObject(), Mockito.anyObject())).thenReturn(new BigDecimal("11.00"));
        Mockito.when(emailService.buildEmailBody(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn("email body");
        assertTrue(parkingSessionService.finishParkingSession(new FinishingSessionVO("stopped"), "1", "test123"));

    }

    @Test
    public void finishParkingSessionTestHappyScenarioWithoutInvoiceEmail() {
        Mockito.when(parkingSessionRepository.getOpenParkingSessionByParkIdAndPlateNumber(Mockito.anyString(), Mockito.anyInt())).thenReturn(new ParkingSession(new Date(), "test123", 1));
        Mockito.when(vehicleService.findByPlateNumber(Mockito.anyString())).thenReturn(new Vehicle("test123", "Gol", new Client("emailTest", "passtest", null, null)));
        Mockito.when(parkService.findParkByParkId(1)).thenReturn(new Park(1, "TEST123", "Test", true, new Price(1, new BigDecimal("10.00"), null)));
        Mockito.when(clientService.updateDebitFromClientAccountBalance(Mockito.anyObject(), Mockito.anyObject())).thenReturn(BigDecimal.ZERO);
        assertTrue(parkingSessionService.finishParkingSession(new FinishingSessionVO("stopped"), "1", "test123"));
    }

    @Test(expected = ParkingSessionServiceException.class)
    public void finishParkingSessionTestHappyScenarioWithExceptionOccurred() {
        Mockito.when(parkingSessionRepository.getOpenParkingSessionByParkIdAndPlateNumber(Mockito.anyString(), Mockito.anyInt())).thenReturn(new ParkingSession(new Date(), "test123", 1));
        Mockito.when(vehicleService.findByPlateNumber(Mockito.anyString())).thenReturn(new Vehicle("test123", "Gol", new Client("emailTest", "passtest", null, null)));
        Mockito.when(parkService.findParkByParkId(1)).thenReturn(new Park(1, "TEST123", "Test", true, new Price(1, new BigDecimal("10.00"), null)));
        doThrow(IllegalStateException.class)
                .when(clientService).updateDebitFromClientAccountBalance(anyObject(), anyObject());
        parkingSessionService.finishParkingSession(new FinishingSessionVO("stopped"), "1", "test123");
    }

}
