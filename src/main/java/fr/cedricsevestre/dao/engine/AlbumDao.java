package fr.cedricsevestre.dao.engine;

import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.custom.Album;

@Repository
public interface AlbumDao extends TranslationDao<Album> {
	

}
