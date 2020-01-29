package utils;

public class utilities {

    public static float getPrice(String price) throws NumberFormatException{
        String finalValue = price;
        String[] prices;
        float finalPrice = 0;
        if (!price.isEmpty() || price != null ){

          if (price.contains("a") || price.contains("to")){
              prices = price.split("a");
              finalValue = prices[0];
          }
          if (price.contains("USD")){
              finalValue = finalValue.replace("USD","");
          }
          if (price.contains(" ")){
              finalValue = finalValue.replace(" ","");
          }
          finalPrice = tryParseFloat(finalValue);

        }
        return finalPrice;
    }

    public static float tryParseFloat(String number){
        try{
            return Float.parseFloat(number);
        }catch (NumberFormatException e ){
            e.printStackTrace();
        }
        return 0;
    }


}
