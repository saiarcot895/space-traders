/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.Models;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 *
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
    
    
    public RandomEvent(RandomEventType eventType) {
        this.eventType = eventType;
        setUp();
    }
    
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
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public String getActionButtonText() {
        return actionButtonText;
    }
    
    public String getCancelButtonText() {
        return cancelButtonText;
    }
    
    public String getActionResultText() {
        return actionResultText;
    }
    
    public String getCancelResultText() {
        return cancelResultText;
    }
    
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
                    actionResultText = "You successfully fend off the pirates. your ship is slightly damaged, but you continue to ";
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
        return true;
    }
    
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
        return true;
    }
}
