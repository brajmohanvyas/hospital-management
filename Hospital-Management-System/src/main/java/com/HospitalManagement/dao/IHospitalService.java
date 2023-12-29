package com.HospitalManagement.dao;

import java.sql.Connection;
import java.util.List;

import com.HospitalManagement.Entity.Appointment;
import com.HospitalManagement.exception.PatientNumberNotFoundException;

public interface IHospitalService {

    List<Appointment> getAppointmentsForDoctor(int doctorId);
	Appointment getAppointmentById(int appointmentId, Connection connection) throws PatientNumberNotFoundException;
	List<Appointment> getAppointmentsForPatient(int patientId, Connection connection);
	boolean scheduleAppointment(Appointment appointment, Connection connection);
	boolean updateAppointment(Appointment appointment, Connection connection);
	boolean cancelAppointment(int appointmentId, Connection connection);
}
