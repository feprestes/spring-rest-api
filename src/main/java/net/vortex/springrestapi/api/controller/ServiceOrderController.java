package net.vortex.springrestapi.api.controller;

import net.vortex.springrestapi.domain.model.ServiceOrder;
import net.vortex.springrestapi.domain.repository.ServiceOrderRepository;
import net.vortex.springrestapi.domain.service.ServiceOrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderManagementService serviceOrderManagementService;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrder create(@Valid @RequestBody ServiceOrder serviceOrder) {
        return serviceOrderManagementService.create(serviceOrder);
    }

    @GetMapping
    public List<ServiceOrder> list(){
        return serviceOrderRepository.findAll();
    }

    @GetMapping("/{serviceOrderId}")
    public ResponseEntity<ServiceOrder> search(@PathVariable Long serviceOrderId){
        Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(serviceOrderId);
        if(serviceOrder.isPresent()){
            return ResponseEntity.ok(serviceOrder.get());
        }
        return ResponseEntity.notFound().build();
    }
}
