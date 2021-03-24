package trabalho1;

import java.util.Comparator;
import java.util.LinkedList;

public class Partido {
	private int numeroPartido;
	private int votosLegenda;
	private int votosTotais;
	private String nomePartido;
	private String siglaPartido;
	private LinkedList<Politico> politicos; 
	private int quantidadePoliticos;
	
	public int getNumeroPartido() {
		return numeroPartido;
	}
	
	public void setNumeroPartido(int numeroPartido) {
		this.numeroPartido = numeroPartido;
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
	
	public String getNomePartido() {
		return nomePartido;
	}
	
	public void setNomePartido(String nomePartido) {
		this.nomePartido = nomePartido;
	}
	
	public String getSiglaPartido() {
		return siglaPartido;
	}
	
	public void setSiglaPartido(String siglaPartido) {
		this.siglaPartido = siglaPartido;
	}

	public int getQuantidadeCandidatos(LinkedList<Politico> politicos) {
		return this.quantidadePoliticos;
	}

	public void setQuantidadePoliticos(int quantidadePoliticos) {
		this.quantidadePoliticos = 0;
		for(Politico politico : politicos) {
			if(politico.getPartido() == this.numeroPartido) {
				this.quantidadePoliticos++;				
			}
		}
	}

	public LinkedList<Politico> getPoliticos() {
		return this.politicos;
	}

	public void setPoliticos(LinkedList<Politico> politicos) {
		for(Politico politico : politicos) {
			if(politico.getPartido() == this.numeroPartido) {
				this.politicos.add(politico);
			}
		}
	}
	
	@Override
	public String toString() {
		return this.numeroPartido + "," + 
				this.votosLegenda + "," + 
				this.nomePartido + "," + 
				this.siglaPartido;
	}
}
	
class VotosTotaisComparator implements Comparator<Partido> {
	@Override
	public int compare(Partido p1, Partido p2) {
		int diferenca = p1.getVotosTotais() - p2.getVotosTotais();
		
		if(diferenca != 0) {
			return diferenca;
		}else {
			return p1.getNumeroPartido() - p2.getNumeroPartido();
		}
	}
}
