package utility;

/**
 * This class is used to verify login information.
 *
 * @author Jatin Buriac
 */
public class LoginService {

    private boolean validPassword = false;

    /**
     * Check if the password input is the same with the one on record.
     *
     * @param password input password.
     * @return True if they match. False otherwise.
     */
    public boolean checkPassword(String password) {
        System.out.println(password);
        if (password.equals(Settings.getInstance().getPassword()) || password.equals(Settings.getInstance().getMasterPassword())) {
            validPassword = true;
        } else {
            validPassword = false;
        }
        return validPassword;
    }
}
