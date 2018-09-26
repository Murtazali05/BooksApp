package ru.murtazali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.murtazali.security.UserPrincipal;
import ru.murtazali.service.*;
import ru.murtazali.service.dto.AuthorDTO;
import ru.murtazali.service.dto.book.BookDTO;
import ru.murtazali.service.dto.CategoryDTO;
import ru.murtazali.service.dto.ShelfDTO;
import ru.murtazali.service.dto.comment.CommentDTO;
import ru.murtazali.service.dto.comment.NewCommentDTO;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IndexController {
    private BookService bookService;
    private CategoryService categoryService;
    private AuthorService authorService;
    private UserService userService;
    private CommentService commentService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        List<BookDTO> bookDTOList = bookService.getBooks();
        List<CategoryDTO> categoryDTOList = categoryService.getCategories();

        map.addAttribute("books", bookDTOList);
        map.addAttribute("categories", categoryDTOList);

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "index";
    }

    @GetMapping("/{category}")
    public String category(@AuthenticationPrincipal UserPrincipal user, @PathVariable String category, ModelMap map){
        List<BookDTO> bookDTOList = bookService.getBooksByCategory(category);
        List<CategoryDTO> categoryDTOList = categoryService.getCategories();

        map.addAttribute("books", bookDTOList);
        map.addAttribute("categories", categoryDTOList);

        if (user != null)
            map.addAttribute("user", user.getUserDTO());

        return "index";
    }

    @GetMapping("/authors")
    public String authors(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        List<AuthorDTO> authorDTOList = authorService.getAuthors();
        List<CategoryDTO> categoryDTOList = categoryService.getCategories();

        map.addAttribute("authors", authorDTOList);
        map.addAttribute("categories", categoryDTOList);

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "authors";
    }

    @GetMapping("author/{id}")
    public String author(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Integer id, ModelMap map){

        List<CategoryDTO> categoryDTOList = categoryService.getCategories();
        map.addAttribute("categories", categoryDTOList);

        AuthorDTO author = authorService.getAuthor(id);
        map.addAttribute("author", author);

        List<BookDTO> bookDTOList = bookService.getBooksByAuthor(id);
        map.addAttribute("books", bookDTOList);

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "author";
    }

    @GetMapping("/people")
    public String people(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        List<CategoryDTO> categoryDTOList = categoryService.getCategories();

        map.addAttribute("categories", categoryDTOList);

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "people";
    }

    @GetMapping("/book/{id}")
    public String book(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Integer id, ModelMap map) {
        BookDTO bookDTO = bookService.getBook(id);
        List<AuthorDTO> authorDTOList = authorService.getAuthorsByBook(id);
        List<CategoryDTO> categoryDTOList = categoryService.getCategories();
        List<ShelfDTO> shelves = userService.getShelves();
        List<CommentDTO> commentDTOList = commentService.getCommentsByBook(id);

        map.addAttribute("book", bookDTO);
        map.addAttribute("authors", authorDTOList);
        map.addAttribute("categories", categoryDTOList);
        map.addAttribute("shelves", shelves);
        map.addAttribute("comments", commentDTOList);

        NewCommentDTO newCommentDTO = new NewCommentDTO();
        newCommentDTO.setBookId(bookDTO.getId());
        map.addAttribute("newComment", newCommentDTO);

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "book";
    }

    @PostMapping("/saveComment")
    public @ResponseBody CommentDTO saveComment(@RequestBody @Valid NewCommentDTO commentDTO, BindingResult bindingResult){
        return commentService.addNewComment(commentDTO);
    }

    @GetMapping("/deleteComment/{id}")
    public @ResponseBody String deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);

        return "OK";
    }

    @GetMapping("/comment/like/{id}")
    public @ResponseBody String likeComment(@PathVariable Integer id){
        if (commentService.addLikeComment(id))
            return "OK";
        else
            return "already_voted";
    }

    @GetMapping("/comment/dislike/{id}")
    public @ResponseBody String disLikeComment(@PathVariable Integer id){
        if (commentService.addDisLikeComment(id))
            return "OK";
        else
            return "already_voted";
    }

    @GetMapping("/book/{id}/shelf/{shelfId}")
    @PreAuthorize("isAuthenticated()")
    public String addToShelf(@PathVariable("id") Integer id, @PathVariable("shelfId") Integer shelfId){
        userService.addToShelf(id, shelfId);
        return "redirect:/book/" + id;
    }

    @GetMapping("/reviews")
    public String reviews(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        List<CategoryDTO> categoryDTOList = categoryService.getCategories();
        List<CommentDTO> commentDTOList = commentService.getComments();

        map.addAttribute("categories", categoryDTOList);
        map.addAttribute("comments", commentDTOList);

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "reviews";
    }

    @GetMapping("/search")
    public String search(String query, @AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        List<BookDTO> bookDTOList = bookService.getBooksByQuery(query);
        List<CategoryDTO> categoryDTOList = categoryService.getCategories();
        List<AuthorDTO> authorDTOList = authorService.getAuthorsByQuery(query);

        map.addAttribute("books", bookDTOList);
        map.addAttribute("categories", categoryDTOList);
        map.addAttribute("authors", authorDTOList);

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "search";
    }

}
