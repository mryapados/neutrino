package fr.cedricsevestre.service.engine;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.IFile;
import fr.cedricsevestre.exception.ServiceException;

@Deprecated
public interface IFileService<T extends IFile> {


	
}
