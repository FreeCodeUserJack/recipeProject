package com.FreeCodeUserJack.recipeProject.controllers;

import com.FreeCodeUserJack.recipeProject.Services.RecipeService;
import com.FreeCodeUserJack.recipeProject.commands.RecipeCommand;
import com.FreeCodeUserJack.recipeProject.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        // valueOf() returns Long obj while parseLong() returns long primitive
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeForm";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/recipeForm";
    }

    @PostMapping("recipe") // don't need -> method = RequestMethod.POST, use Post annotatio, name attr is buggy (?)
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
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
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e) {
        log.error("Handling Not Found Exception");
        log.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");

        modelAndView.addObject("exception", e);

        return modelAndView;
    }

    // for when someone enters string for ownerId (e.g. recipe/asdf/show)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // when NumberFormatException is encountered, return HTTP status of 400
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleBadRequest(Exception e ) {
        log.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400error");

        modelAndView.addObject("exception", e);

        return modelAndView;
    }
}
