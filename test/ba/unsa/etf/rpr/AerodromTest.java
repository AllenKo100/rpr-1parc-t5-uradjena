package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AerodromTest {

    @Test
    void osnovniTest() {
        Aerodrom sarajevo;
        try {
            sarajevo = new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJJ", 43.858, 18.407);
            assertEquals("Međunarodni aerodrom Sarajevo", sarajevo.getNazivAerodroma());
            assertEquals("Sarajevo", sarajevo.getGrad());
            assertEquals("SJJ", sarajevo.getSifra());
            assertEquals(43.858, sarajevo.getSirina());
            assertEquals(18.407, sarajevo.getDuzina());
        } catch (Exception e) {
            fail("Nije trebalo baciti izuzetak u konstruktoru");
        }
    }

    @Test
    void ilegalnaSifra() {
        assertThrows(IlegalnaSifraAerodroma.class,
                () -> new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SjJ", 43.858, 18.407),
                "Ilegalna sifra SjJ, probajte SJJ"
        );
        assertThrows(IlegalnaSifraAerodroma.class,
                () -> new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJj", 43.858, 18.407),
                "Ilegalna sifra SJj, probajte SJJ"
        );
        assertThrows(IlegalnaSifraAerodroma.class,
                () -> new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "sJJ", 43.858, 18.407),
                "Ilegalna sifra sJJ, probajte SJJ"
        );
    }

    @Test
    void ilegalnaSifra2() {
        try {
            new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "SJ5", 43.858, 18.407);
        } catch(Exception e) {
            assertEquals("Ilegalna sifra SJ5, probajte SAR", e.getMessage());
        }
        try {
            new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "S J", 43.858, 18.407);
        } catch(Exception e) {
            assertEquals("Ilegalna sifra S J, probajte SAR", e.getMessage());
        }
        try {
            new Aerodrom("Međunarodni aerodrom Sarajevo", "Sarajevo", "ŠSJ", 43.858, 18.407);
        } catch(Exception e) {
            assertEquals("Ilegalna sifra ŠSJ, probajte SAR", e.getMessage());
        }
    }

    @Test
    void ilegalnaSifraNaknadno() {
        Aerodrom visoko;
        try {
            visoko = new Aerodrom("Međunarodni aerodrom Visoko", "Visoko", "ABC", 43.858, 18.407);
            assertEquals("ABC", visoko.getSifra());
            assertThrows(IlegalnaSifraAerodroma.class,
                    () -> visoko.setSifra("V1S"),
                    "Ilegalna sifra V1S, probajte VIS"
            );
        } catch (Exception e) {
            fail("Nije trebalo baciti izuzetak u konstruktoru");
        }
    }

    @Test
    void ilegalnaSifraNaknadno2() {
        Aerodrom siroki;
        try {
            siroki = new Aerodrom("Aerodrom Franjo Tuđman", "Široki Brijeg", "SIR", 43.858, 18.407);
            assertEquals("SIR", siroki.getSifra());
            assertThrows(IlegalnaSifraAerodroma.class,
                    () -> siroki.setSifra("SI"),
                    "Ilegalna sifra SI, probajte IRO"
            );
            assertThrows(IlegalnaSifraAerodroma.class,
                    () -> siroki.setSifra("SIROKI"),
                    "Ilegalna sifra SIROKI, probajte IRO"
            );
        } catch (Exception e) {
            fail("Nije trebalo baciti izuzetak u konstruktoru");
        }

    }

    @Test
    void ilegalnaSifraNaknadno3() {
        Aerodrom a;
        try {
            a = new Aerodrom("El Alamein", "El Alamein", "ALM", 43.858, 18.407);
            assertEquals("ALM", a.getSifra());
            assertThrows(IlegalnaSifraAerodroma.class,
                    () -> a.setSifra("EL A"),
                    "Ilegalna sifra EL A, probajte ELA"
            );
        } catch (Exception e) {
            fail("Nije trebalo baciti izuzetak u konstruktoru");
        }

    }
}