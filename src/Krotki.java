public class Krotki extends Pakiet {
    private TypPakietu typ;

    public Krotki(String nazwaPakietu, int okres) {
        super(nazwaPakietu,okres);
        this.typ = TypPakietu.KROTKI;
    }

    @Override
    public TypPakietu getTyp() {
        return typ;
    }

    @Override
    public int cenaPakietu(boolean abonament) {
        int result = 0;
        Integer[] ceny = cennik.pobierzCeny(typ, nazwaPakietu);

        int doIluOkresow = ceny[2];
        if(okres == 1)
            result += ceny[0];
        else if(okres >= 2 && okres < doIluOkresow)
            result += ceny[1];
        else if(okres >= doIluOkresow)
            result += ceny[3];

        return result;
    }
}