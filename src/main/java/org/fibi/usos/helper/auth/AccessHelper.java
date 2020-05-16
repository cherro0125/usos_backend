package org.fibi.usos.helper.auth;

import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

public class AccessHelper {
    public static UserModel getUserDetailsFromContext(){
        return (UserModel) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }

    public static boolean hasAuth(Set<UserRole> roles){
        return roles.contains(getUserDetailsFromContext().getRole());
    }
}
