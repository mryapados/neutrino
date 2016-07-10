package fr.cedricsevestre.service.custom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.service.engine.independant.objects.UserService;

@Service
@Scope(value = "singleton")
@CustomService
public class MemberService extends UserService{


}
