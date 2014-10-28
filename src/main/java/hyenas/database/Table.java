package hyenas.database;

/**
 *
 * @author Abhishek
 */
public interface Table {
    
    /**
     * Create a table using SQLite statement.
     */
    public void createTable();
    
    /**
     * Load table on continue.
     */
    public void loadTable();
    
    /**
     * Drop table upon deletion of player.
     */
    public void dropTable();
}
