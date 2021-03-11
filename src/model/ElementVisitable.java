package model;

public interface ElementVisitable {

	void accept(ElementVisitor visitor);
}
