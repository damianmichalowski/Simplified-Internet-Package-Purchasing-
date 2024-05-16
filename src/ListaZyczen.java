import java.util.ArrayList;

public class ListaZyczen {
    private ArrayList<Pakiet> listaZyczen;
    private Klient klient;

    ListaZyczen(ArrayList<Pakiet> listaZyczen, Klient klient){
        this.listaZyczen = listaZyczen;
        this.klient = klient;
    }

    public String toString() {
        String result = klient.getNazwa() + ":";
        for (Pakiet p : listaZyczen) {
            result = result.concat("\n"+p.toString());
        }
        return result + "\n";
    }
}