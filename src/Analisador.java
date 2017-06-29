/**
 *
 * @author gabriel camata
 */
public abstract class Analisador implements Constantes {
    protected String nomeArquivoEntrada;
    public Analisador(String _nomeArquivoEntrada) {
	this.nomeArquivoEntrada = _nomeArquivoEntrada;
    }
    public Analisador() {
	this.nomeArquivoEntrada = NOME_DEFAULT_ARQUIVO_ENTRADA;
    }
}
