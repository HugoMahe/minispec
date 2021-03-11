package model;

import java.util.ArrayList;
import java.util.List;

public class Entity implements ElementVisitable{
	public String nom;
	public List<Attribute> attributes = new ArrayList<Attribute>();
	
	
	
	@Override
	public void accept(ElementVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);

	}
}
