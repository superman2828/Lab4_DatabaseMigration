package com.earlnt.mydb.controllers;


import com.earlnt.mydb.dtos.RegisterUserRequest;
import com.earlnt.mydb.mappers.UserMapper;
import com.earlnt.mydb.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/ui/users")
public class UserUIController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public String listUser(Model model, @RequestParam(defaultValue="name") String sort){
        var users = userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto).toList();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user",new RegisterUserRequest());
        model.addAttribute("isEdit", false);
        return "users/form";
    }

    @PostMapping
    public String createUser(@ModelAttribute RegisterUserRequest request, RedirectAttributes redirectAttributes){
        var user = userMapper.toEntity(request);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "User Created Successfully!");
        return "redirect:/ui/users";
    }
}
