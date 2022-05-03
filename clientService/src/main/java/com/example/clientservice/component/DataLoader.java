package com.example.clientservice.component;

import com.example.clientservice.entity.Human;
import com.example.clientservice.entity.enums.ClientStatus;
import com.example.clientservice.entity.enums.Language;
import com.example.clientservice.entity.enums.Region;
import com.example.clientservice.entity.enums.UserType;
import com.example.clientservice.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String initMode;
    final PasswordEncoder passwordEncoder;
    final HumanRepository humanRepository;
    @Override
    public void run(String... args) {
        if(initMode.equalsIgnoreCase("always")){
            humanRepository.save(Human.builder()
                            .userType(UserType.ADMIN)
                            .birthdate(LocalDate.parse("1991-01-23"))
                            .lang(Language.UZBEK)
                            .name("Tojiboyev Umidjon")
                            .number("+998990472436")
                            .region(Region.TASHKENT)
                            .password(passwordEncoder.encode("1234"))
                    .build());
        }
    }
}
