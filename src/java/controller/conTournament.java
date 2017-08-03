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
import model.Tournament;

/**
 *
 * @author Lukas
 */
public class conTournament 
{   
    private DBConnection con;
    private String prefixTournament;
    

    private String prefixId;
    private String prefixNamaTim;
    private String prefixNamaCaptain;
    private String prefixNoCaptain;
    private String prefixJenisCaptain;
    private String prefixNamaLengkap;
    private String prefixNoTelepon;
    private String prefixJenisKelamin;
    
    public conTournament() 
    {
        con = new Koneksi().getConnection();
        prefixTournament = "http://www.hotel.com/tournament/";
        prefixId = prefixTournament + "id";
        prefixNamaTim = prefixTournament + "nama_tim";
        prefixNamaCaptain = prefixTournament + "nama_captain";
        prefixNoCaptain = prefixTournament + "no_captain";
        prefixJenisCaptain = prefixTournament + "jenis_captain";
        prefixNamaLengkap = prefixTournament + "nama_lengkap";
        prefixNoTelepon = prefixTournament + "no_telepon";
        prefixJenisKelamin = prefixTournament + "jenis_kelamin";

    }

    public List<Tournament> getAllTournament() 
    {
        List<Tournament> listTournament = new ArrayList<>();
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        String query = "PREFIX tournament:<" + prefixTournament + "> "
                + "SELECT "
                + "?id ?nama_tim ?no_captain ?jenis_captain ?nama_lengkap ?no_telepon ?jenis_kelamin "
                + "WHERE { "
                + "?x tournament:nama_tim ?nama_tim; "
                + "tournament:nama_captain ?nama_captain; "
                + "tournament:no_captain ?no_captain; "
                + "tournament:jenis_captain ?jenis_captain; "
                + "tournament:nama_lengkap ?nama_lengkap; "
                + "tournament:no_telepon ?no_telepon; "
                + "tournament:jenis_kelamin ?jenis_kelamin . "
                + "}";

        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
        ResultSet resultSet = queryExecution.execSelect();
        
        while (resultSet.hasNext()) 
        {
            QuerySolution querySolution = resultSet.nextSolution();
            Tournament tournament = new Tournament();
            tournament.setNama_tim(querySolution.getLiteral("nama_tim").getString());
            tournament.setNama_captain(querySolution.getLiteral("nama_captain").getString());
            tournament.setNo_captain(querySolution.getLiteral("no_captain").getString());
            tournament.setJenis_captain(querySolution.getLiteral("jenis_captain").getString());
            tournament.setNama_lengkap(querySolution.getLiteral("nama_lengkap").getString());
            tournament.setNo_telepon(querySolution.getLiteral("no_telepon").getString());
            tournament.setJenis_kelamin(querySolution.getLiteral("jenis_kelamin").getString());

            //resource.addProperty(kode, model.createResource("http://www.tiket.com/" + listTiket.get(i).getKode()));
            listTournament.add(tournament);
        }
        return listTournament;
    }

    public boolean insertTournament(Tournament tournament) 
    {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();

        model.setNsPrefix("tournament", prefixTournament);
        Property propNamaTim = model.createProperty(prefixNamaTim);
        Property propNamaCaptain = model.createProperty(prefixNamaCaptain);
        Property propNoCaptain = model.createProperty(prefixNoCaptain);
        Property propJenisCaptain = model.createProperty(prefixJenisCaptain);
        Property propNamaLengkap = model.createProperty(prefixNamaLengkap);
        Property propNoTelepon = model.createProperty(prefixNoTelepon);
        Property propJenisKelamin = model.createProperty(prefixJenisKelamin);

        Resource resource = model.createResource(prefixTournament + tournament.getId());
        resource.addProperty(propNamaTim, tournament.getNama_tim());
        resource.addProperty(propNamaCaptain, tournament.getNama_captain());
        resource.addProperty(propNoCaptain, tournament.getNo_captain());
        resource.addProperty(propJenisCaptain, tournament.getJenis_captain());
        resource.addProperty(propNamaLengkap, tournament.getNama_lengkap());
        resource.addProperty(propNoTelepon, tournament.getNo_telepon());
        resource.addProperty(propJenisKelamin, tournament.getJenis_kelamin());

        model.commit();
        return true;
    }

    public boolean deleteTournament(String id) 
    {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();

        model.getResource(prefixTournament + id).removeAll(null);

        model.commit();
        return true;
    }
}
