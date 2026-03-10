package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MailerDubTest {
	private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    IMailer mailer = new MailerStub();
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    
    @Test
    void testEnviaCorreo() {
    	mailer.enviaCorreo("ejemplo@ejemplo.com", "Ejemplo");

        assertEquals("ejemplo@ejemplo.com: Ejemplo", outputStreamCaptor.toString().trim());
    }
}
