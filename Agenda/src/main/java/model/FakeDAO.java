package model;

import java.util.ArrayList;

public class FakeDAO implements DAO {
	JavaBeansFactory factory=new JavaBeansFactory();
	@Override
	public void inserirContato(JavaBeans contato) {
		showSomething(contato);
	}

	@Override
	public ArrayList<JavaBeans> recuperarContato(JavaBeans contato) {
		ArrayList<JavaBeans> contatos=new ArrayList<>();
		contatos.add(factory.create("William","98423-4954","williamfp7@yahoo.com.br","1"));
		return contatos;
	}

	@Override
	public ArrayList<JavaBeans> recuperarContato() {
		ArrayList<JavaBeans> contatos=new ArrayList<>();
		contatos.add(factory.create("William","98423-4954","williamfp7@yahoo.com.br","1"));
		contatos.add(factory.create("Pedro","99999-9999","pedro@mail.com","2"));
		return contatos;
	}

	@Override
	public void alterarContato(JavaBeans contato) {
		showSomething(contato);
	}

	@Override
	public void deletarContato(JavaBeans contato) {
		showSomething(contato);
	}
	public void showSomething(JavaBeans contato) {
		System.out.println(contato.getNome());
		System.out.println(contato.getFone());
		System.out.println(contato.getEmail());
		System.out.println(contato.getId());
	}
}
