package org.craftedsw.tripservicekata.user;

public class LoggedUserHolder {
    public User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
