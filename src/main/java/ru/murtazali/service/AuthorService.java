package ru.murtazali.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.murtazali.persistense.entity.AuthorEntity;
import ru.murtazali.persistense.repository.AuthorRepository;
import ru.murtazali.service.dto.AuthorDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<AuthorDTO> getAuthors(){
        List<AuthorEntity> authorEntityList = authorRepository.findAll();
        List<AuthorDTO> authorDTOList = new ArrayList<>();

        for (AuthorEntity author: authorEntityList) {
            authorDTOList.add(new AuthorDTO(author));
        }

        return authorDTOList;
    }

    @Transactional(readOnly = true)
    public AuthorDTO getAuthor(Integer id){
        return new AuthorDTO(authorRepository.findOne(id));
    }


    @Transactional(readOnly = true)
    public List<AuthorDTO> getAuthorsByBook(Integer bookId){
        List<AuthorEntity> authorEntityList = authorRepository.findAllByBookId(bookId);
        List<AuthorDTO> authorDTOList = new ArrayList<>();

        for (AuthorEntity author: authorEntityList) {
            authorDTOList.add(new AuthorDTO(author));
        }

        return authorDTOList;
    }

    @Transactional(readOnly = true)
    public List<AuthorDTO> getAuthorsByQuery(String query){
        List<AuthorEntity> authorEntityList = authorRepository.findAuthorsByQuery(query);
        List<AuthorDTO> authorDTOList = new ArrayList<>();

        for (AuthorEntity author: authorEntityList) {
            authorDTOList.add(new AuthorDTO(author));
        }

        return authorDTOList;
    }

}
