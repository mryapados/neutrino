package fr.cedricsevestre.service.custom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.service.engine.UserService;

@Service
@Scope(value = "singleton")
public class MemberService extends UserService{


}
