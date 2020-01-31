package shopPages.ebay.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import shopPages.ebay.pageObject.ebayHomePage;
import shopPages.ebay.pageObject.ebayResultPage;

import static utils.Constats.PRICE_SORT_HIGH_TO_LOW;

public class EbayUser {

    String actor;
    ebayHomePage homePage;
    ebayResultPage resultPage;

    @Step("#actor enter to ebay")
    public void enter_to_ebay() {
        homePage.open();
    }

    @Step("#actor enter keywords on he search box: {0}")
    public void enter_keywords(String keywords){
        homePage.enterSearchKeywords(keywords);
    }

    /*
     *  In this testcase we capture the exception thrown by the test case
     *  and we continue the test execution because is not part of the scope
     *  the category selection
     */
    @Step("#actor choose the following category: {0}")
    public void choose_category(String category){
        try{
            homePage.chooseCategory(category);
        }catch (NoSuchElementException ex){
            System.out.println("The category: "+category+"does not exist!");
            System.out.println(ex.getMessage());
        }
    }

    @Step("#actor perform search")
    public void perform_search(){
        homePage.search();
    }

    @Step("#actor order results as a list")
    public void order_resuls_as_a_list(){
        resultPage.changeResultsOrder2List();
    }

    @Step("#actor filter by specific brand: {0}")
    public void filter_by_specific_brand(String brand) {
        resultPage.selectbrand(brand);
        Assert.assertTrue(resultPage.validateSelectedBrand(brand));
    }

    @Step("#actor filter by size")
    public void filter_by_size(String size){
        resultPage.selectShoeSize(size);
    }

    @Step("#actor print the result count")
    public void print_the_result_count(){

        System.out.println("The result Count is: "+resultPage.getResultCount());
    }


    @Step("#actor order price high to low")
    public void order_price_high_to_low(){
        resultPage.resultSortByPriceOptions(PRICE_SORT_HIGH_TO_LOW);
    }




}
