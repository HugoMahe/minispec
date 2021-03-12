package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import model.DomMaterializer;
import model.Entity;
import model.Model;
import model.Visiteur;

class testXML {

	@Test
	void test() throws URISyntaxException {
		DomMaterializer dom = new DomMaterializer();
		try {
			Model monModel = dom.lecteurDom2();
			System.out.println(monModel.entities);
			assertTrue(monModel.entities.size()==3);
			for(Entity entity : monModel.entities) {
				System.out.println(entity.attributes);
			}
			
			Visiteur visiteur = new Visiteur();
			monModel.accept(visiteur);
			visiteur.writeFiles();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
