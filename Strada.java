/**
 * Clasa care descrie o strada.
 */
public class Strada {
    private int costSuplimentar;
    private int limitaGabarit;
    private int pctStart;
    private int pctDestinatie;
    private int[] costuriAmbuteiaje; // vector ce retine costurile aferenete ambuteiajelor de pe o strada
    private int contor = 0; // var pentru a tine evidenta a cate ambuteiaje au aparut

    /**
     * Constructor fara parametrii pentru initializarea
     * unui obiect de tipul strada.
     */
    public Strada() {
    }

    /**
     * Constructor cu parametrii care seteaza atributele clasei.
     * @param pctStart Nodul(punctul) de la care incepe strada.
     * @param pctDestinatie Nodul la care ajunge strada.
     * @param costSuplimentar Costul strazii respective.
     * @param limitaGabarit Gabaritul maxim permis pe strada respectiva.
     * De asemenea am initializat un vector pentru ambuteiajele de pe strada
     */
    public Strada(int pctStart, int pctDestinatie, int costSuplimentar, int limitaGabarit) {
        this.costSuplimentar = costSuplimentar;
        this.limitaGabarit = limitaGabarit;
        this.pctStart = pctStart;
        this.pctDestinatie = pctDestinatie;
        this.costuriAmbuteiaje = new int[200];
    }

    /**
     * Constructorul cu parametru de tip Strada ce seteaza
     * atributele clasei folosinduse de strada, dat ca parametru.
     * @param strada Un obiect de tipul Strada
     */
    public Strada(Strada strada) {
        this.costSuplimentar = strada.costSuplimentar;
        this.limitaGabarit = strada.limitaGabarit;
        this.pctStart = strada.pctStart;
        this.pctDestinatie = strada.pctDestinatie;
        this.costuriAmbuteiaje = new int[200];
        this.contor = strada.contor;
        for (int i = 0; i < contor; i++) {
            this.costuriAmbuteiaje[i] = strada.costuriAmbuteiaje[i];
        }
    }

    /**
     * Adauga in vectorul de ambuteiaje al strazii, costul unui nou ambuteiaj .
     * @param cost Un int ce contine costul dat de un ambuteiaj de pe strada
     *             pe care ne aflam.
     */
    public void addCost(int cost) {
        costuriAmbuteiaje[contor] = cost;
        contor++;
    }

    /**
     * Returneaza vectorul ce contine costurile
     * pentru diferitele tipuri de ambuteiaje.
     * @return Un vector de int-uri reprezentand costuri ale ambuteiajelor.
     */
    public int[] getCosturiAmbuteiaje() {
        return costuriAmbuteiaje;
    }

    /**
     * Returneaza costul strazii.
     * @return Un int care reprezinta costul strazii.
     */
    public int getCostSuplimentar() {
        return costSuplimentar;
    }

    /**
     * Seteaza costul strazii.
     * @param costSuplimentar Costul pe care vrem sa il aiba strada.
     */
    public void setCostSuplimentar(int costSuplimentar) {
        this.costSuplimentar = costSuplimentar;
    }

    /**
     * Returneaza gabaritul maxim al strazii.
     * @return Un int care reprezinta gabaritul de pe strada curenta.
     */
    public int getLimitaGabarit() {
        return limitaGabarit;
    }

    /**
     * Seteaza gabaritul strazii.
     * @param limitaGabarit Un int reprezentand gabaritul pe care
     *                      vrem sa il aiba srtada.
     */
    public void setLimitaGabarit(int limitaGabarit) {
        this.limitaGabarit = limitaGabarit;
    }

    /**
     * Returneaza Nodul(punctul) de inceput al strazii.
     * @return Un int reprezentand nodul de inceput pentru strada.
     */
    public int getPctStart() {
        return pctStart;
    }

    /**
     * Returneaza Nodul(punctul) destinatie al strazii.
     * @return Un int reprezentand nodul destinatie pentru strada.
     */
    public int getPctDestinatie() {
        return pctDestinatie;
    }

    /**
     * Returneaza numarul ambuteiajelor de pe strada.
     * @return Un int reprezentand numarul de ambuteiaje de pe strada.
     */
    public int getContor() {
        return contor;
    }
}
