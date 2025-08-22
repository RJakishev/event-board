package ee.ruslan.event_board.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static boolean isLoggedInUser() {
        return SecurityContextHolder.getContext() != null
                && SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null
                && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
    }
}
