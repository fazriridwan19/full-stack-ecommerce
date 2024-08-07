package com.ursklap.ecommerce.annotation;

import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Aspect
@Component
@AllArgsConstructor
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null &&
                CustomUserDetails.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public CustomUserDetails resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
