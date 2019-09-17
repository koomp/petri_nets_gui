package sk.stuba.fei.oop;

import java.awt.*;
import java.util.List;

public abstract class Vrchol {
    private int xSuradnica;
    private int ySuradnica;
    private int ID;
    private int token;
    private int rozmer;
    boolean spustitelnost;
    Shape tvar;

    public abstract boolean spustitelnost(List<Miesto> miesto, List<Hrana> hrana);
    public abstract void vykresli(Graphics2D g);
    public abstract void vytvor();

    public int getRozmer() {
        return rozmer;
    }

    public void setRozmer(int rozmer) {
        this.rozmer = rozmer;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public abstract boolean radiusVrchol(int xKlik, int yKlik);

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getySuradnica() {
        return ySuradnica;
    }

    public void setySuradnica(int ySuradnica) {
        this.ySuradnica = ySuradnica;
    }

    public int getxSuradnica() {
        return xSuradnica;
    }

    public void setxSuradnica(int xSuradnica) {
        this.xSuradnica = xSuradnica;
    }
}
