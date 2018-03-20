package org.lafeuille.ljcp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Period;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Test
    public void application() throws Exception {
        LocalDate.now().datesUntil(LocalDate.now().plusYears(1), Period.ofWeeks(1))
        .forEach(System.out::println);
    }

}
