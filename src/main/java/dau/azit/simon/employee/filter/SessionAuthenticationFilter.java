package dau.azit.simon.employee.filter;

import dau.azit.simon.config.SimonConfigProperties;
import dau.azit.simon.employee.entity.Employee;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SessionAuthenticationFilter extends GenericFilter {

    private final SimonConfigProperties properties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Employee employee = (Employee) session.getAttribute(properties.getKey());
        if (Objects.nonNull(employee)) {
            setAuthentication(employee);
        }
        chain.doFilter(request, response);
    }

    private void setAuthentication(Employee employee) {
        List<SimpleGrantedAuthority> authorities = Stream.of(employee.getRole().toString()).map(SimpleGrantedAuthority::new).toList();
        User user = new User(employee.getPhoneNumber(), employee.getPassword(), authorities);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, "", authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
