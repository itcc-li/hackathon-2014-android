package li.itcc.hackathon2014.vaduztour.hotcold;

import android.location.Location;

public class DistanceCalculator {
    public static float getDistance(Location locationA, Location locationB) {
        return locationA.distanceTo(locationB);
    }
}