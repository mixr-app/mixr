package club.mixr.controller;

import club.mixr.dto.Ingredient;
import club.mixr.dto.IngredientToCreate;
import club.mixr.dto.Source;
import club.mixr.dto.SourceToCreate;
import club.mixr.service.IngredientService;
import club.mixr.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sources/")
public class SourceController {

    @Autowired
    SourceService sourceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Source> allSources() {
        return sourceService.allSources();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Source createIngredient(@RequestBody SourceToCreate sourceToCreate) {
        return sourceService.createSource(sourceToCreate);
    }

}
