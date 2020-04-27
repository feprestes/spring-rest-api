package net.vortex.springrestapi.api.controller;

import net.vortex.springrestapi.api.model.ServiceOrderInputModel;
import net.vortex.springrestapi.api.model.ServiceOrderRepresentationModel;
import net.vortex.springrestapi.domain.model.ServiceOrder;
import net.vortex.springrestapi.domain.repository.ServiceOrderRepository;
import net.vortex.springrestapi.domain.service.ServiceOrderManagementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderManagementService serviceOrderManagementService;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrderRepresentationModel create(@Valid @RequestBody ServiceOrderInputModel serviceOrderInput) {
        ServiceOrder serviceOrder = toEntity(serviceOrderInput);

        return toModel(serviceOrderManagementService.create(serviceOrder));
    }

    @GetMapping
    public List<ServiceOrderRepresentationModel> list() {
        return toCollectionModel(serviceOrderRepository.findAll());
    }

    @GetMapping("/{serviceOrderId}")
    public ResponseEntity<ServiceOrderRepresentationModel> search(@PathVariable Long serviceOrderId) {
        Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(serviceOrderId);

        if (serviceOrder.isPresent()) {
            ServiceOrderRepresentationModel serviceOrderModel = toModel(serviceOrder.get());
            return ResponseEntity.ok(serviceOrderModel);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{serviceOrderId}/finish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long serviceOrderId) {
        serviceOrderManagementService.finish(serviceOrderId);
    }

    private ServiceOrderRepresentationModel toModel(ServiceOrder serviceOrder) {
        return modelMapper.map(serviceOrder, ServiceOrderRepresentationModel.class);
    }

    private List<ServiceOrderRepresentationModel> toCollectionModel(List<ServiceOrder> serviceOrders) {
        return serviceOrders.stream()
                .map(serviceOrder -> toModel(serviceOrder))
                .collect(Collectors.toList());
    }

    private ServiceOrder toEntity(ServiceOrderInputModel serviceOrderInput) {
        return modelMapper.map(serviceOrderInput, ServiceOrder.class);
    }
}
