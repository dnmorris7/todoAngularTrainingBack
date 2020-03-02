package com.davidium.rest.webservices.restfulwebservices.jwt.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.davidium.rest.webservices.restfulwebservices.jwt.JwtUserDetails;


@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "davidiumprime",
        "$2a$10$ZYjCLL8AYk4JLuFXnFR0v..35pLV01EMRONNolajrQ1CqlU4a3/AS", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(1L, "atma",
   		"$2a$10$nkztaHmQ6zXIbtTM4qEizOyrnwpMidfJ99CLDkLtFD/v.gQHP9ix6", "ROLE_USER_2"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }
System.out.println(String.format("USER_IS_FOUND '%s'.", username));
    return findFirst.get();
  }

}


