package model;

public class JavaBeans {
	private String nome;
	private String fone;
	private String email;
	private String id;
	
	public JavaBeans() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JavaBeans(String nome, String fone, String email, String id) {
		super();
		this.nome = nome;
		this.fone = fone;
		this.email = email;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
