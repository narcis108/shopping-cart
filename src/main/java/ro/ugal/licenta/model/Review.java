package ro.ugal.licenta.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Data
@Setter
@Getter
public class Review {
    @Id
    @GeneratedValue
    private Long idReview;

    private String textReview;

    @Min(value = 1)
    @Max(value = 5)
    private Integer rating;

    private Date dataReview;

/*
    @ManyToOne()
    private Produs produs;

    @OneToOne()
    private Utilizator utilizator;
*/

}
