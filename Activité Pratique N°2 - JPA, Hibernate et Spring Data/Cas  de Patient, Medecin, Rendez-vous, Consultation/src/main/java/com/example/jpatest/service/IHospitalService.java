package com.example.jpatest.service;

import com.example.jpatest.entities.Consultation;
import com.example.jpatest.entities.Medecin;
import com.example.jpatest.entities.Patient;
import com.example.jpatest.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
