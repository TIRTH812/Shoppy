package com.mycompany.DAO;

import java.sql.Connection;

public interface db_connection  
{
    
    public Connection getConnection();
    
    public void closeConnection();
    
   
}
