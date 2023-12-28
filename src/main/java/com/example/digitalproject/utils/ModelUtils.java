package com.example.digitalproject.utils;

import com.example.digitalproject.models.security.User;
import org.springframework.ui.Model;

public class ModelUtils {

    public static void fillModel(User user, Model model) {
        model.addAttribute("name", user.getFirstname());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("photo", user.getPhoto());
        model.addAttribute("description", user.getDescription());
    }
}
