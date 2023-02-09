package Ch15Lecture.controllers;

import Ch15Lecture.data.DinoData;
import Ch15Lecture.models.Dinosaur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("dino")
public class DinoController {

//    Here is a method that handles get requests at /dino path
    @GetMapping
    public String dino(Model model) {
//let's pass in a list of all the dinos in our data layer
      model.addAttribute("allDinos", DinoData.getAllDinos());
        return "dino/index";
    }

    @GetMapping("add")
    public String displayAddDinoForm() {
        return "dino/add";
    }

    @PostMapping("add")
//    public String processAddDinoForm(Model model, String species, String diet, String aquatic) {
    public String processAddDinoForm(Model model, @ModelAttribute @Valid Dinosaur newDinoObj, Errors errors) {
//       now that we have the info necessary for a dinosaur from the form, we must create a dinosaur object
//        Dinosaur newDinoObj = new Dinosaur(species, diet, aquatic);

//        Now with validation, we need to add an if statement to handle the error
        if(errors.hasErrors()) {
//            Before re-render the user to dino/add, we want to send an error message to user
            model.addAttribute("errorMsg", "The Species must contain at least 3 characters!");
//            If errors has errors is true, let's re-render the dino/add page
            return "dino/add";
        }

        DinoData.addDino(newDinoObj);
//        must pass in the list of allDinos
        model.addAttribute("allDinos", DinoData.getAllDinos());
//        now that we have stored our Dinosaur object in our allDinos list, what page should we render?
        return "dino/index";
    }
}
