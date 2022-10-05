package com.joverlost.ejournal;

import com.joverlost.ejournal.entity.Role;
import com.joverlost.ejournal.entity.User;
import com.joverlost.ejournal.repository.RoleRepository;
import com.joverlost.ejournal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.joverlost.ejournal.entity.enums.ERole.*;

@Slf4j
@SpringBootApplication
public class EJournalApplication {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(EJournalApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            if(
                    roleRepository.findByName(ROLE_TEACHER).isPresent() &&
                    roleRepository.findByName(ROLE_STUDENT).isPresent() &&
                    roleRepository.findByName(ROLE_DIRECTOR).isPresent() &&
                    roleRepository.findByName(ROLE_DEAN).isPresent()
            ){
                log.info("Все роли существуют");
            }else{
                roleRepository.deleteAll();
                Role student=new Role();
                student.setName(ROLE_STUDENT);
                Role dean=new Role();
                dean.setName(ROLE_DEAN);
                Role teacher=new Role();
                teacher.setName(ROLE_TEACHER);
                Role director=new Role();
                director.setName(ROLE_DIRECTOR);

                roleRepository.save(student);
                roleRepository.save(dean);
                roleRepository.save(teacher);
                roleRepository.save(director);
            }
        };
    }
}
