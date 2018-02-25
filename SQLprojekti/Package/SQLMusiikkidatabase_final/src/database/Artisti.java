package database;

import java.sql.Date;

/**
 * @author Paula Heino
 */
public class Artisti {
	private int id;
	private String etunimi;
	private String sukunimi;
	private Date syntymaaika;

	
	public Artisti(int id, String etunimi, String sukunimi, Date syntymaaika){
		this.id = id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.syntymaaika = syntymaaika;

	}
	
	public int annaId(){
		return this.id;
	}
	
	public String annaEtunimi(){
		return this.etunimi;
	}
	public String annaSukunimi(){
		return this.sukunimi;
	}
	public Date annaSyntymaAika(){
		return this.syntymaaika;
	}

	
}
