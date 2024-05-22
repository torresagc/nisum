package com.app.nisum.users.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {
    @Id
    private UUID id;
    private String number;
    private String cityCode;
    private String countryCode;
    @ManyToOne
    @JoinColumn(name = "user_id",  referencedColumnName = "id")
    private User user;
}
