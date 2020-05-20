package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.domain.Recipe;
import com.FreeCodeUserJack.recipeProject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {

        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] image = new Byte[file.getBytes().length]; // setup new Byte array

            int i = 0;

            // copy over byte by byte
            for (byte b: file.getBytes()) {
                image[i++] = b;
            }

            recipe.setImage(image);
            recipeRepository.save(recipe);
        }
        catch(IOException e) {
            // todo handle error
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}
