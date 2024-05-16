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

        if(okres <= 2)
            result += ceny[0];
        else
            result += ceny[2];

        return result;
    }
}
