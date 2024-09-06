package com.providertechapi.cliente.domain.service.impl;

import com.providertechapi.cliente.core.repository.FilePathRepository;
import com.providertechapi.cliente.domain.entity.ClientEntity;
import com.providertechapi.cliente.domain.entity.FilePath;
import com.providertechapi.cliente.domain.exception.notification.NotificationException;
import com.providertechapi.cliente.core.repository.ClientRepository;
import com.providertechapi.cliente.domain.service.ClientService;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvImportServiceImpl {

    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final FilePathRepository filePathRepository;

    public CsvImportServiceImpl(ClientRepository clientRepository, ClientService clientService, FilePathRepository filePathRepository) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
        this.filePathRepository = filePathRepository;
    }

    public void processFile(String filePath) throws IOException, NotificationException {
        List<ClientEntity> clientEntities = parseCsvFile(filePath);
        for (ClientEntity client : clientEntities) {
            validateClient(client);
        }
        clientRepository.saveAll(clientEntities);

        FilePath filePathEntity = new FilePath(filePath);

        filePathRepository.save(filePathEntity);
    }

    private void validateClient(ClientEntity client) throws NotificationException {
        clientService.validateCpfClient(client.getCpf());
        clientService.isthereAreRegisteredCpf(client.getCpf());
        clientService.isthereAreRegisteredEmail(client.getEmail());
    }

    private List<ClientEntity> parseCsvFile(String filePath) throws IOException {
        List<ClientEntity> clientEntities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<ClientEntity> parsedClients = parseLine(line);
                clientEntities.addAll(parsedClients);
            }
        }
        return clientEntities;
    }

    private List<ClientEntity> parseLine(String line) {
        List<ClientEntity> clientEntities = new ArrayList<>();
        String[] records = line.split(";");
        for (String record : records) {
            ClientEntity client = parseRecord(record);
            if (client != null) {
                clientEntities.add(client);
            }
        }
        return clientEntities;
    }

    private ClientEntity parseRecord(String record) {
        record = record.replaceAll("^\"|\"$", "");
        String[] fields = record.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        if (fields.length == 4) {
            try {
                ClientEntity client = new ClientEntity();
                client.setName(fields[0].trim());
                client.setCpf(fields[1].trim());
                client.setEmail(fields[2].trim());
                client.setBirthDate(parseDate(fields[3].trim()));
                return client;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for record: " + record);
            }
        } else {
            System.out.println("Skipping invalid record: " + record);
        }
        return null;
    }

    private LocalDate parseDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr);
    }
}
