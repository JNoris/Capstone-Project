package utility;

public class LoginService {

    private boolean validPassword = false;

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
