package fr.cedricsevestre.service.engine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.custom.Project;

@Service
@Scope(value = "singleton")
public class ProjectService extends TranslationService<Project>{




}
