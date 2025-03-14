package dev.Zerphyis.medVaps.Repositorys;

import dev.Zerphyis.medVaps.Entity.Login.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {
Optional<Login>findByEmail(String email);
}
