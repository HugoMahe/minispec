package model;

import java.util.ArrayList;
import java.util.List;

public class Model implements ElementVisitable {
	public String nom;
	public List<Entity> entities = new ArrayList<>();
	
	
	
	@Override
	public void accept(ElementVisitor visitor) {
		visitor.visit(this);
		
	}
}
