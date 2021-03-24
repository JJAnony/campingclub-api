package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
