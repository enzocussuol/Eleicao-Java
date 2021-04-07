package trabalho1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 * A classe Eleicao tem como proposta armazenar seus atributos, tais como:
 * 1) Data na qual foi realizada a eleicao
 * 2) Entidades, como partidos e politicos envolvidos
 * 3) Dados estatisticos, como numero de politicos eleitos, numeros totais de votos, votos de legenda e votos nominais
 * Alem disso, essa classe tambem exerce a funcao de realizar logicas internas do programa
 */
public class Eleicao {
	private LocalDate dataEleicao;
	private LinkedHashMap<Integer, Partido> mapaPartidos = new LinkedHashMap<Integer, Partido>();
	private LinkedList<Politico> listaPoliticos = new LinkedList<Politico>();
	private int numVagas;
	private int totalVotos;
	private int votosLegenda;
	private int votosNominais;
	
	Eleicao(String[] args, Leitura leitura){
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataEleicao = LocalDate.parse(args[2], formatador);
		
		leitura.lePartidos(mapaPartidos, args[1]);
		leitura.lePoliticos(listaPoliticos, mapaPartidos, args[0]);
		
		processaNumVagas();
		
		processaVotosTotaisPartidos();
		processaNumEleitosPartidos();
		processaPrimeiroColocadoPartidos();
		processaUltimoColocadoPartidos();
		
		processaTotalVotos();
		processaVotosLegenda();
		processaVotosNominais();
	}
	
	public LocalDate getDataEleicao() {
		return dataEleicao;
	}
	
	public LinkedHashMap<Integer, Partido> getMapaPartidos() {
		return mapaPartidos;
	}
	
	public LinkedList<Politico> getListaPoliticos() {
		return listaPoliticos;
	}
	
	public int getNumVagas() {
		return numVagas;
	}
	
	public int getTotalVotos() {
		return totalVotos;
	}
	
	public int getVotosLegenda() {
		return votosLegenda;
	}

	public int getVotosNominais() {
		return votosNominais;
	}
	
	public void processaNumVagas() {
		this.numVagas = 0;
		
		for(Politico politico: this.listaPoliticos) {
			if(politico.getSituacao().equals("Eleito")) {
				this.numVagas++;
			}
		}
	}
	
	public void processaVotosTotaisPartidos() {
		for(Integer chave: this.mapaPartidos.keySet()) {
			this.mapaPartidos.get(chave).setVotosTotais();
		}
	}
	
	public void processaNumEleitosPartidos() {
		for(Integer chave: this.mapaPartidos.keySet()) {
			this.mapaPartidos.get(chave).setNumEleitos();
		}
	}
	
	public void processaPrimeiroColocadoPartidos() {
		for(Integer chave: this.mapaPartidos.keySet()) {
			this.mapaPartidos.get(chave).setPrimeiroColocado();
		}
	}
	
	public void processaUltimoColocadoPartidos() {
		for(Integer chave: this.mapaPartidos.keySet()) {
			this.mapaPartidos.get(chave).setUltimoColocado();
		}
	}
	
	public void processaTotalVotos() {
		this.totalVotos = 0;
		
		for(Integer chave: this.mapaPartidos.keySet()) {
			this.totalVotos += this.mapaPartidos.get(chave).getVotosTotais();
		}
	}
	
	public void processaVotosLegenda() {
		this.votosLegenda = 0;
		
		for(Integer chave: this.mapaPartidos.keySet()) {
			this.votosLegenda += this.mapaPartidos.get(chave).getVotosLegenda();
		}
	}
	
	public void processaVotosNominais() {
		this.votosNominais = this.totalVotos - this.votosLegenda;
	}
	
	/**
	 * Gera os relatorios de saida conforme especificado
	 * Para isso, realiza as ordenacoes necessarias e imprime cada relatorio
	 * @param escrita
	 */
	public void geraRelatorios(Escrita escrita) {
		Collections.sort(listaPoliticos);
		
		escrita.imprimeNumeroVagas(numVagas);
		escrita.imprimeEleitos(listaPoliticos, mapaPartidos, numVagas);
		escrita.imprimeMaisVotados(listaPoliticos, mapaPartidos, numVagas);
		escrita.imprimeSeriamEleitos(listaPoliticos, mapaPartidos, numVagas);
		escrita.imprimeNaoSeriamEleitos(listaPoliticos, mapaPartidos, numVagas);
		
		LinkedList<Map.Entry<Integer, Partido>> listaPartidos = new LinkedList<Map.Entry<Integer, Partido>>(mapaPartidos.entrySet());
		
		Collections.sort(listaPartidos, new VotosTotaisComparator());
		escrita.imprimeVotosPartidos(listaPartidos);
		
		Collections.sort(listaPartidos, new VotosPrimeiroColocadoComparator());
		escrita.imprimePrimeiroUltimoColocados(listaPartidos);
		
		escrita.imprimeDistribuicaoFaixaEtaria(listaPoliticos, dataEleicao, numVagas);
		escrita.imprimeDistribuicaoSexo(listaPoliticos, numVagas);
		escrita.imprimeNumVotos(totalVotos, votosLegenda, votosNominais);
	}
}
