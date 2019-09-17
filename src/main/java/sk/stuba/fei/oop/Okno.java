package sk.stuba.fei.oop;

import org.w3c.dom.events.MouseEvent;
import sk.stuba.fei.oop.generated.*;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class Okno extends Frame implements WindowListener, ActionListener, AdjustmentListener{

    Panel panel;
    Platno platno;
    Button otvor;
    PN petri;
    Button pridaj,zober, pridajMiesto, pridajPrechod, odstran, pridajHranu, presun,uloz,spusti;


    public Okno()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        petri=new PN();
        pack();
        setSize(screenSize.width,screenSize.height);
        setLayout(new BorderLayout());
        addWindowListener(this);
        panel=new Panel();
        otvor=new Button("Otvor Subor");
        spusti=new Button("Spusti");
        zober=new Button("Zober token");
        zober.addActionListener(this);
        panel.add(zober);
        spusti.addActionListener(this);
        panel.add(spusti);
        uloz=new Button("Uloz Subor");
        uloz.addActionListener(this);
        panel.add(uloz);
        presun=new Button("Presun");
        presun.addActionListener(this);
        panel.add(presun);
        pridajHranu=new Button("Pridaj hranu");
        pridajHranu.setBackground(null);
        pridajHranu.addActionListener(this);
        panel.add(pridajHranu);
        pridaj=new Button("Pridaj token");
        pridaj.setBackground(null);
        pridajMiesto=new Button("Pridaj miesto");
        pridajMiesto.setBackground(null);
        pridajPrechod=new Button("Pridaj prechod");
        pridajPrechod.setBackground(null);
        odstran=new Button("Odstran");
        odstran.setBackground(null);
        odstran.addActionListener(this);
        panel.add(odstran);
        pridajPrechod.addActionListener(this);
        panel.add(pridajPrechod);
        pridajMiesto.addActionListener(this);
        panel.add(pridajMiesto);
        pridaj.addActionListener(this);
        panel.add(pridaj);
        otvor.addActionListener(this);
        panel.add(otvor);
        add("North",panel);
        platno=new Platno();
        add("Center",platno);
       /* petri.nastavPrechod();
        platno.nacitajPN(petri);*/
        setVisible(true);
    }


    public void adjustmentValueChanged(AdjustmentEvent e) {

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==pridajHranu)
        {
            if (platno.pridajHrana==true)
            {
                platno.pridajHrana=false;

            }
            else{
                platno.pridajHrana=true;
                platno.spustaj=false;
                platno.presun=false;
                platno.pridaMiesto=false;
                platno.pridajToken=false;
                platno.pridajPrechod=false;
                platno.odstran=false;
                platno.zoberToken=false;
            }
        }
        if (e.getSource()==pridajMiesto)
        {
            if (platno.pridaMiesto==true)
            {
                platno.pridaMiesto=false;
            }
            else {
                platno.pridajHrana=false;
                platno.spustaj=false;
                platno.presun=false;
                platno.pridaMiesto=true;
                platno.pridajToken=false;
                platno.pridajPrechod=false;
                platno.odstran=false;
                platno.zoberToken=false;
            }
        }
        if(e.getSource()==pridajPrechod)
        {
            if (platno.pridajPrechod==true)
            {
                platno.pridajPrechod=false;
            }
            else {
                platno.pridajHrana=false;
                platno.spustaj=false;
                platno.presun=false;
                platno.pridaMiesto=false;
                platno.pridajToken=false;
                platno.pridajPrechod=true;
                platno.odstran=false;
                platno.zoberToken=false;
            }
        }
        if (e.getSource()==odstran)
        {
            if(platno.odstran==true)
            {
                platno.odstran=false;
            }
            else{
                platno.pridajHrana=false;
                platno.spustaj=false;
                platno.presun=false;
                platno.pridaMiesto=false;
                platno.pridajToken=false;
                platno.pridajPrechod=false;
                platno.odstran=true;
                platno.zoberToken=false;
            }
        }
        if (e.getSource()==pridaj)
        {
            if(platno.pridajToken==true)
            {
                platno.pridajToken=false;
            }
            else
            {
                platno.pridajHrana=false;
                platno.spustaj=false;
                platno.presun=false;
                platno.pridaMiesto=false;
                platno.pridajToken=true;
                platno.pridajPrechod=false;
                platno.odstran=false;
                platno.zoberToken=false;
            }
        }
        if (e.getSource()==spusti)
        {
            if(platno.spustaj==true)
            {
                platno.spustaj=false;
            }
            else
            {
                platno.pridajHrana=false;
                platno.spustaj=true;
                platno.presun=false;
                platno.pridaMiesto=false;
                platno.pridajToken=false;
                platno.pridajPrechod=false;
                platno.odstran=false;
                platno.zoberToken=false;
            }
        }
        if (e.getSource()==zober)
        {
            if(platno.zoberToken==true)
            {
                platno.zoberToken=false;
            }
            else
            {
                platno.pridajHrana=false;
                platno.spustaj=false;
                platno.presun=false;
                platno.pridaMiesto=false;
                platno.pridajToken=false;
                platno.pridajPrechod=false;
                platno.odstran=false;
                platno.zoberToken=true;
            }
        }
        if(e.getSource()==presun)
        {
            if(platno.presun==true)
            {
                platno.presun=false;
            }
            else{
                platno.pridajHrana=false;
                platno.spustaj=false;
                platno.presun=true;
                platno.pridaMiesto=false;
                platno.pridajToken=false;
                platno.pridajPrechod=false;
                platno.odstran=false;
                platno.zoberToken=false;
            }
        }
        if (e.getSource()==otvor)
        {
            FileDialog OpDialog = new FileDialog(this,"Otvor",FileDialog.LOAD);
            OpDialog.setVisible(true);
            try {
                File file = new File(OpDialog.getDirectory()+ OpDialog.getFile()); //todo: from file chooser
                JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Document document = (Document) jaxbUnmarshaller.unmarshal(file);
                petri= new PN();
                petri.nacitajPNzDokumentu(document);
                platno.petri=petri;
                petri.nastavPrechod();
                platno.repaint();
                //platno.nacitajPN(petri);
                System.out.println(document);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        if(e.getSource()==uloz) {
            FileDialog OpDialog = new FileDialog(this,"Uloz",FileDialog.SAVE);
            OpDialog.setVisible(true);
            try {

                InputStream xmlFileStream = ClassLoader.getSystemResourceAsStream("emptymodel.xml");
                JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Document document = (Document) jaxbUnmarshaller.unmarshal(xmlFileStream);
                petri=platno.petri;
                int indMiesto=0,indPrechod=0,indHrana=0;
                for(Map.Entry<Integer,Vrchol> vrchol:petri.vrchol.entrySet())
                {
                    if (vrchol.getValue() instanceof Miesto)
                    {
                        document.getPlace().add(new Place());
                        document.getPlace().get(indMiesto).setId((short)vrchol.getValue().getID());
                        document.getPlace().get(indMiesto).setTokens((short)vrchol.getValue().getToken());
                        document.getPlace().get(indMiesto).setX((short)(vrchol.getValue().getxSuradnica()*2));
                        document.getPlace().get(indMiesto).setY((short)(vrchol.getValue().getySuradnica()*2));
                        indMiesto++;
                    }
                    if (vrchol.getValue() instanceof Prechod)
                    {
                        document.getTransition().add(new Transition());
                        document.getTransition().get(indPrechod).setId((short)vrchol.getValue().getID());
                        document.getTransition().get(indPrechod).setX((short)(vrchol.getValue().getxSuradnica()*2));
                        document.getTransition().get(indPrechod).setY((short)(vrchol.getValue().getySuradnica()*2));
                        indPrechod++;
                    }
                }

                for (Hrana hrana: petri.hrana)
                {
                    document.getArc().add(new Arc());
                    document.getArc().get(indHrana).setDestinationId((short)hrana.getVystupID());
                    document.getArc().get(indHrana).setSourceId((short)hrana.getVstupID());
                    document.getArc().get(indHrana).setMultiplicity((short)hrana.getNasobnost());
                    document.getArc().get(indHrana).setType("regular");
                    indHrana++;
                }
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(document, new File(OpDialog.getDirectory()+ OpDialog.getFile()));
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }

    }

    public void windowClosed(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e)
    {

    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }

    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {
        dispose();

    }

    public void windowActivated(WindowEvent e) {

    }
}
