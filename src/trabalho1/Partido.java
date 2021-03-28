package trabalho1;

import java.util.Comparator;
import java.util.Map;
import java.util.LinkedList;

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
	
	public void setPrimeiroColocado() {
		int maiorNumVotos = -1;
		int votos;
		
		for(Politico politico: politicos) {
			votos = politico.getVotosNominais();
			if(votos > maiorNumVotos) {
				maiorNumVotos = votos;
				this.primeiroColocado = politico;
			}
		}
	}
	
	public Politico getUltimoColocado() {
		return ultimoColocado;
	}
	
	public void setUltimoColocado() {
		int menorNumVotos = Integer.MAX_VALUE;
		int votos;
		
		for(Politico politico: politicos) {
			votos = politico.getVotosNominais();
			if(votos < menorNumVotos) {
				menorNumVotos = votos;
				this.ultimoColocado = politico;
			}
		}
	}
	
	@Override
	public String toString() {
		String str;
		
		str = this.nome + "\n";
		
		for(Politico politico: this.politicos) {
			str += politico.toString() + "\n";
		}
		
		return str;
	}
}
	
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
