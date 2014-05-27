package princetonPlainsboro;

class Patient {

    private String nom;
    private String prenom;
    private int numSecu;
    private String adresse;
    private Date dateNaissance;
    

    public Patient(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;        
    }

    public Patient(String nom, String prenom, Date dateNaissance, int numSecu, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.numSecu = numSecu;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
    }

    public String toString() {
        return prenom + " " + nom;
    }

    public boolean equals(Object o) {
        if (o instanceof Patient) {
            Patient p = (Patient) o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        } else {
            return false;
        }
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getNumSecu() {
        return numSecu;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void afficherDP() {

    }
}
