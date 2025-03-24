package Pages;

import Base.Hook;
import Utilis.Actions;

import static Objects.CourseTypeObject.*;

public class CourseTypePage extends Hook {
    Actions actions = new Actions(driver);


    public CoursesPage clickOnProgramowanie(String courseMode, String typeOfCourse){
        actions.clickXpath(programmingButton);
        if(courseMode == "Online"){
            actions.clickXpath(onlineCourseButton);
            if(typeOfCourse == "Roczne kursy z programowania"){
                actions.clickXpath(oneYearProgrammingCourse);
            }
        }
        return new CoursesPage();
    }

}
