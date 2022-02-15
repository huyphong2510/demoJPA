//package com.vti.demo.dto.filter;
//
//import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import com.vti.demo.service.IUserService;
//import com.vti.demo.service.JWTTokenService;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//
//public class JWTAuthorizationFilter extends GenericFilterBean {
//
//    private IUserService service;
//
//    public JWTAuthorizationFilter(IUserService service) {
//        this.service = service;
//    }
//
//    @Override
//    public void doFilter(
//            ServletRequest servletRequest,
//            ServletResponse servletResponse,
//            FilterChain filterChain) throws IOException, ServletException {
//
//        Authentication authentication = JWTTokenService.parseTokenToUserInformation((HttpServletRequest) servletRequest, service);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
//
