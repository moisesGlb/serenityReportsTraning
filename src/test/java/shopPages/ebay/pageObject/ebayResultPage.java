package shopPages.ebay.pageObject;

import entities.Product;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static utils.utilities.getPrice;

public class ebayResultPage extends PageObject {

    @FindBy(xpath = "//div[@id='w10']/button[@class='x-flyout__button']")
    WebElementFacade btnShowDropdownOrderOption;

    @FindBy(xpath = "//div[@id='w10-w0']/a/div/span")
    WebElementFacade btnChangeResultsOrder;

    @FindBy(xpath = "//input[@class='x-searchable-list__textbox__aspect-Brand']")
    WebElementFacade brandsSearchBox;

    @FindAll(@FindBy(xpath = "//li[@class='x-refine__main__list--value']//input"))
    ArrayList<WebElementFacade> brandListButtons;

    @FindBy(xpath = "//li[@class='srp-carousel-list__item srp-multi-aspect__item--applied']//a/div")
    WebElementFacade appliedBrandFilterLabel;

   @FindAll(@FindBy(xpath = "//div[@id='x-refine__group_1__3']/ul//input"))
    ArrayList<WebElementFacade> sizeListButtons;

   @FindBy(css = "div.srp-controls__count h1 span:first-child")
   WebElementFacade resultCount;

   @FindAll(@FindBy(css = "ul.srp-sort__menu li span"))
   ArrayList<WebElementFacade> resultPriceSortOptions;

    @FindAll(@FindBy(css = "div.s-item__info"))
    ArrayList<WebElement> products;

    public void changeResulsOrder2List(){
        changeResultsOrder("list");
    }

    public void changeResulsOrder2Gallery(){
        changeResultsOrder("gallery");
    }

    public void selectbrand(String brand){
        if (!goOverArrays((WebElementFacade[]) brandListButtons.toArray(),brand)){
            throw new NoSuchElementException();
        }
    }

    public boolean validateSelectedBrand(String brand2validate){
        return appliedBrandFilterLabel.getAttribute("textContent").equalsIgnoreCase(brand2validate);
    }

    public void selectShoeSize(String size){
        if (!goOverArrays((WebElementFacade[]) sizeListButtons.toArray(),size)){
            throw new NoSuchElementException();
        }
    }

    public void resultSortByPriceOptions(String option){
        if (!goOverArrays((WebElementFacade[]) resultPriceSortOptions.toArray(),option)){
            throw new NoSuchElementException();
        }
    }

    private Product[] obtainProducts(){
        Product[] createdProducts = new Product[5];
        WebElement title;
        WebElement price;
        for (int i=0; i<5;i++){
            title = products.get(i).findElement(By.cssSelector("a.s-item__link h3"));
            price = products.get(i).findElement(By.cssSelector("div.s-item__detail--primary:first-child span.s-item__price:first-child "));
            createdProducts[i] = new Product(title.getText(),getPrice(price.getText()));
        }
        Arrays.sort(createdProducts);
        return createdProducts;
    }

    private void changeResultsOrder(String order){
        switch (order){
            case "list":
                if(!getCurrentOrder().contains("list")){ changeOrder(); }
                break;
            case "gallery":
                if(getCurrentOrder().contains("list")){ changeOrder(); }
                break;
        }
    }

    private String getCurrentOrder(){
        return btnChangeResultsOrder.getText().toLowerCase();
    }

    private void changeOrder(){
        btnShowDropdownOrderOption.click();
        btnChangeResultsOrder.click();
    }

    private boolean goOverArrays(WebElementFacade[] array, String string2verify){
        for(int i = 0; i< array.length; i++){
            if (array[i].getAttribute("aria-label").equalsIgnoreCase(string2verify)){
                array[i].click();
                return true;
            }
        }
        return false;
    }

    public String getResultCount(){
        return resultCount.getTextContent();
    }

}
