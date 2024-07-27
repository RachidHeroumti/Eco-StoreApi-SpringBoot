package com.demo.AuthConfigs.services;


import com.demo.AuthConfigs.configs.JwtUtils;
import com.demo.AuthConfigs.DTO.UserDto;
import com.demo.AuthConfigs.models.User;
import com.demo.AuthConfigs.models.VerificationToken;
import com.demo.AuthConfigs.repositories.UserRepo;
import com.demo.AuthConfigs.repositories.VerTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    EmailService emailService ;
    @Autowired
    VerTokenRepo verTokenRepo ;
    @Autowired
    UserRepo userRepo ;
    @Autowired
    JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public UserService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public  String RegisterUser(UserDto user) {
        boolean isExist=  userRepo.existsByEmail(user.getEmail());
        user.setEnabled(false);
       if (isExist) return  "User already exist!";
       User u= new User(user.getName(),user.getEmail(),
                this.passwordEncoder.encode(user.getPassword())
                ,user.getRole());
        userRepo.save(u);
        String token = UUID.randomUUID().toString();
        createVerificationToken(u, token);
        emailService.sendEmail(user.getEmail(), "Email Verification",
                "To verify your email, please click the following link: " + "http://localhost:8080/verify?token=" + token);
        return "created successfuly!";
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)); // 24 hours expiration
        verTokenRepo.save(verificationToken);
    }

    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verTokenRepo.findByToken(token);
        if (verificationToken == null) {
            return "invalid token";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "token expired";
        }

        user.setEnabled(true);
        userRepo.save(user);
        return "valid";
    }

    public String Login(UserDto user) {
        String pass=user.getPassword();
        String email=user.getEmail();
        System.out.println(email+" :"+pass);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, pass)
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return jwtUtils.generateToken(userDetails);

        } catch (AuthenticationException e) {
            return "Invalid email or password!";
        }
    }

    public Optional<User> getUser(int id) {
        return userRepo.findById(id);
    }
}
