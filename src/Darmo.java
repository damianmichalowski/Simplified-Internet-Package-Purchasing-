public class Darmo extends Pakiet{
    private TypPakietu typ;

    public Darmo(String nazwaPakietu, int okres) {
        super(nazwaPakietu,1);
        this.typ = TypPakietu.DARMO;
    }

    @Override
    public TypPakietu getTyp() {
        return typ;
    }
}
