package princetonPlainsboro;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class DossierMedical {

    private List<FicheDeSoins> fiches;
    private List<Patient> patients;
    private List<Medecin> medecins;

    public DossierMedical() {
        fiches = new Vector<FicheDeSoins>();  // liste vide
    }

    public void ajouterFiche(FicheDeSoins fiche) {
        fiches.add(fiche);
        patients.add(fiche.getPatient());
        medecins.add(fiche.getMedecin());
    }

    public void afficher() {
        System.out.println("Dossier medical informatise :");
        System.out.println("-----------------------------");
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            f.afficher();
            // pour separer les fiches de soins :
            System.out.println("--------------------------------------");
        }
    }

    public double coutPatient(Patient p) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (p.equals(f.getPatient())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutMedecin(Medecin m) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutSpecialite(String specialite) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (specialite.equals(f.getMedecin().getSpecialite())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public void afficherListePatients(Medecin m) {
        System.out.println("> liste des patients du " + m.toString() + " :");
        Vector<Patient> liste = new Vector<Patient>();
        // 'liste' contient tous les patients deja affiches
        // --> ceci permet de ne pas reafficher un patient deja affiche
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                Patient p = f.getPatient();
                if (!liste.contains(p)) {
                    System.out.println(" - " + p.toString());
                    liste.add(p);
                }
            }
        }
    }

    public int nombreFichesIntervalle(Date d1, Date d2) {
        int n = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Date d = f.getDate();
            if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
                n++;
            }
        }
        return n;
    }

    public void trierDates() {
        Vector<FicheDeSoins> copieFiches = new Vector<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }

    // tri generique :
    public void trier(ComparaisonFiches c) {
        Vector<FicheDeSoins> copieFiches = new Vector<FicheDeSoins>(fiches);
        DecimalFormat dec = new DecimalFormat("0.00");

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (c.comparer(f2, f1) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("Cout total des soins : " + dec.format(f1.coutTotal()));
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }

    public void afficherListePatients() {
        //on créé une liste de patient
        ArrayList<Patient> lp = new ArrayList<Patient>();
        System.out.println("\nListe des patients :");
        //on la remplit et on affiche chaque patient sous la forme : NOM Prenom
        for (int i = 0; i < fiches.size(); i++) {
            if (!lp.contains(fiches.get(i).getPatient())) {
                lp.add(fiches.get(i).getPatient());
                System.out.println(fiches.get(i).getPatient().getNom().toUpperCase() + " " + fiches.get(i).getPatient().getPrenom());
            }
        }
    }

    public void afficherListeMedecins() {
        //même principe que pour afficherListePatients, on créé une liste de médecins en premier
        ArrayList<Medecin> lm = new ArrayList<Medecin>();
        System.out.println("\nListe des médecins :");
        //on la remplit et on affiche chaque médecin sous la forme : NOM Prenom
        for (int i = 0; i < fiches.size(); i++) {
            if (!lm.contains(fiches.get(i).getMedecin())) {
                lm.add(fiches.get(i).getMedecin());
                System.out.println(fiches.get(i).getMedecin().getNom().toUpperCase() + " " + fiches.get(i).getMedecin().getPrenom());
            }
        }
    }

    public void afficherFichesEntre(Date d1, Date d2) {
        //creation d'une liste de fiche de soins dont les dates sont comprises entre d1 et d2
        List<FicheDeSoins> lf = new ArrayList<FicheDeSoins>();
        System.out.println("Liste des fiches de soins entre le " + d1 + " et le " + d2 + " : \n");
        //on remplit la liste lf
        for (int i = 0; i < fiches.size(); i++) {
            if (d1.compareTo(d2) < 0) {
                if ((fiches.get(i).getDate().compareTo(d1) > 0) && (fiches.get(i).getDate().compareTo(d2) < 0)) {
                    lf.add(fiches.get(i));
                }
            } else {
                if (d1.compareTo(d2) > 0) {
                    if ((fiches.get(i).getDate().compareTo(d1) < 0) && (fiches.get(i).getDate().compareTo(d2) > 0)) {
                        lf.add(fiches.get(i));
                    }
                }
            }
        }
        //on utilise le même code que dans trierDates, appliqué ici à la liste lf et non au dossier médical entier.
        while (!lf.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = lf.get(imin);
            for (int j = 1; j < lf.size(); j++) {
                FicheDeSoins f2 = lf.get(j);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = j;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            lf.remove(imin);
        }
    }

    public void afficherListeCoutCroissant() {
        trier(new ComparaisonFichesCouts());
    }

    public void ajouterPatient() {
        JTextField FieldPrenom = new JTextField(5);
        JTextField FieldNom = new JTextField(7);
        JTextField FieldBirth1 = new JTextField(2);
        JTextField FieldBirth2 = new JTextField(2);
        JTextField FieldBirth3 = new JTextField(4);
        JTextField FieldNumSecu = new JTextField(5);
        JTextField FieldAdresse = new JTextField(10);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Nom :"));
        myPanel.add(FieldNom);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer        
        myPanel.add(new JLabel("Prénom :"));
        myPanel.add(FieldPrenom);
        myPanel.add(Box.createVerticalStrut(15)); // a spacer
        myPanel.add(new JLabel("Date de Naissance :"));
        myPanel.add(FieldBirth1);
        myPanel.add(Box.createVerticalStrut(3)); // a spacer        
        myPanel.add(FieldBirth2);
        myPanel.add(Box.createVerticalStrut(3)); // a spacer        
        myPanel.add(FieldBirth3);
        myPanel.add(Box.createVerticalStrut(15)); // a spacer
        myPanel.add(new JLabel("Adresse :"));
        myPanel.add(FieldAdresse);
        myPanel.add(Box.createVerticalStrut(15)); // a spacer
        myPanel.add(new JLabel("Numéro de sécurité sociale :"));
        myPanel.add(FieldNumSecu);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Veuillez entrer les détails du patient :", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Patient p = new Patient(FieldNom.getText(), FieldPrenom.getText(), new Date(Integer.parseInt(FieldBirth1.getText()), Integer.parseInt(FieldBirth2.getText()), Integer.parseInt(FieldBirth3.getText())), Integer.parseInt(FieldNumSecu.getText()), FieldAdresse.getText());
            patients.add(p);
            System.out.println("Patient ajouté !");
        }
    }

    public void retirerPatient(Patient p) {
        patients.remove(p);
        p = null;
    }
    //coucou
}
