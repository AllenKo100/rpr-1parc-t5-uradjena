package ba.unsa.etf.rpr;

import javax.naming.SizeLimitExceededException;
import java.time.LocalTime;
import java.util.*;

public class Aviokompanija {
    private ArrayList<Let> letovi;
    private int maksimalniBrojLetova;
    private int brojLetova=0;

    public Aviokompanija(int maksimalniBrojLetova){
        this.maksimalniBrojLetova=maksimalniBrojLetova;
        letovi = new ArrayList<>();
        brojLetova=0;
    }

    public int getMaksimalniBrojLetova() {
        return maksimalniBrojLetova;
    }

    public void setMaksimalniBrojLetova(int maksimalniBrojLetova) {
        this.maksimalniBrojLetova = maksimalniBrojLetova;
    }

    int brojLetova(){
        return this.brojLetova;
    }


    public void registrujLet(Aerodrom polazniAerodrom, Aerodrom dolazniAerodrom, LocalTime vrijemePolaska, LocalTime vrijemeDolaska, boolean brzi) throws SizeLimitExceededException {
        if(this.brojLetova() == this.getMaksimalniBrojLetova()) throw new SizeLimitExceededException("Dostignut max kapacitet");
        Let tmp;
        if(brzi)
            tmp = new BrziLet(polazniAerodrom, dolazniAerodrom, vrijemePolaska, vrijemeDolaska);
        else
            tmp = new Let(polazniAerodrom, dolazniAerodrom, vrijemePolaska, vrijemeDolaska);

        brojLetova++;
        letovi.add(tmp);
    }

    public Set<Let> uZraku(LocalTime vrijeme){
        Set<Let> pomocni = new TreeSet<>();
        int trenutnoVrijeme = vrijeme.getMinute() + vrijeme.getHour()*60;

        for(Let let : letovi){
            int vrijemePolaska = let.getVrijemePolaska().getMinute() + let.getVrijemePolaska().getHour()*60;
            int vrijemeDolaska = let.getVrijemeDolaska().getMinute() + let.getVrijemeDolaska().getHour()*60;
            if(vrijemePolaska <= trenutnoVrijeme && vrijemeDolaska >= trenutnoVrijeme)
                pomocni.add(let);
        }

        return pomocni;
    }
}
