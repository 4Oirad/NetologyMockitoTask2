package ru.netology.patient.service.medical;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MedicalServiceImplTest {

    PatientInfo patientInfo = new PatientInfo(
            "123",
            "Ivan",
            "Ivanov",
            LocalDate.of(1975, 03, 28),
            new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80))
    );

    @Test
    void checkBloodPressureShouldSendMessage() {
        PatientInfoRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.when(patientInfoFileRepository.getById("123")).thenReturn(patientInfo);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, sendAlertService);
        medicalService.checkBloodPressure(patientInfo.getId(), new BloodPressure(135, 80));
        Mockito.verify(sendAlertService, Mockito.atLeastOnce()).send(Mockito.anyString());
    }

    @Test
    void checkBloodPressureShouldNotSendMessage() {
        PatientInfoRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.when(patientInfoFileRepository.getById("123")).thenReturn(patientInfo);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, sendAlertService);
        medicalService.checkBloodPressure(patientInfo.getId(), new BloodPressure(120, 80));
        Mockito.verify(sendAlertService, Mockito.never()).send(Mockito.anyString());
    }

    @Test
    void checkTemperatureShouldSendMessage() {
        PatientInfoRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.when(patientInfoFileRepository.getById("123")).thenReturn(patientInfo);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, sendAlertService);
        medicalService.checkTemperature(patientInfo.getId(), new BigDecimal("35"));
        Mockito.verify(sendAlertService, Mockito.atLeastOnce()).send(Mockito.anyString());
    }

    @Test
    void checkTemperatureShouldNotSendMessage() {
        PatientInfoRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.when(patientInfoFileRepository.getById("123")).thenReturn(patientInfo);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, sendAlertService);
        medicalService.checkTemperature(patientInfo.getId(), new BigDecimal("36.6"));
        Mockito.verify(sendAlertService, Mockito.never()).send(Mockito.anyString());
    }
}