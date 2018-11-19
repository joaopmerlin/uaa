package com.example.uaa;

import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpSession;

public class SessionSecurityUtil {

    private static final String sessionAttrName = "SPRING_SECURITY_SAVED_REQUEST";

    public static SavedRequest getSavedRequest(HttpSession session) {
        if (session != null) {
            return (SavedRequest) session.getAttribute(sessionAttrName);
        }
        return null;
    }
}
