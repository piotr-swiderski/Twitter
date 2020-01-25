package controllers.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

import static controllers.utils.ServletUtils.USER_LOGIN;
import static controllers.utils.ServletUtils.USER_PASSWORD;

@WebFilter(filterName = "/RequestLoggingFilter", urlPatterns = {"","/users","/posts"})
public class RequestLoginFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        boolean isLoggedIn = (session != null && session.getAttribute(USER_LOGIN) != null);

        if(isLoggedIn){
            chain.doFilter(request,response);
            return;
        }

        request.getRequestDispatcher("login").forward(request,response);



    }

    @Override
    public void destroy() {
        //we can close resources here
    }

}

