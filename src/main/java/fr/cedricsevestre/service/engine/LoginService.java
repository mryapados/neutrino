package fr.cedricsevestre.service.engine;

import java.util.Arrays;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.dao.engine.UserDao;
import fr.cedricsevestre.entity.engine.User;
 
@Service
public class LoginService implements UserDetailsService{
	private UserDao userDao;
    
    @Autowired
    public LoginService(UserDao userDao)
    {
        this.userDao = userDao;
    }
 
    @Override
    public UserDetails loadUserByUsername( String login ) throws UsernameNotFoundException
    {
		User user = userDao.FindByLogin(login);
	    if( user == null ) throw new UsernameNotFoundException("User " + login + " not found");
	    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
	    
	    UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getEncryptedPassword(), authorities);

	    return userDetails;
    }
}
