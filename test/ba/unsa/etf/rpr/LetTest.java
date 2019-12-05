package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class LetTest {

    @Test
    void osnovniTest() {
        Aerodrom sarajevo, zagreb;
        try {
            sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
            zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
            Let l = new Let(sarajevo, zagreb, LocalTime.of(10, 30), LocalTime.of(12, 0));
            assertEquals("SJJ", l.getPolazniAerodrom().getSifra());
            assertEquals("ZAG", l.getDolazniAerodrom().getSifra());
            assertEquals(10, l.getVrijemePolaska().getHour());
            assertEquals(30, l.getVrijemePolaska().getMinute());
            assertEquals(12, l.getVrijemeDolaska().getHour());
            assertEquals(90, l.trajanje());
            assertEquals(3.1, Math.round(l.duzinaLeta() * 10.0) / 10.0);
        } catch (Exception e) {
            fail("Nije trebalo baciti izuzetak u konstruktoru Aerodroma");
        }
    }
}