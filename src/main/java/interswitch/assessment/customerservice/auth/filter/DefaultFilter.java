package interswitch.assessment.customerservice.auth.filter;

import java.net.URL;

public class DefaultFilter {

        boolean getDefaultFilter(String value){
        try{
            boolean result =false;

            URL defaultCustomerUrl=new URL("http://192.168.1.137:8090/api/v1/login/customer");
            URL createurl=new URL("http://192.168.1.137:8090/api/v1/account/add");
            URL requestUrl=new URL(value);

            if((requestUrl.equals(defaultCustomerUrl))||(requestUrl.equals(createurl))){
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }
}
