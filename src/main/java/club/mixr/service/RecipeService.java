package club.mixr.service;

import club.mixr.data.entity.RecipeEntity;
import club.mixr.data.entity.SourceEntity;
import club.mixr.data.repository.RecipeRepository;
import club.mixr.data.repository.SourceRepository;
import club.mixr.dto.Recipe;
import club.mixr.dto.Source;
import club.mixr.dto.SourceToCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jdifebo on 3/14/2017.
 */
@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    public List<Recipe> allRecipes() {
        return recipeRepository.findAll()
                .map(RecipeEntity::toRecipe)
                .collect(Collectors.toList());
    }

//    public Source createSource(SourceToCreate sourceToCreate) {
//        SourceEntity persisted = new SourceEntity(sourceToCreate.getName(), sourceToCreate.getDescription());
//        return sourceRepository.save(persisted).toSource();
//    }
}
