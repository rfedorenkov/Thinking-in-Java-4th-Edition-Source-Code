package exercises.chapter17;

import net.mindview.util.Countries;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Exercise 2
 * Produce a Map and a Set containing all the countries that begin with 'A'.
 */
public class E02_BeginWithACountries {
    public static void main(String[] args) {
        Map<String, String> countriesMap = new HashMap<>();
        for (Map.Entry<String, String> entry : new TreeMap<>(Countries.capitals()).entrySet()) {
            if (entry.getKey().startsWith("B")) {
                break;
            }
            countriesMap.put(entry.getKey(), entry.getValue());
        }
        System.out.println(countriesMap);
        Set<String> countriesSet = countriesMap.keySet();
        System.out.println(countriesSet);
    }
}
/* Output:
{AUSTRALIA=Canberra, AFGHANISTAN=Kabul, AZERBAIJAN=Baku, ALBANIA=Tirana, ANDORRA=Andorra la Vella, ANTIGUA AND BARBUDA=Saint John's, ARGENTINA=Buenos Aires, ANGOLA=Luanda, ARMENIA=Yerevan, ALGERIA=Algiers, AUSTRIA=Vienna}
[AUSTRALIA, AFGHANISTAN, AZERBAIJAN, ALBANIA, ANDORRA, ANTIGUA AND BARBUDA, ARGENTINA, ANGOLA, ARMENIA, ALGERIA, AUSTRIA]
 */