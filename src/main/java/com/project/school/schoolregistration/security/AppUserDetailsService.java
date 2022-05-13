package com.project.school.schoolregistration.security;

import com.project.school.schoolregistration.entity.Student;
import com.project.school.schoolregistration.repository.StudentRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final StudentRepository repository;

    public AppUserDetailsService(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("school")) {
            Optional<Student> optStudent = this.repository.findByUsername(username);
            Student student = optStudent.orElseThrow(() -> new UsernameNotFoundException("Username or password is invalid."));
            return new BackendUser(username, student.getPassword(), getCredentials(username));
        } else {
            return new BackendUser(username, "pass1234", getCredentials(username));
        }
    }

    private Collection<? extends GrantedAuthority> getCredentials(String username) {
        final Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(username.equals("school") ? "ROLE_SCHOOL" : "ROLE_STUDENT"));
        return authorities;
    }

}
