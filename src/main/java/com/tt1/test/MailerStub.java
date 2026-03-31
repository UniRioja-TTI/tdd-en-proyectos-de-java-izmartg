package com.tt1.test;

/**
 * Implementación de simulación (Stub) para el servicio de correo.
 * En lugar de realizar una conexión de red, imprime los mensajes por la
 * salida estándar (consola) para facilitar la depuración y las pruebas TDD.
 * @author izmartg
 * @version 1.0
 */
public class MailerStub implements IMailer {

    /**
     * Simula el envío de un correo imprimiendo el destinatario y el mensaje en consola.
     * {@inheritDoc}
     */
    @Override
    public void enviaCorreo(String correo, String mensaje) {
        System.out.println(correo + ": " + mensaje);
    }
}