package shopPages.ebay.pageObject;

import entities.Product;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static utils.Constats.BRAND_LIST_BUTTONS;
import static utils.Constats.SIZE_LIST_BUTTONS;
import static utils.Constats.PRODUCTS;
import static utils.Constats.RESULT_PRICE_SORT_OPTIONS;
import static utils.utilities.getPrice;

public class ebayResultPage extends PageObject {
    private  Actions action;

    public ebayResultPage() {
        action = new Actions(super.getDriver());
    }

    @FindBy(xpath = "//div[@id='w10']/button[@class='x-flyout__button']")
    WebElementFacade btnShowDropdownOrderOption;

    @FindBy(xpath = "//div[@id='w10-w0']/a/div/span")
    WebElementFacade btnChangeResultsOrder;

    @FindBy(id = "svg-icon-view-grid")
    WebElementFacade gridOrderIcon;

    @FindBy(xpath = "//li[@class='srp-carousel-list__item srp-multi-aspect__item--applied']//a/div")
    WebElementFacade appliedBrandFilterLabel;

    @FindBy(xpath = "//div[@class='x-flyout']/button")
    WebElementFacade btnShowDropdownSortByPrice;

    @FindBy(css = "div.srp-controls__count h1 span:first-child")
    WebElementFacade resultCount;

    @FindBy(xpath = "//div[@class='srp-controls--selected-value']")
    WebElementFacade sortByPriceLabel;


    public void changeResultsOrder2List() {
        if (gridOrderIcon.isPresent()){
            action.moveToElement(btnShowDropdownOrderOption).perform();
            btnChangeResultsOrder.click();
        }
    }



    public void selectbrand(String brand) {
        if (!goOverArrays(new ArrayList<>( getDataAsObjectList(BRAND_LIST_BUTTONS)), brand)) {
            throw new NoSuchElementException();
        }
    }

    public boolean validateSelectedBrand(String brand2validate) {
        return (appliedBrandFilterLabel.getAttribute("textContent")).toLowerCase().contains(brand2validate);
    }

    public void selectShoeSize(String size) {
        if (!goOverArrays(new ArrayList<>(getDataAsObjectList(SIZE_LIST_BUTTONS)), size)) {
            throw new NoSuchElementException();
        }
    }

    public void resultSortByPriceOptions(String option) {
        if (!sortByPriceLabel.getAttribute("text-content").contains("Precio + Envío: más alto primero")){
            action.moveToElement(btnShowDropdownSortByPrice).perform();
        }



        if (!goOverArrays(new ArrayList<>(getDataAsObjectList(RESULT_PRICE_SORT_OPTIONS)), option)) {
            throw new NoSuchElementException();
        }
    }

    private Product[] obtainProducts() {
        List<WebElementFacade> productsList = getDataAsObjectList(PRODUCTS);
        Product[] createdProducts = new Product[5];
        WebElement title;
        WebElement price;
        for (int i = 0; i < 5; i++) {
            title = productsList.get(i).findElement(By.cssSelector("a.s-item__link h3"));
            price = productsList.get(i).findElement(By.cssSelector("div.s-item__detail--primary:first-child span.s-item__price:first-child "));
            createdProducts[i] = new Product(title.getText(), getPrice(price.getText()));
        }
        Arrays.sort(createdProducts);
        return createdProducts;
    }

    private boolean goOverArrays(ArrayList<WebElementFacade> list, String string2verify) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAttribute("aria-label").equalsIgnoreCase(string2verify)) {
                list.get(i).click();
                return true;
            }
        }
        return false;
    }

    public String getResultCount() {
        return resultCount.getTextContent();
    }

    private List<WebElementFacade> getDataAsObjectList(String selector) {
        return findAll(selector).stream().map(element -> element).collect(Collectors.toList());
    }

}
