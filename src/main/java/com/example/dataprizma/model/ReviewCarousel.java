package com.example.dataprizma.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "reviewCarousel")
public class ReviewCarousel {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarouselId")
    private RevCarousel revCarousel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReviewId")
    private Review review;

}
