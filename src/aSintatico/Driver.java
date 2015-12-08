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
		//chama o visitor de pretty print
		prog.accept(new PrettyPrintVisitor());
	}

}
