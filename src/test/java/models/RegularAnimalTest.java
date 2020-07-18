package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegularAnimalTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    private RegularAnimal newAnimal() {
        return new RegularAnimal("Goat","Healthy","Young");
    }

    @Test
    public void animal_instantiatesCorrectly_true(){
        RegularAnimal regularAnimal = newAnimal();
        assertTrue(regularAnimal instanceof RegularAnimal);
    }

    @Test
    public void getName_returnAnimalsName(){
        RegularAnimal normalAnimal = newAnimal();
        assertEquals("Goat", normalAnimal.getName());
    }

    @Test
    public void getHealth_returnAnimalsHealth(){
        RegularAnimal normalAnimal = newAnimal();
        assertEquals("Healthy", normalAnimal.getHealth());
    }

    @Test
    public void getAge_returnAnimalsAge(){
        RegularAnimal normalAnimal = newAnimal();
        assertEquals("Young", normalAnimal.getAge());
    }

    @Test
    public void getStatus_returnAnimalsStatus(){
        RegularAnimal normalAnimal = newAnimal();
        assertEquals("Not Endangered", normalAnimal.getType());
    }

    @Test
    public void save_savedToDb_int(){
        RegularAnimal normalAnimal = newAnimal();
        normalAnimal.save();
        assertEquals(normalAnimal.getId(),RegularAnimal.all().get(0).getId());
    }

    @Test
    public void find_locateNormalAnimal_Name(){
        RegularAnimal normalAnimal = newAnimal();
        normalAnimal.save();
        RegularAnimal foundAnimal = RegularAnimal.find(normalAnimal.getId());
        assertTrue(normalAnimal.equals(foundAnimal));
    }

}