package fr.cedricsevestre.service.back;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.custom.Project;

@Service
@Scope(value = "singleton")
public class AlbumService extends TranslationService<Album>{




}