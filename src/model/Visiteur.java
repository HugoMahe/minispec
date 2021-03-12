package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visiteur implements ElementVisitor{
	public static final String PATH="src";

	
	public List<String> listeCollections = new ArrayList<String>(Arrays.asList("List","Bag","Set"));
	
	Map<File,String> mapFile = new HashMap<File,String>();
	private String chaine=""; 
	
	
	@Override
	public void visit(Entity entite) {
		File fileEntité = new File(PATH +"/"+ entite.nom + ".java");
		//System.out.println("lancement de la visite entité");
		chaine=chaine + "public class " + entite.nom + "{\n";
		//System.out.println("ajout du file lié à l'entité");
		for(Attribute at : entite.attributes) {
			at.accept(this);
		}
		chaine=chaine+"}";
		//System.out.println("Ecrire le fichier en sortie avec la chaine suivante");
		System.out.println("\n" +  chaine);
		this.mapFile.put(fileEntité, chaine);
		chaine= new String();
	}

	@Override
	public void visit(Model model) {
		//System.out.println("lancement de la visite model");
		//chaine=chaine+"package " + model.nom;
		// TRAITEMENT DE L'ENTITE
		for(Entity en : model.entities) {
			en.accept(this);
		}

	}

	@Override
	public void visit(Attribute attribute) {
		//System.out.println("lancement de la visite attribute");
		if(attribute.type.contentEquals("Array")) {
			System.out.println("dans array");
			chaine = chaine + "\t" + attribute.subType +"[] " + attribute.nom + " =  new " + attribute.subType+"[" + attribute.size + "]; \n"; 
		}else if (this.listeCollections.contains(attribute.type)){
			System.out.println("on est dans le cas d'une collection");
			chaine = chaine + "\t" + attribute.type +"<" + attribute.subType + "> " + attribute.nom + ";\n";
			
		}else {
			chaine= chaine +"\t"+ attribute.type + " " + attribute.nom + ";\n";
		}
	}
	
	
	public void writeFiles() throws IOException, URISyntaxException {
		//Files.write(Paths.get(path), contenu.getBytes(), StandardOpenOption.APPEND);
		for (Map.Entry<File, String> entry : this.mapFile.entrySet()) {
		    entry.getKey().createNewFile();
		    FileWriter write = new FileWriter(entry.getKey());
		    write.write(entry.getValue());
		    write.flush();
		    write.close();
		}
	}

}
