package dev.Zerphyis.medVaps.Controllers;

import dev.Zerphyis.medVaps.Entity.Login.Login;
import dev.Zerphyis.medVaps.Entity.Records.Auth.AutenticationData;
import dev.Zerphyis.medVaps.Entity.Records.Auth.LoginData;
import dev.Zerphyis.medVaps.Entity.Records.Auth.ResponseToken;
import dev.Zerphyis.medVaps.Security.ServiceToken;
import dev.Zerphyis.medVaps.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutheticationController {
    @Autowired
    private LoginService serviceLogin;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServiceToken tokenService;

    @PostMapping("/register")
    public AutenticationData registerUser(@RequestBody @Valid Login login) {
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        Login savedPerson = serviceLogin.createPerson(login);
        return new AutenticationData(savedPerson.getEmail(), savedPerson.getPassword(), savedPerson.getRole());
    }

    @PostMapping("/auth")
    public ResponseEntity<ResponseToken> login(@RequestBody LoginData loginRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Login authenticatedPerson = (Login) authentication.getPrincipal();

        if (authenticatedPerson.getEmail().equals(loginRequest.email()) && authenticatedPerson.getEmail().equals(loginRequest.email())) {
            String token = tokenService.generateToken(authenticatedPerson);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseToken(token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
