package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.LoggedUserHolder;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserTrips;

public class TripService {

	private LoggedUserHolder loggedUserHolder = new LoggedUserHolder();
	private UserTrips userTrips = new UserTrips();

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = loggedUserHolder.getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = userTrips.findTrips(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

	public void setLoggedUserHolder(LoggedUserHolder loggedUserHolder) {
		this.loggedUserHolder = loggedUserHolder;
	}

	public void setUserTrips(UserTrips userTrips) {
		this.userTrips = userTrips;
	}
}
