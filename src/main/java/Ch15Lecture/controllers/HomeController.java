package Ch15Lecture.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

//    Here we have a method that will render the "home.html" view at local host 8080 (root path)
//    Always start with this syntax for a handler: @GetMapping/public String methodName() { return "" };
    @GetMapping
    public String renderHome() {
        return "home";
    }

}
