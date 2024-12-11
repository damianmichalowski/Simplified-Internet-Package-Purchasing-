Uzycie, skompilować poprzez funkcje pliku GBTest
 
# Simplified Internet Package Ordering/Purchasing Simulation Application in a Telecommunications Company
Opis Projekty

Aplikacja symulująca uproszczony proces zamawiania/zakupu pakietów internetowych w pewnej firmie telekomunikacyjnej.
Firma oferuje różne typy pakietów, każdy pakiet ma swoją nazwę oraz typ (np. krótkoterminowy, średnioterminowy, długoterminowy, ...). Pakiety mogą być zakupione na 1 lub kilka kolejnych okresów czasowych. Ceny pakietów znajdują się w cenniku. Cennik można modyfikować w dowolnym momencie.

Klienci po rejestracji otrzymują identyfikatory oraz deklarują kwotę pieniędzy, którą chcą wydać na pakiety. Klienci mogą (ale nie muszą) posiadać abonament w firmie. Klienci wybierają pakiety i umieszczają je na swojej tzw. liście życzeń. Następnie, być może po jakimś czasie, w celu finalizacji zamówienia przepakowują pakiety do koszyka (po uprzednim wyczyszczeniu) i zapłacą za jego zawartość. Każdy klient może mieć tylko 1 listę życzeń i 1 koszyk. Klienci mogą płacić kartą lub przelewem bankowym. Po zapłaceniu, klient ma możliwość dokonania zwrotu pakietów z ostatniej transakcji.

Aplikacja powinna być napisana tak, aby następujacy, przykładowy program

    public class GBTest {

	// cena pakietów danego typu z koszyka 
    static int cena(Koszyk k, String nazwaPakietu) {
    /*<- tu trzeba wpisać ciało metody */
    }

    public static void main(String[] args) {

		// cennik
	Cennik cennik = Cennik.pobierzCennik();

		// dodawanie nowych cen do cennika
	cennik.dodaj(KROTKI, "5GB", 20, 15, 3, 10); 	// 20 zł za 1 okres (jednorazowy), 
							// 15 zł za 1 okres (jeśli klient zakupi od 2 do 3 kolejnych okresów)
							// 10 zł za 1 okres (jeśli klient zakupi co najmniej 4 kolejne okresy)

    	cennik.dodaj(SREDNI, "10GB", 25, 2, 20);	// 25 zł za 1 okres (jeśli klient zakupi do 2 kolejnych okresów), 
							// 20 zł za 1 okres (jeśli klient zakupi od 3 kolejnych okresów)  
 
    	cennik.dodaj(DLUGI, "30GB", 30, 35);		// 30 zł za 1 okres jeśli klient ma abonament, 
							// 35 zł za 1 okres jeśli klient nie ma abonamentu
 
	cennik.dodaj(DARMO, "1GB");			// darmowy dostęp (tylko na 1 okres oraz tylko 1 raz na klienta)
 

   		// Klient lte deklaruje kwotę 200 zł na zamównienia; true oznacza, że klient posiada abonament
    	Klient lte = new Klient("lte", 200, true);

    		// Klient lte dodaje do listy życzeń różne pakiety:
		// "5GB" typu krotkoterminowego na 4 kolejne okresy
		// "10GB" typu średnioterminowego na 3 kolejne okresy,		
		// "30GB" typu długoterminowego na 2 kolejne okresy, 
		// "2GB" typu darmowego na 2 kolejne okresy (ale może mieć tylko 1 okres)  
    	lte.dodaj(new Krotki("5GB", 4));
	lte.dodaj(new Sredni("10GB", 3));
    	lte.dodaj(new Dlugi("30GB", 2));
    	lte.dodaj(new Darmo("2GB", 2));

		// Lista życzeń klienta lte
	ListaZyczen listaLte = lte.pobierzListeZyczen();
 
    	System.out.println("Lista życzeń klienta " + listaLte);

		// Przed płaceniem, klient przepakuje pakiety z listy życzeń do koszyka (po uprzednim wyczyszczeniu).
		// Możliwe, że na liście życzeń są pakiety niemające ceny w cenniku,
    		// w takim przypadku nie trafiłyby do koszyka     	
	Koszyk koszykLte = lte.pobierzKoszyk();
    	lte.przepakuj();

    		// Co jest na liście życzeń klienta lte
    	System.out.println("Po przepakowaniu, lista życzeń klienta " + lte.pobierzListeZyczen());

		// Co jest w koszyku klienta lte
	System.out.println("Po przepakowaniu, koszyk klienta " + koszykLte); 

		// Ile wynosi cena wszystkich pakietów typu długoterminowego w koszyku klienta lte
    	System.out.println("Pakiety 5GB w koszyku klienta lte kosztowały:  " + cena(koszykLte, "5GB"));

    		// Klient zapłaci...
    	lte.zaplac(KARTA, false);	// płaci kartą płatniczą, prowizja 1%
					// true oznacza, że w przypadku braku środków aplikacja sam odłoży nadmiarowe pakiety,
					// false oznacza rezygnację z płacenia razem z wyczyszczeniem koszyka i listy życzeń
					
    		// Ile klientowi lte zostało pieniędzy?
    	System.out.println("Po zapłaceniu, klientowi lte zostało: " + lte.pobierzPortfel() + " zł");
	
    		// Mogło klientowi zabraknąć srodków, wtedy, opcjonalnie, pakiety mogą być odkładane,
		// w przeciwnym przypadku, koszyk jest pusty po zapłaceniu 
    	System.out.println("Po zapłaceniu, koszyk klienta " + lte.pobierzKoszyk());
	System.out.println("Po zapłaceniu, koszyk klienta " + koszykLte); 	
 
		// Teraz przychodzi klient gsm,
    		// deklaruje 65 zł na zamówienia
    	Klient gsm = new Klient("gsm", 65, false);

    		// Zamówił za dużo jak na tę kwotę
	gsm.dodaj(new Sredni("10GB", 2));
    	gsm.dodaj(new Dlugi("30GB", 1));
    
    		// Co klient gsm ma na swojej liście życzeń
    	System.out.println("Lista życzeń klienta " + gsm.pobierzListeZyczen());
			
	Koszyk koszykGsm = gsm.pobierzKoszyk();
    	gsm.przepakuj();
	
    		// Co jest na liście życzeń klienta gsm
    	System.out.println("Po przepakowaniu, lista życzeń klienta " + gsm.pobierzListeZyczen());

	    	// A co jest w koszyku klienta gsm
    	System.out.println("Po przepakowaniu, koszyk klienta " + gsm.pobierzKoszyk());
    	
		// klient gsm płaci
    	gsm.zaplac(PRZELEW, true);	// płaci przelewem, bez prowizji
		
		// Ile klientowi gsm zostało pieniędzy? 
	System.out.println("Po zapłaceniu, klientowi gsm zostało: " + gsm.pobierzPortfel() + " zł");
		
		// Co zostało w koszyku klienta gsm (za mało pieniędzy miał)
 	System.out.println("Po zapłaceniu, koszyk klienta " + koszykGsm);

	gsm.zwroc(DLUGI, "30GB", 1);	// zwrot (do koszyka) pakietu (na 1 okres) z ostatniej transakcji

		// Ile klientowi gsm zostało pieniędzy? 
	System.out.println("Po zwrocie, klientowi gsm zostało: " + gsm.pobierzPortfel() + " zł");
		
		// Co zostało w koszyku klienta gsm
 	System.out.println("Po zwrocie, koszyk klienta " + koszykGsm); 
    
    }
    }

wyprowadził na konsolę podobne do poniższych wyniki:
    Lista życzeń klienta lte:
    5GB, typ: krótkoterminowy, ile: 4 okresy, cena 10
    10GB, typ: średnioterminowy, ile: 3 okresy, cena 20
    30GB typ: długotermoniwy, ile: 2 okresy, cena 30
    2GB, typ: darmowy, ile: 1 okres, ceny brak
    
    Po przepakowaniu, lista życzeń klienta lte:
    2GB, typ: darmowy, ile: 1 okres, ceny brak
    
    Po przepakowaniu, koszyk klienta lte:
    5GB, typ: krótkoterminowy, ile: 4 okresy, cena 10
    10GB, typ: średnioterminowy, ile: 3 okresy, cena 20
    30GB typ: długotermoniwy, ile: 2 okresy, cena 30
    
    Pakiety 5GB w koszyku klienta lte kosztowały: 40
    
    Po zapłaceniu, klientowi lte zostało: 38.4 zł
    
    Po zapłaceniu, koszyk klienta lte: -- pusto
    Po zapłaceniu, koszyk klienta lte: -- pusto
    
    Lista życzeń klienta gsm:
    10GB, typ: średnioterminowy, ile: 2 okresy, cena 25
    30GB, typ: długoterminowy, ile: 1 okres, cena 35
    
    Po przepakowaniu, lista życzeń klienta gsm: -- pusto
    
    Po przepakowaniu, koszyk klienta gsm:
    10GB, typ: średnioterminowy, ile: 2 okresy, cena 25
    30GB, typ: długoterminowy, ile: 1 okres, cena 35
    
    Po zapłaceniu, klientowi gsm zostało: 5.00 zł
    
    Po zapłaceniu, koszyk klienta gsm:
    10GB, typ: średnioterminowy, ile: 1 okres, cena 25
    
    Po zwrocie, klientowi gsm zostało: 40.00 zł
    
    Po zwrocie, koszyk klienta gsm:
    10GB, typ: średnioterminowy, ile: 1 okres, cena 25
    30GB, typ: długoterminowy, ile: 1 okres, cena 35
Dodawanie nowych typów pakietów musi być zrealizowane w sposób bardzo prosty, w takim przypadku modyfikacja pozostałych klas jest niedozwolona.

Wymagania dodatkowe:
korzystanie z klas abstrakcyjnych/interfejsów oraz z polimorfizmu jest obowiązkowe
pożądana jest minimalizacja kodu klas ListaZyczen oraz Koszyk
istnieje tylko jeden obiekt-cennik/obiekt-koszyk dla klienta, do którego dostęp powinien być realizowany wyłącznie za pomocą metody pobierzCennik()/pobierzKoszyk()
Ważne uwagi:
Być może są pakiety niemające ceny w cenniku, wtedy przy przepakowaniu z listy życzeń do koszyka nie są one usuwane z listy życzeń i nie trafią do koszyka.
Niektóre pakiety posiadają różne ceny w zależności od liczby kolejnych okresów czasowych, na które klienci mogą zamówić.
Jeśli klient nie ma wystarczającej ilości pieniędzy na całą zawartość koszyka to, zależy od opcji, może zapłacić za część koszyka o ile zapłacona kwota (za dany pakiet) jest wielokrotnością ceny tego pakietu za 1 okres (zgodnie z cennikiem), w takim przypadku o wyborze pakietów do zapłacenia zadecyduje aplikacja.
Przy płaceniu kartą płatniczą klient zapłaci dodatkowo 1% prowizji.
Przy zwrocie pakietów z ostatniej transakcji, aplikacja powinna zwrócić klientowi właściwą ilość środków uwzględniając faktycznie niezwrócone pakiety (zgodnie z cennikiem). Nie ma zwrotu prowizji.
Zdefiniowanie odpowiedniej metody toString() w niektórych klasach jest obowiązkowe.
Aplikacja powinna działać dla dowolnych danych a nie tylko dla tych podanych w powyższym teście. Przykładowy program nie uwzględnia wszystkich możliwych sytuacji podanych w opisie zadania.
