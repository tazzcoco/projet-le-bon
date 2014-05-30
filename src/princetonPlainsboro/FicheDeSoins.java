package princetonPlainsboro;

import java.text.DecimalFormat;
import java.util.Vector;

public class FicheDeSoins {

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
    
    public void supprimerActe(Acte acte){
        actes.remove(acte); 
    }

    //affichage d'une fiche de soins
    public String afficher() {
        DecimalFormat dec = new DecimalFormat("0.00");
        String s = new String();
        s += "\nFiche de soins du " + date.toString();
        s += "\n- medecin : " + medecin.toString();
        s += "\n- patient : " + patient.toString();
        s += "\n- actes medicaux :";
        for (int i = 0; i < actes.size(); i++) {
            Acte a = actes.get(i);
            s += "    > " + a.toString();
        }
        s += "\n> Cout total des soins : " + dec.format(coutTotal());
        s += "\n------------------------";
        return s;
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
