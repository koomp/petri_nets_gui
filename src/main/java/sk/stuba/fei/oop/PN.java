package sk.stuba.fei.oop;

import sk.stuba.fei.oop.generated.Arc;
import sk.stuba.fei.oop.generated.Document;
import sk.stuba.fei.oop.generated.Place;
import sk.stuba.fei.oop.generated.Transition;

import java.util.*;

public class PN {
    List<Hrana> hrana;
    Map<Integer, Vrchol> vrchol;
    int id=0,hranaID=0;
    public void pridajPrechod(int x, int y)
    {
        try {
        id++;
        System.out.println(id);
        vrchol.put(id, new Prechod());
        vrchol.get(id).setID(id);
        vrchol.get(id).setxSuradnica(x-20);
        vrchol.get(id).setySuradnica(y-20);
        vrchol.get(id).setRozmer(40);
        vrchol.get(id).vytvor();
        vrchol.get(id).spustitelnost=true;
    }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void pridajMiesto (int x, int y)
    {
        try {
            id++;
            System.out.println(id);
            vrchol.put(id, new Miesto());
            vrchol.get(id).setID(id);
            vrchol.get(id).setxSuradnica(x-20);
            vrchol.get(id).setySuradnica(y-20);
            vrchol.get(id).setRozmer(40);
            vrchol.get(id).vytvor();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void spustiPrechod(int id)
    {
        for (Hrana h:hrana)
        {
            if (h.getVstupID()==id)
            {
                vrchol.get(h.getVystupID()).setToken(vrchol.get(h.getVystupID()).getToken()+h.getNasobnost());
            }

            if (h.getVystupID()==id)
            {
                vrchol.get(h.getVstupID()).setToken(vrchol.get(h.getVstupID()).getToken()-h.getNasobnost());
            }
        }
    }
    public void nastavPrechod()
    {
        for (Map.Entry<Integer,Vrchol> map : vrchol.entrySet())
        {

            List<Miesto> miesto=new LinkedList<Miesto>();
            {
                for (Map.Entry<Integer,Vrchol> mapMiesto:vrchol.entrySet())
                {
                    if (mapMiesto.getValue() instanceof Miesto)
                    {
                        Miesto tmpMiesto=new Miesto();
                        tmpMiesto.setToken(mapMiesto.getValue().getToken());
                        tmpMiesto.setID(mapMiesto.getValue().getID());
                        miesto.add(tmpMiesto);
                    }
                }
            }
            //System.out.println(map.getKey());
            if(map.getValue() instanceof Prechod)
            {
                if (map.getValue().spustitelnost(miesto,hrana)==false)map.getValue().spustitelnost=false;
                else map.getValue().spustitelnost=true;
                //System.out.println("Spustil som sa");

            }
        }
    }
    public PN()
    {
        hrana=new ArrayList<Hrana>();
        vrchol=new HashMap<Integer, Vrchol>();
    }

    public void nacitajPNzDokumentu(Document document)
    {


        //nacitaj prechody//
        List<Transition> prechodyZdokumentu=document.getTransition();
        for(Transition i: prechodyZdokumentu)
        {
            if(id<i.getId())id=i.getId();
            Prechod tmpPrechod=new Prechod();
            tmpPrechod.setxSuradnica(i.getX()/2);
            tmpPrechod.setySuradnica(i.getY()/2);
            tmpPrechod.setID((int)i.getId());
            tmpPrechod.vytvor();
            vrchol.put((int)i.getId(),tmpPrechod);
        }

        //nacitaj miesta//
        List<Place> miestaZdokumentu=document.getPlace();
        for(Place i: miestaZdokumentu)
        {
            if(id<i.getId())id=i.getId();
            Miesto tmpMiesto=new Miesto();
            tmpMiesto.setxSuradnica(i.getX()/2);
            tmpMiesto.setySuradnica(i.getY()/2);
            tmpMiesto.setID((int)i.getId());
            tmpMiesto.setToken(i.getTokens());
            tmpMiesto.vypocitajObvod();
            tmpMiesto.vytvor();
            vrchol.put((int)i.getId(),tmpMiesto);
        }

        //nacitaj hrany//
        List<Arc> hranyZdokumentu=document.getArc();
        for(Arc i: hranyZdokumentu)
        {
            //System.out.println(i.getSourceId());
            int x=vrchol.get((int)i.getSourceId()).getxSuradnica();
            int y=vrchol.get((int)i.getSourceId()).getySuradnica();
            int kX=vrchol.get((int)i.getDestinationId()).getxSuradnica();
            int kY=vrchol.get((int)i.getDestinationId()).getySuradnica();
            System.out.println(x);
            System.out.println(y);
            System.out.println(kX);
            System.out.println(kY);
            if(hranaID<i.getId())hranaID=i.getId();
            Hrana tmpHrana=new Hrana();
            tmpHrana.setVstupID(i.getSourceId());
            tmpHrana.setVystupID(i.getDestinationId());
            tmpHrana.setNasobnost(i.getMultiplicity());
            //System.out.println("Spustil som sa?");
            tmpHrana.vytvor(vrchol.get((int)i.getSourceId()).getxSuradnica(),vrchol.get((int)i.getSourceId()).getySuradnica(),vrchol.get((int)i.getDestinationId()).getxSuradnica(),vrchol.get((int)i.getDestinationId()).getySuradnica(),hranaID);
            tmpHrana.id=hranaID;
            hranaID++;
            hrana.add(tmpHrana);
        }
    }
}
