import java.util.ArrayList;

public class ListaZyczen {
    private ArrayList<Pakiet> pakiety;

    public ListaZyczen() {
        this.pakiety = new ArrayList<>();
    }

    public void dodaj(Pakiet pakiet) {
        pakiety.add(pakiet);
    }

    public void wyczysc() {
        pakiety.clear();
    }

    public ArrayList<Pakiet> getPakiety(){
        return pakiety;
    }

    public String toString(){
        String result = "Lista życzeń klienta lte:\n";

        for(Pakiet i : pakiety){
            result = result.concat(i.toString() + "\n");
        }

        return result;
    }
}
