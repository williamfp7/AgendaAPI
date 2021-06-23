package model;

public class JavaBeansFactory {

	public JavaBeansFactory() {
		super();
	}
	public JavaBeans create() {
		return new JavaBeans();
	}
	public JavaBeans create(String nome, String fone, String email, String id) {
		return new JavaBeans(nome,fone,email,id);
	}
}
