import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    @Test
    void shouldDeliveryCardOrder() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Кемерово");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
//        String dateOfMeeting = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(3));
        $("[data-test-id=name] input").setValue("Павел-К");
        $("[data-test-id=phone] input").setValue("+79238456245");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible);
        $("[data-test-id=success-notification] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + dateOfMeeting));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String dateOfMeetingReplan = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateOfMeetingReplan);
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(visible, Duration.ofSeconds(10));
        $("[data-test-id=replan-notification] .notification__content").shouldHave(ownText("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id=replan-notification] .button").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + dateOfMeetingReplan));
    }
}
