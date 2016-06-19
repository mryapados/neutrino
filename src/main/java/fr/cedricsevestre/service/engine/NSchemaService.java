package fr.cedricsevestre.service.engine;

import java.io.File;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.dao.engine.NSchemaDao;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.NSchema;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class NSchemaService extends BaseService<NSchema>{


}
