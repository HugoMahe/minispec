package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import model.DomMaterializer;

class testXML {

	@Test
	void test() throws URISyntaxException {
		DomMaterializer dom = new DomMaterializer();
		try {
			dom.lectureDom();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
