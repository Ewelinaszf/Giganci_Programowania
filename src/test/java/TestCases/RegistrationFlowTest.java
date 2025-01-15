package TestCases;

import Base.Hook;
import Pages.CourseTypePage;
import Pages.StartPage;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.List;

import static Objects.StartPageObjects.*;
import static Objects.StartPageObjects.advertisementAgreedInput;

@ExtendWith(SerenityJUnit5Extension.class)
public class RegistrationFlowTest extends Hook {

    @Steps
    StartPage startPage;

    @Steps
    CourseTypePage courseTypePage;

    List<String> listOfWebElements = Arrays.asList(parentNameInput, emailAddressInput, phoneNumberInput, birthYearInput, statuteAgreedInput, advertisementAgreedInput);

    @ParameterizedTest
    @CsvFileSource(resources = "/Data.csv", numLinesToSkip = 1)
    void testCheckingRegistration(String parentName, String emailAddress, String mobilePhone, String yearOfBirth, String statuteAgreedInput, String advertisementAgreedInput) throws InterruptedException {
        List<String> listOfInputs = Arrays.asList(parentName, emailAddress, mobilePhone, yearOfBirth, statuteAgreedInput, advertisementAgreedInput);
        startPage.fillOutTheForm(parentName, emailAddress, mobilePhone, yearOfBirth, statuteAgreedInput, advertisementAgreedInput);
        courseTypePage.clickOnProgramowanie("Online", "Roczne kursy z programowania");
    }

}
