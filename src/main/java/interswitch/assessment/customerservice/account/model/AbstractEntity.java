package interswitch.assessment.customerservice.account.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public class AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @Version
    protected int version;

    protected String delFlag = "N";

    private Date dateCreated;

    private String clientId;

    private String pecunia_id;


}
