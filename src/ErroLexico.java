public class ErroLexico extends RuntimeException {
    private char caractereEncontrado;
    private String caracteresEsperados;
    private int linha;
 
    public ErroLexico(char _caracterEncontrado, String _caracteresEsperados, int linhaComErro) {
        this.caractereEncontrado = _caracterEncontrado;
        this.caracteresEsperados = _caracteresEsperados;
        this.linha = linhaComErro;
    }

    ErroLexico(String flag, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toString() {
	return "caractere encontrado: "+((char)this.caractereEncontrado)+
                " (na linha " + this.linha + " ) " +
	       "\nera(m) esperado(s): "+this.caracteresEsperados;
    }
}
