package com.FreeCodeUserJack.recipeProject.controllers;

import com.FreeCodeUserJack.recipeProject.Services.ImageService;
import com.FreeCodeUserJack.recipeProject.Services.RecipeService;
import com.FreeCodeUserJack.recipeProject.commands.RecipeCommand;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;


    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String showUploadForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/imageUploadForm";
    }

    @PostMapping("recipe/{recipeId}/image") // requestparam is input tag name (?)
    public String handleImagePost(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(recipeId), file);

        return "redirect:/recipe/" + recipeId + "/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        if(recipeCommand.getImage() != null) { // need to set up start image or default image in Dataloader bootstrap
            byte[] byteArray = new byte[recipeCommand.getImage().length]; // can't use Byte[] here (?)
            int i = 0;

            // why do we need to copy over byte by byte
            for (Byte b: recipeCommand.getImage()) {
                byteArray[i++] = b; // auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
