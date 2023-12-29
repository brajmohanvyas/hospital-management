package com.HospitalManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.HospitalManagement.Entity.Appointment;
import com.HospitalManagement.exception.PatientNumberNotFoundException;
import com.HospitalManagement.util.DBConnection;

public class HospitalServiceImpl implements IHospitalService{
	
	
	 @Override
	    public Appointment getAppointmentById(int appointmentId,Connection connection) throws PatientNumberNotFoundException {
	        try (
	             PreparedStatement statement = connection.prepareStatement("SELECT * FROM appointments WHERE appointment_Id = ?")) {

	            statement.setInt(1, appointmentId);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	              
	                return mapResultSetToAppointment(resultSet);
	            } else {
	                throw new PatientNumberNotFoundException("Appointment with ID " + appointmentId + " not found in the database.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace(); 
	            return null; 
	        }
	    }


	 @Override
	    public List<Appointment> getAppointmentsForPatient(int patientId,Connection connection) {
	        List<Appointment> appointments = new ArrayList<>();

	        try (
	             PreparedStatement statement = connection.prepareStatement("SELECT * FROM appointments WHERE patient_Id = ?")) {

	            statement.setInt(1, patientId);
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	              
	                appointments.add(mapResultSetToAppointment(resultSet));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return appointments;
	    }


	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) {
		
		return null;
	}
	@Override
    public boolean scheduleAppointment(Appointment appointment,Connection connection) {
        try ( 
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO appointments (patient_Id, doctor_Id, appointment_Date, description,appointment_Id) VALUES (?, ?, ?, ?,?)")) {

           
            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            statement.setString(4, appointment.getDescription());
            statement.setInt(5, appointment.getAppointmentId());
            // Execute the update
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
	@Override
	public boolean updateAppointment(Appointment appointment,Connection connection) {
	    try ( 
	         PreparedStatement statement = connection.prepareStatement(
	                 "UPDATE appointments SET patient_Id = ?, doctor_Id = ?, appointment_Date = ?, description = ? WHERE appointment_Id = ?")) {

	      
	        statement.setInt(1, appointment.getPatientId());
	        statement.setInt(2, appointment.getDoctorId());
	        statement.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
	        statement.setString(4, appointment.getDescription());
	        statement.setInt(5, appointment.getAppointmentId());

	       
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	        return false;
	    }
	}

	  @Override
	    public boolean cancelAppointment(int appointmentId,Connection connection) {
	        try ( 
	             PreparedStatement statement = connection.prepareStatement("DELETE FROM appointments WHERE appointment_Id = ?")) {

	            statement.setInt(1, appointmentId);
	            int rowsAffected = statement.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	private Appointment mapResultSetToAppointment(ResultSet resultSet) throws SQLException {
	   
	    return new Appointment(
	            resultSet.getInt("appointment_Id"),
	            resultSet.getInt("patient_Id"),
	            resultSet.getInt("doctor_Id"),
	            resultSet.getDate("appointment_Date"),
	            resultSet.getString("description")
	    );
	}

}
