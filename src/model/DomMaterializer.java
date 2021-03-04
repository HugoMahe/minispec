package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomMaterializer {
	
	public File lectureDom() throws ParserConfigurationException, SAXException, IOException, URISyntaxException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File xml = new File("./resources/test.xml");
		org.w3c.dom.Document document = builder.parse(xml);
		document.getDocumentElement().normalize();
		System.out.println("j'ai parsé");
	
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());
		System.out.println(root.getAttribute("name"));
		String nomFichier = root.getAttribute("name").toString()+".java";
		File sortie = new File(nomFichier);
		String contenu = new String();
		contenu = "Public Class " + root.getAttribute("name").toString() + " {\n";
		
		NodeList listeAttributs = root.getElementsByTagName("attribute");
		contenu = traitementAttribut(listeAttributs,contenu);
		contenu = contenu + "}";
		System.out.println(contenu);
		sortie.createNewFile();
		writeFile(nomFichier, contenu);
		return null;
	}

	public String traitementAttribut(NodeList nl, String contenu) {
		for (int i=0; i<nl.getLength();i++) {
			Node iNode = nl.item(i);
			Element monElement = (Element) iNode;
			String name =monElement.getAttribute("name");
			String type = monElement.getAttribute("type");
			contenu= contenu + "\t" + type + " " + name + ";\n";
		}
		return contenu;
	}
	
	public void writeFile(String path,String contenu) throws IOException, URISyntaxException {
		Files.write(Paths.get(path), contenu.getBytes(), StandardOpenOption.APPEND);
	}
}
