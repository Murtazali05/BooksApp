package ru.murtazali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.murtazali.security.UserPrincipal;
import ru.murtazali.service.AuthorService;
import ru.murtazali.service.BookService;
import ru.murtazali.service.CategoryService;
import ru.murtazali.service.UserService;
import ru.murtazali.service.dto.book.BookDTO;
import ru.murtazali.service.dto.book.NewBookDTO;
import ru.murtazali.service.dto.user.UserDTO;
import ru.murtazali.validator.BookValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;
    private BookValidator bookValidator;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setBookValidator(BookValidator bookValidator) {
        this.bookValidator = bookValidator;
    }

    @GetMapping
    public String toAdminPage(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        List<UserDTO> userDTOList = userService.getUsers();

        map.addAttribute("users", userDTOList);

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "admin/user/view";
    }

    @GetMapping("/makeModerator/{id}")
    public String makeModerator(@PathVariable("id") Integer userId){
        userService.makeModerator(userId);

        return "redirect:/admin";
    }

    @GetMapping("/makeUser/{id}")
    public String makeUser(@PathVariable("id") Integer userId){
        userService.makeUser(userId);

        return "redirect:/admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer userId){
        userService.deleteUser(userId);

        return "redirect:/admin";
    }

    @GetMapping("/books")
    public String toBookList(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        List<BookDTO> bookDTOList = bookService.getBooks();

        map.addAttribute("books", bookDTOList);

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "admin/book/view";
    }

    @GetMapping("/book/edit")
    public String editBook(@RequestParam(value = "id", required = false) Integer id, @AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        map.addAttribute("authors", authorService.getAuthors());
        map.addAttribute("categories", categoryService.getCategories());

        if (id == null)
            map.addAttribute("book", new NewBookDTO());
        else
            map.addAttribute("book", new NewBookDTO(bookService.getBook(id)));

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "admin/book/edit";
    }

    @PostMapping("/book/save")
    public String saveBook(@ModelAttribute("book") @Valid NewBookDTO bookDTO, @AuthenticationPrincipal UserPrincipal userPrincipal, BindingResult bindingResult, ModelMap map){
        bookValidator.validate(bookDTO, bindingResult);

        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            map.addAttribute("authors", authorService.getAuthors());
            map.addAttribute("categories", categoryService.getCategories());

            map.addAttribute("book", bookDTO);
            map.addAttribute("error", fieldErrors.get(0).getCode());

            if (userPrincipal != null)
                map.addAttribute("user", userPrincipal.getUserDTO());

            return "admin/book/edit";
        }

        bookService.saveBook(bookDTO);

        return "redirect:/admin/books";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id){
        bookService.deleteBook(id);

        return "redirect:/admin/book";
    }

}
