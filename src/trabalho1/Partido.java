package trabalho1;

import java.util.LinkedList;

public class Partido {
	private int numeroPartido;
	private int votosLegenda;
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
	
	@Override
	public String toString() {
		return this.numeroPartido + "," + 
				this.votosLegenda + "," + 
				this.nomePartido + "," + 
				this.siglaPartido;
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
}
