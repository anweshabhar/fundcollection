package com.cts.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RegistrationRowMapper implements RowMapper<Registration>{
	
	public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
		Registration registration = new Registration();
		registration.setRegistrationId(rs.getInt("registrationId"));
		registration.setEmpName(rs.getString("EMPNAME"));
		registration.setEmpEmail(rs.getString("EMPEMAIL"));
		registration.setPhoneNo(rs.getString("PHONENO"));
		registration.setUnitCode(rs.getInt("UNITCODE"));
		registration.setAmount(rs.getInt("AMOUNT"));
		
		
		return registration;
	}

}
