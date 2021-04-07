package trabalho1;

import java.util.Comparator;
import java.util.Map;
import java.util.LinkedList;

/**
 * 
 * A classe Partido tem como proposta armazenar os atributos de cada partido individualmente, incluindo:
 * 1) Suas propriedades, como nome, sigla, numero
 * 2) Seus dados eleitorais, como votos de legenda, votos totais e numero de politicos eleitos
 * 3) Seus membros, como uma lista de todos os membros (politicos), o membro mais votado e o membro menos votado
 * 
 */
public class Partido {
	private int numero;
	private int votosLegenda;
	private int votosTotais;
	private int numEleitos;
	private String nome;
	private String sigla;
	private LinkedList<Politico> politicos = new LinkedList<Politico>();
	Politico primeiroColocado = new Politico();
	Politico ultimoColocado = new Politico();
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getVotosLegenda() {
		return votosLegenda;
	}
	
	public void setVotosLegenda(int votosLegenda) {
		this.votosLegenda = votosLegenda;
	}
	
	public int getVotosTotais() {
		return votosTotais;
	}
	
	public void setVotosTotais() {
		int votosNominais = 0;
		
		for(Politico politico: this.politicos) {
			votosNominais += politico.getVotosNominais();
		}
		
		this.votosTotais = votosNominais + this.votosLegenda;
	}
	
	public int getNumEleitos() {
		return numEleitos;
	}
	
	public void setNumEleitos() {
		int contEleitos = 0;
		
		for(Politico politico: this.politicos) {
			if(politico.getSituacao().equals("Eleito")) {
				contEleitos++;
			}
		}
		
		this.numEleitos = contEleitos;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public LinkedList<Politico> getPoliticos() {
		return this.politicos;
	}
	
	public Politico getPrimeiroColocado() {
		return primeiroColocado;
	}
	
	/**
	 * Define o politico mais votado dentro do partido
	 * Para isso, visita-se todos os politicos, buscando o mais votado
	 * Como criterio de desempate, usa-se a idade dos politicos envolvidos
	 */
	public void setPrimeiroColocado() {
		int maiorNumVotos = -1;
		int votos;
		
		for(Politico politico: politicos) {
			votos = politico.getVotosNominais();
			if(votos > maiorNumVotos) {
				maiorNumVotos = votos;
				this.primeiroColocado = politico;
			}else if(votos == maiorNumVotos) {
				if(politico.getDataNascimento().compareTo(this.primeiroColocado.getDataNascimento()) > 0){
					this.primeiroColocado = politico;
				}
			}
		}
	}
	
	public Politico getUltimoColocado() {
		return ultimoColocado;
	}
	
	/**
	 * Define o politico menos votado dentro do partido
	 * Para isso, visita-se todos os politicos, buscando o menos votado
	 * Como criterio de desempate, usa-se a idade dos politicos envolvidos
	 */
	public void setUltimoColocado() {
		int menorNumVotos = Integer.MAX_VALUE;
		int votos;
		
		for(Politico politico: politicos) {
			votos = politico.getVotosNominais();
			if(votos < menorNumVotos) {
				menorNumVotos = votos;
				this.ultimoColocado = politico;
			}else if(votos == menorNumVotos) {
				if(politico.getDataNascimento().compareTo(this.ultimoColocado.getDataNascimento()) > 0){
					this.ultimoColocado = politico;
				}
			}
		}
	}
}

/**
 * 
 * A classe VotosTotaisComparator tem como proposta realiza a comparacao entre partidos
 * Para isso, utiliza-se como criterio os votos totais dos partidos envolvidos
 * Alem disso, como criterio de desempate, usa-se o numero dos partidos envolvidos
 *
 */
class VotosTotaisComparator implements Comparator<Map.Entry<Integer, Partido>> {
	@Override
	public int compare(Map.Entry<Integer, Partido> o1, Map.Entry<Integer, Partido> o2) {
		int diferenca = o2.getValue().getVotosTotais() - o1.getValue().getVotosTotais();
		
		if(diferenca != 0) {
			return diferenca;
		}else {
			return o1.getValue().getNumero() - o2.getValue().getNumero();
		}
	}
}

/**
 * 
 * A classe VotosPrimeiroColocadoComparator tem como proposta realiza a comparacao entre partidos
 * Para isso, utiliza-se como criterio os votos nominais dos primeiros colocados dos partidos envolvidos
 * Alem disso, como criterio de desempate, usa-se o numero dos partidos envolvidos
 *
 */
class VotosPrimeiroColocadoComparator implements Comparator<Map.Entry<Integer, Partido>> {
	@Override
	public int compare(Map.Entry<Integer, Partido> o1, Map.Entry<Integer, Partido> o2) {
		Politico primeiroColocado1 = o1.getValue().getPrimeiroColocado();
		Politico primeiroColocado2 = o2.getValue().getPrimeiroColocado();
		
		int diferenca = primeiroColocado2.getVotosNominais() - primeiroColocado1.getVotosNominais();
		
		if(diferenca != 0) {
			return diferenca;
		}else {
			return o1.getValue().getNumero() - o2.getValue().getNumero();
		}
	}
}
