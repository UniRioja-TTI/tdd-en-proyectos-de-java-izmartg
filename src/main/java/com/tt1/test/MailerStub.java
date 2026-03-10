package com.tt1.test;

public class MailerStub implements IMailer{
	public void enviaCorreo(String correo, String mensaje) {
		System.out.println(correo + ": " + mensaje);
	}
}
