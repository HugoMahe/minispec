package model;

public interface ElementVisitor {

	void visit(Entity entite);
	
	void visit(Model model);
	
	void visit(Attribute attribute);
}
