package org.craftedsw.tripservicekata.user;

public class LoggedUserHolder {
    public static User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
