package club.mixr.service;

import club.mixr.data.entity.IngredientEntity;
import club.mixr.data.entity.SourceEntity;
import club.mixr.data.repository.IngredientRepository;
import club.mixr.data.repository.SourceRepository;
import club.mixr.dto.Ingredient;
import club.mixr.dto.IngredientToCreate;
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
public class SourceService {

    @Autowired
    SourceRepository sourceRepository;

    public List<Source> allSources() {
        return sourceRepository.findAll()
                .map(SourceEntity::toSource)
                .collect(Collectors.toList());
    }

    public Source createSource(SourceToCreate sourceToCreate) {
        SourceEntity persisted = new SourceEntity(sourceToCreate.getName(), sourceToCreate.getDescription());
        return sourceRepository.save(persisted).toSource();
    }
}
