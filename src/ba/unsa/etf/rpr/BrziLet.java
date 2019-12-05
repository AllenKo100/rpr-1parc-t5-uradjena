package ba.unsa.etf.rpr;

import java.time.LocalTime;

public class BrziLet extends Let {
    public BrziLet() {
        super();
    }

    public BrziLet(Aerodrom polazniAerodrom, Aerodrom dolazniAerodrom, LocalTime vrijemePolaska, LocalTime vrijemDolaska) {
        super(polazniAerodrom, dolazniAerodrom, vrijemePolaska, vrijemDolaska);
    }

    @Override
    public Aerodrom getPolazniAerodrom() {
        return super.getPolazniAerodrom();
    }

    @Override
    public void setPolazniAerodrom(Aerodrom polazniAerodrom) {
        super.setPolazniAerodrom(polazniAerodrom);
    }

    @Override
    public Aerodrom getDolazniAerodrom() {
        return super.getDolazniAerodrom();
    }

    @Override
    public void setDolazniAerodrom(Aerodrom dolazniAerodrom) {
        super.setDolazniAerodrom(dolazniAerodrom);
    }

    @Override
    public LocalTime getVrijemePolaska() {
        return super.getVrijemePolaska();
    }

    @Override
    public void setVrijemePolaska(LocalTime vrijemePolaska) {
        super.setVrijemePolaska(vrijemePolaska);
    }

    @Override
    public LocalTime getVrijemeDolaska() {
        return super.getVrijemeDolaska();
    }

    @Override
    public void setVrijemDolaska(LocalTime vrijemDolaska) {
        super.setVrijemDolaska(vrijemDolaska);
    }

    @Override
    public double duzinaLeta() {
        return (super.duzinaLeta())/2;
    }

    @Override
    public int trajanje() {
        return super.trajanje();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Let o) {
        return super.compareTo(o);
    }
}
