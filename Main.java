import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clasa ce contine metoda main.
 */
public class Main {
    /**
     * Metoda in care se realizeaza citirea si scrierea in fisier.
     * @param args Argumente.
     * @throws IOException Arunca Exceptia IOException.
     */
    public static void main(String[] args) throws IOException {
        File text = new File("map.in");
        Scanner scnr = new Scanner(text);

        int nrStrazi = scnr.nextInt();
        int nrNoduri = scnr.nextInt();

        HartaOras harta = new HartaOras(nrNoduri, nrStrazi);

        scnr.nextLine();

        for (int i = 0; i < nrStrazi; i++) {
            String line = scnr.nextLine();
            String[] splited = line.split(" ");
            String temp;
            temp = splited[0].substring(1);
            int pctStart = Integer.parseInt(temp);
            temp = splited[1].substring(1);
            int pctDest = Integer.parseInt(temp);
            int cost = Integer.parseInt(splited[2]);
            int size = Integer.parseInt(splited[3]);
            harta.addStreet(pctStart,pctDest,cost,size);
        }

        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            String[] splited = line.split(" ");
            String comanda = splited[0];

            if (comanda.equals("accident") || comanda.equals("trafic") ||
                    comanda.equals("blocaj")) {
                String tipAmbuteiaj = comanda;
                String temp;
                temp = splited[1].substring(1);
                int pctStart = Integer.parseInt(temp);
                temp = splited[2].substring(1);
                int pctDest = Integer.parseInt(temp);
                int cost = Integer.parseInt(splited[3]);
                harta.addRestriction(tipAmbuteiaj, pctStart, pctDest, cost);
                continue;
            }

            if (comanda.equals("drive")) {
                String vehicle = splited[1];
                    String temp;
                    temp = splited[2].substring(1);
                    int pctStart = Integer.parseInt(temp);
                    temp = splited[3].substring(1);
                    int pctDest = Integer.parseInt(temp);
                    Bicicleta bicicleta = new Bicicleta();
                File out = new File("map.out");
                FileWriter fw = new FileWriter(out, true);
                fw.write( harta.drive(vehicle,pctStart,pctDest) + "\n");
                fw.close();
            }

        }

    }

}