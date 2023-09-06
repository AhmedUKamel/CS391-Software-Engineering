package com.ahmedukamel.hms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ApplicationUserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    private final List<User> USERS;

    public ApplicationUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.USERS = List.of(
                new User("employee1", passwordEncoder.encode("employee1"), Collections.singletonList(new SimpleGrantedAuthority("EMPLOYEE"))),
                new User("employee2", passwordEncoder.encode("employee2"), Collections.singletonList(new SimpleGrantedAuthority("EMPLOYEE"))),
                new User("ahmed",     passwordEncoder.encode("ahmed123"), Collections.singletonList(new SimpleGrantedAuthority("GUEST"))),
                new User("mohammed",  passwordEncoder.encode("mohammed"), Collections.singletonList(new SimpleGrantedAuthority("GUEST"))),
                new User("hassan",    passwordEncoder.encode("hassan"), Collections.singletonList(new SimpleGrantedAuthority("GUEST")))
        );
    }

    public Set<User> getGuests() {
        return USERS.stream().filter(user -> user.getAuthorities().contains(new SimpleGrantedAuthority("GUEST"))).collect(Collectors.toSet());
    }

    public User get(String username) {
        return USERS.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return USERS.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElseThrow();
    }
}
