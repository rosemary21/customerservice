package interswitch.assessment.customerservice.auth.filter;

import java.net.URL;

public class DefaultFilter {

        boolean getDefaultFilter(String value){
        try{
            boolean result =false;

            URL defaultCustomerUrl=new URL(" http://localhost:8090/api/v1/login/api/v1/login/customer");

            URL requestUrl=new URL(value);

            if((requestUrl.equals(defaultCustomerUrl))){
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }
}
