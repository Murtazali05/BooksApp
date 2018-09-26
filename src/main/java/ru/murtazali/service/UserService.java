package ru.murtazali.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.murtazali.persistense.entity.*;
import ru.murtazali.persistense.repository.*;
import ru.murtazali.security.UserPrincipal;
import ru.murtazali.service.dto.book.BookDTO;
import ru.murtazali.service.dto.ShelfDTO;
import ru.murtazali.service.dto.user.EditUserDTO;
import ru.murtazali.service.dto.user.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private ShelfRepository shelfRepository;
    private BookRepository bookRepository;
    private BookShelfRepository bookShelfRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setShelfRepository(ShelfRepository shelfRepository) {
        this.shelfRepository = shelfRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookShelfRepository(BookShelfRepository bookShelfRepository) {
        this.bookShelfRepository = bookShelfRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email){
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null)
            return new UserDTO(userEntity);
        else
            return null;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getUsers(){
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (UserEntity user: userEntityList) {
            userDTOList.add(new UserDTO(user));
        }

        return userDTOList;
    }

    @Transactional(readOnly = true)
    public RoleEntity getRole(Integer id){
        return roleRepository.findOne(id);
    }

    @Transactional
    public void createUser(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();

        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userEntity.setRole(roleRepository.findOne(userDTO.getIdRole()));
        userRepository.save(userEntity);
    }

    @Transactional(readOnly = true)
    public List<ShelfDTO> getShelves(){
        List<ShelfEntity> shelfEntityList = shelfRepository.findAll();
        List<ShelfDTO> shelfDTOList = new ArrayList<>();

        for (ShelfEntity shelf: shelfEntityList) {
            shelfDTOList.add(new ShelfDTO(shelf));
        }

        return shelfDTOList;
    }

    @Transactional(readOnly = true)
    public ShelfDTO getShelve(Integer id){
        return new ShelfDTO(shelfRepository.findOne(id));
    }

    @Transactional
    public List<BookDTO> getBooksByUserAndShelf(Integer userId, Integer shelfId){
        UserEntity userEntity = userRepository.findOne(userId);
        ShelfEntity shelfEntity = shelfRepository.findOne(shelfId);

        List<BookshelfEntity> bookshelfEntityList = bookShelfRepository.findAllByUserAndShelf(userEntity, shelfEntity);
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (BookshelfEntity bookshelfEntity: bookshelfEntityList) {
            bookDTOList.add(new BookDTO(bookshelfEntity.getBook()));
        }

        return bookDTOList;
    }

    @Transactional
    public void addToShelf(Integer bookId, Integer shelfId){
        ShelfEntity shelfEntity = shelfRepository.findOne(shelfId);
        BookEntity bookEntity = bookRepository.findOne(bookId);
        UserPrincipal userPrincipal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByEmail(userPrincipal.getUserDTO().getEmail());

        BookshelfEntity bookshelfEntity = new BookshelfEntity();
        bookshelfEntity.setShelf(shelfEntity);
        bookshelfEntity.setBook(bookEntity);
        bookshelfEntity.setUser(userEntity);
        bookShelfRepository.save(bookshelfEntity);
    }

    public void saveUser(EditUserDTO userDTO) {
        UserEntity userEntity = userRepository.findOne(userDTO.getId());

        if (!userDTO.getEmail().equals(userEntity.getEmail()) && userRepository.findByEmail(userDTO.getEmail()) != null) {
            // ошибка
        } else {
            userEntity.setName(userDTO.getName());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setBirthday(userDTO.getBirthday());
            userEntity.setLinkAvatarka(userDTO.getLinkAvatarka());
            userRepository.save(userEntity);
        }
    }

    public Integer getCountBookRead(Integer userId){
        return bookShelfRepository.findAllByUserAndShelf(userRepository.findOne(userId), shelfRepository.findOne(1)).size();
    }

    @Transactional
    public void makeModerator(Integer userId){
        UserEntity userEntity = userRepository.findOne(userId);
        RoleEntity roleEntity = roleRepository.findOne(2);
        userEntity.setRole(roleEntity);
        userRepository.save(userEntity);
    }

    @Transactional
    public void makeUser(Integer userId){
        UserEntity userEntity = userRepository.findOne(userId);
        RoleEntity roleEntity = roleRepository.findOne(3);
        userEntity.setRole(roleEntity);
        userRepository.save(userEntity);
    }

    @Transactional
    public void deleteUser(Integer userId){
        userRepository.delete(userId);
    }

}
