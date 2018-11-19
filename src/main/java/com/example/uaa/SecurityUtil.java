package com.example.uaa;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpSession;

public class SecurityUtil {

    private static final String sessionAttrName = "SPRING_SECURITY_SAVED_REQUEST";

    public static SavedRequest getSavedRequest(HttpSession session) {
        if (session != null) {
            return (SavedRequest) session.getAttribute(sessionAttrName);
        }
        return null;
    }

    public static User getUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return null;
    }

    public static String getClient() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return ((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication()).getOAuth2Request().getClientId();
        }
        return null;
    }
}
