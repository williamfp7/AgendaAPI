<%@ page language="java" contentType="application/JSON; charset=UTF-8"	pageEncoding="UTF-8"%><%@ page import="model.JavaBeans"%><%@ page import="java.util.ArrayList"%><%	ArrayList<JavaBeans> lista=(ArrayList<JavaBeans>) request.getAttribute("contatos");%>{"contatos":[<% for(int i=0;i<lista.size();i++){ %>{
	"nome":"<%=lista.get(i).getNome() %>",
	"fone":"<%=lista.get(i).getFone() %>",
	"email":"<%=lista.get(i).getEmail() %>",
	"id":"<%=lista.get(i).getId() %>"
}<% if(i<(lista.size()-1)){%>,<%}%><%} %>
]}