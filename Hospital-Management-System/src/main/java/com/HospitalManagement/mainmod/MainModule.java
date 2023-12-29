package com.HospitalManagement.mainmod;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.HospitalManagement.Entity.Appointment;
import com.HospitalManagement.dao.HospitalServiceImpl;
import com.HospitalManagement.dao.IHospitalService;
import com.HospitalManagement.util.DBConnection;
public class MainModule {
    public static void main(String[] args) {
      
      
        IHospitalService hospitalService = new HospitalServiceImpl();

     
        try {
        	Connection connection = DBConnection.getConnection();
        	
            
            Appointment newAppointment = new Appointment
            		();
            newAppointment.setAppointmentDate(new Date());
            newAppointment.setAppointmentId(99);
            newAppointment.setDescription("DEscription");
            newAppointment.setDoctorId(1);
            newAppointment.setPatientId(1);
            boolean isScheduled = hospitalService.scheduleAppointment(newAppointment,connection);
            System.out.println("Appointment Scheduled: " + isScheduled);

            
            Appointment appointment = hospitalService.getAppointmentById(99,connection);
            System.out.println("Appointment: " + appointment);

          
            List<Appointment> patientAppointments = hospitalService.getAppointmentsForPatient(1,connection);
            System.out.println("Appointments for Patient: " + patientAppointments);

           
            List<Appointment> doctorAppointments = hospitalService.getAppointmentsForDoctor(1);
            System.out.println("Appointments for Doctor: " + doctorAppointments);

           
            Appointment existingAppointment = hospitalService.getAppointmentById(99,connection);
            existingAppointment.setDescription("Updated Description");
            boolean isUpdated = hospitalService.updateAppointment(existingAppointment,connection);
            System.out.println("Appointment Updated: " + isUpdated);

           
            boolean isCancelled = hospitalService.cancelAppointment(99,connection);
            System.out.println("Appointment Cancelled: " + isCancelled);

          connection.close();
        } catch (Exception e) {
           
            System.out.println("Error: " + e.getMessage());
        }
    }
}
