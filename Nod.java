import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Clasa care descrie un nod.
 */
public class Nod implements Comparable<Nod> {
    //numarul asociat nodului
    private int nume;
    //costul de la nodul de start pana la nodul curent
    private Integer cost = Integer.MAX_VALUE;

    // contine nodurile parcurse de la nodul sursa pana la nodul curent fara acesta din urma
    private Queue<Nod> caleaCeaMaiScurta = new LinkedList<>();
    //mapa care cuprinde la cheie, numele nodurilor vecine si la valoare, costurile asociate drumurilor respective
    Map<Integer, Integer> noduriVecine = new HashMap<>();

    /**
     * Adauga in mapa un nod vecin pentru nodul curent.
     * @param destinatie Nodul vecin.
     * @param distanta Costul pana la nodul vecin.
     */
    public void addVecin(Nod destinatie, int distanta) {
        noduriVecine.put(destinatie.getNume(), distanta);
    }

    /**
     * Constructorul unui nod cu numele specificat.
     * @param nume Numele nodului pe care dorim sa il creem.
     */
    public Nod(int nume) {
        this.nume = nume;
    }

    /**
     * Constructorul cu parametru de tip Nod ce seteaza
     * atributele clasei folosinduse de nodul dat ca parametru.
     * @param nod Un obiect de tipul Nod.
     */
    public Nod(Nod nod) {
        this.nume = nod.nume;
        this.noduriVecine = new HashMap<>();
        this.noduriVecine = new HashMap<>(nod.noduriVecine);
    }

    /**
     * Returneaza numele nodului.
     * @return Un int care reprezinta numele nodului.
     */
    public int getNume() {
        return nume;
    }

    /**
     * Returneaza calea cea mai scurta pana la nodul curent.
     * @return O coada de prioritate cu nodurile pana la nodul curent.
     */
    public Queue<Nod> getCaleaCeaMaiScurta() {
        return caleaCeaMaiScurta;
    }

    /**
     * Seteaza calea cea mai scurta pana la nodul curent.
     * @param caleaCeaMaiScurta Coada de prioritate continand calea cea mai
     *                          scurta pana la nodul curent.
     */
    public void setCaleaCeaMaiScurta(Queue<Nod> caleaCeaMaiScurta) {
        this.caleaCeaMaiScurta = caleaCeaMaiScurta;
    }

    /**
     * Returneaza costul nodului de la sursa pana la el.
     * @return Un Integer reprezentand costul nodului.
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * Seteaza costul nodului.
     * @param cost Un Integer care contine costul nodului.
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * Returneaza mapa cu nodurile vecine si costurile acestora raportat la nodul curent.
     * @return O mapa ce cuprinde la cheie nodurile vecine si la valoare costul(drumul) dintre
     * vecini si nodul curent
     */
    public Map<Integer, Integer> getNoduriVecine() {
        return noduriVecine;
    }

    /**
     * Suprascrierea metodei compareTo pentru a efectua comparatia
     * in functie de cost
     * @param o Un obiect de tip Nod.
     * @return Un int (-1, 0 ,1) in functie de rezultat.
     */
    @Override
    public int compareTo(Nod o) {
        return Double.compare(this.getCost(), o.getCost());
    }
}
