package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContatoDAO implements DAO{
	DataBase db=new MySQL();
	
	/** CRUD CREATE **/
	@Override
	public void inserirContato(JavaBeans contato) {
		sqlQuery("insert into contatos (nome,fone,email) values ('"+contato.getNome()+"','"+contato.getFone()+"','"+contato.getEmail()+"')");
	}
	
	/** CRUD READ **/
	@Override
	public ArrayList<JavaBeans> recuperarContato(JavaBeans contato) {
		return read("select * from contatos where id='"+contato.getId()+"'");
	}
	@Override
	public ArrayList<JavaBeans> recuperarContato() {
		return read("select * from contatos order by id");
	}
	private ArrayList<JavaBeans> read(String sql) {
		ArrayList<JavaBeans> contatos=new ArrayList<>();
		try {
			Connection con=db.getCon();
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				contatos.add(new JavaBeans(rs.getString("nome"),rs.getString("fone"),rs.getString("email"),rs.getString("id")));
			}
			return contatos;
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}
	
	/** CRUD UPDATE **/
	@Override
	public void alterarContato(JavaBeans contato) {
		sqlQuery("update contatos set nome='"+contato.getNome()+"', fone='"+contato.getFone()+"', email='"+contato.getEmail()+"' where id='"+contato.getId()+"'");
	}
	
	/** CRUD DELETE **/	
	@Override
	public void deletarContato(JavaBeans contato) {
		sqlQuery("delete from contatos where id='"+contato.getId()+"'");
	}

	/** Métodos acessórios **/
	public void handleException(Exception e) {
		System.out.println("erro no sql");
		System.out.println(e);
	}

	public void sqlQuery(String sql) {
		try {
			Connection con=db.getCon();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.executeUpdate();
			con.close();		
		} catch (Exception e) {
			handleException(e);
		}
	}
}
