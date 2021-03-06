package shopPages.ebay.pageObject;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static utils.Constats.BASE_URL;

@DefaultUrl(BASE_URL)
public class ebayHomePage extends PageObject {

    public ebayHomePage() {
    }

    @FindBy(xpath = "//input[@id='gh-ac']")
    WebElementFacade searchBox;

    @FindBy(xpath = "//input[@id='gh-btn']")
    WebElementFacade searchBtn;

    @FindBy(xpath = "//*[@id='gh-cat']")
    Select categoriesDropdown;

    @FindBy(xpath = "//a[@id='gh-eb-Geo-a-default']/span[1]")
    WebElementFacade languageBtn;

    @FindBy(xpath = "//a[@id='gh-eb-Geo-a-en']")
    WebElementFacade alernativeLanguageBtn;

    public void enterSearchKeywords(String searchKeyword ){
        searchBox.type(searchKeyword);
    }

    public void search(){
        if (searchBox.getText().isEmpty()){
            System.out.println("searchBox is empty!");
        }
        searchBtn.click();
    }

    public void chooseCategory(String category) throws NoSuchElementException {
        categoriesDropdown.selectByVisibleText(category);
    }

    public void selectLanguage(){
        if (!languageBtn.getText().contains("español")){
            alernativeLanguageBtn.click();
        }
    }
}
