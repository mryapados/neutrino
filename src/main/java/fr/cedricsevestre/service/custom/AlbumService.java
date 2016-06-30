package fr.cedricsevestre.service.custom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@Service
@Scope(value = "singleton")
public class AlbumService extends TranslationService<Album>{




}
