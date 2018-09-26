package ru.murtazali.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.murtazali.persistense.entity.CommentEntity;
import ru.murtazali.persistense.entity.UserEntity;
import ru.murtazali.persistense.entity.VoteEntity;
import ru.murtazali.persistense.repository.BookRepository;
import ru.murtazali.persistense.repository.CommentRepository;
import ru.murtazali.persistense.repository.UserRepository;
import ru.murtazali.persistense.repository.VoteRepository;
import ru.murtazali.security.UserPrincipal;
import ru.murtazali.service.dto.comment.CommentDTO;
import ru.murtazali.service.dto.comment.NewCommentDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private VoteRepository voteRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setVoteRepository(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Transactional(readOnly = true)
    public List<CommentDTO> getComments(){
        List<CommentEntity> commentEntityList = commentRepository.findAll();
        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (CommentEntity comment: commentEntityList) {
            commentDTOList.add(new CommentDTO(comment));
        }

        return commentDTOList;
    }

    @Transactional(readOnly = true)
    public List<CommentDTO> getCommentsByBook(Integer bookId){
        List<CommentEntity> commentEntityList = commentRepository.findAllByBookId(bookId);
        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (CommentEntity comment: commentEntityList) {
            commentDTOList.add(new CommentDTO(comment));
        }

        return commentDTOList;
    }

    @Transactional
    public CommentDTO addNewComment(NewCommentDTO newCommentDTO){
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setTitle(newCommentDTO.getTitle());
        commentEntity.setContent(newCommentDTO.getContent());
        commentEntity.setDate(new Timestamp(System.currentTimeMillis()));
        commentEntity.setCountLike(0);
        commentEntity.setCountDislike(0);
        commentEntity.setBook(bookRepository.findOne(newCommentDTO.getBookId()));
        UserPrincipal userPrincipal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentEntity.setUser(userRepository.findByEmail(userPrincipal.getUserDTO().getEmail()));

        return new CommentDTO(commentRepository.save(commentEntity));
    }

    @Transactional
    public void deleteComment(Integer id){
        commentRepository.delete(id);
    }

    @Transactional
    public boolean addLikeComment(Integer commentId) {
        CommentEntity commentEntity = commentRepository.findOne(commentId);
        UserPrincipal userPrincipal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByEmail(userPrincipal.getUserDTO().getEmail());

        if (voteRepository.existsAllByCommentAndUser(commentEntity, userEntity)) {
            return false;
        } else {
            commentEntity.setCountLike(commentEntity.getCountLike() + 1);
            commentRepository.save(commentEntity);

            VoteEntity voteEntity = new VoteEntity();
            voteEntity.setComment(commentEntity);
            voteEntity.setUser(userEntity);
            voteRepository.save(voteEntity);
        }

        return true;
    }

    public boolean addDisLikeComment(Integer commentId) {
        CommentEntity commentEntity = commentRepository.findOne(commentId);
        UserPrincipal userPrincipal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByEmail(userPrincipal.getUserDTO().getEmail());

        if (voteRepository.existsAllByCommentAndUser(commentEntity, userEntity)) {
            return false;
        } else {
            commentEntity.setCountDislike(commentEntity.getCountDislike() + 1);
            commentRepository.save(commentEntity);

            VoteEntity voteEntity = new VoteEntity();
            voteEntity.setComment(commentEntity);
            voteEntity.setUser(userRepository.findByEmail(userPrincipal.getUserDTO().getEmail()));
            voteRepository.save(voteEntity);
        }

        return true;
    }

}
