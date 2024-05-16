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
    TypPakietu getTyp() {
        return null;
    }

    String getNazwaPakietu() {
        return nazwaPakietu;
    }

    int getOkres() {
        return okres;
    }
    int cenaPakietu(boolean abonament) {
        return 0;
    }

    @Override
    public String toString() {
        String typ = switch (getTyp()) {
            case TypPakietu.KROTKI -> "krótkoterminowy";
            case TypPakietu.SREDNI -> "średnioterminowy";
            case TypPakietu.DLUGI -> "długoterminowy";
            case TypPakietu.DARMO -> "darmowy";
            default -> "";
        };

        return getNazwaPakietu()
                + ", typ: " + typ
                + ", ile: " + getOkres() + " okresy";
    }
}