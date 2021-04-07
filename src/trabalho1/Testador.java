package trabalho1;

/**
 * 
 * A classe Testador tem como proposta executar o programa
 *
 */
public class Testador {
	public static void main(String[] args) {
		Leitura leitura = new Leitura();
		Escrita escrita = new Escrita();
		Eleicao eleicao = new Eleicao(args, leitura);
	
		eleicao.geraRelatorios(escrita);
	}
}
