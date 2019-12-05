package ba.unsa.etf.rpr;

import java.time.LocalTime;
import java.util.Objects;

public class Let implements Comparable<Let> {
    private Aerodrom polazniAerodrom;
    private Aerodrom dolazniAerodrom;
    private LocalTime vrijemePolaska;
    private LocalTime vrijemeDolaska;

    public Let(){}

    public Let(Aerodrom polazniAerodrom, Aerodrom dolazniAerodrom, LocalTime vrijemePolaska, LocalTime vrijemDolaska) {
        this.polazniAerodrom = polazniAerodrom;
        this.dolazniAerodrom = dolazniAerodrom;
        this.vrijemePolaska = vrijemePolaska;
        this.vrijemeDolaska = vrijemDolaska;
    }

    public Aerodrom getPolazniAerodrom() {
        return polazniAerodrom;
    }

    public void setPolazniAerodrom(Aerodrom polazniAerodrom) {
        this.polazniAerodrom = polazniAerodrom;
    }

    public Aerodrom getDolazniAerodrom() {
        return dolazniAerodrom;
    }

    public void setDolazniAerodrom(Aerodrom dolazniAerodrom) {
        this.dolazniAerodrom = dolazniAerodrom;
    }

    public LocalTime getVrijemePolaska() {
        return vrijemePolaska;
    }

    public void setVrijemePolaska(LocalTime vrijemePolaska) {
        this.vrijemePolaska = vrijemePolaska;
    }

    public LocalTime getVrijemeDolaska() {
        return vrijemeDolaska;
    }

    public void setVrijemDolaska(LocalTime vrijemDolaska) {
        this.vrijemeDolaska = vrijemDolaska;
    }

    public double duzinaLeta() {
        //Znam da Zemlja nije ravna ploča, ali radi jednostavnosti ćemo koristiti euklidsku udaljenost
        double duzinaDolaznog=dolazniAerodrom.getDuzina();
        double duzinaPolaznog=polazniAerodrom.getDuzina();
        double sirinaDolaznog=dolazniAerodrom.getSirina();
        double sirinaPolaznog=polazniAerodrom.getSirina();

        return Math.sqrt(Math.pow((duzinaDolaznog-duzinaPolaznog),2)+Math.pow((sirinaDolaznog-sirinaPolaznog),2));
    }

    public int trajanje(){
        int vrijemeDolaska=getVrijemeDolaska().getMinute()+getVrijemeDolaska().getHour()*60;
        int vrijemePolaska=getVrijemePolaska().getMinute()+getVrijemePolaska().getHour()*60;

        return vrijemeDolaska-vrijemePolaska;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Let let = (Let) o;
        return Objects.equals(polazniAerodrom, let.polazniAerodrom) && Objects.equals(dolazniAerodrom, let.dolazniAerodrom) &&
                Objects.equals(vrijemeDolaska, let.vrijemeDolaska) && Objects.equals(vrijemePolaska, let.vrijemePolaska);
    }

    @Override
    public int hashCode(){
        return Objects.hash(polazniAerodrom, dolazniAerodrom, vrijemePolaska, vrijemeDolaska);
    }

    @Override
    public int compareTo(Let o){
        return vrijemePolaska.compareTo(o.vrijemePolaska);
    }
}
