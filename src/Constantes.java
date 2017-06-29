/**
 *
 * @author gabriel camata//moura
 */
public interface Constantes {
    enum Token { NUM, ATRIB, IDENT, EOF, PT_VIRG, VAZIO, WHILE, OP,OP_UNARIOS,DOISPONTOS, FCH,ACH,AP,FP,
                FOR,CASE,SWITCH,IF,DO,ACO,FCO,SINAL,ASPAS};
 
	String 	DIGITOS	= "0123456789",
		LETRAS	= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_",
		ATRIB  	= " = += -= ",
		VAZIOS	= " \r\n\t",
                WHILE   = "w",
                OP = "+-*/%&&||<>!=",
                OP_UNARIOS = "+-&|",   
                FOR = "f",
                CASE = "c",
                SWITCH = "s",
                IF = "i",
                DO = "d",
                SINAL = "+-";
               
        
                
                
	char	EOF	= 0,
		PT_VIRG	= ';',
		DOISPONTOS = ':',
                IGUAL = '=',
                FCH = '}',
                ACH = '{',
                AP = '(',
                FP = ')',
                ACO = '[',
                FCO = ']',
                ASPAS = '\'';
                
	String   NOME_DEFAULT_ARQUIVO_ENTRADA = "entrada.txt";
}
