package utility;

public class LoginService {
    private boolean validPassword = false;

    /*
     * @author Jatin Noris Buriac
     * 
     * @date Feb 05, 2020
     * 
     * ADD LATER: Password should be less than 25 and more than 6 characters in
     * length. Password should contain at least one upper case and one lower case
     * alphabet. Password should contain at least one number. Password should
     * contain at least one special character.
     */
    public boolean checkPassword(String password) {
        if (password.equals("password")) {
            validPassword = true;
        } else {
            validPassword = false;
        }
        return validPassword;
    }
}