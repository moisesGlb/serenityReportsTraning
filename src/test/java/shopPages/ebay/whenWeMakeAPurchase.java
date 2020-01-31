package shopPages.ebay;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import shopPages.ebay.steps.EbayUser;


@RunWith(SerenityRunner.class)
public class whenWeMakeAPurchase {

    public static final String KEYWORDS_2_SEARCH = "zapatos";
    public static final String CATEGORY = "Ropa, calzado y accesorios";
    public static final String BRAND = "Puma";

    @Steps
    EbayUser jacinto;

    @Managed
    WebDriver driver;

    @Test
    public void performSearchAdSortResults() {
        jacinto.enter_to_ebay();
        jacinto.enter_keywords(KEYWORDS_2_SEARCH);
        jacinto.perform_search();
        jacinto.order_resuls_as_a_list();
        jacinto.filter_by_specific_brand(BRAND);
        jacinto.filter_by_size("10");
        jacinto.print_the_result_count();
        jacinto.order_price_high_to_low();
    }
}
