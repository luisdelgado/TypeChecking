import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;

 
%%

%class Lexer
%implements sym
%public
%unicode
%line
%column
%cup
%char

%{  
    public Lexer(ComplexSymbolFactory sf, java.io.InputStream is){
        this(is);
        symbolFactory = sf;
    }
    public Lexer(ComplexSymbolFactory sf, java.io.Reader reader){
        this(reader);
        symbolFactory = sf;
    }
    
    private StringBuffer sb;
    private ComplexSymbolFactory symbolFactory;
    private int csline,cscolumn;

    public Symbol symbol(String name, int code){
        return symbolFactory.newSymbol(name, code,
                        new Location(yyline+1,yycolumn+1, yychar), // -yylength()
                        new Location(yyline+1,yycolumn+yylength(), yychar+yylength())
                );
    }
    public Symbol symbol(String name, int code, String lexem){
    return symbolFactory.newSymbol(name, code, 
                        new Location(yyline+1, yycolumn +1, yychar), 
                        new Location(yyline+1,yycolumn+yylength(), yychar+yylength()), lexem);
    }
    
    protected void emit_warning(String message){
        System.out.println("scanner warning: " + message + " at : 2 "+ 
                (yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
    
    protected void emit_error(String message){
        System.out.println("scanner error: " + message + " at : 2" + 
                (yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
%}


ws = [ \t\r\n]
coment = "/*"~"*/"

digit = [1-9]
id = (_|[a-z]|[A-Z])(_|[a-z]|[A-Z]|{digit}|0)*
numeroI = (({digit}+(0|{digit})*)|0)|[1-9] 
fraci = {numeroI}+(\.numeroI*)

%%
"class" {return symbolFactory.newSymbol("CLASS", CLASS);}
"main" {return symbolFactory.newSymbol("MAIN", MAIN);}
"public" {return symbolFactory.newSymbol("PUBLIC", PUBLIC);}
"extends" {return symbolFactory.newSymbol("EXTENDS", EXTENDS);}
"static" {return symbolFactory.newSymbol("STATIC", STATIC);}
"void" {return symbolFactory.newSymbol("VOID", VOID);}
"int" {return symbolFactory.newSymbol("INT", INT);}
"boolean" {return symbolFactory.newSymbol("BOOLEAN", BOOLEAN);;}
"while" {return symbolFactory.newSymbol("WHILE", WHILE);}
"if" {return symbolFactory.newSymbol("IF", IF);}
"else" {return symbolFactory.newSymbol("ELSE", ELSE);}
"return" {return symbolFactory.newSymbol("RETURN", RETURN);}
"||" {return symbolFactory.newSymbol("OR", OR);}
"&&" {return symbolFactory.newSymbol("AND", AND);}
"==" {return symbolFactory.newSymbol("EQUAL", EQUAL);}
"=" {return symbolFactory.newSymbol("ASSING", ASSING);}
"!=" {return symbolFactory.newSymbol("NOT_EQUAL", NOT_EQUAL);}
"<" {return symbolFactory.newSymbol("LT", LT);}
"<=" {return symbolFactory.newSymbol("LET", LET);}
">" {return symbolFactory.newSymbol("GT", GT);}
">=" {return symbolFactory.newSymbol("GET", GET);}
"+" {return symbolFactory.newSymbol("PLUS", PLUS);}
"-" {return symbolFactory.newSymbol("MINUS", MINUS);}
"*" {return symbolFactory.newSymbol("MULT", MULT);}
"/" {return symbolFactory.newSymbol("DIV", DIV);}
"%" {return symbolFactory.newSymbol("MOD", MOD);} 
"!" {return symbolFactory.newSymbol("NOT", NOT);} 
"false" {return symbolFactory.newSymbol("FALSE", FALSE);}
"true" {return symbolFactory.newSymbol("TRUE", TRUE);}
"this" {return symbolFactory.newSymbol("THIS", THIS);}
"new" {return symbolFactory.newSymbol("NEW", NEW);}
"(" {return symbolFactory.newSymbol("APARENTESE", APARENTESE);}
")" {return symbolFactory.newSymbol("FPARENTESE", FPARENTESE);}
"{" {return symbolFactory.newSymbol("ACHAVE", ACHAVE);}
"}" {return symbolFactory.newSymbol("FCHAVE", FCHAVE);}
"[" {return symbolFactory.newSymbol("ACOLCHETE", ACOLCHETE);}
"]" {return symbolFactory.newSymbol("FCOLCHETE", FCOLCHETE);}
"," {return symbolFactory.newSymbol("VIRGULA", VIRGULA);}
";" {return symbolFactory.newSymbol("PVIRGULA", PVIRGULA);}
"." {return symbolFactory.newSymbol("PONTO", PONTO);}
"length" {return symbolFactory.newSymbol("LENGTH", LENGTH);}

{ws} {}
{coment} {}
{id} {return symbolFactory.newSymbol("IDENTIFIER", IDENTIFIER, yytext();}
{numeroI} {return symbolFactory.newSymbol("NUMBER", NUMBER, Integer.parseInt(yytext()));}
{fraci} {return symbolFactory.newSymbol("NUMBER_DOUBLE", NUMBER, Double.parseDouble(yytext()));}


