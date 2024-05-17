import java.util.ArrayList;
import java.util.HashMap;

public class Cennik {
    private static Cennik instance;
    private HashMap<TypPakietu, HashMap<String, Integer[]>> cennik;

    private Cennik() {
        this.cennik = new HashMap<>();
    }

    public static Cennik pobierzCennik() {
        if (instance == null) {
            instance = new Cennik();
        }
        return instance;
    }

    public Integer[] pobierzCeny(TypPakietu typPakietu, String nazwaPakietu) {
        HashMap<String, Integer[]> cenyPakietow = cennik.get(typPakietu);
        if (cenyPakietow != null) {
            return cenyPakietow.get(nazwaPakietu);
        }
        return null;
    }

    public void dodaj(TypPakietu typPakietu, String nazwa, Integer... ceny) {
        if (!cennik.containsKey(typPakietu)) {
            cennik.put(typPakietu, new HashMap<>());
        }
        cennik.get(typPakietu).put(nazwa, ceny);
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Cennik:\n");
        for (TypPakietu typ : cennik.keySet()) {
            result.append("Typ pakietu: ").append(typ);
            HashMap<String, Integer[]> cenyPakietow = cennik.get(typ);
            for (String nazwa : cenyPakietow.keySet()) {
                result.append(", Nazwa pakietu: ").append(nazwa).append(", ");
                Integer[] ceny = cenyPakietow.get(nazwa);
                for (int cena : ceny) {
                    result.append(cena).append(" ");
                }
                result.append("\n");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
