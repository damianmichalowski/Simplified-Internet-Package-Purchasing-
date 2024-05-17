enum TypPakietu {
    KROTKI,
    SREDNI,
    DLUGI,
    DARMO
}

public class Pakiet {
    String nazwaPakietu;
    int okres;
    Cennik cennik = Cennik.pobierzCennik();

    Pakiet(String nazwaPakietu, int okres){
        this.nazwaPakietu = nazwaPakietu;
        this.okres = okres;
    }
    public TypPakietu getTyp() {
        return null;
    }

    public String getNazwaPakietu() {
        return nazwaPakietu;
    }

    public int getOkres() {
        return okres;
    }
    public int cenaPakietu(boolean abonament) {
        return 0;
    }

    public void zmienOkres(int okres){
        this.okres = okres;
    }

    @Override
    public String toString() {
        String typ = switch (getTyp()) {
            case KROTKI -> "krótkoterminowy";
            case SREDNI -> "średnioterminowy";
            case DLUGI -> "długoterminowy";
            case DARMO -> "darmowy";
            default -> "";
        };

        return getNazwaPakietu()
                + ", typ: " + typ
                + ", ile: " + getOkres() + " okresy";
    }
}