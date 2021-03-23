package trabalho1;

public class Partido {
	private int numeroPartido;
	private int votosLegenda;
	private String nomePartido;
	private String siglaPartido;
	
	public Partido(int numeroPartido, int votosLegenda, String nomePartido, String siglaPartido) {
		this.numeroPartido = numeroPartido;
		this.votosLegenda = votosLegenda;
		this.nomePartido = nomePartido;
		this.siglaPartido = siglaPartido;
	}

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
}

