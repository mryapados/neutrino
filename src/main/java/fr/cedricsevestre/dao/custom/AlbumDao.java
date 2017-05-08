package fr.cedricsevestre.dao.custom;

import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Album;

@Repository
public interface AlbumDao extends TranslationDao<Album> {
	
}
