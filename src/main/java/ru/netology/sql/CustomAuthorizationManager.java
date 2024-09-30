package ru.netology.sql;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

public class CustomAuthorizationManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String username = filterInvocation.getRequestUrl().split("/")[filterInvocation.getRequestUrl().split("/").length - 1];

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (!userDetails.getUsername().equals(username) && !authentication.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
            throw new AccessDeniedException("Access denied");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
