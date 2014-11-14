package hyenas;

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

    @FXML
    private Label playerHealth;
    
    @FXML
    private Label playerShields;

    @FXML
    private Label enemyHealth;
    
    @FXML
    private Label enemyShields;

    @FXML
    private Button fight;

    @FXML
    private Button run;

    @FXML
    private Button gadget;

    private Ship playerShip;

    private Ship enemyShip;
    
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerShip = Player.getInstance().getShip();
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

    }

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
                attackPlayer();
            }
        };
        EventHandler<ActionEvent> beamAction = (ActionEvent e2) -> {
            boolean damageDealt = beamAttack();
            if(damageDealt) {
                anchorPane.getChildren().remove(alertPane);
                attackPlayer();
            }
        };
        EventHandler<ActionEvent> missileAction = (ActionEvent e2) -> {
            boolean damageDealt = missileAttack();
            if(damageDealt) {
                anchorPane.getChildren().remove(alertPane);
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
    public void attackPlayer()  {
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
    public boolean missileAttack()  {
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
    public boolean beamAttack()   {
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
     * @return whether or not there were pulse weapons on the player ship
     */
    public boolean pulseAttack()   {
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
