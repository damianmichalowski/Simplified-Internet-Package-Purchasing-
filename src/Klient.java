import java.util.ArrayList;

enum MetodaPlatnosci {
    KARTA,
    PRZELEW
}

public class Klient {
    private static int idCounter;
    private int id;
    private String nazwa;
    private double kwota;
    private boolean abonament;
    private ArrayList<Pakiet> listaZyczen;
    private ArrayList<Pakiet> koszyk;


    Klient(String nazwa, double kwota, boolean abonament){
        this.nazwa = nazwa;
        this.kwota = kwota;
        this.abonament = abonament;
        this.listaZyczen = new ArrayList<>();
        this.koszyk = new ArrayList<>();
        this.id = idCounter++;
    }

    public String getNazwa(){
        return nazwa;
    }

    public boolean getAbonament(){
        return abonament;
    }

    public void dodaj(Pakiet pakiet) {
        if (pakiet.getTyp().equals(TypPakietu.DARMO) && listaZyczen.stream().anyMatch(p -> p.getTyp().equals(TypPakietu.DARMO))) {
            System.out.println("Pakiet darmowy może być dodany tylko raz.");
        } else {
            listaZyczen.add(pakiet);
        }
    }

    public ListaZyczen pobierzListeZyczen() {
        return new ListaZyczen(listaZyczen, this);
    }

    public Koszyk pobierzKoszyk() {
        return new Koszyk(koszyk,this);
    }

    public void przepakuj(){
        for (int i = listaZyczen.size() - 1; i >= 0; i--) {
            if (listaZyczen.get(i).getTyp() != TypPakietu.DARMO) {
                koszyk.add(listaZyczen.remove(i));
            }
        }
    }

//TODO zaplac i zwroc
//    public void zaplac();
//    public void zwroc();
}
