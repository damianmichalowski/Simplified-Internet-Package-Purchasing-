import java.util.ArrayList;
import java.util.HashMap;

public class ListaZyczen {
    private ArrayList<Pakiet> listaZyczen;
    private Klient klient;

    ListaZyczen(ArrayList<Pakiet> listaZyczen, Klient klient){
        this.listaZyczen = listaZyczen;
        this.klient = klient;
    }

    public String toString() {
        if(!listaZyczen.isEmpty()){
            String result = klient.getNazwa() + ":";
            boolean abonament = klient.getAbonament();

            for (Pakiet p : listaZyczen) {
                int cena = p.cenaPakietu(abonament);
                result = result.concat("\n"+p.toString() + (cena == 0 ? ", ceny brak" : ", cena " + cena));
            }
            return result + "\n";
        }else
            return "--pusto";
    }
}