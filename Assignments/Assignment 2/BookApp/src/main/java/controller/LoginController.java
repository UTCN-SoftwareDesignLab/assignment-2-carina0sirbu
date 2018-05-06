package controller;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dto.AuthorDto;
import dto.BookDto;
import dto.UserDto;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getCreate(Model model) {

        model.addAttribute("userDto", new UserDto());
        model.addAttribute("bookDto", new BookDto());
        return "login";
    }

    @PostMapping(params="login")
    public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserDto user,
                        BindingResult result) throws ServletException {
        try {
            RequestCache requestCache = new HttpSessionRequestCache();
            request.login(user.getUsername(),user.getPassword());
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if (savedRequest != null) {
                return "redirect:" + savedRequest.getRedirectUrl();
            } else {
                return "redirect:/";
            }
        } catch (ServletException authenticationFailed) {
            result.rejectValue(null, "authentication.failed");
            return "login";
        }
    }

}
