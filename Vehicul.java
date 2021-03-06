/**
 * Clasa abstracta care descrie un vehicul prin
 * tipul acestuia, gabarit si cost.
 */
public abstract class Vehicul {
    private String tip;
    private int gabarit;
    private int cost;

    /**
     * Constructorul fara parametri al clasei Vehicul
     */
    public Vehicul() {
    }

    /**
     * Constructorul cu parametri care seteaza atributele clasei.
     * @param tip Un string reprezentand categoria de vehicul.
     * @param gabarit Un int reprezentand gabaritul vehiculului.
     * @param cost Un int reprezentand costul asociat vehiculului.
     */
    public Vehicul(String tip, int gabarit, int cost) {
        this.tip = tip;
        this.gabarit = gabarit;
        this.cost = cost;
    }

    /**
     * Returneaza tipul de vehicul.
     * @return Un String reprezentand litera
     * corespunzatoare vehicului.
     */
    public String getTip() {
        return tip;
    }

    /**
     * Seteaza tipul de vehicul.
     * @param tip Un string reprezentand vehiculul ce urmeaza sa fie setat.
     */
    public void setTip(String tip) {
        this.tip = tip;
    }

    /**
     * Returneaza gabaritul pe care il are vehiculul.
     * @return Un int reprezentand gabaritul vehicolului.
     */
    public int getGabarit() {
        return gabarit;
    }

    /**
     * Seteaza gabaritul unui vehicul.
     * @param gabarit Un int reprezentand gabaritul ce
     * urmeaza sa fie setat pentru vehicul.
     */
    public void setGabarit(int gabarit) {
        this.gabarit = gabarit;
    }

    /**
     * Returneaza costul pe care il are un vehicul.
     * @return Un int reprezentand costul vehicolului.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Seteaza costul unui vehicul.
     * @param cost Un int reprezentand costul ce
     * urmeaza sa fie setat.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
}
