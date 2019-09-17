package sk.stuba.fei.oop;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Miesto extends Vrchol {
    Map<Integer,ArrayList<Integer>> obvod; //kluc je x suradnica, ktora bude mat pridelenu dve y body
    int stredX;
    int stredY;

    public void vytvor()
    {
        tvar=new Ellipse2D.Double(getxSuradnica(),getySuradnica(),getRozmer(),getRozmer());
    }
    public void vykresli(Graphics2D g)
    {

        //g.drawOval(getxSuradnica(),getySuradnica(),getRozmer(),getRozmer());
        ((Ellipse2D)tvar).setFrame(getxSuradnica(),getySuradnica(),getRozmer(),getRozmer());
        g.draw(tvar);
        //g.draw(kruh);
        g.drawString(Integer.toString(getToken()),getxSuradnica()+getRozmer()/2,getySuradnica()+getRozmer()/2);
    }

    public void vypocitajObvod()
    {
        obvod=new HashMap<Integer, ArrayList<Integer> >();
        stredX=this.getxSuradnica()+20;
        stredY=this.getySuradnica()+20;
        int Xdlzka=20;

        for(int i=getxSuradnica();i<stredX;i++)//pocitame kraje druheho a tretieho kvadrantu kruznice
        {
            obvod.put(i,new ArrayList<Integer>(2));
            obvod.get(i).add(getySuradnica()+20-(int) Math.sqrt(20*20-Xdlzka*Xdlzka));
            obvod.get(i).add((int) Math.sqrt(20*20-Xdlzka*Xdlzka)+getySuradnica()+20);
            Xdlzka--;

        }
        Xdlzka=0;
        /* 1 a 4 kvadrant */
       for (int i=stredX;i<stredX+20;i++)
        {
            obvod.put(i,new ArrayList<Integer>(2));
            obvod.get(i).add(getySuradnica()+20-(int) Math.sqrt(20*20-Xdlzka*Xdlzka));
            obvod.get(i).add(getySuradnica()+20+(int) Math.sqrt(20*20-Xdlzka*Xdlzka));
            Xdlzka++;
        }

    }

    Miesto()
    {
        super.setRozmer(40);

    }

    @Override
    public boolean spustitelnost(List<Miesto> miesto, List<Hrana> hrana) {
        return false;
    }

    @Override
    public boolean radiusVrchol(int xKlik, int yKlik) { //kruh

        if(obvod.get(xKlik)==null)
        {
            return false;
        }
        /*System.out.println("nasla sa x suradnica");
        System.out.println("horna hranica Y: "+obvod.get(xKlik).get(0));
        System.out*/
        if(yKlik>obvod.get(xKlik).get(0) && yKlik<obvod.get(xKlik).get(1)) {
            return true;
        }
        else return false;

    }
}
