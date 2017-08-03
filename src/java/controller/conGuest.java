/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import connection.Koneksi;
import java.util.ArrayList;
import java.util.List;
import model.Guest;

/**
 *
 * @author Lukas
 */
public class conGuest 
{
    private DBConnection con;
    private String prefixGuest;
    
    private String prefixId;
    private String prefixUsername;
    private String prefixName;
    private String prefixEmail;
    private String prefixPassword;
    
    public conGuest() 
    {
        con = new Koneksi().getConnection();
        prefixGuest = "http://www.hotel.com/guest/";
        
        prefixId = prefixGuest + "id";
        prefixUsername = prefixGuest + "username";
        prefixName = prefixGuest + "name";
        prefixEmail = prefixGuest + "email";
        prefixPassword = prefixGuest + "password";
    }
    
    public List<Guest> getAllGuest() 
    {
        List<Guest> listGuest = new ArrayList<>();
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        
        String query = "PREFIX tb_guest:<" + prefixGuest + "> "
                + "SELECT "
                + "?id ?username ?name ?email ?password " 
                + "WHERE { "
                + "?x tb_guest:id ?id; "
                + "tb_guest:username ?username; "
                + "tb_guest:name ?name; "
                + "tb_guest:email ?email; "
                + "tb_guest:password ?password . "
                + "}";
        
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) 
        {
            QuerySolution querySolution = resultSet.nextSolution();
            Guest guest = new Guest();
            
            guest.setUsername(querySolution.getLiteral("username").getString());
            guest.setName(querySolution.getLiteral("name").getString());
            guest.setEmail(querySolution.getLiteral("email").getString());
            guest.setPassword(querySolution.getLiteral("password").getString());
            listGuest.add(guest);
        }
        return listGuest; //mereturn listGuest
    }
    
    public boolean insertGuest(Guest guest) 
    {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();

        model.setNsPrefix("guest", prefixGuest);
        Property propUsername = model.createProperty(prefixUsername);
        Property propName = model.createProperty(prefixName);
        Property propEmail = model.createProperty(prefixEmail);
        Property propPassword = model.createProperty(prefixPassword);
        
        Resource resource = model.createResource(prefixGuest + guest.getId());
        resource.addProperty(propUsername, guest.getUsername());
        resource.addProperty(propName, guest.getName());
        resource.addProperty(propEmail, guest.getEmail());
        resource.addProperty(propPassword, guest.getPassword());


        model.commit();
        return true;
    }
    
    public boolean deleteGuest(String id) 
    {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();

        model.getResource(prefixGuest + id).removeAll(null);

        model.commit();
        return true;
    }
}
