package filters;

import dao.UserDaoImpl;
import model.User;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "cookieFilter", urlPatterns = {
        "/main", "/profile", "/basket",  "/catalog", "/editProfile",
            "/promo"})

public class CookieFilter implements Filter {

    public CookieFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

//        User userInSession = MyUtils.getLoginedUser(session);
        //
//        if (userInSession != null) {
//            session.setAttribute("COOKIE_CHECKED", "CHECKED");
//            chain.doFilter(request, response);
//            return;
//        }

        String flag = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userEmail".equals(cookie.getName())) {
                    flag = cookie.getValue();
                }
            }
        }
        User user = new User();
        if (flag != null) {
            UserDaoImpl userDao = new UserDaoImpl();
            UsersService usersService = new UsersServiceImpl(userDao);
            try {
                user = usersService.findByEmail(flag);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (user != null) {
                session.setAttribute("loginedUser", user.getEmail());
            }
        }


        // Флаг(flag) для проверки Cookie.
//        String checked = (String) session.getAttribute("COOKIE_CHECKED");
//        if (checked == null && conn != null) {
//            String userName = MyUtils.getUserNameInCookie(req);
//            try {
//                User user = DBUtils.findUser(conn, userName);
//                MyUtils.storeLoginedUser(session, user);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            // Отметить проверенные Cookie.
//            session.setAttribute("COOKIE_CHECKED", "CHECKED");
//        }

        chain.doFilter(request, response);
    }
}
