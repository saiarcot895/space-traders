package hyenas.UI;

import javafx.scene.control.TableColumn;

/**
 * Table column for marketplace
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
