package com.example.jpatest;

import com.example.jpatest.entities.*;
import com.example.jpatest.repositories.ConsultationRepository;
import com.example.jpatest.repositories.MedecinRepository;
import com.example.jpatest.repositories.PatientRepository;
import com.example.jpatest.repositories.RendezVousRepository;
import com.example.jpatest.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class JpaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaTestApplication.class, args);
    }
//    @Bean
//    CommandLineRunner start(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository,
//                            ConsultationRepository consultationRepository){
//        return args -> {
//            patientRepository.save(new Patient(null,"Hassan",new Date(),false,null));
//            patientRepository.save(new Patient(null,"Mohamed",new Date(),true,null));
//            patientRepository.save(new Patient(null,"Najat",new Date(),false,null));
//            medecinRepository.save(new Medecin(null,"Ahmed","hh@gg.com","H1",null));
//            medecinRepository.save(new Medecin(null,"Rachid","hh@gg.com","H2",null));
//            medecinRepository.save(new Medecin(null,"Abdo","hh@gg.com","H3",null));
//
//            Patient p = patientRepository.findById(1L).orElse(null);
//            Patient p1 = patientRepository.findByNom("Hassan");
//
//            Medecin m= medecinRepository.findByNom("Abdo");
//
//            rendezVousRepository.save(new RendezVous(null,new Date(), StatusRDV.PENDING,p,m,null));
//
//            RendezVous r =rendezVousRepository.findById(1L).orElse(null);
//            consultationRepository.save(new Consultation(null,new Date(),"he is okey",r));
//
//        };
//    }
    @Bean
    CommandLineRunner start(IHospitalService iHospitalService,
    PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository){
        return args -> {
            iHospitalService.savePatient(new Patient(null,"Hassan",new Date(),false,null));
            iHospitalService.savePatient(new Patient(null,"Mohamed",new Date(),true,null));
            iHospitalService.savePatient(new Patient(null,"Najat",new Date(),false,null));
            iHospitalService.saveMedecin(new Medecin(null,"Ahmed","hh@gg.com","H1",null));
            iHospitalService.saveMedecin(new Medecin(null,"Rachid","hh@gg.com","H2",null));
            iHospitalService.saveMedecin(new Medecin(null,"Abdo","hh@gg.com","H3",null));

            Patient p = patientRepository.findById(1L).orElse(null);
            Patient p1 = patientRepository.findByNom("Hassan");

            Medecin m= medecinRepository.findByNom("Abdo");

            iHospitalService.saveRendezVous(new RendezVous(null,new Date(), StatusRDV.PENDING,p,m,null));

            RendezVous r =rendezVousRepository.findAll().get(0);
            iHospitalService.saveConsultation(new Consultation(null,new Date(),"he is okey",r));

        };
    }

}
