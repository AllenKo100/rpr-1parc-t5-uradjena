package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BrziLetTest {

    @Test
    void duzinaLeta() {
        Aerodrom sarajevo, zagreb;
        try {
            sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
            zagreb = new Aerodrom("Aerodrom Franjo Tuđman", "Zagreb", "ZAG", 45.813, 15.977);
            Let konkord = new BrziLet(sarajevo, zagreb, LocalTime.of(10, 30), LocalTime.of(12, 0));
            assertEquals(1.56, Math.round(konkord.duzinaLeta() * 100.0) / 100.0);
        } catch (Exception e) {
            fail("Nije trebalo baciti izuzetak u konstruktoru Aerodroma");
        }
    }
}