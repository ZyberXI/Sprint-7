package ru.praktikum_services.qa_scooter.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierDataGenerator {

    public static CourierData random() {
        final String login = RandomStringUtils.randomAlphabetic(5);
        final String password = RandomStringUtils.randomAlphabetic(5);
        final String firstName = RandomStringUtils.randomAlphabetic(5);
        return new CourierData(login, password, firstName);
    }
}
