package net.kimleo.monadic.example;

import net.kimleo.monadic.either.Either;
import net.kimleo.monadic.optional.Optional;
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

        assertThat(screen.welcome(customer).right(), is("Hello Kimmy"));
    }

    @Test
    public void should_display_welcome_message_if_customer_has_only_name() throws Exception {
        Customer customer = new Customer("Qing Liu", null);

        assertThat(screen.welcome(customer).right(), is("Hello Qing Liu"));
    }

    @Test
    public void should_display_welcome_message_if_customer_noting() throws Exception {
        Customer customer = new Customer(null, null);

        assertNull(screen.welcome(customer).right());

    }

    @Test
    public void should_success_when_customer_id_starts_with_letter() throws Exception {
        Customer customer = new Customer("Kimmy Leo", "Kimmy");

        assertThat(screen.welcome(customer).right(), is("Hello Kimmy"));
    }

    @Test
    public void should_success_when_customer_id_between_4_and_18() throws Exception {
        Customer customer = new Customer("Kimmy Leo", null);

        assertThat(screen.welcome(customer).right(), is("Hello Kimmy Leo"));
    }

    @Test
    public void should_fail_when_start_with_not_allowed_char() throws Exception {
        Customer customer = new Customer("*1abcdef", null);

        assertFalse(screen.welcome(customer).valid());
        assertThat(screen.welcome(customer).left(), is("Customer name must start with letters"));
    }

    @Test
    public void should_fail_when_contains_not_allowed_char() throws Exception {
        Customer customer = new Customer("abcdef1*", null);

        assertFalse(screen.validate(customer).valid());

        screen.validate(customer).bind(cust -> {
            fail();
            return Optional.empty();
        });

        assertThat(screen.welcome(customer).left(), is("Customer name should not contain special characters"));
    }

    @Test
    public void should_fail_when_length_not_allowed() throws Exception {
        Customer customer = new Customer("abcdefghijklmnopqrstuvwxyz", null);

        assertFalse(screen.validate(customer).valid());
        assertThat(screen.welcome(customer).left(), is("Customer name length must between 4 and 18"));
    }
}