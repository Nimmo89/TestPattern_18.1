import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class DataGenerator {
    private DataGenerator() {
    }

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int shift) {
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity() {
        String city = faker.address().city();
        return city;
    }

    public static String generateName() {
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }
}
