package org.fibi.usos.interceptor;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.helper.auth.AccessHelper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoleAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            if (method.getMethod().isAnnotationPresent(RequireRole.class)) {
                Optional<RequireRole> annotation = Optional.ofNullable(method.getMethodAnnotation(RequireRole.class));
                if(annotation.isPresent()) {
                    if (!AccessHelper.hasAuth(Arrays.stream(annotation.get().value()).collect(Collectors.toSet()))) {
                        response.sendError(401, "Error: You don't have needed role.");
                        return false;
                    }

                }
            }
        }
        return true;
    }
}
