package net.vortex.springrestapi.domain.service;

import net.vortex.springrestapi.api.model.Comment;
import net.vortex.springrestapi.domain.exception.BusinessException;
import net.vortex.springrestapi.domain.exception.EntityNotFoundException;
import net.vortex.springrestapi.domain.model.Client;
import net.vortex.springrestapi.domain.model.ServiceOrder;
import net.vortex.springrestapi.domain.model.ServiceOrderStatus;
import net.vortex.springrestapi.domain.repository.ClientRepository;
import net.vortex.springrestapi.domain.repository.CommentRepository;
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

    @Autowired
    private CommentRepository commentRepository;

    public ServiceOrder create(ServiceOrder serviceOrder) {
        Client client = clientRepository.findById(serviceOrder.getClient().getId())
                .orElseThrow(() -> new BusinessException("Client not found"));

        serviceOrder.setClient(client);
        serviceOrder.setStatus(ServiceOrderStatus.OPEN);
        serviceOrder.setCreationDate(OffsetDateTime.now());

        return serviceOrderRepository.save(serviceOrder);
    }

    public void finish(Long serviceOrderId) {
        ServiceOrder serviceOrder = searchServiceOrder(serviceOrderId);
        serviceOrder.finish();
        serviceOrderRepository.save(serviceOrder);
    }

    public Comment addComment(Long serviceOrderId, String description) {
        ServiceOrder serviceOrder = searchServiceOrder(serviceOrderId);

        Comment comment = new Comment();
        comment.setCommentDate(OffsetDateTime.now());
        comment.setDescription(description);
        comment.setServiceOrder(serviceOrder);

        return commentRepository.save(comment);
    }

    private ServiceOrder searchServiceOrder(Long serviceOrderId) {
        return serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new EntityNotFoundException("Service Order not found."));
    }
}
