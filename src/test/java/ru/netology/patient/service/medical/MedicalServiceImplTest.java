package ru.netology.patient.service.medical;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.repository.PatientInfoRepository;

import static org.junit.jupiter.api.Assertions.*;

class MedicalServiceImplTest {

    @Test
    void checkBloodPressureTest() {
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        patientInfoRepository.
    }

    @Test
    void checkTemperature() {
    }
}