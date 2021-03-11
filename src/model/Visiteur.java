package model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Visiteur implements ElementVisitor{

	
	Map<File,String> mapFile = new HashMap<File,String>();
	private String chaine=""; 
	
	
	@Override
	public void visit(Entity entite) {
		File fileEntité = new File(entite.nom + ".java");
		System.out.println("lancement de la visite entité");
		chaine=chaine + "public class " + entite.nom + "{\n";
		System.out.println("ajout du file lié à l'entité");
		for(Attribute at : entite.attributes) {
			at.accept(this);
		}
		chaine=chaine+"}";
		System.out.println("Ecrire le fichier en sortie avec la chaine suivante");
		System.out.println("\n" +  chaine);
		this.mapFile.put(fileEntité, chaine);
		chaine= new String();
	}

	@Override
	public void visit(Model model) {
		System.out.println("lancement de la visite model");
		//chaine=chaine+"package " + model.nom;
		// TRAITEMENT DE L'ENTITE
		for(Entity en : model.entities) {
			en.accept(this);
		}

	}

	@Override
	public void visit(Attribute attribute) {
		System.out.println("lancement de la visite attribute");
		chaine= chaine +"\t"+ attribute.type + ":" + attribute.nom + ";\n";
	}

}
