package sk.stuba.fei.oop;

import com.sun.deploy.util.BlackList;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.Map;

public class Platno extends Canvas implements MouseListener,MouseMotionListener{
    PN petri;
    int hranaFazaVykreslovanie, tmpHranaID,indexTmpHrana;
    boolean pridajToken;
    boolean zoberToken;
    boolean pridaMiesto;
    boolean pridajPrechod;
    boolean pridajHrana;
    boolean presun;
    boolean odstran;
    boolean spustaj;
    boolean klikVedla;

    @Override
    public void paint(Graphics g1) {
        Graphics2D g=(Graphics2D)g1;
        super.paint(g1);
        try
        {
            for (Map.Entry<Integer,Vrchol>vrchol:petri.vrchol.entrySet())
            {
                vrchol.getValue().vykresli(g);
            }
            for(Hrana hrana:petri.hrana)
            {
                /*System.out.println(hrana.x);
                System.out.println(hrana.y);*/
                hrana.vykresli(getGraphics());
            }
        }

        catch (Exception e)
        {
            e.getMessage();
        }
    }

    public void mouseDragged(MouseEvent e) {
        if(presun) {
            for (Map.Entry<Integer, Vrchol> vrchol : petri.vrchol.entrySet()) {
                if (vrchol.getValue().tvar.contains(e.getX(), e.getY())) {
                    vrchol.getValue().setxSuradnica(e.getX()-20);
                    vrchol.getValue().setySuradnica(e.getY()-20);
                    for(Hrana hrana:petri.hrana)
                    {
                        if(hrana.getVystupID()==vrchol.getKey())
                        {
                            hrana.xK=e.getX();
                            hrana.yK=e.getY();
                        }
                        if(hrana.getVstupID()==vrchol.getKey())
                        {
                            hrana.x=e.getX();
                            hrana.y=e.getY();
                        }
                    }
                    repaint();
                    break;
                }
            }
        }

    }

    public void mouseMoved(MouseEvent e) {
        if(hranaFazaVykreslovanie==1)
        {
            for (int i=0; i<petri.hrana.size();i++)
            {
                if(petri.hrana.get(i).id==petri.hranaID)
                {
                    petri.hrana.get(i).xK=e.getX();
                    petri.hrana.get(i).yK=e.getY();
                    //petri.hrana.get(i).update();
                    repaint();
                    break;
                }
            }

        }
    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {
        if(spustaj)
        {
            for(Map.Entry<Integer,Vrchol> vrchol:petri.vrchol.entrySet())
            {
                if(vrchol.getValue().tvar.contains(e.getX(),e.getY()) && vrchol.getValue() instanceof Prechod && vrchol.getValue().spustitelnost)
                {
                    petri.spustiPrechod(vrchol.getValue().getID());
                    petri.nastavPrechod();
                }
            }
        }
        if(zoberToken)
        {
            for(Map.Entry<Integer,Vrchol>vrchol:petri.vrchol.entrySet())
            {
                if(vrchol.getValue().tvar.contains(e.getX(),e.getY()) && vrchol.getValue() instanceof Miesto && vrchol.getValue().getToken()>0) {
                    vrchol.getValue().setToken(vrchol.getValue().getToken() - 1);
                    petri.nastavPrechod();
                }


            }
        }
        if(pridajToken)
        {
            for(Map.Entry<Integer,Vrchol>vrchol:petri.vrchol.entrySet())
            {
                if(vrchol.getValue().tvar.contains(e.getX(),e.getY()) && vrchol.getValue() instanceof Miesto) {
                    vrchol.getValue().setToken(vrchol.getValue().getToken() + 1);
                    petri.nastavPrechod();
                    repaint();
                }
            }
        }
        if(pridaMiesto)
        {
            pridajMiestoPlatno(e.getX(),e.getY());
        }
        if(pridajPrechod)
        {
            pridajPrechodPlatno(e.getX(),e.getY());
        }

        repaint();
    }

    public void mouseEntered(MouseEvent e)
    {

    }


    public void pridajMiestoPlatno(int x, int y)
    {
        petri.pridajMiesto(x,y);
        nacitajPN(petri);
    }

    public void pridajPrechodPlatno(int x, int y)
    {
        petri.pridajPrechod(x,y);
        nacitajPN(petri);
    }


    public void mouseClicked(MouseEvent e)
    {
        if (odstran)
        {
            boolean odstranenieUspesne=false;
            for(Map.Entry<Integer,Vrchol> vrchol:petri.vrchol.entrySet())
            {
                if(vrchol.getValue().tvar.contains(e.getX(),e.getY()))
                {
                    int id=vrchol.getValue().getID();
                    for (int i=0;i<petri.hrana.size();i++)
                    {
                        if(petri.hrana.get(i).getVstupID()==id || petri.hrana.get(i).getVystupID()==id)
                        {
                            petri.hrana.remove(i);
                            i=0;
                        }
                    }
                    petri.vrchol.remove(id);
                    petri.nastavPrechod();
                    repaint();
                    odstranenieUspesne=true;
                    break;
                }
            }
            if(odstranenieUspesne==false)
            {

                for (Hrana hrana:petri.hrana)
                {
                    if(hrana.ciara.intersects(e.getX()-20,e.getY()-20,20,20))
                    {
                        for (int i=0;i<petri.hrana.size();i++)
                        {
                            if(petri.hrana.get(i).id==hrana.id)
                            {
                                petri.hrana.remove(i);
                                petri.nastavPrechod();
                                repaint();
                                break;
                            }
                        }
                    }
                }
            }
        }
        if(pridajHrana)
        {
            for(Map.Entry<Integer,Vrchol> vrchol:petri.vrchol.entrySet())
            {

                if(vrchol.getValue().tvar.contains(e.getX(),e.getY()) && hranaFazaVykreslovanie==0)
                {
                    Hrana tmpHrana=new Hrana();
                    tmpHrana.setNasobnost(1);
                    tmpHrana.setVstupID(vrchol.getValue().getID());
                    tmpHrana.vytvor(vrchol.getValue().getxSuradnica(),vrchol.getValue().getySuradnica(),e.getX(),e.getY(),petri.hranaID);
                    petri.hranaID++;
                    tmpHrana.id=petri.hranaID;
                    tmpHranaID=petri.hranaID;
                    petri.hrana.add(tmpHrana);
                    indexTmpHrana=petri.hrana.indexOf(tmpHrana);
                    //System.out.println(b);
                    hranaFazaVykreslovanie=1;
                    klikVedla=false;
                    break;
                }

                if(vrchol.getValue().tvar.contains(e.getX(),e.getY()) && hranaFazaVykreslovanie==1 && ((vrchol.getValue() instanceof Miesto && petri.vrchol.get(petri.hrana.get(indexTmpHrana).getVstupID()) instanceof Prechod)|| (vrchol.getValue() instanceof Prechod && petri.vrchol.get(petri.hrana.get(indexTmpHrana).getVstupID()) instanceof Miesto)))
                {
                    for (int i=0; i<petri.hrana.size();i++)
                    {
                        if(petri.hrana.get(i).id==petri.hranaID)
                        {
                            petri.hrana.get(i).setVystupID(vrchol.getValue().getID());
                            petri.hrana.get(i).xK=vrchol.getValue().getxSuradnica()+20;
                            petri.hrana.get(i).yK=vrchol.getValue().getySuradnica()+20;
                            petri.nastavPrechod();
                            hranaFazaVykreslovanie=0;
                            repaint();
                            break;
                        }
                    }

                }
                klikVedla=true;
            }

            if(hranaFazaVykreslovanie==1 && klikVedla)
            {
                if(hranaFazaVykreslovanie==1)
                {
                    for (int i=0;i<petri.hrana.size();i++)
                    {
                        if (petri.hrana.get(i).id==petri.hranaID)
                        {
                            petri.hrana.remove(i);
                            break;
                        }
                    }
                    /*petri.hrana.remove(tmpHranaID);
                    petri.hranaID--;*/
                    hranaFazaVykreslovanie=0;
                    repaint();
                }
            }
        }

    }

    Platno()
    {
        spustaj=true;
        addMouseListener(this);
        addMouseMotionListener(this);
        petri=new PN();
        hranaFazaVykreslovanie=0;
    }

    public void nacitajPN(PN tmpPetri)
    {


    }


}
