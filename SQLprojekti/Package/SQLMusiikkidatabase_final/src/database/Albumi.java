package database;

import java.sql.Date;

/**
 * @author Paula Heino
 */
public class Albumi {
	private int id;
	private String nimi;
	private Date julkaisupvm;
	private String genre;

	public Albumi(int id, String nimi, Date julkaisupvm, String genre){
		this.id = id;
		this.nimi = nimi;
		this.julkaisupvm = julkaisupvm;
		this.genre = genre;
		}
	
	public int annaId(){
		return this.id;
	}
	
	public String annaNimi(){
		return this.nimi;
	}
	
	public Date annaJulkaisupvm(){
		return this.julkaisupvm;
	}
	public String annaGenre(){
		return this.genre;
	}
}
