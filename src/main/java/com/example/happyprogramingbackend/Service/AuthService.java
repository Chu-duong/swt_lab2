package com.example.happyprogramingbackend.Service;

import com.example.happyprogramingbackend.common.JwtTokenUtil;
import com.example.happyprogramingbackend.dto.request.LoginRequest;
import com.example.happyprogramingbackend.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final UserDetailsService userDetailsService;
  private final JwtTokenUtil jwtTokenUtil;

  public LoginResponse authenticateUser(LoginRequest request) {
    log.info("Request : {}", request);
    // Tạo đối tượng
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

    // Xác thực từ username và password.
    Authentication authentication = authenticationManager.authenticate(token);
    log.info("Authentication : {}", authentication);

    // Nếu không xảy ra exception tức là thông tin hợp lệ
    // Set thông tin authentication vào Security Context
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Tạo token và trả về cho client
    UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
    String tokenJwt = jwtTokenUtil.generateToken(userDetails);

    return new LoginResponse(null, tokenJwt, true);
  }
}
