package hyenas;

import hyenas.Models.Gadget;
import hyenas.Models.Player;
import hyenas.Models.Ship;
import hyenas.Models.Weapon;
import hyenas.UI.AlertPane;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Brian Surber
 */


public class CombatController implements Initializable {

    /**
     * label displaying the player's current health
     */
    @FXML
    private Label playerHealth;

    /**
     * label displaying the player's current shields
     */
    @FXML
    private Label playerShields;

    /**
     * label displaying the enemy's current health
     */
    @FXML
    private Label enemyHealth;

    /**
     * label displaying the enemy's current shields
     */
    @FXML
    private Label enemyShields;

    /**
     * button to click to pick a weapon and attack
     */
    @FXML
    private Button fight;

    /**
     * button to click to attempt to flee the fight
     */
    @FXML
    private Button flee;

    /**
     * button to click to use a gadget in the fight
     */
    @FXML
    private Button gadget;

    private Ship playerShip;

    private Ship enemyShip;

    @FXML
    private AnchorPane anchorPane;

    private boolean stealthed;
    
    private int targeters;
    
    private int timesStealthed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerShip = Player.getInstance().getShip();
        stealthed = false;
        timesStealthed = 0;
        Random rand = new Random();
        int randomInt = rand.nextInt(Ship.getDefaultShips().size());
        //enemy ship type is completely random
        enemyShip = new Ship(Ship.getDefaultShips().
                get(randomInt).getShipType());
        for (int i = 0; i < enemyShip.getWeaponSlots(); i++)    {
            enemyShip.getWeapons().add(new Weapon(Weapon.WeaponType.PULSE));
        }
        playerHealth.setText(playerShip.getHealth() + "/"
                 + playerShip.getMaxHealth());
        playerShields.setText(playerShip.getShieldStrength()+ "/"
                 + playerShip.getMaxShieldStrength());
        enemyHealth.setText(enemyShip.getHealth() + "/"
                 + enemyShip.getMaxHealth());
        enemyShields.setText(enemyShip.getShieldStrength() + "/"
                 + enemyShip.getMaxShieldStrength());
        targeters = 0;
        for (Gadget g : playerShip.getGadgets())   {
            if(g.getType() == Gadget.GadgetType.TARGETING_SYSTEM)
                targeters++;
        }
    }

    public void onGadget(ActionEvent e) {
        boolean found = false;
        for (Gadget gadget : playerShip.getGadgets())   {
            if(gadget.getType() == Gadget.GadgetType.CLOAKING_DEVICE && timesStealthed < 3)   {
                found = true;
                break;
            }
        }
        if(found)  {
            AlertPane alertPane = new AlertPane(AlertPane.AlertPaneType.TWOBUTTONS,
                "Gadgets", "Pick a gadget.");
            alertPane.getActionButton().setText("Cloaking Device");
            EventHandler<ActionEvent> stealthAction = (ActionEvent e2) -> {
                anchorPane.getChildren().remove(alertPane);
                stealthed = true;
            };
            EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
                anchorPane.getChildren().remove(alertPane);
            };
            alertPane.getActionButton().setOnAction(stealthAction);
            alertPane.getCloseButton().setOnAction(closeAction);
            anchorPane.getChildren().add(alertPane);
        }
        else    {
            AlertPane alertPane = new AlertPane(AlertPane.AlertPaneType.ONEBUTTON,
                "Invalid", "You don't have any useable gadgets.");
            EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
                anchorPane.getChildren().remove(alertPane);
            };
            alertPane.getCloseButton().setOnAction(closeAction);
            anchorPane.getChildren().add(alertPane);
        }
    }

    /**
     * ActionListener for "flee" button in main combat screen
     * @param e Action Trigger
     */
    public void onFlee(ActionEvent e)   {
        int fleeChance = (enemyShip.getWeaponSlots()
                + enemyShip.getShieldSlots())*10
                + Player.getInstance().getPilotSkill()*5;
        Random rand = new Random();
        if(rand.nextInt(100) < fleeChance)  {
            AlertPane alertPane = new AlertPane(AlertPane.AlertPaneType.ONEBUTTON,
                "Success", "You've successfully fled the " + enemyShip.getShipType() + ".");
            EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
                anchorPane.getChildren().remove(alertPane);
                successfullyFled();
            };
            alertPane.getCloseButton().setOnAction(closeAction);
            anchorPane.getChildren().add(alertPane);
        }
        else    {
            AlertPane alertPane = new AlertPane(AlertPane.AlertPaneType.ONEBUTTON,
                "Failure", "You've failed to flee the " + enemyShip.getShipType() + ".");
            EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
                anchorPane.getChildren().remove(alertPane);
                if(!stealthed)
                    attackPlayer();
            };
            alertPane.getCloseButton().setOnAction(closeAction);
            anchorPane.getChildren().add(alertPane);
        }
    }

    private void successfullyFled()  {
        //TODO
    }

    /**
     * ActionListener for "fight" button in main combat screen
     * @param e Action Trigger
     */
    public void onFight(ActionEvent e)  {
        AlertPane alertPane = new AlertPane(AlertPane.AlertPaneType.FIGHTBUTTONS,
                "Attack", "How would you like to attack?");
        EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
            anchorPane.getChildren().remove(alertPane);
        };
        EventHandler<ActionEvent> pulseAction = (ActionEvent e2) -> {
            boolean damageDealt = pulseAttack();
            if(damageDealt) {
                anchorPane.getChildren().remove(alertPane);
                if(!stealthed)
                    attackPlayer();
            }
        };
        EventHandler<ActionEvent> beamAction = (ActionEvent e2) -> {
            boolean damageDealt = beamAttack();
            if(damageDealt) {
                anchorPane.getChildren().remove(alertPane);
                if(!stealthed)
                    attackPlayer();
            }
        };
        EventHandler<ActionEvent> missileAction = (ActionEvent e2) -> {
            boolean damageDealt = missileAttack();
            if(damageDealt) {
                anchorPane.getChildren().remove(alertPane);
                if(!stealthed)
                    attackPlayer();
            }
        };
        alertPane.getPulseButton().setOnAction(pulseAction);
        alertPane.getBeamButton().setOnAction(beamAction);
        alertPane.getMissileButton().setOnAction(missileAction);
        alertPane.getCloseButton().setOnAction(closeAction);
        anchorPane.getChildren().add(alertPane);
    }
    
    /**
     * Enemy calculates potential damage of the 3 weapon groups and picks the
     * most effective. The player is then attacked by the selected weapon group.
     */
    private void attackPlayer()  {
        double damagePulse = 0;
        double damageBeam = 0;
        double damageMissile = 0;
        if(playerShip.getShieldStrength() > 0) {
            for (Weapon weapon : enemyShip.getWeapons())    {
                if (weapon.getType() == Weapon.WeaponType.BEAM) {
                    damageBeam = damageBeam + weapon.getShieldDamage();
                }
                else if(weapon.getType() == Weapon.WeaponType.PULSE)    {
                    damagePulse = damagePulse + weapon.getShieldDamage();
                }
                else if(weapon.getType() == Weapon.WeaponType.MISSILE)  {
                    damageMissile = damageMissile + weapon.getShieldDamage();
                }
            }
        }
        else    {
            for (Weapon weapon : enemyShip.getWeapons())    {
                if (weapon.getType() == Weapon.WeaponType.BEAM) {
                    damageBeam = damageBeam + weapon.getShipDamage();
                }
                else if(weapon.getType() == Weapon.WeaponType.PULSE)    {
                    damagePulse = damagePulse + weapon.getShipDamage();
                }
                else if(weapon.getType() == Weapon.WeaponType.MISSILE)  {
                    damageMissile = damageMissile + weapon.getShipDamage();
                }
            }
        }
        double damage;
        double dealtDamage;
        String weaponGroup;
        if(damagePulse >= damageBeam && damagePulse >= damageMissile) {
            damage = damagePulse;
            dealtDamage = damagePulse;
            weaponGroup = "Pulse weapons";
        }
        else if(damageBeam >= damagePulse && damageBeam >= damageMissile) {
            damage = damageBeam;
            dealtDamage = damageBeam;
            weaponGroup = "Beam weapons";
        }
        else    {
            damage = damageMissile;
            dealtDamage = damageMissile;
            weaponGroup = "Missile weapons";
        }
        if (playerShip.getShieldStrength() < damage)  {
            damage = damage - playerShip.getShieldStrength();
            playerShip.setShieldStrength(0);
        }
        else    {
            playerShip.setShieldStrength(playerShip.getShieldStrength() - damage);
            damage = 0;
        }
        playerShip.setHealth(playerShip.getHealth() - damage);
        AlertPane alertPane = new AlertPane(AlertPane.AlertPaneType.ONEBUTTON,
                "Damage Received", "You've taken " + dealtDamage
                + " from their " + weaponGroup + ".");
        EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
            anchorPane.getChildren().remove(alertPane);
        };
        alertPane.getCloseButton().setOnAction(closeAction);
        anchorPane.getChildren().add(alertPane);
    }

    /**
     * calculate and deal damage from the user's missile weaponry
     * @return whether or not there were missile weapons on the player ship
     */
    private boolean missileAttack()  {
        double damage = 0;
        if (enemyShip.getShieldStrength() > 0)    {
            for (Weapon weapon : playerShip.getWeapons())    {
                if (weapon.getType() == Weapon.WeaponType.MISSILE)  {
                    damage += weapon.getShieldDamage();
                }
            }
            if(damage == 0) {
                return false;
            }
        }
        else    {
            for (Weapon weapon : playerShip.getWeapons())    {
                if (weapon.getType() == Weapon.WeaponType.MISSILE)  {
                    damage += weapon.getShipDamage();
                }
            }
            if(damage == 0) {
                return false;
            }
        }
        damage += (targeters + Player.getInstance().getFighterSkill()) * 10;
        if (enemyShip.getShieldStrength() < damage)  {
            damage = damage - enemyShip.getShieldStrength();
            enemyShip.setShieldStrength(0);
        }
        else    {
            enemyShip.setShieldStrength(enemyShip.getShieldStrength() - damage);
            damage = 0;
        }
        enemyShip.setHealth(enemyShip.getHealth() - damage);
        return true;
    }

    /**
     * calculate and deal damage from the user's beam weaponry
     * @return whether or not there were beam weapons on the player ship
     */
    private boolean beamAttack()   {
        double damage = 0;
        if (enemyShip.getShieldStrength() > 0)    {
            for (Weapon weapon : playerShip.getWeapons())    {
                if (weapon.getType() == Weapon.WeaponType.BEAM)  {
                    damage += weapon.getShieldDamage();
                }
            }
            if(damage == 0) {
                return false;
            }
        }
        else    {
            for (Weapon weapon : playerShip.getWeapons())    {
                if (weapon.getType() == Weapon.WeaponType.BEAM)  {
                    damage += weapon.getShipDamage();
                }
            }
            if(damage == 0) {
                return false;
            }
        }
        damage += (targeters + Player.getInstance().getFighterSkill()) * 10;
        if (enemyShip.getShieldStrength() < damage)  {
            damage = damage - enemyShip.getShieldStrength();
            enemyShip.setShieldStrength(0);
        }
        else    {
            enemyShip.setShieldStrength(enemyShip.getShieldStrength() - damage);
            damage = 0;
        }
        enemyShip.setHealth(enemyShip.getHealth() - damage);
        return true;
    }

    /**
     * calculate and deal damage from the user's pulse weaponry
     * calculation includes gadgets and fighter skill
     * @return whether or not there were pulse weapons on the player ship
     */
    private boolean pulseAttack()   {
        double damage = 0;
        if (enemyShip.getShieldStrength() > 0)    {
            for (Weapon weapon : playerShip.getWeapons())    {
                if (weapon.getType() == Weapon.WeaponType.PULSE)  {
                    damage += weapon.getShieldDamage();
                }
            }
            if(damage == 0) {
                return false;
            }
        }
        else    {
            for (Weapon weapon : playerShip.getWeapons())    {
                if (weapon.getType() == Weapon.WeaponType.PULSE)  {
                    damage += weapon.getShipDamage();
                }
            }
            if(damage == 0) {
                return false;
            }
        }
        damage += (targeters + Player.getInstance().getFighterSkill()) * 10;
        if (enemyShip.getShieldStrength() < damage)  {
            damage = damage - enemyShip.getShieldStrength();
            enemyShip.setShieldStrength(0);
        }
        else    {
            enemyShip.setShieldStrength(enemyShip.getShieldStrength() - damage);
            damage = 0;
        }
        enemyShip.setHealth(enemyShip.getHealth() - damage);
        return true;
    }
}
