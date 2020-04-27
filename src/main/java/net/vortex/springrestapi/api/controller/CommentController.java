package net.vortex.springrestapi.api.controller;

import net.vortex.springrestapi.api.model.Comment;
import net.vortex.springrestapi.api.model.CommentInputModel;
import net.vortex.springrestapi.api.model.CommentModel;
import net.vortex.springrestapi.domain.exception.EntityNotFoundException;
import net.vortex.springrestapi.domain.model.ServiceOrder;
import net.vortex.springrestapi.domain.repository.ServiceOrderRepository;
import net.vortex.springrestapi.domain.service.ServiceOrderManagementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service-orders/{serviceOrderId}/comments")
public class CommentController {

    @Autowired
    private ServiceOrderManagementService serviceOrderManagement;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CommentModel> list(@PathVariable Long serviceOrderId) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new EntityNotFoundException("Service order not found"));

        return toCollectionModel(serviceOrder.getComments());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentModel add(@PathVariable Long serviceOrderId, @Valid @RequestBody CommentInputModel commentInput) {
        Comment comment = serviceOrderManagement.addComment(serviceOrderId, commentInput.getDescription());

        return toModel(comment);
    }

    private CommentModel toModel(Comment comment) {
        return modelMapper.map(comment, CommentModel.class);
    }

    private List<CommentModel> toCollectionModel(List<Comment> comments) {
        return comments.stream()
                .map(comment -> toModel(comment))
                .collect(Collectors.toList());
    }
}
