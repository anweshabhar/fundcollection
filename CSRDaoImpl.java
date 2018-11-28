package com.cts.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.cts.bo.DetailsVO;
import com.cts.bo.RegistrationVO;

@Repository("csrDao")
public class CSRDaoImpl extends JdbcDaoSupport implements CSRDao{

	public CSRUnit getUnitDetails(Integer unitCode) {
		CSRUnit csrUnit=null;
		try{
		String sql = "SELECT * FROM CSRUnit  WHERE unitCode = ?";

		csrUnit = (CSRUnit) getJdbcTemplate().queryForObject(sql,	new Object[] { unitCode }, new CSRUnitRowMapper());
		}catch (final EmptyResultDataAccessException e) {
			return null;
		}
		return csrUnit;

	}
	
	public Integer donateAmount(RegistrationVO registrationVO)  {
		
		String sql = "INSERT INTO Registration "
				+ "(empName, empEmail,phoneNo,unitCode,amount) VALUES (?, ?,?,?,?)";
		
		getJdbcTemplate().update(sql,
				new Object[] { registrationVO.getEmpName(),registrationVO.getEmpEmail(),registrationVO.getPhoneNo(),registrationVO.getUnitCode(),registrationVO.getAmount() });
	
		CSRUnit csrUnit = getUnitDetails(registrationVO.getUnitCode());
		
		String sql1 ="UPDATE CSRUnit  SET  LASTUPDATEDDATE=? , TOTALAMOUNT =? where unitCode=?" ;
		
		int tAmount =registrationVO.getAmount() + csrUnit.getTotalAmount();
		
		getJdbcTemplate().update(sql1, new Object[] {new Date(),tAmount ,csrUnit.getUnitCode()});
		
		
		String sql2 = "SELECT * FROM Registration ";

		List<Registration> registrationList = getJdbcTemplate().query(sql2,	new RegistrationRowMapper());

		 int size=registrationList. size();
		return registrationList.get(size-1).getRegistrationId();

	}
	
	public List<DetailsVO> viewDonatedDetails(Integer unitCode)	 {
		String sql = "select *  from Registration where unitCode=? ";
		List<Registration> registrations = getJdbcTemplate().query(sql,
				new Object[] { unitCode },	new RegistrationRowMapper());
		List<DetailsVO> detailsVOList = new ArrayList<DetailsVO>();

		//System.out.println("registrations size " + registrations.size());

		Iterator<Registration> iterator = registrations.iterator();
		while (iterator.hasNext()) {
			Registration registration = (Registration) iterator.next();

			DetailsVO vo = new DetailsVO();
			vo.setEmpName(registration.getEmpName());
			vo.setEmpEmail(registration.getEmpEmail());
			vo.setPhoneNo(registration.getPhoneNo());
			vo.setAmount(registration.getAmount());
						
			CSRUnit csrUnit  = getUnitDetails(registration.getUnitCode());
			if (csrUnit != null) {
				vo.setUnitName(csrUnit.getUnitName());
				vo.setLastUpdatedDate(csrUnit.getLastUpdatedDate());
			}

			detailsVOList.add(vo);

		}
		//System.out.println("detailsTOList" + detailsVOList.size());
		return detailsVOList;

}

}
