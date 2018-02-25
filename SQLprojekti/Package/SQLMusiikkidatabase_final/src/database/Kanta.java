package database;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Paula Heino, Mio Mattila
 */
public class Kanta {
	private Connection yhteys;
	private PreparedStatement lause = null;

	//Konstruktori: luodaan yhteys kantaan
	public Kanta() throws ClassNotFoundException {

		yhteys = null;

		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/musiikki";
		String user = "postgres";
		String password = "pass";

		try {
			yhteys = DriverManager.getConnection(url, user, password);
			if (yhteys != null)
				System.out.println("yhteys luotu");
			else
				System.out.println("yhteytt‰ ei luotu");

		} catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
	}

	// Lis‰‰ luodun artistin tietokantaan
	public void lisaaArtisti(Artisti artisti) {
		try {
			String kysely = "INSERT INTO artisti VALUES (?,?,?,?);";
			lause = yhteys.prepareStatement(kysely);
			lause.setInt(1, artisti.annaId());
			lause.setString(2, artisti.annaEtunimi());
			lause.setString(3, artisti.annaSukunimi());
			lause.setDate(4, artisti.annaSyntymaAika());
			lause.executeUpdate();
			lause.close();
			System.out.println("Artisti " + artisti.annaEtunimi() + " " + artisti.annaSukunimi() + " lisatty");
		} catch (SQLException e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}

	}
    
    //Poistaa artistin artisteista, yhtyeest‰, ja artistin kappaleet ja albumiin liittymisen
    public void poistaArtisti(Artisti artisti){
    	try {
	 		String kysely = 
	 				
	 				/*Itse albumeja EI poisteta, niissa voi olla muidenkin artistien kappaleita*/

	 				/*Poistetaan albumiin liittyminen*/
	 				 "DELETE FROM kuuluualbumiin a "
	 				+ "USING artistinkappale b "
	 				+ "WHERE a.kappale_id = b.kappale_id "
	 				+ "AND b.artisti_id = ?; "
	 				
	 				/*Poistetaan kappaleet*/
	 				+ "DELETE FROM kappale a "
	 				+ "USING artistinkappale b "
	 				+ "WHERE a.id = b.kappale_id "
	 				+ "AND b.artisti_id = ?; "
	 				
	 				/*poistetaan kappaleen omistaminen*/
					+ "DELETE FROM artistinkappale WHERE "
	 				+ "artisti_id = ?; "
					
	 				/*Poistetaan yhtyeeseen kuuluminen*/
					+ "DELETE FROM kuuluuyhtyeeseen WHERE "
	 				+ "artisti_id = ?; "
					
	 				/*Poistetaan artisti*/
	 				+ "DELETE FROM artisti WHERE "
	 				+ "artisti.id = ?; ";
	 				
			lause = yhteys.prepareStatement(kysely);
			lause.setInt(1, artisti.annaId());
			lause.setInt(2, artisti.annaId());
			lause.setInt(3, artisti.annaId());
			lause.setInt(4, artisti.annaId());
			lause.setInt(5, artisti.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Artisti " + artisti.annaEtunimi() + " " + artisti.annaSukunimi() + " poistettu");

		} catch (SQLException e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
	}
    
    //Lis‰‰ luodun yhtyeen tietokantaan
	public void lisaaYhtye(Yhtye yhtye) {
		try {
			String kysely = "INSERT INTO yhtye VALUES (?, ?, ?);";
			lause = yhteys.prepareStatement(kysely);
			lause.setInt(1, yhtye.annaId());
			lause.setString(2, yhtye.annaNimi());
			lause.setDate(3, yhtye.annaPerustusaika());
			lause.executeUpdate();
			lause.close();
			System.out.println("Yhtye " + yhtye.annaNimi() + " lisatty");

		} catch (SQLException e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
	}
    
    //Poistaa yhtyeen, artistien kuulumisen yhtyeeseen, yhtyeen kappaleet ja albumiin liittymisen
    public void poistaYhtye(Yhtye yhtye){
    	try {
	 		String kysely = 
	 				
	 				/*Itse albumeja EI poisteta, niissa voi olla muidenkin artistien kappaleita*/

	 				/*Poistetaan silti kappaleiden liittyminen albumiin*/
	 				 "DELETE FROM kuuluualbumiin a  "
	 				+ "USING yhtyeenkappale b "
	 				+ "WHERE a.kappale_id = b.kappale_id "
	 				+ "AND b.yhtye_id = ?; "
	 
	 				/*poistetaan kappaleen omistaminen*/
					+ "DELETE FROM yhtyeenkappale WHERE "
	 				+ "yhtye_id = ?; "
					
	 				/*Poistetaan kappaleet*/
	 				+ "DELETE FROM kappale a "
	 				+ "USING yhtyeenkappale b "
	 				+ "WHERE a.id = b.kappale_id "
	 				+ "AND b.yhtye_id = ?; "

	 				/*poistetaan kaikki artistiliitannat*/
					+ "DELETE FROM kuuluuyhtyeeseen WHERE "
	 				+ "yhtye_id = ?; "
	 				
	 				/*Poistetaan yhtye*/
	 				+ "DELETE FROM yhtye WHERE "
	 				+ "yhtye.id =  ?; ";
	 		
			lause = yhteys.prepareStatement(kysely);
			lause.setInt(1, yhtye.annaId());
			lause.setInt(2, yhtye.annaId());
			lause.setInt(3, yhtye.annaId());
			lause.setInt(4, yhtye.annaId());
			lause.setInt(5, yhtye.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Yhtye " + yhtye.annaNimi() + " poistettu");

		} catch (SQLException e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
	}
    
    //Lis‰‰ luodun kappaleen tietokantaan	
	public void lisaaKappale(Kappale kappale) {
		try {
			String kysely = "INSERT INTO kappale VALUES (?, ?, ?, ?, ?);";
			lause = yhteys.prepareStatement(kysely);
			lause.setInt(1, kappale.annaId());
			lause.setString(2, kappale.annaNimi());
			lause.setTime(3, kappale.annaPituus());
			lause.setDate(4, kappale.annaJulkaisupvm());
			lause.setString(5, kappale.annaGenre());
			lause.executeUpdate();
			lause.close();
			System.out.println("Kappale " + kappale.annaNimi() + " lisatty");

		} catch (SQLException e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
	}
	
	//Poistaa kappaleen albumeista, artistilta/yhtyeelt‰ ja itse kappaleen
    public void poistaKappale(Kappale kappale){
    	try {
	 		String kysely = 
	 				"DELETE FROM kuuluualbumiin"
	 				+ " WHERE kappale_id = ?;"
	 				
	 				+ " DELETE FROM artistinkappale"
	 				+ " WHERE kappale_id = ?;"
	 				
					+ " DELETE FROM yhtyeenkappale"
					+ " WHERE kappale_id = ?;"
	 				
	 				+ " DELETE FROM kappale"
	 				+ " WHERE id = ?;";
	 		lause = yhteys.prepareStatement(kysely);
	 		lause.setInt(1, kappale.annaId());
	 		lause.setInt(2, kappale.annaId());
	 		lause.setInt(3, kappale.annaId());
	 		lause.setInt(4, kappale.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Kappale " + kappale.annaNimi() + " poistettu");
			
		} catch (SQLException e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}

    }
    //Lis‰‰ luodun albumin tietokantaan	
    public void lisaaAlbumi(Albumi albumi){
    	try {
	 		String kysely = "INSERT INTO albumi VALUES (?, ?, ?, ?);";
	 		lause = yhteys.prepareStatement(kysely);
	 		lause.setInt(1, albumi.annaId());
	 		lause.setString(2, albumi.annaNimi());
	 		lause.setDate(3, albumi.annaJulkaisupvm());
	 		lause.setString(4, albumi.annaGenre());
			lause.executeUpdate();
			lause.close();
			System.out.println("Albumi " + albumi.annaNimi() + " lisatty");
			
		} catch (SQLException e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}

    }
    
    //Poistaa albumin ja kappaleiden liitokset siihen
    public void poistaAlbumi(Albumi albumi){
    	try {
	 		String kysely = 
	 				"DELETE FROM kuuluualbumiin"
	 				+ " WHERE albumi_id = ?;"

	 				+ " DELETE FROM albumi"
	 				+ " WHERE id = ?;";
	 		
	 		lause = yhteys.prepareStatement(kysely);
	 		lause.setInt(1, albumi.annaId());
	 		lause.setInt(2, albumi.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Albumi " + albumi.annaNimi() + " poistettu");
			
		} catch (SQLException e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
    }
    
    //Lisaa kappaleen artistin kappaleeksi
 	public void lisaaArtistiYhtyeeseen(Artisti artisti, Yhtye yhtye) {
 	 	try{
 	 		String kysely = 
 	 				"INSERT INTO kuuluuyhtyeeseen (artisti_id, yhtye_id)"
 	 				+ " SELECT ?,?"
 	 				+ " WHERE NOT EXISTS ("
 	 				+ " SELECT artisti_id, yhtye_id"
 	 				+ " FROM kuuluuyhtyeeseen"
 	 				+ " WHERE artisti_id = ? AND yhtye_id = ?);";
 	 		
 	 		lause = yhteys.prepareStatement(kysely);
			lause.setInt(1, artisti.annaId());
			lause.setInt(2, yhtye.annaId());
			lause.setInt(3, artisti.annaId());
			lause.setInt(4, yhtye.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Artisti " + artisti.annaEtunimi() + " " + artisti.annaSukunimi() 
					+ " lisatty yhtyeeseen " + yhtye.annaNimi() + ", ellei jo ollut olemassa");
			
		} catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 	}
 	
 	//Poistaa artistin liitoksen yhtyeeseen
 	public void poistaArtistiYhtyeesta(Artisti artisti, Yhtye yhtye) {
 	 	try{
	 		String kysely = 
	 				"DELETE FROM kuuluuyhtyeeseen"
	 				+ " WHERE artisti_id = ? AND yhtye_id = ? ;";

 	 		lause = yhteys.prepareStatement(kysely);
			lause.setInt(1, artisti.annaId());
			lause.setInt(2, yhtye.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Artisti " + artisti.annaEtunimi() + " " + artisti.annaSukunimi() 
					+ " poistettu yhtyeesta " + yhtye.annaNimi());
			
		} catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 	}
 	
    //Lisaa kappaleen artistin kappaleeksi
 	public void yhdistaKappaleArtistiin(Artisti artisti, Kappale kappale) {
 	 	try{
 	 		String kysely = 
 	 				"INSERT INTO artistinkappale (artisti_id, kappale_id)"
 	 				+ " SELECT ?,?"
 	 				+ " WHERE NOT EXISTS ("
 	 				+ " SELECT artisti_id, kappale_id"
 	 				+ " FROM artistinkappale"
 	 				+ " WHERE artisti_id = ? AND kappale_id = ?);";
 	 		
 	 		lause = yhteys.prepareStatement(kysely);
 	 		lause.setInt(1, artisti.annaId());
			lause.setInt(2, kappale.annaId());
			lause.setInt(3, artisti.annaId());
			lause.setInt(4, kappale.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Kappale " + kappale.annaNimi()
			+ " liitetty artistiin " + artisti.annaEtunimi() + " " + artisti.annaSukunimi() + ", ellei jo ollut olemassa");
			
		} catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 	}
 	
 	//poistaa kappaleen artistin kappaleista (ei itse kappaletta)
 	public void poistaKappaleArtistilta(Artisti artisti, Kappale kappale) {
 	 	try{
 	 		String kysely = "DELETE FROM artistinkappale WHERE (artisti_id = ? AND kappale_id = ?);";
 	 		lause = yhteys.prepareStatement(kysely);
 	 		lause.setInt(1, artisti.annaId());
			lause.setInt(2, kappale.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Kappaleen " + kappale.annaNimi()
			+ " liitos poistettu artistilta " + artisti.annaEtunimi() + " " + artisti.annaSukunimi() );
		} catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 	}
 	
    //Lisaa kappaleen artistin kappaleeksi
 	public void yhdistaKappaleYhtyeeseen(Yhtye yhtye, Kappale kappale) {
 	 	try{
 	 		String kysely = 
 	 				"INSERT INTO yhtyeenkappale (yhtye_id, kappale_id)"
 	 				+ " SELECT ?,?"
 	 				+ " WHERE NOT EXISTS ("
 	 				+ " SELECT yhtye_id, kappale_id"
 	 				+ " FROM yhtyeenkappale"
 	 				+ " WHERE yhtye_id = ? AND kappale_id = ?);";
 	 		
 	 		lause = yhteys.prepareStatement(kysely);
	 		lause.setInt(1, yhtye.annaId());
			lause.setInt(2, kappale.annaId());
			lause.setInt(3, yhtye.annaId());
			lause.setInt(4, kappale.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Kappale " + kappale.annaNimi() + " liitetty yhtyeeseen " + yhtye.annaNimi() + ", ellei jo ollut olemassa");
			
		} catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 	}
 	
 	//poistaa kappaleen artistin kappaleista (ei itse kappaletta)
 	public void poistaKappaleYhtyeelta(Yhtye yhtye, Kappale kappale) {
 	 	try{
 	 		String kysely = "DELETE FROM yhtyeenkappale WHERE (yhtye_id = ? AND kappale_id = ?);";
 	 		lause = yhteys.prepareStatement(kysely);
	 		lause.setInt(1, yhtye.annaId());
			lause.setInt(2, kappale.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Kappaleen " + kappale.annaNimi() + " liitos poistettu yhtyeelta " + yhtye.annaNimi());
			
		} catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 	}

 	
 	//Yhdistaa kappaleen abumiin
 	public void yhdistaKappaleAlbumiin(Kappale kappale, Albumi albumi) {
 	 	try{
 	 		String kysely = 
 	 				"INSERT INTO kuuluualbumiin (kappale_id, albumi_id)"
 	 				+ " SELECT ?,?"
 	 				+ " WHERE NOT EXISTS ("
 	 				+ " SELECT kappale_id, albumi_id"
 	 				+ " FROM kuuluualbumiin"
 	 				+ " WHERE kappale_id = ? AND albumi_id = ?);";
 	 		
 	 		lause = yhteys.prepareStatement(kysely);
 	 		lause.setInt(1, kappale.annaId());
 	 		lause.setInt(2, albumi.annaId());
 	 		lause.setInt(3, kappale.annaId());
 	 		lause.setInt(4, albumi.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Kappale " + kappale.annaNimi() + " liitetty albumiin " + albumi.annaNimi() + ", ellei jo ollut olemassa");
			
		} catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 	}
 	
 	//poistaa kappaleen albumista (ei itse kappaletta)
 	public void poistaKappaleAlbumista(Kappale kappale, Albumi albumi) {
 	 	try{
 	 		String kysely = "DELETE FROM kuuluualbumiin WHERE (albumi_id = ? AND kappale_id = ?);";
 	 		lause = yhteys.prepareStatement(kysely);
 	 		lause.setInt(1, kappale.annaId());
 	 		lause.setInt(2, albumi.annaId());
			lause.executeUpdate();
			lause.close();
			System.out.println("Kappale " + kappale.annaNimi() + " poistettu albumista " + albumi.annaNimi());
			
		} catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 	}
 	
 	//Palauttaa artistin kappaleet arraylistina
	public ArrayList<Kappale> artistinKappaleet(Artisti artisti){
 		ArrayList<Kappale> kappaleet = new ArrayList<Kappale>();
 		try {
			String kysely = "SELECT DISTINCT kappale_id, kappale.nimi, pituus, julkaisupvm, genre" 
	                + " FROM artistinkappale, kappale" 
	                + " WHERE kappale.id = kappale_id"
	                + " AND artisti_id = ?";
			lause = yhteys.prepareStatement(kysely);
 	 		lause.setInt(1, artisti.annaId());
			ResultSet tulos = lause.executeQuery();
			
			while (tulos.next()) {
					kappaleet.add(new Kappale(tulos.getInt("kappale_id"), 
					tulos.getString("nimi"), 
					tulos.getTime("pituus"), 
					tulos.getDate("julkaisupvm"), 
					tulos.getString("genre") 
					));	
			}
			tulos.close();
			lause.close();
			
 		} catch (Exception e) {
 			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 		return kappaleet;
	}
	
	//Haetaan artistin kappaleet + kappaleet yhtyeelta, johon artisti kuuluu
	//Ei kovin jarkeva operaatio, silla artisti ei valttamatta ole ollut mukana tekemassa kappaletta
	public ArrayList<Kappale> kaikkiArtistinKappaleet(Artisti artisti){
 		ArrayList<Kappale> kappaleet = new ArrayList<Kappale>();
 		
 		try {
			String kysely1 = 
					"SELECT DISTINCT kappale_id, kappale.nimi, pituus, julkaisupvm, genre" 
	                + " FROM artistinkappale, kappale" 
	                + " WHERE kappale.id = kappale_id"
	                + " AND artisti_id = ?;";
			String kysely2 = 	    
					
					//Haetaan kappaleet silt‰ yhtyeelt‰, johon artisti kuuluu
					"SELECT DISTINCT kappale_id, kappale.nimi, pituus, julkaisupvm, genre"
					+ " FROM  kappale, kuuluuyhtyeeseen, yhtyeenkappale"
					+ " WHERE kuuluuyhtyeeseen.yhtye_id = yhtyeenkappale.yhtye_id"
					+ " AND yhtyeenkappale.kappale_id = kappale.id"
					+ " AND kuuluuyhtyeeseen.artisti_id = ?;";
			
			lause = yhteys.prepareStatement(kysely1);
 	 		lause.setInt(1, artisti.annaId());
			ResultSet tulos = lause.executeQuery();
			
			while (tulos.next()) {
					kappaleet.add(new Kappale(tulos.getInt("kappale_id"), 
					tulos.getString("nimi"), 
					tulos.getTime("pituus"), 
					tulos.getDate("julkaisupvm"), 
					tulos.getString("genre") 
					));	
			}
			lause = yhteys.prepareStatement(kysely2);
 	 		lause.setInt(1, artisti.annaId());
			ResultSet tulos2 = lause.executeQuery();
			
			while (tulos2.next()) {
				kappaleet.add(new Kappale(tulos2.getInt("kappale_id"), 
				tulos2.getString("nimi"), 
				tulos2.getTime("pituus"), 
				tulos2.getDate("julkaisupvm"), 
				tulos2.getString("genre") 
				));	
		}
			tulos.close();
			tulos2.close();
			lause.close();
			
 		} catch (Exception e) {
 			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 		return kappaleet;
	}
	
	//Palauttaa yhtyeen kappaleet arraylistin‰
	public ArrayList<Kappale> yhtyeenKappaleet(Yhtye yhtye){
 		ArrayList<Kappale> kappaleet = new ArrayList<Kappale>();
 		try {
			String kysely = "SELECT DISTINCT kappale_id, kappale.nimi, pituus, julkaisupvm, genre" 
	                + " FROM yhtyeenkappale, kappale" 
	                + " WHERE kappale.id = kappale_id"
	                + " AND yhtye_id = ?";
			lause = yhteys.prepareStatement(kysely);
 	 		lause.setInt(1, yhtye.annaId());
			ResultSet tulos = lause.executeQuery();
			
			while (tulos.next()) {
					kappaleet.add(new Kappale(tulos.getInt("kappale_id"), 
					tulos.getString("nimi"), 
					tulos.getTime("pituus"), 
					tulos.getDate("julkaisupvm"), 
					tulos.getString("genre") 
					));	
			}
			tulos.close();
			lause.close();
			
 		} catch (Exception e) {
 			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 		return kappaleet;
	}
	
	//Palauttaa yhtyeen j‰senet arraylistin‰
	public ArrayList<Artisti> yhtyeenJasenet(Yhtye yhtye){
		ArrayList<Artisti> jasenet = new ArrayList<Artisti>();
		try {
			String kysely = "SELECT DISTINCT artisti_id, fname, lname, bdate" 
	                + " FROM artisti, kuuluuyhtyeeseen" 
	                + " WHERE artisti.id = artisti_id"
	                + " AND yhtye_id = ?";
			lause = yhteys.prepareStatement(kysely);
 	 		lause.setInt(1, yhtye.annaId());
			ResultSet tulos = lause.executeQuery();
			
			while (tulos.next()) {
					jasenet.add(new Artisti(tulos.getInt("artisti_id"), 
					tulos.getString("fname"), 
					tulos.getString("lname"), 
					tulos.getDate("bdate")
					));	
			}
			tulos.close();
			lause.close();
			
 		} catch (Exception e) {
 			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 		return jasenet;
	}
	
	//Palauttaa albumin kappaleet arraylistin‰
	public ArrayList<Kappale> albuminKappaleet(Albumi albumi){
		ArrayList<Kappale> kappaleet = new ArrayList<Kappale>();
 		try {
			String kysely = "SELECT DISTINCT kappale_id, kappale.nimi, pituus, julkaisupvm, genre" 
	                + " FROM kuuluualbumiin, kappale" 
	                + " WHERE kappale.id = kappale_id"
	                + " AND albumi_id = ?";
			lause = yhteys.prepareStatement(kysely);
 	 		lause.setInt(1, albumi.annaId());
			ResultSet tulos = lause.executeQuery();
			
			while (tulos.next()) {
					kappaleet.add(new Kappale(tulos.getInt("kappale_id"), 
					tulos.getString("nimi"), 
					tulos.getTime("pituus"), 
					tulos.getDate("julkaisupvm"), 
					tulos.getString("genre") 
					));	
			}
			tulos.close();
			lause.close();
			
 		} catch (Exception e) {
 			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 		return kappaleet;
	}

	//Palauttaa albumit, jotka on julkaistu samana vuonna kuin parametrin‰ annettu vuosi
	public ArrayList<Albumi> vuodenAlbumit(int vuosi){
		ArrayList<Albumi> albumit = new ArrayList<Albumi>();
		String lause2 = "" + vuosi + "-%%-%%";
		try {String kysely = "SELECT DISTINCT id, albumi.nimi, julkaisupvm, genre" 
                + " FROM albumi" 
                + " WHERE julkaisupvm::text LIKE ?;";
		
		lause = yhteys.prepareStatement(kysely);
		lause.setString(1, lause2);
		ResultSet tulos = lause.executeQuery();
		
		while (tulos.next()) {
				albumit.add(new Albumi(tulos.getInt("id"), 
				tulos.getString("nimi"), 
				tulos.getDate("julkaisupvm"), 
				tulos.getString("genre") 
				));	
		}
		tulos.close();
		lause.close();
		
		} catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
		return albumit;
	}
	
	//Palauttaa artistin sinkkujulkaisut eli ne kappaleet, jotka eiv‰t kuulu albumiin
	public ArrayList<Kappale> artistinSinglet(Artisti artisti){
 		ArrayList<Kappale> singlet = new ArrayList<Kappale>();
 		
 		try {
			String kysely = "SELECT DISTINCT kappale_id, kappale.nimi, pituus, kappale.julkaisupvm, kappale.genre" 
	                + " FROM artistinkappale, kappale, albumi" 
	                + " WHERE kappale_id NOT IN (SELECT kappale_id FROM kuuluualbumiin)"
	                + " AND kappale_id = kappale.id"
	                + " AND artisti_id = ?";
			lause = yhteys.prepareStatement(kysely);
 	 		lause.setInt(1, artisti.annaId());
			ResultSet tulos = lause.executeQuery();
			
			while (tulos.next()) {
				    singlet.add(new Kappale(tulos.getInt("kappale_id"), 
					tulos.getString("nimi"), 
					tulos.getTime("pituus"), 
					tulos.getDate("julkaisupvm"), 
					tulos.getString("genre") 
					));	
			}
			tulos.close();
			lause.close();
			
 		} catch (Exception e) {
 			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 		return singlet;
	}
	
	//Palauttaa yhtye sinkkujulkaisut eli ne kappaleet, jotka eiv‰t kuulu albumiin
	public ArrayList<Kappale> yhtyeenSinglet(Yhtye yhtye){
 		ArrayList<Kappale> singlet = new ArrayList<Kappale>();
 		try {
			String kysely = 
					"SELECT DISTINCT kappale_id, kappale.nimi, pituus, kappale.julkaisupvm, kappale.genre" 
	                + " FROM yhtyeenkappale, kappale, albumi" 
	                + " WHERE kappale_id NOT IN (SELECT kappale_id FROM kuuluualbumiin)"
	                + " AND kappale_id = kappale.id"
	                + " AND yhtye_id = ?";
			
			lause = yhteys.prepareStatement(kysely);
 	 		lause.setInt(1, yhtye.annaId());
			ResultSet tulos = lause.executeQuery();
			
			while (tulos.next()) {
				    singlet.add(new Kappale(tulos.getInt("kappale_id"), 
					tulos.getString("nimi"), 
					tulos.getTime("pituus"), 
					tulos.getDate("julkaisupvm"), 
					tulos.getString("genre") 
					));	
			}
			tulos.close();
			lause.close();
			
 		} catch (Exception e) {
 			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
 		return singlet;
	}
}
