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
        Customer customer = new Customer("Kimmy Leo", "Kimmy");

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

        assertNull(screen.welcome(customer));

    }

    @Test
    public void should_success_when_customer_id_starts_with_letter() throws Exception {
        Customer customer = new Customer("Kimmy Leo", "Kimmy");

        assertThat(screen.validate(customer), is(true));
    }

    @Test
    public void should_success_when_customer_id_between_4_and_18() throws Exception {
        Customer customer = new Customer("Kimmy Leo", null);

        assertThat(screen.validate(customer), is(true));
    }

    @Test(expected = ValidationException.class)
    public void should_fail_when_start_with_not_allowed_char() throws Exception {
        Customer customer = new Customer("*1abcdef", null);

        screen.validate(customer);
    }

    @Test(expected = ValidationException.class)
    public void should_fail_when_contains_not_allowed_char() throws Exception {
        Customer customer = new Customer("abcdef1*", null);

        screen.validate(customer);
    }

    @Test(expected = ValidationException.class)
    public void should_fail_when_length_not_allowed() throws Exception {
        Customer customer = new Customer("abcdefghijklmnopqrstuvwxyz", null);

        screen.validate(customer);
    }
}