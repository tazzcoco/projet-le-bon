package princetonPlainsboro;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DossierMedical {

    //attributs
    private DefaultListModel<FicheDeSoins> fiches; //liste des fiches de soins 
    private DefaultListModel<Patient> patients; //liste des patients
    private DefaultListModel<Medecin> medecins; //liste des médecins

    public DefaultListModel<FicheDeSoins> getFiches() {
        return fiches;
    }

    public DefaultListModel<Patient> getPatients() {
        return patients;
    }

    public DefaultListModel<Medecin> getMedecins() {
        return medecins;
    }

    //constructeur
    public DossierMedical() {
        fiches = new DefaultListModel();
        patients = new DefaultListModel();
        medecins = new DefaultListModel();
    }

    public void ajouterFiche(FicheDeSoins fiche) {
        fiches.addElement(fiche);
        for (int i = 0; i < fiches.size(); i++) {
            if (!medecins.contains(fiche.getMedecin())) {
                medecins.addElement(fiche.getMedecin());
            }
            if (!patients.contains(fiche.getPatient())) {
                patients.addElement(fiche.getPatient());
            }
        }
    }

    public void afficher() {
        System.out.println("Dossier medical informatisé :");
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
        List<FicheDeSoins> copieFiches = new ArrayList<FicheDeSoins>();

        //on remplit copieFiches avec les éléments de la DefaultListModel fiches
        for (int i = 0; i < fiches.size(); i++) {
            copieFiches.add(fiches.get(i));
        }
        System.out.println("Listes des fiches rangées par date croissante :");
        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int j = 1; j < copieFiches.size(); j++) {
                FicheDeSoins f2 = copieFiches.get(j);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = j;
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
        List<FicheDeSoins> copieFiches = new ArrayList<FicheDeSoins>();

        //on remplit copieFiches avec les éléments de la DefaultListModel fiches
        for (int i = 0; i < fiches.size(); i++) {
            copieFiches.add(fiches.get(i));
        }

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
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }

    public void afficherListePatients() {
        //on créé une liste de patient
        ArrayList<Patient> lp = new ArrayList<>();
        System.out.println("\nListe des patients :");
        //on la remplit et on affiche chaque patient sous la forme : NOM Prenom
        for (int i = 0; i < fiches.size(); i++) {
            if (!lp.contains(fiches.get(i).getPatient())) {
                lp.add(fiches.get(i).getPatient());
                System.out.println(fiches.get(i).getPatient().getNom().toUpperCase() + " " + fiches.get(i).getPatient().getPrenom());
            }//end if
        }//end for
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
            }//end if
        }//end for
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
                }//end if
            } else {
                if (d1.compareTo(d2) > 0) {
                    if ((fiches.get(i).getDate().compareTo(d1) < 0) && (fiches.get(i).getDate().compareTo(d2) > 0)) {
                        lf.add(fiches.get(i));
                    }//end if
                }//end if
            }//end if/else
        }//end for
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
                }//end if
            }//end for
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
        //creation des JTextFields pour récupérer les renseignements du patient et du JPanel
        JTextField fieldPrenom = new JTextField(5);
        JTextField fieldNom = new JTextField(7);
        JTextField fieldBirth1 = new JTextField(3);
        JTextField fieldBirth2 = new JTextField(3);
        JTextField fieldBirth3 = new JTextField(7);
        JTextField fieldNumSecu = new JTextField(5);
        JTextField fieldAdresse = new JTextField(10);
        //création des JLabels
        JLabel labelNom = new JLabel("Nom :");
        JLabel labelPrenom = new JLabel("Prénom :");
        JLabel labelBirth = new JLabel("Date de naissance :");
        JLabel labelAdresse = new JLabel("Adresse :");
        JLabel labelNumSecu = new JLabel("Numéro de sécurité sociale (13 chiffres) :");
        //création du JPanel
        JPanel myPanel = new JPanel();

        //création d'un panel pour les 3 JTextField de la date de naissance
        JPanel panelBirth = new JPanel();
        panelBirth.setLayout(new FlowLayout());
        panelBirth.add(fieldBirth1);
        panelBirth.add(fieldBirth2);
        panelBirth.add(fieldBirth3);

        //organisation de la fenêtre d'entrée utilisateur
        myPanel.setLayout(new GridLayout(5, 2));
        myPanel.add(labelNom);
        myPanel.add(fieldNom);
        myPanel.add(labelPrenom);
        myPanel.add(fieldPrenom);
        myPanel.add(labelBirth);
        myPanel.add(panelBirth);
        myPanel.add(labelAdresse);
        myPanel.add(fieldAdresse);
        myPanel.add(labelNumSecu);
        myPanel.add(fieldNumSecu);

        //instanciation de la fenêtre d'entrée utilisateur
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Veuillez entrer les détails du patient :", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Patient p = new Patient(fieldNom.getText(), fieldPrenom.getText(), new Date(Integer.parseInt(fieldBirth1.getText()), Integer.parseInt(fieldBirth2.getText()), Integer.parseInt(fieldBirth3.getText())), Long.parseLong(fieldNumSecu.getText()), fieldAdresse.getText());
            patients.addElement(p);
            System.out.println("Patient ajouté !");
        }//end if
    }

    public void retirerPatient(Patient p) {
        patients.removeElement(p);
        p = null;
    }
    //coucou
}
