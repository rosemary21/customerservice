package interswitch.assessment.customerservice.bvn.web.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BvnDetailsResp {

    private String status;
    @JsonProperty("detail")
    private String message;
    @JsonProperty("response_code")
    private String responseCode;

    private BvnData data;
}
