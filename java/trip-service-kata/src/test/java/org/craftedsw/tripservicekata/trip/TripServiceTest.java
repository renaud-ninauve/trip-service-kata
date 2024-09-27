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

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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

    @Test
    void empty_when_user_has_no_friends() {
        when(loggedUserHolder.getLoggedUser()).thenReturn(new User());

        List<Trip> actual = tripService.getTripsByUser(new User());

        assertIterableEquals(emptyList(), actual);
    }

    @Test
    void empty_when_logged_is_friend_with_user_but_inverse_not_true() {
        User userParam = new User();

        User loggedIn = new User();
        loggedIn.getFriends().add(userParam);
        when(loggedUserHolder.getLoggedUser()).thenReturn(loggedIn);

        List<Trip> actual = tripService.getTripsByUser(userParam);

        assertIterableEquals(emptyList(), actual);
    }

    @Test
    void user_trips_when_user_is_friend_with_logged() {;
        User loggedIn = new User();
        when(loggedUserHolder.getLoggedUser()).thenReturn(loggedIn);

        User userParam = new User();
        userParam.getFriends().add(loggedIn);

        List<Trip> userTripList = Arrays.asList(new Trip(), new Trip());
        when(userTrips.findTrips(userParam)).thenReturn(userTripList);

        List<Trip> actual = tripService.getTripsByUser(userParam);

        assertIterableEquals(userTripList, actual);
    }
}
