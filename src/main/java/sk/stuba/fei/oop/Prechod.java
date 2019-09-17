package sk.stuba.fei.oop;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prechod extends Vrchol {

    Color c;
    public Prechod()
    {
        super.setRozmer(40);

    }

    public void vytvor()
    {
        tvar=new Rectangle2D.Double(getxSuradnica(),getySuradnica(),getRozmer(),getRozmer());
    }

    public void vykresli(Graphics2D g)
    {
        ((Rectangle2D)tvar).setRect(getxSuradnica(),getySuradnica(),getRozmer(),getRozmer());
        if(spustitelnost)
        {
            c=Color.green;
        }
        else {
            c=Color.red;
        }
        g.setColor(c);
        g.fill(tvar);
        g.setColor(Color.black);
    }
    @Override
    public boolean radiusVrchol(int xKlik, int yKlik) {

       return false;
    }

    public boolean spustitelnost(List<Miesto>miesto, List<Hrana> hrana)
    {
        for (Hrana h:hrana)
        {
            if(this.getID()==h.getVstupID())
            {
               for (Miesto m:miesto)
               {
                   if (m.getID()==h.getVystupID())
                   {
                       m.setToken(m.getToken()+h.getNasobnost());
                   }
               }
            }

            if(this.getID()==h.getVystupID())
            {
                for (Miesto m:miesto)
                {
                    if (m.getID()==h.getVstupID())
                    {
                        m.setToken(m.getToken()-h.getNasobnost());
                    }
                }
            }
        }

        for (Miesto m: miesto)
        {
            if (m.getToken()<0)
            {
                return false;
            }
        }
        return true;
    }
}
