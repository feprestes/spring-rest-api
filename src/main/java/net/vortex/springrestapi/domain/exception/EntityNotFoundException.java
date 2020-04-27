package net.vortex.springrestapi.domain.exception;

public class EntityNotFoundException extends BusinessException{

    public static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
