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

    //TODO abonament
    @Override
    public int cenaPakietu() {
        int result = 0;
        Integer[] ceny = cennik.pobierzCeny(typ, nazwaPakietu);

        if(true)
            result += ceny[0];
        else
            result += ceny[1];

        return result;
    }
}
