package symboltable;

import ast.Type;

public class Variable {

	String id;
	Type type;

	public Variable(String id, Type type) {
		this.id = id;
		this.type = type;
	}

	public String id() {
		return id;
	}

	public Type type() {
		return type;
	}

} // Variable