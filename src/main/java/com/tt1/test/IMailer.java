package com.tt1.test;

/**
 * Interfaz que define el servicio de mensajería del sistema.
 * Permite abstraer la lógica de envío de notificaciones para que el sistema
 * sea independiente del proveedor de correo real (SMTP, API externa, etc.).
 * @author izmartg
 * @version 1.0
 */
public interface IMailer {

    /**
     * Envía un correo a una dirección de correo específica.
     * @param correo Dirección de correo electrónico del destinatario.
     * @param mensaje Contenido del cuerpo del correo que se desea enviar.
     */
    public void enviaCorreo(String correo, String mensaje);
}