package org.lafeuille.ljcp;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FakerTest {

    @Autowired
    private Faker faker;

    @Test
    public void foo() {
        String pokemonName = faker.pokemon().name();
        assertThat(pokemonName).isNotBlank();
    }
}
