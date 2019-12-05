package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import javax.naming.SizeLimitExceededException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AviokompanijaTest {

    @Test
    void osnovniTest() throws IlegalnaSifraAerodroma {
        Aviokompanija airBosnia = new Aviokompanija(100);
        Aerodrom sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
        Aerodrom zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
        try {
            airBosnia.registrujLet(sarajevo, zagreb, LocalTime.of(10, 30), LocalTime.of(12, 0), false);
            assertEquals(1, airBosnia.brojLetova());
        } catch (Exception e) {
            fail("Nije trebalo baciti izuzetak u metodi registrujLet");
        }
    }

    @Test
    void maxBrojLetova() throws IlegalnaSifraAerodroma {
        Aviokompanija airBosnia = new Aviokompanija(2);
        Aerodrom sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
        Aerodrom zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
        Aerodrom newYork = new Aerodrom("John F. Kennedy", "New York", "JFK", 40.38, -73.46);
        try {
            airBosnia.registrujLet(sarajevo, zagreb, LocalTime.of(10, 30), LocalTime.of(12, 0), false);
            airBosnia.registrujLet(zagreb, newYork, LocalTime.of(12, 30), LocalTime.of(18, 15), true);
            assertEquals(2, airBosnia.brojLetova());
        } catch (Exception e) {
            fail("Nije trebalo baciti izuzetak u metodi registrujLet");
        }

        assertThrows(SizeLimitExceededException.class,
                () -> airBosnia.registrujLet(sarajevo, newYork, LocalTime.of(10, 30), LocalTime.of(12, 0), false)
        );
    }

    /*@Test
    void dolazniLetovi() throws IlegalnaSifraAerodroma, SizeLimitExceededException {
        Aviokompanija airBosnia = new Aviokompanija(6);
        Aerodrom sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
        Aerodrom zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
        Aerodrom newYork = new Aerodrom("John F. Kennedy", "New York", "JFK", 40.38, -73.46);

        airBosnia.registrujLet(sarajevo, zagreb, LocalTime.of(10, 30), LocalTime.of(12, 0), false);
        airBosnia.registrujLet(zagreb, sarajevo, LocalTime.of(16, 30), LocalTime.of(18, 0), true);
        airBosnia.registrujLet(zagreb, newYork, LocalTime.of(12, 30), LocalTime.of(18, 15), false);
        airBosnia.registrujLet(newYork, zagreb, LocalTime.of(1, 15), LocalTime.of(7, 55), false);
        assertEquals(4, airBosnia.brojLetova());

        Map<String, List<Let>> dolazni = airBosnia.dolazniLetovi();
        assertEquals(2, dolazni.get("Zagreb").size());
        assertEquals(1, dolazni.get("Sarajevo").size());
        assertEquals(1, dolazni.get("New York").size());
    }

    @Test
    void dolazniLetovi2() throws IlegalnaSifraAerodroma, SizeLimitExceededException {
        Aviokompanija airBosnia = new Aviokompanija(6);
        Aerodrom sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
        Aerodrom zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
        Aerodrom newYork = new Aerodrom("John F. Kennedy", "New York", "JFK", 40.38, -73.46);

        airBosnia.registrujLet(sarajevo, zagreb, LocalTime.of(10, 30), LocalTime.of(12, 0), false);
        airBosnia.registrujLet(zagreb, sarajevo, LocalTime.of(16, 30), LocalTime.of(18, 0), true);
        airBosnia.registrujLet(zagreb, newYork, LocalTime.of(12, 30), LocalTime.of(18, 15), false);
        airBosnia.registrujLet(newYork, zagreb, LocalTime.of(1, 15), LocalTime.of(7, 55), false);
        assertEquals(4, airBosnia.brojLetova());

        Map<String, List<Let>> dolazni = airBosnia.dolazniLetovi();

        int ok = 0;
        for(Let l : dolazni.get("Zagreb")) {
            if (l.getPolazniAerodrom().getSifra().equals("SJJ")) ok++;
            if (l.getPolazniAerodrom().getSifra().equals("JFK")) ok++;
        }
        assertEquals(2, ok);
    }*/

    @Test
    void uZraku() throws IlegalnaSifraAerodroma, SizeLimitExceededException {
        Aviokompanija airBosnia = new Aviokompanija(6);
        Aerodrom sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
        Aerodrom zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
        Aerodrom newYork = new Aerodrom("John F. Kennedy", "New York", "JFK", 40.38, -73.46);

        airBosnia.registrujLet(sarajevo, zagreb, LocalTime.of(10, 30), LocalTime.of(12, 0), false);
        airBosnia.registrujLet(zagreb, sarajevo, LocalTime.of(16, 30), LocalTime.of(18, 0), true);
        airBosnia.registrujLet(zagreb, newYork, LocalTime.of(12, 30), LocalTime.of(18, 15), false);
        airBosnia.registrujLet(newYork, zagreb, LocalTime.of(1, 15), LocalTime.of(7, 55), false);

        Set<Let> uZraku = airBosnia.uZraku(LocalTime.of(17, 30));
        assertEquals(2, uZraku.size());

        Let[] uZrakuNiz = new Let[2];
        uZrakuNiz = uZraku.toArray(uZrakuNiz);
        assertEquals("ZAG", uZrakuNiz[0].getPolazniAerodrom().getSifra());
        assertEquals("ZAG", uZrakuNiz[1].getPolazniAerodrom().getSifra());
        assertEquals("JFK", uZrakuNiz[0].getDolazniAerodrom().getSifra());
        assertEquals("SJJ", uZrakuNiz[1].getDolazniAerodrom().getSifra());
    }

    @Test
    void uZraku2() throws IlegalnaSifraAerodroma, SizeLimitExceededException {
        Aviokompanija airBosnia = new Aviokompanija(6);
        Aerodrom sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
        Aerodrom zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
        Aerodrom newYork = new Aerodrom("John F. Kennedy", "New York", "JFK", 40.38, -73.46);

        airBosnia.registrujLet(sarajevo, zagreb, LocalTime.of(12, 29), LocalTime.of(14, 0), false);
        airBosnia.registrujLet(zagreb, sarajevo, LocalTime.of(11, 30), LocalTime.of(17, 0), false);
        airBosnia.registrujLet(zagreb, newYork, LocalTime.of(12, 30), LocalTime.of(18, 15), false);
        airBosnia.registrujLet(newYork, zagreb, LocalTime.of(10, 15), LocalTime.of(16, 55), true);

        Set<Let> uZraku = airBosnia.uZraku(LocalTime.of(10, 10));
        assertEquals(0, uZraku.size());

        uZraku = airBosnia.uZraku(LocalTime.of(13, 10));
        assertEquals(4, uZraku.size());

        Let[] uZrakuNiz = new Let[4];
        uZrakuNiz = uZraku.toArray(uZrakuNiz);
        assertEquals("JFK", uZrakuNiz[0].getPolazniAerodrom().getSifra());
        assertEquals("ZAG", uZrakuNiz[1].getPolazniAerodrom().getSifra());
        assertEquals("SJJ", uZrakuNiz[2].getPolazniAerodrom().getSifra());
        assertEquals("ZAG", uZrakuNiz[3].getPolazniAerodrom().getSifra());
    }

    /*@Test
    void nadjiNajkraciTrivijalno() throws IlegalnaSifraAerodroma, SizeLimitExceededException {
        Aviokompanija airBosnia = new Aviokompanija(6);
        Aerodrom sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
        Aerodrom zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
        Aerodrom newYork = new Aerodrom("John F. Kennedy", "New York", "JFK", 40.38, -73.46);
        Aerodrom bech = new Aerodrom("Wien", "Beč", "VIE", 48.228, 16.397);

        airBosnia.registrujLet(sarajevo, zagreb, LocalTime.of(12, 29), LocalTime.of(14, 0), false);
        airBosnia.registrujLet(zagreb, sarajevo, LocalTime.of(11, 30), LocalTime.of(17, 0), false);
        airBosnia.registrujLet(zagreb, newYork, LocalTime.of(12, 30), LocalTime.of(18, 15), false);
        airBosnia.registrujLet(newYork, zagreb, LocalTime.of(10, 15), LocalTime.of(16, 55), true);
        airBosnia.registrujLet(sarajevo, bech, LocalTime.of(15, 50), LocalTime.of(17, 40), false);
        airBosnia.registrujLet(bech, newYork, LocalTime.of(17, 55), LocalTime.of(23, 59), false);

        ArrayList<Let> letovi = airBosnia.nadjiNajkraci("Sarajevo", "Beč");
        assertEquals(1, letovi.size());
        assertEquals("VIE", letovi.get(0).getDolazniAerodrom().getSifra());
    }

    @Test
    void nadjiNajkraciNetrivijalno() throws IlegalnaSifraAerodroma, SizeLimitExceededException {
        Aviokompanija airBosnia = new Aviokompanija(6);
        Aerodrom sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
        Aerodrom zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
        Aerodrom newYork = new Aerodrom("John F. Kennedy", "New York", "JFK", 40.38, -73.46);
        Aerodrom bech = new Aerodrom("Wien", "Beč", "VIE", 48.228, 16.397);

        airBosnia.registrujLet(sarajevo, zagreb, LocalTime.of(12, 29), LocalTime.of(14, 0), false);
        airBosnia.registrujLet(zagreb, sarajevo, LocalTime.of(11, 30), LocalTime.of(17, 0), false);
        airBosnia.registrujLet(zagreb, newYork, LocalTime.of(12, 30), LocalTime.of(18, 15), false);
        airBosnia.registrujLet(newYork, zagreb, LocalTime.of(10, 15), LocalTime.of(16, 55), true);
        airBosnia.registrujLet(sarajevo, bech, LocalTime.of(15, 50), LocalTime.of(17, 40), false);
        airBosnia.registrujLet(bech, newYork, LocalTime.of(17, 55), LocalTime.of(23, 59), false);

        ArrayList<Let> letovi = airBosnia.nadjiNajkraci("Sarajevo", "New York");
        // Jedini mogući let je preko Beča jer iz Zagreb polazi ranije
        assertEquals(2, letovi.size());
        assertEquals("VIE", letovi.get(0).getDolazniAerodrom().getSifra());
    }

    @Test
    void nadjiNajkraciNetrivijalno2() throws IlegalnaSifraAerodroma, SizeLimitExceededException {
        Aviokompanija airBosnia = new Aviokompanija(6);
        Aerodrom sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
        Aerodrom zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 17.977);
        Aerodrom newYork = new Aerodrom("John F. Kennedy", "New York", "JFK", 40.38, -73.46);
        Aerodrom bech = new Aerodrom("Wien", "Beč", "VIE", 48.228, 16.397);

        airBosnia.registrujLet(sarajevo, zagreb, LocalTime.of(12, 29), LocalTime.of(14, 0), false);
        airBosnia.registrujLet(zagreb, sarajevo, LocalTime.of(11, 30), LocalTime.of(17, 0), false);
        airBosnia.registrujLet(zagreb, newYork, LocalTime.of(14, 30), LocalTime.of(21, 15), false);
        airBosnia.registrujLet(newYork, zagreb, LocalTime.of(10, 15), LocalTime.of(16, 55), true);
        airBosnia.registrujLet(sarajevo, bech, LocalTime.of(15, 50), LocalTime.of(17, 40), false);
        airBosnia.registrujLet(bech, newYork, LocalTime.of(17, 55), LocalTime.of(23, 59), false);

        ArrayList<Let> letovi = airBosnia.nadjiNajkraci("Sarajevo", "New York");
        // Sada je preko Zagreba bliže
        assertEquals(2, letovi.size());
        assertEquals("ZAG", letovi.get(0).getDolazniAerodrom().getSifra());
    }

    @Test
    void nadjiNajbolji() throws IlegalnaSifraAerodroma, SizeLimitExceededException {
        Aviokompanija airBosnia = new Aviokompanija(6);
        Aerodrom sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
        Aerodrom zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
        Aerodrom newYork = new Aerodrom("John F. Kennedy", "New York", "JFK", 40.38, -73.46);
        Aerodrom bech = new Aerodrom("Wien", "Beč", "VIE", 48.228, 16.397);

        airBosnia.registrujLet(sarajevo, zagreb, LocalTime.of(12, 29), LocalTime.of(14, 0), false);
        airBosnia.registrujLet(zagreb, sarajevo, LocalTime.of(14, 30), LocalTime.of(21, 0), false);
        airBosnia.registrujLet(zagreb, newYork, LocalTime.of(14, 30), LocalTime.of(21, 15), false);
        airBosnia.registrujLet(newYork, zagreb, LocalTime.of(10, 15), LocalTime.of(16, 55), false);
        airBosnia.registrujLet(sarajevo, bech, LocalTime.of(15, 50), LocalTime.of(17, 40), false);
        airBosnia.registrujLet(bech, newYork, LocalTime.of(17, 55), LocalTime.of(23, 59), false);

        ArrayList<Let> letovi = airBosnia.nadjiNajbolji("Sarajevo", "New York", (Let l) -> { return Double.valueOf(l.trajanje()); } );
        // Preko Beča je brže
        assertEquals(2, letovi.size());
        assertEquals("VIE", letovi.get(0).getDolazniAerodrom().getSifra());
    }*/
}