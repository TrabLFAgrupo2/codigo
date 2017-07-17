public class AnalisadorSintatico extends Analisador implements Constantes {

    protected MyAnalisadorLexico scanner;
	
    public AnalisadorSintatico(String _nomeArquivoEntrada) {
	this.scanner = new MyAnalisadorLexico(_nomeArquivoEntrada);
	// lê o primeiro token e o coloca no campo tokenReconhecido
	this.leProxToken();
    }
    public AnalisadorSintatico() {
    	super();
    }

// executa 1 vez a máquina de Moore
    public void leProxToken() {
	this.scanner.s0();
    }

	// verifica se o próximo token é t
	// avança o ponteiro para o próximo token
    public void reconhece(Token t) {
	if(t == this.scanner.tokenReconhecido) 
            this.leProxToken();
	else
            throw new ErroSintatico(this.scanner.tokenReconhecido, t);
    }

    // verifica se o próximo token é t
    // N O avança o ponteiro de leitura
    public boolean proxTokenIs(Token t) {
	if(t == this.scanner.tokenReconhecido) 
            return true;
	else
            return false;
    }
}
