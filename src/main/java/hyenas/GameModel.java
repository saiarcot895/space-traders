/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

/**
 *
 * @author saikrishna
 */
public class GameModel {
    private static Player player;
    private static Galaxy galaxy;

    private GameModel() {
    }

    public static Player getPlayer() {
        return player;
    }

    public static Galaxy getGalaxy() {
        return galaxy;
    }
}
