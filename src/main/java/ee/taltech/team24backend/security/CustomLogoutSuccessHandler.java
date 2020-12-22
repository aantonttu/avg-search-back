package ee.taltech.team24backend.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class CustomLogoutSuccessHandler extends
        SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication)
      throws IOException, ServletException {
 
        String refererUrl = request.getHeader("Referer");
        log.info("Logout from: " + refererUrl);

        super.onLogoutSuccess(request, response, authentication);
    }
}