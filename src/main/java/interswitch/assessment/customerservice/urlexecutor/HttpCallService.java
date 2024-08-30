package interswitch.assessment.customerservice.urlexecutor;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HttpCallService {



    @Value("${idPass.api.key}")
    private String idPassApiKey;

    @Value("${idPass.api.id}")
    private String idPassApiId;

    public String idVerifyPassUrlCall(String url, String payload){
        log.info("id pass id {}",idPassApiId);
        log.info("id pass key {}",idPassApiKey);
        HttpResponse<String> response = null;
        log.info("ENTRY idVerifyPassUrlCall -> url: {} ",url);
        try {
            response = Unirest.post(url)
                    .header("Content-Type","application/json")
                    .header("x-api-key", idPassApiKey)
                    .header("app-id",idPassApiId)
                    .body(payload)
                    .asString();
        }catch (Exception e){

        }
        return response != null ? response.getBody() : "No_response_from_idVerificationPass";
    }


    public String doBasicGetFlutterWave(String url) throws Exception{
        HttpResponse<String> httpResponse;
        try {
            httpResponse = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer FLWSECK_TEST-7aa34dd66215a7f2fae31ca3763a88e7-X")
                    .asString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage(), e.getCause());
        }
        return  httpResponse.getBody();
    }


    public String doBasicPostFlutterWave(String url, String payload) throws Exception{
        log.info("ENTRY -> doBasicPost Endpoint: {}", url);
        HttpResponse<String> httpResponse;
        try {
            httpResponse = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer FLWSECK_TEST-7aa34dd66215a7f2fae31ca3763a88e7-X")
                    .body(payload)
                    .asString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage(), e.getCause());
        }
        return  httpResponse.getBody();
    }

}
