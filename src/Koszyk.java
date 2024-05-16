import java.util.ArrayList;

public class Koszyk {
    private ArrayList<Pakiet> koszyk;
    private Klient klient;

    Koszyk(ArrayList<Pakiet> koszyk, Klient klient){
        this.koszyk = koszyk;
        this.klient = klient;
    }

    public ArrayList<Pakiet> pobierzKoszyk() {
        return new ArrayList<>(koszyk);
    }

    public Klient getKlient(){
        return klient;
    }

    public String toString() {
        String result = klient.getNazwa() + ":";
        boolean abonament = klient.getAbonament();
        for (Pakiet p : koszyk) {
            int cena = p.cenaPakietu(abonament);
            result = result.concat("\n"+p.toString() + (cena == 0 ? ",ceny brak" : ", cena " + cena));
        }
        return result + "\n";
    }
}