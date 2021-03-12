package model;

public class Attribute implements ElementVisitable {
	public String nom;
	public String type;
	public Entity entite;
	public String subType;
	public String size;
	public String min;
	public String max;
	
	
	
	@Override
	public void accept(ElementVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}
}
