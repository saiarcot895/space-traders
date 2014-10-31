package hyenas.database;

/**
 *
 * @author Abhishek
 */
public interface Table<K, P> {
    
    /**
     * Create a table using SQLite statement.
     */
    public void createTable();
    
    public void addRow(K item, P parent);
    
    public void update(K item, P parent);
    
    public void remove(K item, P parent);
    
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
