import java.util.ArrayList;

public class Cennik {
    ArrayList<Pakiet> pakiety;

    public Cennik() {
        this.pakiety = new ArrayList<>();
    }

    public static Cennik pobierzCennik(){
        return new Cennik();
    }

    void dodaj(TypPakietu typ, String nazwa, int... okresy){
        this.pakiety.add(new Pakiet(typ,nazwa, okresy));
    }

}
