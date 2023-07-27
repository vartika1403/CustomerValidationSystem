package springbootproject.courses.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomerValidation {
	
	public static boolean isValidEmail(String email) {
		if (email== null || email.isEmpty()) {
			return false;
		}
		
        // Regex to check valid email address.

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";  
        Pattern pattern = Pattern.compile(regex);  
        
        // Pattern class contains matcher() method
        // to find matching between given
        // email address using regular expression.
        Matcher matcher = pattern.matcher(email);  

        return matcher.matches();

	}
	
	public static boolean isValidPanCardNo(String panCardNo)
    {
 
        // Regex to check valid PAN Card number.
        String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
 
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
 
        // If the PAN Card number
        // is empty return false
        if (panCardNo == null || panCardNo.isEmpty())
        {
            return false;
        }
 
        // Pattern class contains matcher() method
        // to find matching between given
        // PAN Card number using regular expression.
        Matcher m = p.matcher(panCardNo);
 
        // Return if the PAN Card number
        // matched the ReGex
        return m.matches();
    }

}
