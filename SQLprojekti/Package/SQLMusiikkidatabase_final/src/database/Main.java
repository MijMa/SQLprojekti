package database;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Paula Heino
 */
public class Main {
	public static void main(String[] args) throws ClassNotFoundException{
		Kanta k = new Kanta();
		//Daten monimutkaisuudesta johtuen kaikissa kohdissa on sama (v‰‰r‰) p‰iv‰m‰‰r‰
		Date paivays = new Date(13929800);
		Time aika = new Time(00,04,30);
		Time aika2 = new Time(00,03,01);
		Time aika3 = new Time(00,02,45);
		Artisti remu = new Artisti(123123, "Remu","Aaltonen", paivays);
		Artisti nipa = new Artisti(555666, "Nipa","Niilonen", paivays);
		Yhtye hurriganes = new Yhtye(444, "Hurriganes", paivays);
		Kappale roller = new Kappale (22, "Rock íní Roller", aika, paivays, "rock and roll");
		Kappale planeetta = new Kappale (2, "Planeetta", aika2, paivays, "rock, tango");
		Kappale shorai = new Kappale (8, "Bourbon Street / Just for You", aika3, paivays, "rock n roll");
		Albumi albumi = new Albumi(1, "Rock And Roll All Night Long", paivays, "rock and roll, blues");
		
		/* Lis‰ysoperaatiot */
		
		k.lisaaArtisti(remu);
		k.lisaaArtisti(nipa);
		k.lisaaYhtye(hurriganes);
		k.lisaaArtistiYhtyeeseen(remu, hurriganes);
		k.lisaaArtistiYhtyeeseen(nipa, hurriganes);
		k.lisaaKappale(roller);
		k.lisaaKappale(planeetta);
		k.lisaaKappale(shorai);
		k.lisaaAlbumi(albumi);
		k.yhdistaKappaleYhtyeeseen(hurriganes, roller);
		k.yhdistaKappaleYhtyeeseen(hurriganes, shorai);
		k.yhdistaKappaleArtistiin(remu, planeetta);
		k.yhdistaKappaleAlbumiin(roller, albumi);
		
		/* Loopit listausoperaatioille */
		
		ArrayList<Kappale> lista = k.artistinKappaleet(remu);
		System.out.println("\n" + "Remun soolokappaleet:" );
		for (Kappale ka:lista){
			System.out.println(ka.annaNimi());
		}
		
		ArrayList<Kappale> singlet = k.artistinSinglet(remu);
		System.out.println("\n" + "Remun singlet:" );
		for (Kappale ka:singlet){
			System.out.println(ka.annaNimi());
		}
		
		ArrayList<Kappale> lista3 = k.kaikkiArtistinKappaleet(remu);
		System.out.println("\n" + "Remun soolokappaleet + yhtyeen kappaleet:" );
		for (Kappale ka:lista3){
			System.out.println(ka.annaNimi());
		}
		
		ArrayList<Kappale> lista2 = k.yhtyeenKappaleet(hurriganes);
		System.out.println("\n" + "Yhtyeen Hurriganes kappaleet:" );
		for (Kappale ka:lista2){
			System.out.println(ka.annaNimi());
		}
		
		ArrayList<Kappale> yhtyeensinglet = k.yhtyeenSinglet(hurriganes);
		System.out.println("\n" + "Yhtyeen Hurriganes singlet:" );
		for (Kappale ka:yhtyeensinglet){
			System.out.println(ka.annaNimi());
		}
		
		ArrayList<Albumi> lista0 = k.vuodenAlbumit(1970);
		System.out.println("\n" + "Vuoden 1970 albumit:" );
		for (Albumi ka:lista0){
			System.out.println(ka.annaNimi());
		}
	
		ArrayList<Kappale> lista5 = k.albuminKappaleet(albumi);;
		System.out.println("\n" + "Albumin kappaleet:" );
		for (Kappale ka:lista5){
			System.out.println(ka.annaNimi());
			
		}
		ArrayList<Artisti> lista4 = k.yhtyeenJasenet(hurriganes);
		System.out.println("\n" + "Hurriganesin jasenet:" );
		for (Artisti a:lista4){
			System.out.println(a.annaEtunimi() + " " + a.annaSukunimi());
		}
		System.out.println("\n");
		
		/* Poisto-operaatiot */
		//Poista kommentointi testausta varten
		
//		k.poistaArtistiYhtyeesta(remu, hurriganes);
//		k.poistaKappaleAlbumista(roller, albumi);
//		k.poistaYhtye(hurriganes);
//		k.poistaKappale(roller);
//		k.poistaKappaleArtistilta(remu, planeetta);
//		k.poistaArtisti(remu);
//		k.poistaKappaleYhtyeelta(hurriganes, shorai);
//		k.poistaAlbumi(albumi);
		
	}
}