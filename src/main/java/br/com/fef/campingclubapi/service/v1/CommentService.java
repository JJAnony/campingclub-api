package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.model.Camping;
import br.com.fef.campingclubapi.model.Comment;
import br.com.fef.campingclubapi.model.User;
import br.com.fef.campingclubapi.repository.CampingRepository;
import br.com.fef.campingclubapi.repository.CommentRepository;
import br.com.fef.campingclubapi.repository.UserRepository;
import br.com.fef.campingclubapi.service.interfaces.IStandardService;
import br.com.fef.campingclubapi.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService implements IStandardService<CommentDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampingRepository campingRepository;

    @Autowired
    private CommentRepository comentarioRepository;

    @Override
    public List<CommentDTO> list(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public CommentDTO get(Long id) throws Exception {
        return null;
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) throws Exception {

        Comment comment = commentDTO.toComment();

        Camping camping = campingRepository.findById(commentDTO.getId_camping())
                .orElseThrow(() -> new NullPointerException("Camping Não Encontrado!"));

        comment.setCamping(camping);

        User user = userRepository.findById(commentDTO.getId_user())
                .orElseThrow(() -> new NullPointerException("Usuario Não Encontrado!"));

        comment.setUser(user);

        comment.setDate_send(new Date());

        comment = comentarioRepository.save(comment);

        return comment.toComment();
    }

    @Override
    public CommentDTO update(Long id, CommentDTO commentDTO) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public void verifyIfExists(Long id) throws Exception {

    }
}
