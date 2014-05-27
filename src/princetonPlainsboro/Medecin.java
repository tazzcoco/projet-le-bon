package princetonPlainsboro;

class Medecin {

    private String nom;
    private String prenom;
    private String specialite;
    private String numTel;
    private String mdp;

    public Medecin(String nom, String prenom, String specialite) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
    }

    public Medecin(String nom, String prenom, String specialite, String numTel, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.numTel = numTel;
        this.mdp = mdp;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String toString() {
        return "Dr " + prenom + " " + nom + ", " + specialite;
    }

    public boolean equals(Object o) {
        if (o instanceof Medecin) {
            Medecin p = (Medecin) o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        } else {
            return false;
        }
    }
}
