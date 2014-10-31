package hyenas.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * A gadget that can be added to a ship
 * @author Alex
 */
public class Gadget {
    private GadgetType type;
    private String name;
    private int price;
    
    /**
     * A GadgetType, used to distinguish between the types of gadget
     */
    public enum GadgetType {
        EXTRA_CARGO,
        NAVIGATION_SYSTEM,
        AUTO_REPAIR_SYSTEM,
        TARGETING_SYSTEM,
        CLOAKING_DEVICE,
        ESCAPE_POD
    }
    
    /**
     * Initializes an instance of Gadget
     * Sets default values based on the gadget type
     * @param type, the type of gadget
     */
    public Gadget(GadgetType type) {
        switch (type) {
            case EXTRA_CARGO:
                name = "Extra Cargo";
                price = 100;
                break;
            case NAVIGATION_SYSTEM:
                name = "Navigation System";
                price = 200;
                break;
            case AUTO_REPAIR_SYSTEM:
                name = "Auto-Repair System";
                price = 300;
                break;
            case TARGETING_SYSTEM:
                name = "Targeting System";
                price = 300;
                break;
            case CLOAKING_DEVICE:
                name = "Cloaking Device";
                price = 1000;
                break;
            case ESCAPE_POD:
                name = "Escape Pod";
                price = 200;
                break;
            
            default: break;
        }
        this.type = type;
    }
    
    /**
     * Get the name of the gadget
     * @return name, the name of the gadget
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the price of the gadget
     * @return price, the price of the gadget
     */
    public int getPrice()    {
        return price;
    }
    
    /**
     * Get the list of default buyable gadgets
     * @return gadgets, the list of gadgets
     */
    public static List<Gadget> getDefaultGadgets() {
        ArrayList<Gadget> gadgets = new ArrayList<>(GadgetType.values().length);
        for (GadgetType type: GadgetType.values()) {
            Gadget gadget = new Gadget(type);
            gadgets.add(gadget);
        }
        return gadgets;
    }
}