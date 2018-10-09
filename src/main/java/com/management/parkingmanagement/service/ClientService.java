package com.management.parkingmanagement.service;

import com.management.parkingmanagement.entities.Client;
import com.management.parkingmanagement.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;


    public BigDecimal getAccountBalance(final String clientEmail){
        Client client = clientRepository.findByEmail(clientEmail);
        if(Objects.isNull(client)){
            //return erro
        }

        return client.getWallet().getBalanceAccount();
    }

    public Client saveClient(final Client client){
        return clientRepository.save(client);
    }

    public BigDecimal discountDebitFromAccountBalance(final BigDecimal debit, final String clientEmail){
        Client client = clientRepository.findByEmail(clientEmail);
        if(Objects.isNull(client)){
            //return erro
        }

        client.getWallet().setBalanceAccount( client.getWallet().getBalanceAccount().subtract(debit));
        saveClient(client);
        return client.getWallet().getBalanceAccount();
    }
}
