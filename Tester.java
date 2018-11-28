/**
 * This file contains Tester class
 */
package com.cts.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cts.bo.DetailsVO;
import com.cts.bo.RegistrationVO;
import com.cts.exceptions.InvalidEmailException;
import com.cts.exceptions.InvalidEmpNameException;
import com.cts.exceptions.InvalidUnitCodeException;
import com.cts.exceptions.NoRecordsException;
import com.cts.manager.CSRManager;


/**
 * <h3>Description :</h3>This is the tester class of the application which
 * displays the menu and accepts data and populates the transfer object and
 * sends it to the Manager class.
 * 
 * @author Academy Java Solutions, Bangalore, Cognizant Technology Solutions
 */
public class Tester {
	 
	private static CSRManager manager;
	private static RegistrationVO registrationVO;
	private static ApplicationContext context;
	
	public static AnnotationConfigApplicationContext ctx;
	//private static DetailsVO detailsVO;
	
	
	
	public static RegistrationVO getRegistrationVO() {
		return registrationVO;
	}

	public static CSRManager getManager() {
		return manager;
	}

	public static void setManager(CSRManager manager) {
		Tester.manager = manager;
	}

	public static void setRegistrationVO(RegistrationVO registrationVO) {
		Tester.registrationVO = registrationVO;
	}

	/*public static DetailsVO getDetailsVO() {
		return detailsVO;
	}

	public static void setDetailsVO(DetailsVO detailsVO) {
		Tester.detailsVO = detailsVO;
	}
*/
	public void donateAmount() {
			
	
			try{
			  Integer regId= manager.donateAmount(registrationVO);
			  			  
			  if(regId!=0)
				{
					System.out.println("Donated successfully with Registration Id "+regId); 
				}
				else{
					System.out.println("Sorry .. Registration was Unsuccessfull..");
				}
			} catch (InvalidEmailException e) {
			
				System.out.println("Please enter valid Email Id");
			}catch (InvalidEmpNameException e) {
			
				System.out.println("Please enter valid Name");
			}  catch (InvalidUnitCodeException e) {
				
				System.out.println("Please enter valid unit code");
			}  
			catch (Exception e) {
				
				System.out.println("Some error occured"+e.getMessage());
				e.printStackTrace();
			}		
			
		

	}

	public void viewDonatedDetails() {
		System.out.println("\n\n\n\n"); 
		System.out.println("  ****   View Doanted Details   ****"); 
		System.out.println("  -----------------------------------------"); 
		
		System.out.print("  Enter the Unit Code    :"); 
		Integer unitCode=Integer.parseInt(acceptString());
	
		System.out.println();

		 List<DetailsVO> list;
		try {
			list = manager.viewDonatedDetails(unitCode);
			System.out.println("\n\n  EmpEmail      \t\tEmpName      \tPhoneNo      \t UnitName      \t Amount     \tLastUpdatedDate"); 
			 System.out.println("---------------------	       ----------------	----------------	  ----------------	      -----------        --------------------------" );
			for(DetailsVO vo:list ){
				
				String lastDate= new DateUtility().getStringFromDate(vo.getLastUpdatedDate());
				 
				System.out.println(vo.getEmpEmail()+"\t\t"+vo.getEmpName()+"\t\t\t"+vo.getPhoneNo()+"\t\t"+vo.getUnitName()+"\t\t"+vo.getAmount()+"\t\t"+lastDate);
			 }
			
		} catch (NoRecordsException e) {
			// TODO Auto-generated catch block
			//e.getMessage();
			System.out.println("List is empty");
			//e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			System.out.println("Invalid date ");
			//e.printStackTrace();
		}
		
				
	}
	
	public void displayEnglish(){
		
		/*String name = context.getMessage("customer.name", 
				null, Locale.US);
		System.out.println("Customer name (English) : " + name);
		
		String title = context.getMessage("title", 
				null, Locale.US);
		String service1 = context.getMessage("service1", 
				null, Locale.US);
		String service2 = context.getMessage("service2", 
				null, Locale.US);
		String service3 = context.getMessage("service3", 
				null, Locale.US);*/
		
		ctx.get
		
		while (true) {

			
			System.out.println();
			System.out.println("**********************************"); 
			System.out.println(title);				 
			System.out.println("***********************************");
			System.out.println(service1); 
			System.out.println(service2);		
			System.out.println(service3); 

			System.out.print("\n Enter the choice : "); 

			// accepting the choice
			String choice = acceptString();
			int option = 0;
			try {
				// parsing the accepted option into integer
				option = Integer.parseInt(choice);
				// choosing the right module
				switch (option) {
				case 1:
					donateAmount();
					break;
				case 2:
					viewDonatedDetails();
					break;
				case 3:
					System.out.println("Exiting the application!!!");
					System.exit(0);
				default:
					System.out.println("Please enter a Valid input i.e 1/2/3");
					break;
				}
			} catch (Exception exception) {
				System.out
						.println("\n\nThis is an error message from Starter Class - \nDetails of exception : "
								+ exception);
			}
		}
		
	}
	
public void displayFrench(){
		
	/*String name = context.getMessage("customer.name", 
			null, Locale.US);
	System.out.println("Customer name (English) : " + name);*/
	
	String title = context.getMessage("title", 
			null, Locale.FRENCH);
	String service1 = context.getMessage("service1", 
			null, Locale.FRENCH);
	String service2 = context.getMessage("service2", 
			null, Locale.FRENCH);
	String service3 = context.getMessage("service3", 
			null, Locale.FRENCH);
	String urChoice=context.getMessage("choice", 
			null, Locale.FRENCH);
	String exiting=context.getMessage("exiting", 
			null, Locale.FRENCH);
	String validInput=context.getMessage("vailidInput", 
			null, Locale.FRENCH);
	
	while (true) {

		
		System.out.println();
		System.out.println("**********************************"); 
		System.out.println(title);				 
		System.out.println("***********************************");
		System.out.println(service1); 
		System.out.println(service2);		
		System.out.println(service3); 

		System.out.print("\n"+urChoice+" : "); 

		// accepting the choice
		String choice = acceptString();
		int option = 0;
		try {
			// parsing the accepted option into integer
			option = Integer.parseInt(choice);
			// choosing the right module
			switch (option) {
			case 1:
				donateAmount();
				break;
			case 2:
				viewDonatedDetails();
				break;
			case 3:
				System.out.println(exiting);
				System.exit(0);
			default:
				System.out.println(validInput);
				break;
			}
		} catch (Exception exception) {
			System.out
					.println("\n\nThis is an error message from Starter Class - \nDetails of exception : "
							+ exception);
		}
	}
	
		
	}

	public static void main(String args[]) throws Exception {

	/*	context = new ClassPathXmlApplicationContext(
				"spring1.xml");

		manager = (CSRManager) context.getBean("manager");
		registrationVO=(RegistrationVO)context.getBean("registrationVO");*/
		
		ctx = new AnnotationConfigApplicationContext( AppConfig.class );
		registrationVO = ctx.getBean( RegistrationVO.class );
		
		while (true) {
		System.out.println();
		System.out.println("**********************************"); 
		System.out.println("  Pls select the language ");				 
		System.out.println("***********************************");
		System.out.println("    1. English"); 
		System.out.println("    2. French");		
		System.out.println("    3. Exit"); 
		
		
		System.out.print("\n Enter the choice : "); 

		// accepting the choice
		String choice = acceptString();
		int option = 0;
		try {
			// parsing the accepted option into integer
			option = Integer.parseInt(choice);
			// choosing the right module
			switch (option) {
			case 1:
				new Tester().displayEnglish();
				break;
			case 2:
				new Tester().displayFrench();
				break;
			case 3:
				System.out.println("Exiting the application!!!");
				System.exit(0);
			default:
				System.out.println("Please enter a Valid input i.e 1/2/3");
				break;
			}
		} catch (Exception exception) {
			System.out
					.println("\n\nThis is an error message from Starter Class - \nDetails of exception : "
							+ exception);
		}
		}
		
	}

	public static String acceptString() {
		// the return variable initialization
		String stringData = null;
		BufferedReader input = null;
		try {
			// chaining the streams
			input = new BufferedReader(new InputStreamReader(System.in));

			// reading data from the reader
			stringData = input.readLine();
		} catch (IOException ioException) {
			System.out.println("Error in accepting data.");
		} finally {
			input = null;
		}
		return stringData;
	}

}
