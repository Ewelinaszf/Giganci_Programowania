package Pages;


import Base.Hook;
import Objects.StartPageObjects;
import Utilis.Actions;
import net.serenitybdd.annotations.Steps;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Objects.CourseTypeObject.freeClasses;
import static Objects.StartPageObjects.*;


public class StartPage extends Hook {

    @Steps
    StartPageObjects startPageObjects;

    Actions actions = new Actions(driver);
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


    public void fillOutTheForm(String inputName, String emailAddress, String mobilePhone, String yearOfBirth, String statuteAgreed, String advertisementAgreed) {
        if (inputName != null && !inputName.isEmpty()) {
            actions.sendKeys(parentNameInput, inputName);
        }
        if (emailAddress != null && !emailAddress.isEmpty()) {
            actions.sendKeys(emailAddressInput, emailAddress);
        }
        if (mobilePhone != null && !mobilePhone.isEmpty()) {
            actions.sendKeys(phoneNumberInput, mobilePhone);
        }
        if (yearOfBirth != null && !yearOfBirth.isEmpty()) {
            actions.sendKeys(birthYearInput, yearOfBirth);
        }
        if (statuteAgreed.equals("YES")){
            actions.clickXpath(statuteAgreedInput);
        }
        if(advertisementAgreed.equals("YES"))
            actions.clickXpath(advertisementAgreedInput);

        actions.clickSubmitButton(submitButton);
    }


    public boolean verifyCommunicate(String webElement, String input) {
        String poleJestWymaganeCommunicate = webElement+"//ancestor::div[@class='formControls']//span[text()='Pole jest wymagane']";
        String nieprawidlowyAdressEmailCommunicate = webElement+"//ancestor::div[@class='formControls']//span[text()='Nieprawidłowy adres e-mail']";
        String nieprawidlowyMobilePhoneCOmmunicate = webElement+"//ancestor::div[@class='formControls']//span[text()='Niepoprawny numer telefonu. Numer powinien zawierać 9 cyfr, z opcjonalnym kierunkowym +48 lub +380 na początku.']";


        if(webElement.equals(advertisementAgreedInput) || webElement.equals(statuteAgreedInput)){
            poleJestWymaganeCommunicate = webElement + "/following-sibling::div[@class='formValidation']";
        }
        boolean isCommunitateDisplayedCorrectly =  false;
        boolean isVisibleElement = actions.isElementVisible(poleJestWymaganeCommunicate);
        if ((input == null || input.equals("NO")) && isVisibleElement) {
            isCommunitateDisplayedCorrectly = true;
        }
        else if(input != null ) {
            if(webElement.equals(emailAddressInput)) {
                if (!isEmailValid(input)) {
                    isCommunitateDisplayedCorrectly = actions.isElementVisible(nieprawidlowyAdressEmailCommunicate);
                }
                else isCommunitateDisplayedCorrectly = true;
            }
            if(webElement.equals(phoneNumberInput)) {
                if (!isPhoneNumberValid(input)) {
                    isCommunitateDisplayedCorrectly = actions.isElementVisible(nieprawidlowyMobilePhoneCOmmunicate);
                }
                else isCommunitateDisplayedCorrectly = true;
            }
            else if (!isVisibleElement)
                isCommunitateDisplayedCorrectly=true;
            else
                isCommunitateDisplayedCorrectly=false;
        }

        return isCommunitateDisplayedCorrectly;
    }


    public boolean isEmailValid(String email) {
        // Metoda do sprawdzenia, czy e-mail jest poprawny
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public boolean checkValidationOfFields(List<String> listOfInputs) {
        for (int i = 0; i < listOfInputs.size(); i++) {
            if (listOfInputs.get(i)==null || !(isEmailValid(listOfInputs.get(1))) || !(isPhoneNumberValid(listOfInputs.get(2))) ) {
                return false;
            }
        }
        return true;
    }

    public boolean validateFormAndVisibility(List<String> listOfInputs) {
        boolean isValidationSuccessful = checkValidationOfFields(listOfInputs);

        if (!isValidationSuccessful) {
            return actions.isElementVisible(parentNameInput);
        }

        if (isValidationSuccessful ) {
            return actions.isElementVisible(freeClasses);
        }

        return false;
    }

    public boolean isPhoneNumberValid(String phoneNumber) {

        String regex = "^(\\+48|\\+380)?\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }


    public void checkErrorPopup(List<String> listOfInputs) {
        if(checkValidationOfFields(listOfInputs)){
            actions.isElementVisible(errorPopupRequiredField);
        }
    }
}
