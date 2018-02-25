package database;

import java.sql.*;

/**
 * @author Paula Heino
 */
public class Kappale {
	private int id;
	private String nimi;
	private Time pituus;
	private Date julkaisupvm;
	private String genre;
	
	public Kappale(int id, String nimi, Time pituus, Date julkaisupvm, String genre){
		this.id = id;
		this.nimi = nimi;
		this.pituus = pituus;
		this.julkaisupvm = julkaisupvm;
		this.genre = genre;
	}
	
	public int annaId(){
		return this.id;
	}
	
	public String annaNimi(){
		return this.nimi;
	}
	
	public Time annaPituus(){
		return this.pituus;
	}
	
	public Date annaJulkaisupvm(){
		return this.julkaisupvm;
	}
	public String annaGenre(){
		return this.genre;
	
	}

}

