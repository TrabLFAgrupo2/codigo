/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel camata
 */
public class ErroSintatico extends RuntimeException implements Constantes {
	private Token tokenEncontrado;
	private Token[] tokensEsperados;

	public ErroSintatico(Token _tokenEncontrado, Token[] _tokensEsperados) {
		this.tokenEncontrado = _tokenEncontrado;
		this.tokensEsperados = _tokensEsperados;
	}
	public ErroSintatico(Token _tokenEncontrado, Token _tokenEsperado) {
		this.tokenEncontrado = _tokenEncontrado;
		this.tokensEsperados = new Token[1];
		tokensEsperados[0] = _tokenEsperado;
	}
	public String toString() {
		String listaDeTokensEsperados = "";
		for(int i=0; i<this.tokensEsperados.length; i++)
			listaDeTokensEsperados += this.tokensEsperados[i] + " ";
		return "token encontrado: "+this.tokenEncontrado+
		       "\nera(m) esperado(s): "+listaDeTokensEsperados;
	}
}

