/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Provider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author TRAN VAN AN
 */
public class Validate {
    
    private static String regexPasswordCharacterSpecial = "~`!@#$%^&*()-_+=:;',<>.?/|";
    private static String regexPasswordNumber = "1234567890";
    private static String regexPasswordCharacter = "qwertyuiopasdfghjklzxcvbnm";
    private static String regexPasswordCharacterCap = "QWERTYUIOPASDFGHJKLZXCVBNM";
    
    public static boolean validatePassword(String password){
        boolean hasCharacterSpecial = false;
        boolean hasNumber = false;
        boolean hasCharacter = false;
        boolean hasCharacterCap = false;
        if (password.length() <=8){
            return false;
        }else {
            for (int i = 0; i < password.length(); i++){
                String character = String.valueOf(password.charAt(i));
                if (!hasCharacterSpecial && regexPasswordCharacterSpecial.contains(character)){
                    hasCharacterSpecial = true;
                }
                if (!hasCharacter && regexPasswordCharacter.contains(character)){
                    hasCharacter = true;
                }
                if (!hasNumber && regexPasswordNumber.contains(character)){
                    hasNumber = true;
                }
                if (!hasCharacterCap && regexPasswordCharacterCap.contains(character)){
                    hasCharacterCap = true;
                }
            }
        }
        return hasCharacter && hasNumber && hasCharacterCap && hasCharacterSpecial;
    }
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.find();
    }
}
