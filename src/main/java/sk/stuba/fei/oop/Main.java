package sk.stuba.fei.oop;

import sk.stuba.fei.oop.generated.Document;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import sk.stuba.fei.oop.Okno;

public class Main {

    public static void main(String[] args) {

        new Okno();

       /*try {
            File file = new File("src/main/resources/newmodel.xml"); //todo: from file chooser
            JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Document document = (Document) jaxbUnmarshaller.unmarshal(file);
            PN petri=new PN(document);
            petri.nastavPrechod();
            //new Okno();
            System.out.println(document);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }*/


    }
}
