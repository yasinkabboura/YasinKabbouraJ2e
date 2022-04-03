package ma.enset.spring_mvc;

import ma.enset.spring_mvc.entities.Patient;
import ma.enset.spring_mvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args ->{
//            patientRepository.save(new Patient(null,"Ahmed",new Date(),true,15));
//            patientRepository.save(new Patient(null,"Rachid",new Date(),false,5));
//            patientRepository.save(new Patient(null,"Souad",new Date(),true,12));
//            patientRepository.save(new Patient(null,"Ahmed",new Date(),true,15));
//            patientRepository.save(new Patient(null,"Rachid",new Date(),false,5));
//            patientRepository.save(new Patient(null,"Souad",new Date(),true,12));
//            patientRepository.save(new Patient(null,"Ahmed",new Date(),true,15));
//            patientRepository.save(new Patient(null,"Rachid",new Date(),false,5));
//            patientRepository.save(new Patient(null,"Souad",new Date(),true,12));
            System.out.println("**********************************************************************************");
            patientRepository.findAll().forEach(r ->{
                System.out.println(r.getNom());
            });
            System.out.println("**********************************************************************************");
        };
    }

}
