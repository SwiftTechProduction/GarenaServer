/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.conGuest;
import controller.conTournament;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Guest;
import model.Tournament;

/**
 *
 * @author Lukas
 */
@WebService(serviceName = "GarenaService")
public class GarenaService 
{

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllTournament")//nama operasi yang digunakan
    public List<model.Tournament> getAllTournament() 
    {
        //membuat list Booking melalui model Tournament yang telah dibuat pada package model
        return new conTournament().getAllTournament();//memanggil fungsi getAllTournament pada conTournament 
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertTournament")
    public boolean insertTournament(@WebParam(name = "tournament") Tournament tournament) 
    {
       return new conTournament().insertTournament(tournament);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteTournament")
    public boolean deleteBooking(@WebParam(name = "id") String id) 
    {
        return new conTournament().deleteTournament(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllGuest")
    public List<model.Guest> getAllKamar() 
    {
        //TODO write your implementation code here:
        return new conGuest().getAllGuest();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertGuest")
    public boolean insertGuest(@WebParam(name = "guest") Guest guest) 
    {
        //TODO write your implementation code here:
        return new conGuest().insertGuest(guest);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteGuest")
    public boolean deleteGuest(@WebParam(name = "kodeGuest") String kodeGuest) 
    {
        //TODO write your implementation code here:
        return new conGuest().deleteGuest(kodeGuest);
    }
}
