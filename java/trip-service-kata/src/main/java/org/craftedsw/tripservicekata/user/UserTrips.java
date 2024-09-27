package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.trip.TripDAO;

import java.util.List;

public class UserTrips {
    public List<Trip> findTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
