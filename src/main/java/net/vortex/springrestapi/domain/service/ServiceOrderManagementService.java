package net.vortex.springrestapi.domain.service;

import net.vortex.springrestapi.domain.exception.BusinessException;
import net.vortex.springrestapi.domain.model.Client;
import net.vortex.springrestapi.domain.model.ServiceOrder;
import net.vortex.springrestapi.domain.model.ServiceOrderStatus;
import net.vortex.springrestapi.domain.repository.ClientRepository;
import net.vortex.springrestapi.domain.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ServiceOrderManagementService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ServiceOrder create(ServiceOrder serviceOrder) {
        Client client = clientRepository.findById(serviceOrder.getClient().getId())
                .orElseThrow(() -> new BusinessException("Client not found"));

        serviceOrder.setClient(client);
        serviceOrder.setStatus(ServiceOrderStatus.OPEN);
        serviceOrder.setCreationDate(OffsetDateTime.now());

        return serviceOrderRepository.save(serviceOrder);
    }
}
