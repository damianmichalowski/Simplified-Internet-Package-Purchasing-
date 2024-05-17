public class Dlugi extends Pakiet{
    private TypPakietu typ;

    public Dlugi(String nazwaPakietu, int okres) {
        super(nazwaPakietu,okres);
        this.typ = TypPakietu.DLUGI;
    }

    @Override
    public TypPakietu getTyp() {
        return typ;
    }

    @Override
    public int cenaPakietu(boolean abonament) {
        int result = 0;
        Integer[] ceny = cennik.pobierzCeny(typ, nazwaPakietu);

        if(abonament)
            result += ceny[0];
        else
            result += ceny[1];

        return result;
    }
}
