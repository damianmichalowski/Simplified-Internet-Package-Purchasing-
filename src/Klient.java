import java.util.ArrayList;

public class Klient {
    int id;
    String nazwa;
    double kwota; //deklarowana kwota pieniedzy która chce wydać klient na pakiet
    boolean abonament; // klient może ale nie musi posiadać abonament
    static int counter = 0;
    ListaZyczen listaZyczen; //wybierają pakiety i umieszczają je na liście życzeń
    Koszyk koszyk; //z listy zyczen po wyczyszczeniu do koszyka

    public Klient(String nazwa, int kwota, boolean abonament) {
        this.id = counter++;
        this.nazwa = nazwa;
        this.kwota = kwota;
        this.abonament = abonament;
        this.listaZyczen = new ListaZyczen();
        this.koszyk = new Koszyk();
    }

    public int getId() {
        return id;
    };

    public ListaZyczen pobierzListeZyczen(){
        return listaZyczen;
    };

    public Koszyk pobierzKoszyk(){
        return koszyk;
    };

    public double pobierzPortfel() {
        return kwota;
    }

    public void dodaj(Pakiet pakiet){
        listaZyczen.dodaj(pakiet);
    };

    public void przepakuj(){
        koszyk.wyczysc();
        for(Pakiet pakiet : listaZyczen.getPakiety()){
            koszyk.dodaj(pakiet);
        }
        listaZyczen.wyczysc();
    };

}
