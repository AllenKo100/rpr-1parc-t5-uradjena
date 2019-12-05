package ba.unsa.etf.rpr;

import java.util.Objects;

public class Aerodrom {
    private String nazivAerodroma;
    private String grad;
    private String sifra;
    private Double sirina;
    private Double duzina;

    public Aerodrom(String nazivAerodroma, String grad, String sifra, double sirina, double duzina) throws IlegalnaSifraAerodroma{
        if(sifra.length() != 3) throw new IlegalnaSifraAerodroma(("Ilegalna sifra " + sifra + ", probajte " + sifra.substring(0,2).toUpperCase()));
        else if(sifra.length() == 3 && sifra.matches("[a-zA-z]+")){
            if(sifra.matches("[A-Z]+")){
                this.nazivAerodroma = nazivAerodroma;
                this.grad = grad;
                this.sifra = sifra;
                this.sirina = sirina;
                this.duzina = duzina;
            }
            else if(sifra.matches("[a-zA-Z]+")){
                String tmp = sifra.toUpperCase();
                throw new IlegalnaSifraAerodroma("Ilegalna sifra " + sifra + ", probajte " + tmp);
            }
            else{
                String skracenica="";
                int brojSlova=0;
                for(int i=0; i<grad.length(); i++){
                    if(Character.isLetter(grad.charAt(i))){
                        skracenica=skracenica+grad.charAt(i);
                        brojSlova++;
                    }
                    if(brojSlova==3) break;
                }

                skracenica=skracenica.toUpperCase();
                throw new IlegalnaSifraAerodroma("Ilegalna sifra " + sifra + " probajte " + skracenica);
            }
        }
    }

    public String getNazivAerodroma() {
        return nazivAerodroma;
    }

    public void setNazivAerodroma(String nazivAerodroma) {
        this.nazivAerodroma = nazivAerodroma;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) throws IlegalnaSifraAerodroma {
        if(sifra.length()!=3){
            throw new IlegalnaSifraAerodroma("Ilegalna sifra " + sifra + ", probajte " + sifra.substring(0,2).toUpperCase());
        }else if(sifra.length()==3){
            if(sifra.matches("[A-Z]+")) {
                this.sifra = sifra;
            }else if(sifra.matches("[a-zA-Z]+")){
                String pomocni=sifra.toUpperCase();
                throw new IlegalnaSifraAerodroma("Ilegalna sifra " + sifra + ", probajte " + pomocni);
            }else{
                String skracenica="";
                int brojac=0;
                for(int i=0;i<grad.length();i++){
                    if(Character.isLetter(grad.charAt(i))){
                        skracenica+=grad.charAt(i);
                        brojac++;
                    }
                    if(brojac==3) break;
                }
                skracenica=skracenica.toUpperCase();
                throw new IlegalnaSifraAerodroma("Ilegalna sifra " + sifra + ", probajte "+ skracenica);
            }
        }
    }

    public Double getDuzina() {
        return duzina;
    }

    public void setDuzina(Double duzina) {
        this.duzina = duzina;
    }

    public Double getSirina() {
        return sirina;
    }

    public void setSirina(Double sirina) {
        this.sirina = sirina;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Aerodrom aerodrom = (Aerodrom) o;

        return Double.compare(sirina, aerodrom.sirina) == 0 && Double.compare(duzina, aerodrom.duzina) == 0 &&
                Objects.equals(nazivAerodroma, aerodrom.nazivAerodroma) && Objects.equals(grad, aerodrom.grad) &&
                Objects.equals(sifra, aerodrom.sifra);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nazivAerodroma, grad, sifra, sifra, duzina);
    }
}
