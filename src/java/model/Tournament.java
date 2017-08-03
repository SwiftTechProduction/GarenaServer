/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lukas
 */
public class Tournament 
{
    //captain
    private int id;
    private String nama_tim;
    private String nama_captain;
    private String no_captain;
    private String jenis_captain;
    
    //anggota
    private String nama_lengkap;
    private String no_telepon;
    private String jenis_kelamin;

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getNama_tim() 
    {
        return nama_tim;
    }

    public void setNama_tim(String nama_tim) 
    {
        this.nama_tim = nama_tim;
    }

    public String getNama_captain() 
    {
        return nama_captain;
    }

    public void setNama_captain(String nama_captain) 
    {
        this.nama_captain = nama_captain;
    }

    public String getNo_captain() 
    {
        return no_captain;
    }

    public void setNo_captain(String no_captain) 
    {
        this.no_captain = no_captain;
    }

    public String getJenis_captain() 
    {
        return jenis_captain;
    }

    public void setJenis_captain(String jenis_captain) 
    {
        this.jenis_captain = jenis_captain;
    }

    public String getNama_lengkap() 
    {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) 
    {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNo_telepon() 
    {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) 
    {
        this.no_telepon = no_telepon;
    }

    public String getJenis_kelamin() 
    {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) 
    {
        this.jenis_kelamin = jenis_kelamin;
    }
 
}
