package hyenas.Models;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 *
 * @author Brian Surber
 */


public class ShipTest {
    
    public ShipTest() {
    }

    /**
     * Test of getShipType method, of class Ship.
     */
    @Test
    public void testGetShipType() {
        Ship ship = new Ship(Ship.ShipType.BUMBLEBEE);
        assertEquals(ship.getShipType(), Ship.ShipType.BUMBLEBEE);
        ship = new Ship(Ship.ShipType.FIREFLY);
        assertEquals(ship.getShipType(), Ship.ShipType.FIREFLY);
        ship = new Ship(Ship.ShipType.FLEA);
        assertEquals(ship.getShipType(), Ship.ShipType.FLEA);
        ship = new Ship(Ship.ShipType.GNAT);
        assertEquals(ship.getShipType(), Ship.ShipType.GNAT);
        ship = new Ship(Ship.ShipType.MOSQUITO);
        assertEquals(ship.getShipType(), Ship.ShipType.MOSQUITO);
    }

    /**
     * Test of setShipType method, of class Ship.
     */
    @Test
    public void testSetShipType() {
        Ship ship = new Ship(Ship.ShipType.BUMBLEBEE);
        ship.setShipType(Ship.ShipType.BUMBLEBEE);
        assertEquals(Ship.ShipType.BUMBLEBEE, ship.getShipType());
        testShipAgainstDefaults(Ship.ShipType.BUMBLEBEE, ship);
        ship.setShipType(Ship.ShipType.FIREFLY);
        assertEquals(Ship.ShipType.FIREFLY, ship.getShipType());
        testShipAgainstDefaults(Ship.ShipType.FIREFLY, ship);
        ship.setShipType(Ship.ShipType.FLEA);
        assertEquals(Ship.ShipType.FLEA, ship.getShipType());
        testShipAgainstDefaults(Ship.ShipType.FLEA, ship);
        ship.setShipType(Ship.ShipType.GNAT);
        assertEquals(Ship.ShipType.GNAT, ship.getShipType());
        testShipAgainstDefaults(Ship.ShipType.GNAT, ship);
        ship.setShipType(Ship.ShipType.MOSQUITO);
        assertEquals(Ship.ShipType.MOSQUITO, ship.getShipType());
        testShipAgainstDefaults(Ship.ShipType.MOSQUITO, ship);
    }

    private void testShipAgainstDefaults(Ship.ShipType type, Ship ship)   {
        Ship def = new Ship(type);
        assertEquals(def.getName(), ship.getName());
        assertEquals(def.getMaxFuel(), ship.getMaxFuel());
        assertEquals(def.getMaxHealth(), ship.getMaxHealth());
        assertEquals(def.getMinTechLevel(), ship.getMinTechLevel());
        assertEquals(def.getPrice(), ship.getPrice());
        assertEquals(def.getWeaponSlots(), ship.getWeaponSlots());
        assertEquals(def.getShieldSlots(), ship.getShieldSlots());
        assertEquals(def.getGadgetSlots(), ship.getGadgetSlots());
        assertEquals(def.getCrewSlots(), ship.getCrewSlots());
        assertEquals(def.getCargoSlots(), ship.getCargoSlots());
    }

    /**
     * Test of getName method, of class Ship.
     */
    @Test
    public void testGetName() {
        Ship ship = new Ship(Ship.ShipType.BUMBLEBEE);
        assertEquals(ship.getName(), "BUMBLEBEE");
        ship = new Ship(Ship.ShipType.FIREFLY);
        assertEquals(ship.getName(), "FIREFLY");
        ship = new Ship(Ship.ShipType.FLEA);
        assertEquals(ship.getName(), "FLEA");
        ship = new Ship(Ship.ShipType.GNAT);
        assertEquals(ship.getName(), "GNAT");
        ship = new Ship(Ship.ShipType.MOSQUITO);
        assertEquals(ship.getName(), "MOSQUITO");
    }

    /**
     * Test of getWeaponSlots method, of class Ship.
     */
    @Test
    public void testGetWeaponSlots() {
        Ship ship = new Ship(Ship.ShipType.BUMBLEBEE);
        assertEquals(ship.getWeaponSlots(), 3);
        ship = new Ship(Ship.ShipType.FIREFLY);
        assertEquals(ship.getWeaponSlots(), 1);
        ship = new Ship(Ship.ShipType.FLEA);
        assertEquals(ship.getWeaponSlots(), 0);
        ship = new Ship(Ship.ShipType.GNAT);
        assertEquals(ship.getWeaponSlots(), 1);
        ship = new Ship(Ship.ShipType.MOSQUITO);
        assertEquals(ship.getWeaponSlots(), 2);
    }

    /**
     * Test of getShieldSlots method, of class Ship.
     */
    @Test
    public void testGetShieldSlots() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getGadgetSlots method, of class Ship.
     */
    @Test
    public void testGetGadgetSlots() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getCrewSlots method, of class Ship.
     */
    @Test
    public void testGetCrewSlots() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getCargoSlots method, of class Ship.
     */
    @Test
    public void testGetCargoSlots() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getFreeCargo method, of class Ship.
     */
    @Test
    public void testGetFreeCargo() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getCargo method, of class Ship.
     */
    @Test
    public void testGetCargo() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getWeapons method, of class Ship.
     */
    @Test
    public void testGetWeapons() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getShields method, of class Ship.
     */
    @Test
    public void testGetShields() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getGadgets method, of class Ship.
     */
    @Test
    public void testGetGadgets() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getCrew method, of class Ship.
     */
    @Test
    public void testGetCrew() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getWares method, of class Ship.
     */
    @Test
    public void testGetWares() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of addCargo method, of class Ship.
     */
    @Test
    public void testAddCargo() {
        List<Ware> cargo = new ArrayList<>();
        Ship ship = new Ship(Ship.ShipType.FLEA);
    }

    /**
     * Test of removeCargo method, of class Ship.
     */
    @Test
    public void testRemoveCargo() {
        //TODO RIGHT NOW
    }

    /**
     * Test of getFuel method, of class Ship.
     */
    @Test
    public void testGetFuel() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of setFuel method, of class Ship.
     */
    @Test
    public void testSetFuel() {
        //TODO
    }

    /**
     * Test of getMaxFuel method, of class Ship.
     */
    @Test
    public void testGetMaxFuel() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getHealth method, of class Ship.
     */
    @Test
    public void testGetHealth() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of setHealth method, of class Ship.
     */
    @Test
    public void testSetHealth() {
        //TODO
    }

    /**
     * Test of getMaxHealth method, of class Ship.
     */
    @Test
    public void testGetMaxHealth() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getPrice method, of class Ship.
     */
    @Test
    public void testGetPrice() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getMinTechLevel method, of class Ship.
     */
    @Test
    public void testGetMinTechLevel() {
        //TODO as with testGetWeaponSlots()
    }

    /**
     * Test of getDefaultShips method, of class Ship.
     */
    @Test
    public void testGetDefaultShips() {
        //TODO
    }
}
