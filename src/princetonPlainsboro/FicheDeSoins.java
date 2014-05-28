package princetonPlainsboro;

import java.text.DecimalFormat;
import java.util.Vector;

class FicheDeSoins {

    //attributs
    private Patient patient;
    private Medecin medecin;
    private Date date;
    private Vector<Acte> actes;       // contient des objets de classe 'Acte'

    //constructeur
    public FicheDeSoins(Patient patient, Medecin medecin, Date date) {
        this.patient = patient;
        this.medecin = medecin;
        this.date = date;
        actes = new Vector<Acte>();   // liste vide
    }

    //accesseurs
    public Patient getPatient() {
        return patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public Date getDate() {
        return date;
    }

    public void ajouterActe(Acte acte) {
        actes.add(acte);
    }

    public void ajouterActe(Code code, int coefficient) {
        Acte acte = new Acte(code, coefficient);
        actes.add(acte);
    }

    //affichage d'une fiche de soins
    public void afficher() {
        DecimalFormat dec = new DecimalFormat("0.00");
        System.out.println("Fiche de soins du " + date.toString());
        System.out.println("- medecin : " + medecin.toString());
        System.out.println("- patient : " + patient.toString());
        System.out.println("- actes medicaux :");
        for (int i = 0; i < actes.size(); i++) {
            Acte a = actes.get(i);
            System.out.println("    > " + a.toString());
        }
        System.out.println("Cout total des soins : " + dec.format(coutTotal()));
        System.out.println("------------------------");
    }

    //calcul de cout total d'une fiche
    public double coutTotal() {
        double total = 0;
        for (int i = 0; i < actes.size(); i++) {
            Acte a = actes.get(i);
            total += a.cout();
        }
        return total;
    }
}
