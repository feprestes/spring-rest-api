package net.vortex.springrestapi.domain.repository;

import net.vortex.springrestapi.api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
