/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 *
 * @author Lukas
 */
import com.hp.hpl.jena.db.DBConnection;
public class Koneksi 
{
    private String DBDriver = "com.mysql.jdbc.Driver";
    private String DBUrl = "jdbc:mysql://127.0.0.1/db_garena";
    private String DBUser = "root";
    private String DBPass = "";
    private String DBType = "MySQL";
    private DBConnection con;

    public Koneksi() 
    {
        try 
        {
            Class.forName(DBDriver);
            con =  new DBConnection(DBUrl, DBUser, DBPass, DBType);
        } 
        catch (ClassNotFoundException e) 
        {
            
        }
    }
    
    public DBConnection getConnection() 
    {
        return con;
    }
}
