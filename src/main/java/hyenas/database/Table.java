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
     * Remove all entries in the table.
     */
    public void clearTable();
    
    /**
     * Drop the table from the database.
     */
    public void dropTable();
}
