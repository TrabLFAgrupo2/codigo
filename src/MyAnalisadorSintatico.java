
/**
 * Grupo 2 (Gabriel Moura, Gabriel Camata, Raphael, Rafael aragao)
 * @author Gabriel Moura, Gabriel Camata, Raphael, Rafael aragao
 */
public class MyAnalisadorSintatico extends AnalisadorSintatico {
	
    /**
     *
     * @param _nomeArquivoEntrada
     */
    public MyAnalisadorSintatico(String _nomeArquivoEntrada) {
	super(_nomeArquivoEntrada);
    }

    /**
     *
     */
    public void inicio() {
	blocoComp();
	reconhece(Token.EOF);
    }

    /**
     *Funcao responsável pelo bloco de comando de outras funções.
     * bloco -> cmdWhile | cmdIf | cmdDoWhile | cmdSwitch | atrib | lambda
     */
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
        else if(proxTokenIs(Token.DO)){
            cmdDo();
        }
        else if(proxTokenIs(Token.IDENT)) {
            comandoAtribuicao();
            reconhece(Token.PT_VIRG);
            corpo();
	}
        
	else if(proxTokenIs(Token.EOF));
	}

    /**
     *Função responsável pelo atribuição de variaveis .
     * 
     */
    public void comandoAtribuicao() {
        reconhece(Token.IDENT);
        if(proxTokenIs(Token.ATRIB)){
            reconhece(Token.ATRIB);
            exp();
        }
        if(proxTokenIs(Token.OP_UNARIOS)){
            leProxToken();
        }
    }

    /**
     *Função responsavel por expressão arimeticas e logicas.
     * Exp -> num R1 | IDENT R1 | APexpFP R1
     */
    public void exp() {
        if(proxTokenIs(Token.NUM)){ 
            leProxToken();
                R1();
            if(proxTokenIs(Token.PT_VIRG));
        }
        else if(proxTokenIs(Token.AP)){
                leProxToken();
                exp();
                if(proxTokenIs(Token.FP)){
                    leProxToken();
                    R1();
                }
            }
        else if(proxTokenIs(Token.IDENT)){ 
        	leProxToken();
                R1();
                if(proxTokenIs(Token.PT_VIRG));
            }    
            else {
		Token[] tokensEsperados = {Token.NUM,Token.IDENT};
		throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
            }
    }

    /**
     *Funcao para tirar recursividade  a esquerda da "EXP".
     * R1 -> OP exp | lambda
     */
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

    /**
     *Funcao do While.
     * cmdDoWhile ->  DO ACH bloco FCH WHILE AP exp FP PT-VIRG 
     */
    public void cmdWhile(){
        if(proxTokenIs(Token.WHILE)){
            leProxToken();
            if(proxTokenIs(Token.AP)){
                leProxToken();
                exp();
                if(proxTokenIs(Token.FP)){
                    leProxToken();
                    blocoComp();
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
    
    /**
     *Funcao do IF.
     * cmdIf -> IF AP exp FP blocoComp
     */
    public void cmdIf(){
        if(proxTokenIs(Token.IF)){
            leProxToken();
            if(proxTokenIs(Token.AP)){
                leProxToken();
                exp();
                if(proxTokenIs(Token.FP)){
                    leProxToken();
                    blocoComp();
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

    /**
     * Funcao responsavel pela montagem do Switch.
     * cmdSwhitch -> SWITCH AP IDENT FP ACH listaCase FCH
     */
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

    /**
     * Funcao responsavel por montar a lista de cases do switch.
     * listaCase -> case R3
     */
    public void listaCase(){
        Case();
        R3();
    }

    /**
     * Funcao responsavel pela montagem do case;
     * case -> CASE exp DOIS-PT bloco
     */
    public void Case(){
        if(proxTokenIs(Token.CASE)){
            leProxToken();
            if(proxTokenIs(Token.ASPAS)){
                leProxToken();
                exp();
                if(proxTokenIs(Token.ASPAS)){
                    leProxToken();
                    if(proxTokenIs(Token.DOISPONTOS)){
                        leProxToken();
                        blocoComp();
                    }
                }
            }else if(proxTokenIs(Token.IDENT)){
                exp();
                if(proxTokenIs(Token.DOISPONTOS)){
                leProxToken();
                blocoComp();
                }else{
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

    /**
     * Funcao para retirar recursividade a esquerda da lista de case.
     * R3 -> listaCase | lambda
     */
    public void R3(){
        if(proxTokenIs(Token.CASE))
            listaCase();
    }
        
    /**
     * Funcao responsavel pela construção do for. 
     * cmdFor -> FOR AP atrib PT-VIRG exp PT-VIRG atrib FP blocComp
     */
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
                                blocoComp();
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

    /**
     * Funcao que trata a atribuicao.
     * atrib -> IDENT R2
     */
    public void atrib(){
        if(proxTokenIs(Token.IDENT)){
            leProxToken();
            R2();
        }
    }    

    /**
     * Funcao para retirar recursividade a esquerda da atribuicao.
     * R2 -> ATRIB exp | OP_UN
     */
    public void R2(){
        if(proxTokenIs(Token.ATRIB)){
            leProxToken();
            exp();
        }
        if(proxTokenIs(Token.OP_UNARIOS)){
            leProxToken();
        }
    }
    /**
     * Criação de bloco de comandos.
     * blocoComp ->  ACH bloco FCH  | bloco
     */
    public void blocoComp(){
        if(proxTokenIs(Token.ACH)){
            leProxToken();
            bloco();
            if(proxTokenIs(Token.FCH)){
                leProxToken();
                blocoComp();
            }
            else{
                Token[] tokensEsperados = {Token.FCH};
                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);            
            }    
        }
        else
            bloco();
        
    }
    /**
     *Chama a Funcao com o bloco de comandos.
     */
    public void bloco(){
        corpo();
    }


    /**
     * Funcao onde e feita a analise sintatida do comando dowhile.
     * cmdDoWhile -> <DO><ACH>bloco<FCH><WHILE><AP>exp<FP><PT-VIRG>
     */
 
     public void cmdDo(){
        if(proxTokenIs(Token.DO)){
            leProxToken();
            if(proxTokenIs(Token.ACH)){
                leProxToken();
                bloco();
                //leProxToken();
                if(proxTokenIs(Token.FCH)){
                    leProxToken();
                    if (proxTokenIs(Token.WHILE)){
                        leProxToken();
                        if(proxTokenIs(Token.AP)){
                            leProxToken();
                            exp();
                            if(proxTokenIs(Token.FP)){
                                leProxToken();
                                if(proxTokenIs(Token.PT_VIRG))
                                    leProxToken();
                                else{
                                Token[] tokensEsperados = {Token.PT_VIRG};
                                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);            
                                }   
                            }else{
                                Token[] tokensEsperados = {Token.FP};
                                throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);            
                            }
                        }else{
                              Token[] tokensEsperados = {Token.AP};
                              throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);            
                         }
                    }else{
                         Token[] tokensEsperados = {Token.WHILE};
                         throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);            
                    }
                }else{
                   Token[] tokensEsperados = {Token.FCH};
                   throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);            
                }
            }else{
                  Token[] tokensEsperados = {Token.ACH};
                  throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);            
             }
        }
    }
}