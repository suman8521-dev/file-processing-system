package com.file.security;
import com.file.entity.User;
import com.file.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepo.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(
                        "User not found with email: " + username));

        GrantedAuthority authority=new SimpleGrantedAuthority(user.getRole().toString());
        Collection<GrantedAuthority> grantedAuthorityCollections=Collections.singletonList(authority);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),grantedAuthorityCollections);
    }
}
