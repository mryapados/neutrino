package fr.cedricsevestre.service.engine.independant.objects;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOService;
import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.custom.MediaDao;
import fr.cedricsevestre.entity.custom.Media;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.File;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;
import fr.cedricsevestre.service.engine.IFileService;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@Service
@Scope(value = "singleton")
@BOService
public class FileService extends BaseService<File>{


}
