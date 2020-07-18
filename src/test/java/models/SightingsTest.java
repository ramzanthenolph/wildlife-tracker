package models;

import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class SightingsTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    private Sightings newSighting(){
        return new Sightings("Kondoo","Zone A",1);
    }

    private Sightings newSighting2(){return new Sightings("Mbuzi","Zone B",2);}

    @Test
    public void sighting_instantiatesCorrectly(){
        Sightings sighting = newSighting();
        assertTrue(sighting instanceof Sightings);
    }

    @Test
    public void getAnimalId_returnAnimalName_true(){
        Sightings sighting = newSighting();
        assertEquals("Kondoo",sighting.getAnimalName());
    }

    @Test
    public void getLocation_returnSightingLocation_true(){
        Sightings sighting = newSighting();
        assertEquals("Zone A",sighting.getLocation());
    }

    @Test
    public void getRangerId_returnRangerId_true(){
        Sightings sighting = newSighting();
        assertEquals(1,sighting.getRangerid());
    }

    @Test
    public void getTimestamp_returnRangerId_true(){
        Sightings sighting = newSighting();
        Timestamp testTimestamp = new Timestamp(new Date().getTime());
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        assertEquals(dateFormat.format(testTimestamp),dateFormat.format(sighting.getTimestamp()));
    }

    @Test
    public void save_savesSightingIntoDB_true(){
        Sightings sighting = newSighting();
        int idBefore = sighting.getId();
        sighting.save();
        assertNotEquals(idBefore,sighting.getId());
    }

    @Test
    public void find_searchForSighting_true(){
        Sightings sighting1 = newSighting();
        Sightings sighting2 = newSighting2();
        sighting1.save();
        sighting2.save();
        System.out.println();
        assertTrue(Sightings.find(sighting2.getId()).equals(sighting2));
    }

    @Test
    public void all_getAllSightings_true(){
        Sightings sighting1 = newSighting();
        Sightings sighting2 = newSighting2();
        sighting1.save();
        sighting2.save();
        assertTrue(Sightings.all().contains(sighting1));
        assertTrue(Sightings.all().contains(sighting2));
    }

    @Test
    public void all_getAllLocations_true(){
        Sightings sighting1 = newSighting();
        Sightings sighting2 = newSighting2();
        sighting1.save();
        sighting2.save();
        assertEquals(2, Sightings.getAllLocations().size());
    }

    @Test
    public void filter_getSightingInSingleLocation_true(){
        Sightings sighting1 = newSighting();
        Sightings sighting2 = newSighting2();
        Sightings sighting3 = new Sightings("Mbwa","Zone A",3);
        sighting1.save();
        sighting2.save();
        sighting3.save();
        assertTrue(Sightings.getAllSightingsInLocation("Zone A").contains(sighting1));
        assertTrue(Sightings.getAllSightingsInLocation("Zone A").contains(sighting3));
    }

}