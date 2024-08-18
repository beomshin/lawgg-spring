package com.kr.lg.module;

import com.kr.lg.model.annotation.UserAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserTbToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserAdapter) {
            UserAdapter userAdapter = (UserAdapter) authentication.getPrincipal();
            model.addAttribute("userTb", userAdapter.getUserTb());
            model.addAttribute("lawFirmTb", userAdapter.getLawFirmTb());
            model.addAttribute("tierTb", userAdapter.getTierTb());
        }
    }

}
