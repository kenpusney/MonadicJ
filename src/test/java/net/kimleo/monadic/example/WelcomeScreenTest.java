package net.kimleo.monadic.example;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WelcomeScreenTest {

    private WelcomeScreen screen;

    @Before
    public void setUp() throws Exception {
        screen = new WelcomeScreen();
    }

    @Test
    public void should_display_welcome_message_if_customer_has_nickname() throws Exception {
        Customer customer = new Customer("Qing Liu", "Kimmy");

        assertThat(screen.welcome(customer), is("Hello Kimmy"));
    }

    @Test
    public void should_display_welcome_message_if_customer_has_only_name() throws Exception {
        Customer customer = new Customer("Qing Liu", null);

        assertThat(screen.welcome(customer), is("Hello Qing Liu"));
    }

    @Test
    public void should_display_welcome_message_if_customer_noting() throws Exception {
        Customer customer = new Customer(null, null);

        assertThat(screen.welcome(customer), is("Hello Anonymous"));

    }
}