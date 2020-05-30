package com.FreeCodeUserJack.recipeProject.controllers;

import com.FreeCodeUserJack.recipeProject.Services.RecipeService;
import com.FreeCodeUserJack.recipeProject.commands.RecipeCommand;
import com.FreeCodeUserJack.recipeProject.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {
    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeForm";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        // valueOf() returns Long obj while parseLong() returns long primitive
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id))); // maybe findCommandById?

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("recipe") // don't need -> method = RequestMethod.POST, use Post annotation, name attr is buggy (?)
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult result) {
//        @Valid will apply validation constraints on the recipeCommand obj or else won't apply validations
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                log.debug(error.toString());
            });

            // BindingResult allows us to access #fields property in thymeleaf (?)
                // #fields, #list, etc. are thymeleaf utility methods

            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model) {
        log.debug("deleting recipe with id: " + id);

        recipeService.deleteCommandById(Long.valueOf(id));

        return "redirect:/";
    }

    // will respond with 404 but also redirect to 404error.html
    @ResponseStatus(HttpStatus.NOT_FOUND)
    // to handle if the specified exception is thrown
    @ExceptionHandler(NotFoundException.class) // this is saying for NotFoundException, response will be 404
    public ModelAndView handleNotFound(Exception e) {
        log.error("Handling Not Found Exception");
        log.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");

        modelAndView.addObject("exception", e);

        return modelAndView;
    }
}
