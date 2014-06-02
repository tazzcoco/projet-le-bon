package princetonPlainsboro;

public class Acte {
    //attributs
    private Code code;
    private int coef;
    
    //constructeur
    public Acte(Code code, int coef) {
        this.code = code;
        this.coef = coef;
        }
    public Acte(){
        Code c1= Code.CS;
        this.code=c1;
        this.coef=1;
    }

    @Override
    public String toString() {
        return code.toString() + ", coefficient : " + coef;
        }

    public double cout() {
        return code.calculerCout(coef);
        }
    public String getCodeLibelle (){
        return code.getLibelle();
    }
    public Code getCode(){
        return code;
    }
    
    }
