package net.vortex.springrestapi.domain.service;

import net.vortex.springrestapi.domain.exception.BusinessException;
import net.vortex.springrestapi.domain.model.Client;
import net.vortex.springrestapi.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientRegisterService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {
        Client existentClient = clientRepository.findByEmail(client.getEmail());

        if (existentClient != null && !existentClient.equals(client)) {
            throw new BusinessException("client already registered");
        }

        return clientRepository.save(client);
    }

    public void deleteById(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
