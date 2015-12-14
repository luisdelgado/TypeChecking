package visitor;

import symboltable.SymbolTable;
import ast.And;
import ast.ArrayAssign;
import ast.ArrayLength;
import ast.ArrayLookup;
import ast.Assign;
import ast.Block;
import ast.BooleanType;
import ast.Call;
import ast.ClassDeclExtends;
import ast.ClassDeclSimple;
import ast.False;
import ast.Formal;
import ast.Identifier;
import ast.IdentifierExp;
import ast.IdentifierType;
import ast.If;
import ast.IntArrayType;
import ast.IntegerLiteral;
import ast.IntegerType;
import ast.LessThan;
import ast.MainClass;
import ast.MethodDecl;
import ast.Minus;
import ast.NewArray;
import ast.NewObject;
import ast.Not;
import ast.Plus;
import ast.Print;
import ast.Program;
import ast.This;
import ast.Times;
import ast.True;
import ast.Type;
import ast.VarDecl;
import ast.While;

public class TypeCheckVisitor implements TypeVisitor {

	public SymbolTable symbolTable;

	public TypeCheckVisitor(SymbolTable st) {
		symbolTable = st;
	}

	// MainClass m;
	// ClassDeclList cl;
	public Type visit(Program n) {
		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i1,i2;
	// Statement s;
	public Type visit(MainClass n) {
		n.i1.accept(this);
		n.i2.accept(this);
		n.s.accept(this);
		return null;
	}

	// Identifier i;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Type visit(ClassDeclSimple n) {
		n.i.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i;
	// Identifier j;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Type visit(ClassDeclExtends n) {
		n.i.accept(this);
		n.j.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Type t;
	// Identifier i;
	public Type visit(VarDecl n) {
		n.t.accept(this);
		n.i.accept(this);
		return null;
	}

	// Type t;
	// Identifier i;
	// FormalList fl;
	// VarDeclList vl;
	// StatementList sl;
	// Exp e;
	public Type visit(MethodDecl n) {
		n.t.accept(this);
		n.i.accept(this);
		for (int i = 0; i < n.fl.size(); i++) {
			n.fl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		n.e.accept(this);
		return null;
	}

	// Type t;
	// Identifier i;
	public Type visit(Formal n) {
		n.t.accept(this);
		n.i.accept(this);
		return null;
	}

	public Type visit(IntArrayType n) {
		Type t=n.accept(this);
		if(!(t instanceof IntegerType))
			System.out.print("");
			//apresentar erro
		
		return new IntArrayType();
		//return null;
	}

	public Type visit(BooleanType n) {
		Type t=n.accept(this);
		if(!(t instanceof BooleanType))
			System.out.print("");//exception
		
		return new BooleanType();
		//return null;
	}

	public Type visit(IntegerType n) {
		Type t=n.accept(this);
		if(!(t instanceof IntegerType))
			System.out.print("");
		return new IntArrayType();
		//return null;
	}

	// String s;
	public Type visit(IdentifierType n) {
		return null;
	}

	// StatementList sl;
	public Type visit(Block n) {
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		return null;
	}

	// Exp e;
	// Statement s1,s2;
	public Type visit(If n) {
		Type t1=n.e.accept(this);
		if (!(t1 instanceof BooleanType))
			System.out.print("");//exception 
		n.s1.accept(this);
		n.s2.accept(this);
		return null;
	}

	// Exp e;
	// Statement s;
	public Type visit(While n) {
		Type t = n.e.accept(this); // Exp e
		 if(!(t instanceof BooleanType))
			System.out.print(""); //EXCEPTION
		n.s.accept(this); 
		return null;
	}

	// Exp e;
	public Type visit(Print n) {
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e;
	public Type visit(Assign n) {
		Type t=n.i.accept(this);
		Type e=n.e.accept(this);
		/*if (!(t.equals(e)))
		//se expressao e é do tipo t 
		 * e*/
		return null;
	}

	// Identifier i;
	// Exp e1,e2;
	public Type visit(ArrayAssign n) {
		n.i.accept(this);
		Type e1=n.e1.accept(this);
		Type e2=n.e2.accept(this);
		if(!(e1 instanceof IntArrayType || e2 instanceof IntArrayType))
			System.out.print(""); //exception
		return new IntArrayType();
		//return null;
	}

	// Exp e1,e2;
	public Type visit(And n) {
		Type t1 = n.e1.accept(this);
		Type t2 = n.e2.accept(this);
		 if(!(t1 instanceof BooleanType||t2 instanceof BooleanType))
			System.out.print(""); //EXCEPTION
		return null;
	}

	// Exp e1,e2;
	public Type visit(LessThan n) {
		Type t1 = n.e1.accept(this);
		Type t2 = n.e2.accept(this);
		 if(!(t1 instanceof IntegerType||t2 instanceof IntegerType))
			System.out.print(""); //EXCEPTION
		return null;
	}

	// Exp e1,e2;
	public Type visit(Plus n) {
		Type e1 = n.e1.accept(this);
		Type e2 = n.e2.accept(this);
		 if(!(e1 instanceof IntegerType||e2 instanceof IntegerType))
			System.out.print(""); //EXCEPTION
		return null;
	}

	// Exp e1,e2;
	public Type visit(Minus n) {
		Type e1 = n.e1.accept(this);
		Type e2 = n.e2.accept(this);
		 if(!(e1 instanceof IntegerType||e2 instanceof IntegerType))
			System.out.print(""); //EXCEPTION
		return null;
	}

	// Exp e1,e2;
	public Type visit(Times n) {
		Type e1 = n.e1.accept(this);
		Type e2 = n.e2.accept(this);
		 if(!(e1 instanceof IntegerType||e2 instanceof IntegerType))
			System.out.print(""); //EXCEPTION
		return null;
	}

	// Exp e1,e2;
	public Type visit(ArrayLookup n) {
		Type e1 = n.e1.accept(this);
		Type e2 = n.e2.accept(this);
		 if(!(e1 instanceof IntegerType||e2 instanceof IntegerType))
			System.out.print(""); //EXCEPTION
		return null;
	}

	// Exp e;
	public Type visit(ArrayLength n) {
		Type e = n.e.accept(this);
		 if(!(e instanceof IntegerType))
			System.out.print(""); //EXCEPTION
		return null;
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public Type visit(Call n) {
		n.e.accept(this);
		n.i.accept(this);
		for (int i = 0; i < n.el.size(); i++) {
			n.el.elementAt(i).accept(this);
		}
		return null;
	}

	// int i;
	public Type visit(IntegerLiteral n) {
		n.accept(this);
		return null;
	}

	public Type visit(True n) {
		n.accept(this);
		return new BooleanType();
	}

	public Type visit(False n) {
		n.accept(this);
		return new BooleanType();
	}

	// String s;
	public Type visit(IdentifierExp n) {
		n.accept(this);
		return null;
	}

	public Type visit(This n) {
		return null;
	}

	// Exp e;
	public Type visit(NewArray n) {
		Type t=n.e.accept(this);
		if(!(t instanceof IntegerType))
			System.out.print("");//exception
			//apresentar erro
		
		return new IntArrayType();
		//return null;
	}

	// Identifier i;
	public Type visit(NewObject n) {
		n.accept(this);
		return null;
	}

	// Exp e;
	public Type visit(Not n) {
		n.e.accept(this);
		return null;
	}

	// String s;
	public Type visit(Identifier n) {
		n.accept(this);
		return null;
	}
}
