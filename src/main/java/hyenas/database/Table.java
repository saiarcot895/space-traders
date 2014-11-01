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
     * Add a row to the table.
     * @param item the item to add
     * @param parent the parent
     */
    public void addRow(K item, P parent);
    
    /**
     * Update a row in the table.
     * @param item the item to update
     * @param parent the parent
     */
    public void update(K item, P parent);
    
    /**
     * Remove a row from the table.
     * @param item the item to remove
     * @param parent the parent
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
