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
        for (Pakiet p : koszyk) {
            result = result.concat("\n"+p.toString());
        }
        return result + "\n";
    }
}