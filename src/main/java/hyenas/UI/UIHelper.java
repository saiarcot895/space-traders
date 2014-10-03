/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Alex
 */
public class UIHelper {
    public static final int GALAXY_SIZE = 2000;
    
    public static final Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
