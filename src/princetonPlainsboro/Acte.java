package princetonPlainsboro;

class Acte {
    private Code code;
    private int coef;
    
    public Acte(Code code, int coef) {
        this.code = code;
        this.coef = coef;
        }
    //manon la pute
    @Override
    public String toString() {
        return code.toString() + ", coefficient : " + coef;
        }
    //manon la pute
    public double cout() {
        return code.calculerCout(coef);
        }
    }
