package hyenas.database;

/**
 *
 * @author Abhishek
 * @param <K>
 * @param <P>
 */
public interface Table<K, P> {
    
    /**
     * Create a table using SQLite statement.
     */
    public void createTable();
    
    /**
     * add a row to the table
     * @param item
     * @param parent 
     */
    public void addRow(K item, P parent);
    
    /**
     * update a row in the table
     * @param item
     * @param parent 
     */
    public void update(K item, P parent);
    
    /**
     * remove a row from the table
     * @param item
     * @param parent 
     */
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
