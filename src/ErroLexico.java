/**
 *
 * @author gabriel camata//moura
 */
public class ErroLexico extends RuntimeException {
    private char caractereEncontrado;
    private String caracteresEsperados;
 
    public ErroLexico(char _caracterEncontrado, String _caracteresEsperados) {
        this.caractereEncontrado = _caracterEncontrado;
        this.caracteresEsperados = _caracteresEsperados;
    }

    ErroLexico(String flag, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String toString() {
	return "caractere encontrado: "+((char)this.caractereEncontrado)+
	       "\nera(m) esperado(s): "+this.caracteresEsperados;
    }

}
