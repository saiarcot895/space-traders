/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import javafx.scene.control.TableColumn;

/**
 *
 * @author Alex
 */
public class MarketTableColumn extends TableColumn {
    public MarketTableColumn(String text) {
        super(text);
        setResizable(false);
        setPrefWidth(100);
        setSortable(false);
        
    }
}
