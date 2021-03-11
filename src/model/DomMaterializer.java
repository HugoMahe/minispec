package model;

import java.io.File;
import java.io.IOException;
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
	
	Model model = new Model();
	
	/*
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
		traitementAttribut(listeAttributs,contenu);
		contenu = contenu + "}";
		System.out.println(contenu);
		sortie.createNewFile();
		writeFile(nomFichier, contenu);
		return null;
	}
	*/
	
	
	public Model lecteurDom2() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File xml = new File("./resources/test.xml");
		org.w3c.dom.Document document = builder.parse(xml);
		document.getDocumentElement().normalize();
		
		
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());
		NodeList listeEntities = root.getElementsByTagName("entity");
		System.out.println(listeEntities);
		traitementListEntities(listeEntities);
		System.out.println(model.entities);
		return this.model;
	}
	
	public void traitementListEntities(NodeList nl) {
		for(int i=0; i<nl.getLength();i++) {
			Node iNodeEntity = nl.item(i);
			Element monElementEntity = (Element) iNodeEntity;
			
			// DECLARATION DE MON ELEMENT ENTITY
			Entity entite = new Entity();
			entite.nom=monElementEntity.getAttribute("name");
			
			// TRAITEMENT DES ATTRIBUTS DE L'ENTITE
			NodeList listeAttributs = monElementEntity.getElementsByTagName("attribute");
			this.traitementAttribut(listeAttributs, entite);
			this.model.entities.add(entite);
		}
	}
	

	public void traitementAttribut(NodeList nl, Entity entité) {
		for (int i=0; i<nl.getLength();i++) {
			Node iNode = nl.item(i);
			Element monElement = (Element) iNode;
			Attribute attribut = new Attribute();
			attribut.nom=monElement.getAttribute("name");
			attribut.type=monElement.getAttribute("type");
			attribut.entite=entité;
			entité.attributes.add(attribut);
		}
	}
	
	
	
	
	public void writeFile(String path,String contenu) throws IOException, URISyntaxException {
		Files.write(Paths.get(path), contenu.getBytes(), StandardOpenOption.APPEND);
	}
}
