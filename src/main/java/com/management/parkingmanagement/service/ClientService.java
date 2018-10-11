package com.management.parkingmanagement.service;

import com.management.parkingmanagement.entities.Client;
import com.management.parkingmanagement.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client saveClient(final Client client) {
        return clientRepository.save(client);
    }

    public BigDecimal updateDebitFromClientAccountBalance(final BigDecimal debit, final Client client) {
        log.info("Update debit from client account balance");
        BigDecimal accountBalance = client.getWallet().getBalanceAccount().subtract(debit);
        BigDecimal invoice;
        if (accountBalance.compareTo(BigDecimal.ZERO) < 0) {
            client.getWallet().setBalanceAccount(BigDecimal.ZERO);
            invoice = accountBalance.abs();
        } else {
            client.getWallet().setBalanceAccount(accountBalance);
            invoice = BigDecimal.ZERO;
        }
        client.getWallet().setBalanceAccount((accountBalance.compareTo(BigDecimal.ZERO) < 0) ? BigDecimal.ZERO : accountBalance);
        saveClient(client);
        return invoice;
    }
}
