package com.headlightbackend.data.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "headlights")
public class Headlight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "articul")
    private String articul;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "common_type_id")
    private CommonType commonType;

    @Column(name = "country")
    private String country;

    @Column(name = "fb_side_type")
    private String frontOrBackSideType;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "light_type")
    private String lightType;
    @Column(name = "lamps")
    private String lamps;
    @Column(name = "corrector")
    private String corrector;
    @Column(name = "release_period")
    private String headlightReleasePeriod;
    @Column(name = "lr_side_type")
    private String leftOrRightSideType;
    @Column(name = "sendable_by_ru_post")
    private Boolean sendableByRuPost;
    @Column(name = "replace_origin_number")
    private Boolean isOrigNumberReplaced;
    @Column(name = "pt_complection")
    private String PTComplection;
    @Column(name = "pt_measure")
    private String PTMeasure;
    @Column(name = "tu_in_case")
    private Boolean TUInCase;
    @Column(name = "tu_mold_colour")
    private String TUMoldColour;
    @Column(name = "tu_tuning")
    private String TUTuning;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "generation_id")
    private CarModelGeneration carModelGeneration;

    @Override
    public int hashCode() {
        return id.intValue();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Headlight && Objects.equals(((Headlight) o).getId(), this.id);
    }
}
