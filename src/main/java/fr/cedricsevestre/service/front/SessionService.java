package fr.cedricsevestre.service.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.custom.Member;
import fr.cedricsevestre.entity.engine.User;
import fr.cedricsevestre.exception.ServiceException;

@Component
@Scope("session")
public class SessionService {

//	@Autowired
//	private UserService userService;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
//    private boolean isAuthenticated(){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
//    }
//	
//	public User getUser() throws ServiceException {
//		if (user == null) {
//			if (isAuthenticated()){
//				org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//				user = userService.findByLogin(userDetail.getUsername());
//			} else {
//				user = new Member();
//				user.setEnabled(true);
//				user.setRole(User.ROLE_PUBLIC);
//			}
//		}
//		return user;
//	}


	
	
}
