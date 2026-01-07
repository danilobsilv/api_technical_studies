package com.studies.api.features.user.validation;

import com.studies.api.features.user.dto.CreateUserType;
import com.studies.api.infra.errorHandler.invalidEmailException.InvalidEmailException;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Component
public class CheckIfEmailIsValid implements UserValidator {
    @Override
    public void validate(CreateUserType data) {
        String userEmail = data.userEmail();
        String domain = userEmail.substring(userEmail.indexOf("@") + 1);
    try {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
        DirContext ctx = new InitialDirContext(env);
        Attributes attrs = ctx.getAttributes(domain, new String[]{"MX"});
        if (attrs.get("MX") == null){
            throw new InvalidEmailException("Esse e-mail pertence a um domínio não registrado.");
        }
    } catch (Exception exception) {
        throw new InvalidEmailException("E-mail inválido: " + exception);
    }

    }
}
