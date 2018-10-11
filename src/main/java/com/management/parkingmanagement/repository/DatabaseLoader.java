package com.management.parkingmanagement.repository;

import com.management.parkingmanagement.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final ParkRepository parkRepository;

    @Autowired
    public DatabaseLoader(ClientRepository clientRepo, ParkRepository parkRepo) {
        this.clientRepository = clientRepo;
        this.parkRepository = parkRepo;
    }

    @Override
    public void run(String... strings) {

        Vehicle vehicle = new Vehicle("teste1234", "Palio", null);

        Wallet wallet = new Wallet(1, new BigDecimal(500.00), null);

        Client client = new Client("abobado@test.com", "pass123", null, null);
        vehicle.setClient(client);
        client.setVehicles(Arrays.asList(vehicle));

        wallet.setClient(client);
        client.setWallet(wallet);

        this.clientRepository.save(client);

        Price price = new Price(1, new BigDecimal(20.00), null);
        Park park = new Park(1, "Garagem do alemao", "rua do meu ovo", true, null);
        price.setPark(park);
        park.setPrice(price);

        parkRepository.save(park);


    }

}
