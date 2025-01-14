package TestCases;

import Base.Hook;
import Pages.StartPage;
import jxl.common.AssertionFailed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.List;

import static Objects.StartPageObjects.*;


@ExtendWith(SerenityJUnit5Extension.class)
public class RegistrationFormTest extends Hook {


    @Steps
    StartPage startPage;
    List<String> listOfWebElements = Arrays.asList(parentNameInput, emailAddressInput, phoneNumberInput, birthYearInput, statuteAgreedInput, advertisementAgreedInput);

    @ParameterizedTest
    @CsvFileSource(resources = "/UserData.csv", numLinesToSkip = 1)
    void testCheckingRegistration(String parentName, String emailAddress, String mobilePhone, String yearOfBirth, String statuteAgreedInput, String advertisementAgreedInput) throws InterruptedException {
        List<String> listOfInputs = Arrays.asList(parentName, emailAddress, mobilePhone, yearOfBirth, statuteAgreedInput, advertisementAgreedInput);

        startPage.fillOutTheForm(parentName, emailAddress, mobilePhone, yearOfBirth, statuteAgreedInput, advertisementAgreedInput);
        startPage.checkErrorPopup(listOfInputs);
        for (int i = 0; i < listOfWebElements.size(); i++) {
            boolean isCorrect = startPage.verifyCommunicate(listOfWebElements.get(i), listOfInputs.get(i));
            if (!isCorrect) {
                System.out.println("The message for the element " + listOfWebElements.get(i) + " is not displayed correctly");
            }
            Assertions.assertTrue(isCorrect);
        }
        Assertions.assertTrue(startPage.validateFormAndVisibility(listOfInputs));


    }


}
