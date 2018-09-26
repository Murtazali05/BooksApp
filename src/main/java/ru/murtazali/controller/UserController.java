package ru.murtazali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.murtazali.security.UserPrincipal;
import ru.murtazali.service.dto.book.BookDTO;
import ru.murtazali.service.dto.ShelfDTO;
import ru.murtazali.service.UserService;
import ru.murtazali.service.dto.user.EditUserDTO;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userPage(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        List<ShelfDTO> shelves = userService.getShelves();

        map.addAttribute("shelves", shelves);

        if (userPrincipal != null) {
            map.addAttribute("user", userPrincipal.getUserDTO());
            map.addAttribute("countReadBook", userService.getCountBookRead(userPrincipal.getUserDTO().getId()));
        }

        return "user";
    }

    @GetMapping("/shelf{id}")
    public String toShelfPage(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("id") int idShelf, ModelMap map){
        List<BookDTO> bookDTOList = userService.getBooksByUserAndShelf(userPrincipal.getUserDTO().getId(), idShelf);
        List<ShelfDTO> shelves = userService.getShelves();
        ShelfDTO shelfDTO = userService.getShelve(idShelf);

        map.addAttribute("books", bookDTOList);
        map.addAttribute("shelves", shelves);
        map.addAttribute("shelf", shelfDTO);

        map.addAttribute("user", userPrincipal.getUserDTO());

        return "shelf";
    }

    @GetMapping("/edit")
    public String editUser(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        List<ShelfDTO> shelves = userService.getShelves();

        map.addAttribute("shelves", shelves);

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        map.addAttribute("newUser", new EditUserDTO());

        return "edit";
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody @Valid EditUserDTO userDTO, BindingResult bindingResult){
        userService.saveUser(userDTO);
        return "redirect:/user";
    }

}
