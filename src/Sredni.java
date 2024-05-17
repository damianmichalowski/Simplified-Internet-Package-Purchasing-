public class Sredni extends Pakiet{
    private TypPakietu typ;

    public Sredni(String nazwaPakietu, int okres) {
        super(nazwaPakietu,okres);
        this.typ = TypPakietu.SREDNI;
    }

    @Override
    public TypPakietu getTyp() {
        return typ;
    }

    @Override
    public int cenaPakietu(boolean abonament) {
        int result = 0;
        Integer[] ceny = cennik.pobierzCeny(typ, nazwaPakietu);

        int doIluOkresow = ceny[1];
        if(okres <= doIluOkresow)
            result += ceny[0];
        else if (okres > doIluOkresow)
            result += ceny[2];

        return result;
    }
}