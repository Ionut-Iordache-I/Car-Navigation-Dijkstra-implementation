import java.util.*;

/**
 * Clasa care descrie graful, denumita HartaOras
 */
public class HartaOras {

    Nod[] puncte; // declararea unui vector de puncte(noduri)
    Strada[] strazi; //declararea unui vector de strazi(muchii)
    private int countP = 0; // numarul de puncte
    private int countS = 0; // numarul de strazi

    /**
     * Constructor care initializeaza vectorii de puncte si strazi.
     * @param nrPuncte Un int reprezentand numarul de puncte din vector.
     * @param nrStrazi Un int reprezentand numarul de strazi din vector.
     */
    public HartaOras(int nrPuncte, int nrStrazi) {
        this.puncte = new Nod[nrPuncte];
        this.strazi = new Strada[nrStrazi];
    }

    /**
     * Constructor care creeaza un obiect de tipul HartaOras prin copierea
     * informatiilor din harta primita ca parametru.
     * @param harta Un obiect de tipul HartaOras.
     */
    public HartaOras(HartaOras harta) {
        this(harta.countP, harta.countS);
        this.countP = harta.countP;
        this.countS = harta.countS;
        for (int i = 0; i < countP; i++) {
            this.puncte[i] = new Nod(harta.puncte[i]);
        }
        for (int i = 0; i < countS; i++) {
            this.strazi[i] = new Strada(harta.strazi[i]);
        }
    }

    /**
     * Metoda care adauga o strada(muchie) in harta(graf).
     * @param pctStart Un int reprezentand punctul de inceput al strazii.
     * @param pctDest Un int reprezentand punctul destinatie al strazii.
     * @param cost Un int, reprezentand costul asociat strazii adaugate in harta.
     * @param size Un int, reprezentand gabaritul asociat strazii adaugate in harta.
     */
    public void addStreet(int pctStart, int pctDest, int cost, int size) {
        Nod start = null;
        Nod dest  = null;
        boolean existStartNod = false;
        boolean existDest = false;

        // verificam daca pctStart exista in vectorul de puncte, caz in care
        // nu il mai adaugam
        if (this.countP > 0) {
            for (int i = 0; i < countP; i++) {
                if (pctStart == puncte[i].getNume()) {
                    start = puncte[i];
                    existStartNod = true;
                }
            }
        }
        // daca nu exista in vectorul de puncte, il adaugam
        if(!existStartNod) {
            start = new Nod(pctStart);
            puncte[countP] = start;
            countP++;
        }

        // verificam daca pctDest exista in vectorul de puncte, caz in care
        // nu il mai adaugam
        if (this.countP > 0) {
            for (int i = 0; i < countP; i++) {
                if (pctDest == puncte[i].getNume()) {
                    dest = puncte[i];
                    existDest = true;
                }
            }
        }
        // daca nu exista in vectorul de puncte, il adaugam
        if(!existDest) {
            dest = new Nod(pctDest);
            puncte[countP] = dest;
            countP++;
        }

        // facem adaugarea in mapa de noduri vecine
        // avem strada care porneste din start si se opreste in dest
        // deci dest va fi un vecin al lui start
        start.getNoduriVecine().put(dest.getNume(), cost);

        // adaugam strada in vectorul de strazi si incrementam contorul countS
        this.strazi[countS] = new Strada(pctStart, pctDest, cost, size);
        countS++;
    }

    /**
     * Metoda care adauga o restrictie sub forma unui cost pe o strada.
     * @param type Un String reprezentand numele restrictiei adaugate.
     * @param pctStart Un int reprezentand punctul de inceput al strazii.
     * @param pctDest Un int reprezentand punctul destinatie al strazii.
     * @param cost Un int, reprezentand costul asociat restrictiei adaugate in harta.
     */
    public void addRestriction(String type, int pctStart, int pctDest, int cost) {
        for (int i = 0; i < countS; i++) {
            if(strazi[i].getPctStart() == pctStart && strazi[i].getPctDestinatie() == pctDest) {
                strazi[i].addCost(cost);
                break;
            }
        }
    }

    /**
     * Returneaza nodul(punctul) cautat din vectorul de puncte daca acesta
     * exista sau null in caz contrar.
     * @param pctStart Un int reprezentand punctul cautat in vector.
     * @return Un punct din vector sau null daca nu se afla in vector.
     */
    private Nod getNodeByName(int pctStart) {
        for (int i = 0; i < countP; i++) {
            if (this.puncte[i].getNume() == pctStart) {
                return this.puncte[i];
            }
        }
        return null;
    }

    /**
     * Returneaza strada din vectorul de strazi daca a gasit-o sau null in caz contrar.
     * @param pctStart Un int reprezentand inceputul strazii.
     * @param pctDest Un int reprezentand capatul strazii.
     * @return O strada sau null in cazul in care aceasta nu se
     * afla in vectorul de strazi.
     */
    private Strada getStradBYName(int pctStart, int pctDest) {

        for (int i = 0; i < countS; i++) {
            if (this.strazi[i].getPctStart() == pctStart && this.strazi[i].getPctDestinatie() == pctDest) {
                return this.strazi[i];
            }
        }
        return null;
    }

    /**
     * Metoda care returneaza punctele prin care trece drumul de la pctStart la pctDest si costul dintre acestea.
     * @param vehicle Un string reprezentand tipul de vehicul condus pe drumul de la pctStart la pctDest.
     * @param pctStart Un int reprezentand punctul de inceput al strazii.
     * @param pctDest Un int reprezentand punctul destinatie al strazii.
     * @return Intoarce un string reprezentand drumul de la pctStart la pctDest cat si costul dintre acestea.
     */
    public String drive(String vehicle, int pctStart, int pctDest) {
        // am tratat cazul in care pctStart si pctDest coincid
        if (pctDest == pctStart) {
            return "P" + pctStart + " " + "P" +  pctDest + " 0";
        }

        Vehicul v;
        if (vehicle.equals("b")) {
            v = new Bicicleta();
        } else
        if (vehicle.equals("m")) {
            v = new Motocicleta();
        } else
        if (vehicle.equals("a")) {
            v = new Autoturism();
        } else {
            v = new Camion();
        }
        //  Am creat obiectul graf folosind constructorul cu parametri,
        //  care face o copie a grafului actual in obiectul graf.
        HartaOras graf = new HartaOras(this);

        // coada de prioritate pentru nodurile care se afla in curs de parcurgere
        PriorityQueue<Nod> listaNoduriInParcurgere = new PriorityQueue<>();
        // lista pentru nodurile care au fost parcurse
        List<Nod> listaNoduriParcurse = new LinkedList<>();
        // adaug in coada de noduri in curs de parcurgere nodul de la care pornim
        // folosindu-ma de copia hartii
        Nod nodStart = graf.getNodeByName(pctStart);
        nodStart.setCost(0);
        listaNoduriInParcurgere.add(nodStart);

        while (!listaNoduriInParcurgere.isEmpty()) {
            // am extras din coada nodul de la care incepem verificarea
            Nod nodCurent = listaNoduriInParcurgere.poll();
            for (int nodVecinNumar : nodCurent.noduriVecine.keySet()) {
                //iau strada curenta
                Strada stradaActuala = graf.getStradaByPuncte(nodCurent.getNume(), nodVecinNumar);

                Nod nodVecin = graf.getNodeByName(nodVecinNumar);
                //calculez intreg costul pana la nodul vecin
                int costVecin = nodCurent.noduriVecine.get(nodVecinNumar) * v.getCost()
                        + graf.getCostStrada(stradaActuala);
                //verific daca autovehicolul condus poate merge pe strada actuala, daca nu, trec mai \
                //departe la urmatorul nod
                if (stradaActuala.getLimitaGabarit() < v.getGabarit()) {
                    continue;
                }

                //daca ajung la nodul start cumva, nu vreau sa schimb valoarea acestuia
                if (nodVecin.getNume() == pctStart) {
                    continue;
                }
                int costCurent = nodCurent.getCost();
                // verific daca exista un drum cu cost mai mic pana la nodul vecin
                // si in caz afirmativ modific costul nodului vecin
                // am pus un SAU pentru cazul in care este prima data cand intru in acest nod
                // si el initial are valoarea 0 pentru cost
                if (costCurent + costVecin < nodVecin.getCost()) {
                    nodVecin.setCost(costCurent + costVecin);
                    //imi creeaza o noua coada cu nodurile din coada deja existenta a nodului curent
                    // la care adauga nodul curent si o folosesc drept noua cale spre nodul vecin
                    Queue<Nod> copieCaleNodCurent = new LinkedList<>(nodCurent.getCaleaCeaMaiScurta());
                    copieCaleNodCurent.add(nodCurent);
                    nodVecin.setCaleaCeaMaiScurta(copieCaleNodCurent);

                }
                    // adaug nodul vecin in coada de noduri in curs de parcurgere
                if (!listaNoduriParcurse.contains(nodVecin)) {
                    listaNoduriInParcurgere.add(nodVecin);
                }

            }
            // pun nodul parcurs in lista de noduri parcurse
            if (!listaNoduriParcurse.contains(nodCurent))
            listaNoduriParcurse.add(nodCurent);

        }

        Nod dest = null;
        for (Nod nod : listaNoduriParcurse) {
            if (nod.getNume() == pctDest) {
                dest = nod;
            }
        }
        if (dest == null) {
            return "P" + pctStart + " " + "P" +  pctDest + " null";
        }

        // preiau calea cea mai scurta din graf catre nodul destinatie intr-o coada de prioritate
        Queue<Nod> caleaCeaMaiScurta = new LinkedList<>(dest.getCaleaCeaMaiScurta());
        // lista ce va fii populata cu strazile pana la destinatie
        List<Strada> straziParcurse = new LinkedList<>();

        // parcurgem calea cea mai scurta cat timp nu este goala
        while(!caleaCeaMaiScurta.isEmpty()) {
            // cat timp sunt cel putin 2 elem. in coada, punem in lista de strazi parcurse strada dintre
            // primele 2 noduri din coada de prioritate
            if (caleaCeaMaiScurta.size() > 1) {
                // primul nod din coada il extragem iar pe al doilea il copiem ca sa putem ulterior
                // forma urmatoarea strada pornind din el
                straziParcurse.add(graf.getStradBYName(caleaCeaMaiScurta.poll().getNume(), caleaCeaMaiScurta.peek().getNume()));
            } else {
                straziParcurse.add(graf.getStradBYName(caleaCeaMaiScurta.poll().getNume(), pctDest));
            }
        }

        //trebuie parcuser toate strazile sa verificam daca exista vreuna care are limita de gabarit mai mica decat gabaritul vehicului nostru
        //daca trece de verifc anterioara, calculez costul pt fiecare drum si returnez un string
        int costTotal = 0;
        String mesajFinal = "";
        for (Strada indexStrada : straziParcurse) {
            if (indexStrada.getLimitaGabarit() < v.getGabarit()) {
                return "P" + pctStart + " " + "P" + pctDest + " " + "null";
            }
            else {
                costTotal += indexStrada.getCostSuplimentar() * v.getCost();
                for (int i = 0; i < indexStrada.getContor(); i++) {
                    costTotal += indexStrada.getCosturiAmbuteiaje()[i];
                }
                mesajFinal = mesajFinal.concat("P" + indexStrada.getPctStart() + " ");
            }
        }
        return mesajFinal.concat("P" + pctDest + " " + costTotal);

    }

    /**
     * Returneaza o strada care are ca start si destinatie parametrii functiei
     * @param start inceput strada
     * @param dest final strada
     * @return o Strada sau null daca nu exista
     */
    public Strada getStradaByPuncte(int start, int dest) {
        for (Strada  strada : this.strazi) {
            if (strada.getPctStart() == start && strada.getPctDestinatie() == dest) {
                return strada;
            }
        }
        return null;
    }

    /**
     * Calculeaza costul ambuteiajelor a unei strazi date ca parametru
     * @param strada Strada pentru care se calculeaza
     * @return un int reprezentand costul total al ambuteiajelor strazii
     */
    public int getCostStrada(Strada strada) {
        int costtotal = 0;
                for (int cost : strada.getCosturiAmbuteiaje()){
                    costtotal+=cost;
                }

        return costtotal;
    }
}