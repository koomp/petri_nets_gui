package sk.stuba.fei.oop;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.List;

public class Hrana {
    private int vstupID;
    private int vystupID;
    private int nasobnost;
    int x,y,xK,yK,id;
    Line2D ciara;
    public void vytvor(int x, int y, int xK,int yK,int id)
    {
        this.x=x+20; this.y=y+20; this.xK=xK+20; this.yK=yK+20; this.id=id;
        ciara=new Line2D.Double(this.x,this.y,this.xK,this.yK);
    }
    public void update()
    {
        System.out.println("x "+x+" y "+y);
    }

    public void vykresli(Graphics g1)
    {
        Graphics2D g=(Graphics2D) g1;
        //g.drawLine(x,y,xK,yK);
        ciara.setLine(x,y,xK,yK);
        g.draw(ciara);
        Line2D.Double line= new Line2D.Double(x,y,xK,yK);
        AffineTransform tx = new AffineTransform();
        Polygon arrowHead = new Polygon();
        arrowHead.addPoint( 0,5);
        arrowHead.addPoint( -5, -5);
        arrowHead.addPoint( 5,-5);

        tx.setToIdentity();
        double angle = Math.atan2(line.y2-line.y1, line.x2-line.x1);
        tx.translate(line.x2, line.y2);
        tx.rotate((angle-Math.PI/2d));
        g.setTransform(tx);
        g.fill(arrowHead);
        g.setColor(Color.black);
        g.dispose();
    }


    public int getVstupID() {
        return vstupID;
    }

    public void setVstupID(int vstupID) {
        this.vstupID = vstupID;
    }

    public int getVystupID() {
        return vystupID;
    }

    public void setVystupID(int vystupID) {
        this.vystupID = vystupID;
    }

    public int getNasobnost() {
        return nasobnost;
    }

    public void setNasobnost(int nasobnost) {
        this.nasobnost = nasobnost;
    }
}
