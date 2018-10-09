package com.management.parkingmanagement.repository;

import com.management.parkingmanagement.entities.Client;
import com.management.parkingmanagement.entities.Vehicle;
import com.management.parkingmanagement.entities.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ClientRepository clientRepository;

    @Autowired
    public DatabaseLoader(ClientRepository clientRepo) {
        this.clientRepository = clientRepo;
    }

    @Override
    public void run(String... strings) {


      //  this.clientRepository.save(new Client("test@gmail.com", "1234", new Wallet(1, new BigDecimal(200.0)), Arrays.asList(new Vehicle("IGL6969", "Sentra"))));


    }

}
