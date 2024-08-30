package interswitch.assessment.customerservice.bvn.web.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BvnData {


    private String bvn;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
    private String registrationDate;
    private String email;
    private String gender;
    private String lgaOfOrigin;
    private String lgaOfResidence;
    private String maritalStatus;
    private String nin;
    private String nameOnCard;
    private String nationality;
    @JsonProperty("phoneNumber1")
    private String phoneNumber;
    private String phoneNumber2;
    private String residentialAddress;
    private String stateOfOrigin;
    private String stateOfResidence;
    private String title;
    private String base64Image;

    @Lob
    private String bvnResponse;


}
