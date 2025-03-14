package dev.Zerphyis.medVaps.Service;

import dev.Zerphyis.medVaps.Entity.Login.Login;
import dev.Zerphyis.medVaps.Repositorys.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    LoginRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public Login createPerson(Login person) {
        return repository.save(person);
    }
}
