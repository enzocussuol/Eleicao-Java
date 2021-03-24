package trabalho1;

public class Partido {
	private int numeroPartido;
	private int votosLegenda;
	private String nomePartido;
	private String siglaPartido;
	
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
}
