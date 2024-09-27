package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.LoggedUserHolder;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserTrips;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {
    TripService tripService;
    @Mock
    LoggedUserHolder loggedUserHolder;
    @Mock
    UserTrips userTrips;

    @BeforeEach
    void setup() {
        tripService = new TripService();
        tripService.setLoggedUserHolder(loggedUserHolder);
        tripService.setUserTrips(userTrips);
    }

    @Test
    void throw_when_not_logged() {
        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(new User());
        });
    }
}
