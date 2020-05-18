package beans;

public class BeanProdutoJSP {
	
	private Long id;
	private String nome;
	private Double valor;
	private Double quantidade;
	private Long categoria_id;
	
	public BeanProdutoJSP() {
	}

	public BeanProdutoJSP(String nome, Double preco, Double quantidade) {
		super();
		this.nome = nome;
		this.valor = preco;
		this.quantidade = quantidade;
	}
	
	
	public Long getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double preco) {
		this.valor = preco;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "BeanProdutoJSP [id=" + id + ", nome=" + nome + ", preco=" + valor + ", quantidade=" + quantidade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanProdutoJSP other = (BeanProdutoJSP) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
