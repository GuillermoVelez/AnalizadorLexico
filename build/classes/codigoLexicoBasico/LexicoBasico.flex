package codigoLexicoBasico;
import static codigoLexicoBasico.Tokens.*;
%%
%class LexicoBasico
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]
%{
    public String lexemas;
%}
%%
int |
if |
void |
char |
float |
double |
case |
switch |
else |
for |
while {lexemas=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {return Igual;}
"+" {return Suma;}
"-" {return Resta;}
"*" {return Multiplicacion;}
"/" {return Division;}
";" {return P_coma;}
"(" {return Parentesis_a;}
")" {return Parentesis_c;}
"{" {return Llave_a;}
"}" {return Llave_c;}
"==" {return D_igual;}
"=!" {return Diferencial;}
"<" {return Menor;}
">" {return Mayor;}
"<<" {return D_menor;}
">>" {return D_mayor;}
"++" {return D_suma;}
"--" {return D_menos;}
{L}({L}|{D})* {lexemas=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexemas=yytext(); return Numero;}
 . {return ERROR;}
