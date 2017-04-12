package fr.cedricsevestre.service.custom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.service.engine.translation.TranslationService;


@Service
@Scope(value = "singleton")
@CustomService
public class ResumeService extends TranslationService<Resume>{


}
