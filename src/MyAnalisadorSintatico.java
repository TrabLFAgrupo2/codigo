/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel camata
 */
public class MyAnalisadorSintatico extends AnalisadorSintatico {
	
    public MyAnalisadorSintatico(String _nomeArquivoEntrada) {
	super(_nomeArquivoEntrada);
    }
    public void inicio() {
	corpo(); 
	reconhece(Token.EOF);
    }
    public void corpo() {
        if(proxTokenIs(Token.WHILE)){
            cmdWhile();
        }
        else if(proxTokenIs(Token.IF)){
            cmdIf();
        }
        else if(proxTokenIs(Token.IDENT)) {
            comandoAtribuicao();
            reconhece(Token.PT_VIRG);
            corpo();
	}
        
	else if(proxTokenIs(Token.EOF))
		;
            else {
		Token[] tokensEsperados = {Token.IDENT,Token.EOF};
		throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
            }
	}
    public void comandoAtribuicao() {
        reconhece(Token.IDENT);
        reconhece(Token.ATRIB);
        exp();
    }
    public void exp() {
        if(proxTokenIs(Token.NUM)){ 
            leProxToken();
            //if(proxTokenIs(Token.OP))
                R1();
            /*if(proxTokenIs(Token.OP)){
                leProxToken();
                if(proxTokenIs(Token.NUM)||proxTokenIs(Token.IDENT))
                    exp();
                else{
                    Token[] tokensEsperados = {Token.NUM,Token.IDENT};
                    throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                }    
            }*/
            if(proxTokenIs(Token.PT_VIRG));
            /*else{
                //if(!proxTokenIs(Token.OP)){
                    Token[] tokensEsperados = {Token.OP};
                    throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                //}
            }*/
        }
        else if(proxTokenIs(Token.AP)){
                leProxToken();
                exp();
                if(proxTokenIs(Token.FP)){
                    leProxToken();
                   // if(proxTokenIs(Token.OP))
                        R1();
                }
            }
        else if(proxTokenIs(Token.IDENT)){ 
        	leProxToken();
                //if(proxTokenIs(Token.OP))
                    R1();
                /*if(proxTokenIs(Token.OP)){
                    leProxToken();
                    if(proxTokenIs(Token.NUM)||proxTokenIs(Token.IDENT))
                        exp();
                    else{
                        Token[] tokensEsperados = {Token.NUM,Token.IDENT};
                        throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                    }
                }*/
                if(proxTokenIs(Token.PT_VIRG));
                /*else{
                    //if(!proxTokenIs(Token.OP)){
                        Token[] tokensEsperados = {Token.OP};
                        throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                    //}
                }*/
            }    
            else {
		Token[] tokensEsperados = {Token.NUM,Token.IDENT};
		throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
            }
    }
    public void R1(){
        if(proxTokenIs(Token.OP)){
            leProxToken();
            if(proxTokenIs(Token.NUM)||proxTokenIs(Token.IDENT)||proxTokenIs(Token.AP))
                exp();
            else{
                Token[] tokensEsperados = {Token.NUM,Token.IDENT};
                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
            }    
        }
    }
        public void cmdWhile(){
        if(proxTokenIs(Token.WHILE)){
            leProxToken();
            if(proxTokenIs(Token.AP)){
                leProxToken();
                exp();
                if(proxTokenIs(Token.FP)){
                    leProxToken();
                }
                else{
                    Token[] tokensEsperados = {Token.FP};
                    throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                }
            }    
            else{
                Token[] tokensEsperados = {Token.AP};
                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
            }   
        }
    }
       public void cmdIf(){
        if(proxTokenIs(Token.IF)){
            leProxToken();
            if(proxTokenIs(Token.AP)){
                leProxToken();
                exp();
                if(proxTokenIs(Token.FP)){
                    leProxToken();
                }
                else{
                    Token[] tokensEsperados = {Token.FP};
                    throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                }
            }    
            else{
                Token[] tokensEsperados = {Token.AP};
                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
            }   
        }
    }
    public void cmdSwhitch(){
        if(proxTokenIs(Token.SWITCH)){
        
        }
    }
}
