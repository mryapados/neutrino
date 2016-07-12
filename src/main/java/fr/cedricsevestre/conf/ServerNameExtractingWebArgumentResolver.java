package fr.cedricsevestre.conf;

import static javax.servlet.jsp.PageContext.REQUEST_SCOPE;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import fr.cedricsevestre.entity.engine.independant.objects.Folder;

public final class ServerNameExtractingWebArgumentResolver implements WebArgumentResolver {

	@Override
	public Object resolveArgument(final MethodParameter mp, final NativeWebRequest nwr) throws Exception {
		Object argument = UNRESOLVED;
		if (mp.getParameterType().equals(Folder.class)) {
			// Assumes that a MyObject is bound to the session elsewhere using
			// attribute key "myobjkey" on a successful authentication.
			if ((argument = nwr.getAttribute("folder", REQUEST_SCOPE)) == null) {
				throw new Exception("Fail, no Folder bound to request!");
			}
		}
		return argument;
	}

}