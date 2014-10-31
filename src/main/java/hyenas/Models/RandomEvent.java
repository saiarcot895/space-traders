package hyenas.Models;

import hyenas.HyenasLoader;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a Random Event
 * @author Alex
 */
public class RandomEvent {
    private RandomEventType eventType;
    private String name;
    private String description;
    private String question;
    private String actionButtonText;
    private String cancelButtonText;
    
    private String actionResultText;
    private String cancelResultText;
    
    /**
     * A RandomEventType, used to distinguish between random events
     */
    public enum RandomEventType {
        Police,
        Trader,
        Pirate,
    }

    /**
     * Initialized RandomEvent based on the RandomEventType
     * @param eventType, the type of random event
     */
    public RandomEvent(RandomEventType eventType) {
        this.eventType = eventType;
        setUp();
    }
    
    /**
     * Sets up the default random event values based on the RandomEvent's
     * eventType
     */
    private void setUp() {
        switch(eventType) {
            case Police:
                name = "Police";
                description = "You've been stopped by police!";
                question = "Pay fine or attempt to run away?";
                actionButtonText = "Pay Fine";
                cancelButtonText = "Run Away";
                break;
            case Trader:
                name = "Trader";
                description = "You've encountered a trader!";
                question = "Trade or ignore?";
                actionButtonText = "Trade";
                cancelButtonText = "Ignore";
                break;
            case Pirate:
                name = "Pirate";
                description = "You've been attacked by pirates!";
                question = "Fight or attempt to run away?";
                actionButtonText = "Fight!";
                cancelButtonText = "Run Away!";
                break;
            default: break;
        }
    }
    
    /**
     * Get the random event's name
     * @return name, the name of the random event
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the random event's description
     * @return description, the description of the random event
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Get the random event's question
     * @return question, the question of the random event
     */
    public String getQuestion() {
        return question;
    }
    
    /**
     * Get the random event's actionButtonText
     * @return actionButtonText, the actionButtonText of the random event
     */
    public String getActionButtonText() {
        return actionButtonText;
    }
    
    /**
     * Get the random event's cancel button text
     * @return cancelButtonText, the cancel button text of the random event
     */
    public String getCancelButtonText() {
        return cancelButtonText;
    }
    
    /**
     * Get the random event's action result text
     * @return actionResultText, the action result text of the random event
     */
    public String getActionResultText() {
        return actionResultText;
    }
    
    /**
     * Get the random event's cancel result text
     * @return cancelResultText, the cancel result text of the random event
     */
    public String getCancelResultText() {
        return cancelResultText;
    }
    
    /**
     * Performs the standard action for the given random event type. Informs
     * whether the player's action succeeded or failed.
     * 
     * @return success, whether the action was successful or failed
     */
    public boolean performAction() {
        Player player = Player.getInstance();
        switch(eventType) {
            case Police:
                player.setCredits(player.getCredits() - 50);
                actionResultText = "You pay the fine and are deducted 50 credits.";
                return true;
            case Trader:
                player.setCredits(player.getCredits() + 100);
                actionResultText = "You trade some unused parts and gain 100 credits.";
                return true;
            case Pirate:
                Random rand = new Random();
                boolean success = rand.nextBoolean();
                Ship ship = player.getShip();
                if (success) {
                    actionResultText = "You successfully fend off the pirates. Your ship is slightly damaged, but you continue to ";
                    double newhealth = ship.getHealth() * .9;
                    ship.setHealth(newhealth);
                } else {
                    actionResultText = "You unsuccessfully fend of the pirates and they steal some credits. Your ship has been severely damaged, so you head back to ";
                    double newhealth = ship.getHealth() * .5;
                    ship.setHealth(newhealth);
                    player.setCredits(player.getCredits() - 50);
                }
                return success;
        }
        
        try {
            HyenasLoader.getInstance().getConnectionManager().getShipTable()
                .updateShipHealth(player.getShip().getHealth());
            HyenasLoader.getInstance().getConnectionManager().getShipTable()
                    .updateShipFuel(player.getShip().getFuel());
        } catch (SQLException ex) {
            Logger.getLogger(RandomEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    /**
     * Performs the standard cancel for the given random event type. Informs
     * whether the player's action succeeded or failed.
     * 
     * @return success, whether the action was successful or failed
     */
    public boolean performCancel() {
        Player player = Player.getInstance();
        switch(eventType) {
            case Police:
                Random rand = new Random();
                boolean success = rand.nextBoolean();
                if (success) {
                    cancelResultText = "You successfully outmaneuver the police and continue to the planet.";
                } else {
                    cancelResultText = "You fail to escape from the police, who fines you double. You return to where you came from having lost some fuel.";
                    player.setCredits(player.getCredits() - 100);
                }
                return success;
            case Trader: return true;
            case Pirate:
                int x = 6;
                Random random = new Random();
                boolean successful = random.nextBoolean();
                Ship ship = player.getShip();
                if (successful) {
                    cancelResultText = "You successfully outmaneuver the pirates and continue to the planet.";
                } else {
                    cancelResultText = "You fail to outmaneuver the pirates and with your ship half damaged, you head back to ";
                    double newhealth = ship.getHealth() * .5;
                    ship.setHealth(newhealth);
                }
                
                return successful;
        }
        
        try {
            HyenasLoader.getInstance().getConnectionManager().getShipTable()
                .updateShipHealth(player.getShip().getHealth());
            HyenasLoader.getInstance().getConnectionManager().getShipTable()
                    .updateShipFuel(player.getShip().getFuel());
        } catch (SQLException ex) {
            Logger.getLogger(RandomEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
}
