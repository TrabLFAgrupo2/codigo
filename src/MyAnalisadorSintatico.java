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
        if(proxTokenIs(Token.FOR)){
            cmdFor();
        }
        else if(proxTokenIs(Token.SWITCH)){
            cmdSwitch();
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
    public void cmdSwitch(){
        if(proxTokenIs(Token.SWITCH)){
            leProxToken();
            if(proxTokenIs(Token.AP)){
                leProxToken();
                if(proxTokenIs(Token.IDENT)){
                    leProxToken();
                    if(proxTokenIs(Token.FP)){
                        leProxToken();
                        if(proxTokenIs(Token.ACH)){
                            leProxToken();
                            listaCase();
                            if(proxTokenIs(Token.FCH))
                                leProxToken();
                            else{
                                Token[] tokensEsperados = {Token.FCH};
                                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                            }
                        }
                        else{
                            Token[] tokensEsperados = {Token.ACH};
                            throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);          
                        }  
                    }
                    else{
                        Token[] tokensEsperados = {Token.FP};
                        throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                    }
                }
                else{
                    Token[] tokensEsperados = {Token.IDENT};
                    throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                }
            }
            else{
                Token[] tokensEsperados = {Token.AP};
                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
            }
        }
        else{
            Token[] tokensEsperados = {Token.SWITCH};
            throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
        }
    }
    public void listaCase(){
        Case();
        R3();
    }
    public void Case(){
        if(proxTokenIs(Token.CASE)){
            leProxToken();
            if(proxTokenIs(Token.ASPAS)){
                leProxToken();
                exp();
                if(proxTokenIs(Token.ASPAS)){
                    leProxToken();
                    if(proxTokenIs(Token.DOISPONTOS))
                        leProxToken();
                }
            }else if(proxTokenIs(Token.IDENT)){
                exp();
                if(proxTokenIs(Token.DOISPONTOS))
                leProxToken();
                else{
                Token[] tokensEsperados = {Token.DOISPONTOS};
                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                }
            }else{
                Token[] tokensEsperados = {Token.IDENT};
                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
            }
        }
        else{
            Token[] tokensEsperados = {Token.CASE};
            throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
        }
    }
    public void R3(){
        if(proxTokenIs(Token.CASE))
            listaCase();
    }
        
    public void cmdFor(){
        if(proxTokenIs(Token.FOR)){
            leProxToken();
            if (proxTokenIs(Token.AP)){
                leProxToken();
                if(proxTokenIs(Token.FP))
                    leProxToken();
                else{
                    atrib();
                    if(proxTokenIs(Token.PT_VIRG)){
                        leProxToken();
                        exp();
                        if(proxTokenIs(Token.PT_VIRG)){
                            leProxToken();
                            atrib();
                            if(proxTokenIs(Token.FP)){
                                leProxToken();
                            }
                            else{
                                Token[] tokensEsperados = {Token.FP};
                                 throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                            }
                        }
                        else{
                            Token[] tokensEsperados = {Token.PT_VIRG};
                            throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                        }
                    }
                    else{
                        Token[] tokensEsperados = {Token.PT_VIRG};
                        throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
                    }
                }
                }else{
                Token[] tokensEsperados = {Token.AP};
                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
            }
        }
    }
    public void atrib(){
        if(proxTokenIs(Token.IDENT)){
            leProxToken();
            R2();
        }
    }    
    public void R2(){
        if(proxTokenIs(Token.ATRIB)){
            leProxToken();
            exp();
        }
        if(proxTokenIs(Token.OP)){
            leProxToken();
        }
    }
}
