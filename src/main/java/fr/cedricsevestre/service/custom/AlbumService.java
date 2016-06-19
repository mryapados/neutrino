package fr.cedricsevestre.service.custom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.service.engine.TranslationService;

@Service
@Scope(value = "singleton")
public class AlbumService extends TranslationService<Album>{




}
