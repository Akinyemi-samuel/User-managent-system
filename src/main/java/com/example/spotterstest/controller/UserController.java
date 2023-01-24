package com.example.spotterstest.controller;

import com.example.spotterstest.model.User;
import com.example.spotterstest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //    THIS IS WHAT MAPS THE INDEX PAGE TO OUR DESIGN PAGE
    @GetMapping("/")
    public String HomePage(Model model) {
        User user = new User();
        model.addAttribute("User", user);
        return "/register";
    }

    //THIS IS AN ACTION IN THE HREF OF A LINK FOR
    //MOVING TO THE LOGIN PAGE
    @GetMapping("gotologinpage")
    public String gotologinpage() {
        return "/login";
    }

    //THIS IS FOR REGISTERING A NEW USER TO THE DB
    @PostMapping("/submituserinfo")
    public String signinuser(@ModelAttribute("User") User user) {
        userService.signinUser(user);
        return "redirect:/dashboard";
    }

    //THIS IS THE CODE FOR FINDING ALL OF THE USER IN THE DATABASE
    @GetMapping("/dashboard")
    public String finduser(Model model) {
        model.addAttribute("receiveuser", userService.selectalluser());
        return "/dashboard";
    }

    @GetMapping("/gotdash")
    public String gotdash(Model model) {
        return "redirect:/dashboard";
    }


    //DELETE THE USER BY ID
    @GetMapping("/deleteuser/{id}")
    public String deleteuser(@PathVariable("id") Long id) {
        this.userService.deleteuserbyid(id);
        return "redirect:/dashboard";
    }

    //UPDATES THE USER BY ID
    @GetMapping("/updateuser/{id}")
    public String updateuser(@PathVariable("id") Long id, Model model) {
        User user = userService.UpdateuserbyId(id);
        model.addAttribute("user", user);
        return "/Update";
    }

}
