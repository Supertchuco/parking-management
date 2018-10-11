package com.management.parkingmanagement.unit.service;

import com.management.parkingmanagement.entities.Client;
import com.management.parkingmanagement.entities.Wallet;
import com.management.parkingmanagement.repository.ClientRepository;
import com.management.parkingmanagement.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    private Client client;

    @Before
    public void setUp() {
        Wallet wallet = new Wallet(1, new BigDecimal(200), null);
        client = new Client();
        client.setWallet(wallet);

    }

    @Test
    public void updateDebitFromClientAccountBalanceTestWhenClientHasAccountBalance(){
        assertEquals(new BigDecimal(0), clientService.updateDebitFromClientAccountBalance(new BigDecimal(100), client));
    }

    @Test
    public void updateDebitFromClientAccountBalanceTestWhenClientNotHasAccountBalanceEnough(){
        client.getWallet().setBalanceAccount(new BigDecimal(50));
        assertEquals(new BigDecimal(50), clientService.updateDebitFromClientAccountBalance(new BigDecimal(100), client));
    }

    @Test
    public void updateDebitFromClientAccountBalanceTestWhenClientHas0AccountBalanceEnough(){
        client.getWallet().setBalanceAccount(BigDecimal.ZERO);
        assertEquals(new BigDecimal(100), clientService.updateDebitFromClientAccountBalance(new BigDecimal(100), client));
    }

}
