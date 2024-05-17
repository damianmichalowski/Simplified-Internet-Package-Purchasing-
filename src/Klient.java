import java.util.ArrayList;

enum MetodaPlatnosci {
    KARTA,
    PRZELEW
}

public class Klient {
    private static int idCounter;
    private int id;
    private String nazwa;
    private double portfel;
    private boolean abonament;
    private ArrayList<Pakiet> listaZyczen;
    private ArrayList<Pakiet> koszyk;
    private ArrayList<Pakiet> ostatnieTransakcje;


    Klient(String nazwa, double portfel, boolean abonament) {
        this.nazwa = nazwa;
        this.portfel = portfel;
        this.abonament = abonament;
        this.listaZyczen = new ArrayList<>();
        this.koszyk = new ArrayList<>();
        this.ostatnieTransakcje = new ArrayList<>();
        this.id = idCounter++;
    }

    public String getNazwa() {
        return nazwa;
    }

    public boolean getAbonament() {
        return abonament;
    }

    public void dodaj(Pakiet pakiet) {
        if (pakiet.getTyp().equals(TypPakietu.DARMO) && listaZyczen.stream().anyMatch(p -> p.getTyp().equals(TypPakietu.DARMO))) {
            System.out.println("Pakiet DARMO może być dodany tylko raz !");
        } else {
            listaZyczen.add(pakiet);
        }
    }

    public ListaZyczen pobierzListeZyczen() {
        return new ListaZyczen(listaZyczen, this);
    }

    public Koszyk pobierzKoszyk() {
        return new Koszyk(koszyk, this);
    }

    public void przepakuj() {
        for (int i = listaZyczen.size() - 1; i >= 0; i--) {
            if (listaZyczen.get(i).getTyp() != TypPakietu.DARMO) {
                koszyk.add(listaZyczen.remove(i));
            }
        }
    }

    public double pobierzPortfel() {
        return Math.round(portfel * 10.0) / 10.0;
    }

    public void zaplac(MetodaPlatnosci metodaPlatnosci, boolean czyPlatnoscAutomatyczna) {
        double cena = 0.0;
        double prowizja = metodaPlatnosci == MetodaPlatnosci.KARTA ? 0.01 : 0;

        for (Pakiet p : koszyk) {
            cena += p.cenaPakietu(abonament) * p.getOkres();
        }
        cena +=(cena * prowizja);

        if (cena <= portfel) {
            portfel -= cena;
            ostatnieTransakcje = new ArrayList<>(koszyk);
            koszyk.clear();
        } else if (czyPlatnoscAutomatyczna) {
            double tempCena = 0.0;

            for (Pakiet p : koszyk) {
                int okres = p.getOkres();
                double pakietCena = p.cenaPakietu(abonament);

                while (okres > 0 && tempCena + pakietCena <= portfel) {
                    tempCena += pakietCena;
                    okres--;
                }

                if (okres < p.getOkres()) {
                    TypPakietu typ = p.getTyp();
                    Pakiet nowyPakiet = switch (typ) {
                        case KROTKI -> new Krotki(p.getNazwaPakietu(), p.getOkres() - okres);
                        case SREDNI -> new Sredni(p.getNazwaPakietu(), p.getOkres() - okres);
                        case DLUGI -> new Dlugi(p.getNazwaPakietu(), p.getOkres() - okres);
                        case DARMO -> new Darmo(p.getNazwaPakietu(), p.getOkres() - okres);
                    };
                    ostatnieTransakcje.add(nowyPakiet);
                    p.zmienOkres(okres);
                }
            }
            portfel -= tempCena + (tempCena * prowizja);
            koszyk.removeIf(p -> p.getOkres() == 0);
        } else {
            koszyk.clear();
            listaZyczen.clear();
        }
    }

    public void zwroc(TypPakietu typPakietu, String nazwaPakietu, int ileOkresow) {
        double kwotaZwrotu = 0.0;

        for (Pakiet p : new ArrayList<>(ostatnieTransakcje)) {
            if (p.getTyp() == typPakietu && p.getNazwaPakietu().equals(nazwaPakietu)) {
                Pakiet zwrotPakietu = switch (typPakietu) {
                    case KROTKI -> new Krotki(nazwaPakietu, ileOkresow);
                    case SREDNI -> new Sredni(nazwaPakietu, ileOkresow);
                    case DLUGI -> new Dlugi(nazwaPakietu, ileOkresow);
                    case DARMO -> new Darmo(nazwaPakietu, ileOkresow);
                };
                koszyk.add(zwrotPakietu);
                kwotaZwrotu += zwrotPakietu.cenaPakietu(abonament) * ileOkresow;
            }
        }

        portfel += kwotaZwrotu;
    }
}