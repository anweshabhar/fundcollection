package com.cts.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.dao.CSRDao;
import com.cts.dao.CSRUnit;
import com.cts.exceptions.InvalidEmailException;
import com.cts.exceptions.InvalidEmpNameException;
import com.cts.exceptions.InvalidPhoneNoException;
import com.cts.exceptions.InvalidUnitCodeException;
import com.cts.exceptions.NoRecordsException;

@Component("csrBO")
public class CSRBO {

	private CSRDao csrDao;

	public CSRDao getCsrDao() {
		return csrDao;
	}

	@Autowired
	public void setCsrDao(CSRDao csrDao) {
		this.csrDao = csrDao;
	}

	public CSRBO() {

	}
	
public Integer donateAmount(RegistrationVO registrationVO) throws InvalidEmpNameException, InvalidEmailException, InvalidUnitCodeException, InvalidPhoneNoException {
	
		
		//String Name=registrationVO.getEmpName();
		String nameRegex="^[A-Za-z\\s]+$";
		/*for (int i = 0; i < Name.length(); i++) {
			if(!((Name.charAt(i)>=65 && Name.charAt(i)<=90) ||(Name.charAt(i)>=97 && Name.charAt(i)<=123) ||(Name.charAt(i)==32)) )
			{
				throw new InvalidEmpNameException();
			}
		}*/
		
		if(!registrationVO.getEmpName().matches( nameRegex )) {
			throw new InvalidEmpNameException();
		}
	
		
		/*for (int i = 0; i < Email.length(); i++) {
			if(!((Email.charAt(i)>=65 && Email.charAt(i)<=90) ||(Email.charAt(i)>=97 && Email.charAt(i)<=123) ||(Email.charAt(i)==95) || Email.charAt(i)>=48 && Email.charAt(i)<=57) )
			{
				throw new InvalidEmailException();
			}
		}*/
		
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(!(registrationVO.getEmpEmail().matches(EMAIL_REGEX))){
			throw new InvalidEmailException();
		}

		String phoneNo = registrationVO.getPhoneNo();
		if ((phoneNo.length() == 10)) {
			for (int i = 0; i < phoneNo.length(); i++) {
				if (!((phoneNo.charAt(i) >= 48 && phoneNo.charAt(i) <= 57))) {
					throw new InvalidPhoneNoException();
				}
			}
		} else {
			throw new InvalidPhoneNoException();
		}
		
		CSRUnit csrUnit=csrDao.getUnitDetails(registrationVO.getUnitCode());
		if(csrUnit==null)
		{
			throw new InvalidUnitCodeException();
		}
	

		return		csrDao.donateAmount(registrationVO);
		
	
}
	
	public List<DetailsVO> viewDonatedDetails(Integer unitCode) throws NoRecordsException{
		List<DetailsVO> list= csrDao.viewDonatedDetails(unitCode);
		if(list.size()==0){
			throw new NoRecordsException();
		}
		return list;
		
	}
	
	

}
