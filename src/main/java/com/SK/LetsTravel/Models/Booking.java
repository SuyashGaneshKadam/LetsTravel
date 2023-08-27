package com.SK.LetsTravel.Models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data                 //Getter Setter ToString RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    private String seatNos; //Comma separated - E1,E2,B6

    private LocalDate journeyDate;

    @ManyToOne
    @JoinColumn
    private Transport transport;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn
    private User user;
}
