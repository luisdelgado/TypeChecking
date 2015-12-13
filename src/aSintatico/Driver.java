package aSintatico;

import java_cup.runtime.*;
import ast.*;
import visitor.*;

public class Driver {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		parser  p = new parser();
		//programa na forma de AST
		Program prog = (Program)p.parse().value;
		BuildSymbolTableVisitor stVis = new BuildSymbolTableVisitor();
		//construindo tabela de símbolos
		prog.accept(stVis); 
		//fazendo a checagem de tipos
		prog.accept(new TypeCheckVisitor(stVis.getSymbolTable()));
	}

}
