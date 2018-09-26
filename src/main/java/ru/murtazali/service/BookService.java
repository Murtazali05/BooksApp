package ru.murtazali.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.murtazali.persistense.entity.*;
import ru.murtazali.persistense.repository.AuthorRepository;
import ru.murtazali.persistense.repository.BookRepository;
import ru.murtazali.persistense.repository.CategoryRepository;
import ru.murtazali.service.dto.book.BookDTO;
import ru.murtazali.service.dto.book.NewBookDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getBooks(){
        List<BookEntity> bookEntityList = bookRepository.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (BookEntity bookEntity : bookEntityList) {
            bookDTOList.add(new BookDTO(bookEntity));
        }

        return bookDTOList;
    }

    @Transactional(readOnly = true)
    public BookDTO getBook(Integer id){
        return new BookDTO(bookRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getBooksByCategory(String category){
        List<BookEntity> bookEntityList = bookRepository.findAllByURLCategory(category);

        List<BookDTO> bookDTOList = new ArrayList<>();

        for (BookEntity bookEntity : bookEntityList) {
            bookDTOList.add(new BookDTO(bookEntity));
        }

        return bookDTOList;
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getBooksByAuthor(Integer authorId){
        List<BookEntity> bookEntityList = bookRepository.findAllByAuthorId(authorId);
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (BookEntity book: bookEntityList){
            bookDTOList.add(new BookDTO(book));
        }

        return bookDTOList;
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getBooksByQuery(String query){
        List<BookEntity> bookEntityList = bookRepository.findBooksByQuery(query);
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (BookEntity book: bookEntityList){
            bookDTOList.add(new BookDTO(book));
        }

        return bookDTOList;
    }

    @Transactional
    public void saveBook(NewBookDTO bookDTO) {
        BookEntity book = new BookEntity();

        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setPubyear(bookDTO.getPubyear());
        book.setPic(bookDTO.getPic());
        book.setLink(bookDTO.getLink());
        book.setLinkShop(bookDTO.getLinkShop());

        List<AuthorEntity> authorEntityCollection = new ArrayList<>();
        for (Integer authorId: bookDTO.getAuthorsId()){
            authorEntityCollection.add(authorRepository.findOne(authorId));
        }
        book.setAuthors(authorEntityCollection);

        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        for (Integer categoryId: bookDTO.getCategoriesId()){
            categoryEntityList.add(categoryRepository.findOne(categoryId));
        }
        book.setCategories(categoryEntityList);

        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Integer bookId) {
        bookRepository.delete(bookId);
    }

}
