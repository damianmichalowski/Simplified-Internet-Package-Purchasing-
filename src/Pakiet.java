public class Pakiet {
    TypPakietu typ;
    String nazwa;
    int[] okresy;

    Pakiet(TypPakietu typ, String nazwa, int... okresy) {
        this.typ = typ;
        this.nazwa = nazwa;
        this.okresy = okresy;
    }

    public TypPakietu getTyp(){
        return typ;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int[] getOkresy() {
        return okresy;
    }

    private String getTypeToString() {
        return switch (this.typ) {
            case KROTKI -> "krótkoterminowy";
            case SREDNI -> "średnioterminowy";
            case DLUGI -> "długoterminowy";
            case DARMO -> "darmowy";
        };
    }

    public String toString() {
        return nazwa + ", typ: " + getTypeToString() + ", ile: " + okresy.length + " okresy, cena ";
    }



}
