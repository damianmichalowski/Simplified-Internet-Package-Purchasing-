import java.util.ArrayList;

public class Koszyk {
    private ArrayList<Pakiet> pakiety;

    public Koszyk() {
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
        return pakiety.toString();
    }
}
