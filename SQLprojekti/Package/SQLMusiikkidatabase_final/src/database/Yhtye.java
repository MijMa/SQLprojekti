package database;

import java.sql.Date;

/**
 * @author Paula Heino
 */
public class Yhtye {
	private int id;
	private String nimi;
	private Date perustusaika;
	
	public Yhtye(int id, String nimi, Date perustusaika){
		this.id = id;
		this.nimi = nimi;
		this.perustusaika = perustusaika;
	}
	
	public int annaId(){
		return this.id;
	}
	
	public String annaNimi(){
		return this.nimi;
	}
	public Date annaPerustusaika(){
		return this.perustusaika;
	}
}
