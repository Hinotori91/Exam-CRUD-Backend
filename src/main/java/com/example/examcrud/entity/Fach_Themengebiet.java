package com.example.examcrud.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(Fach_Themengebiet_PK.class)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fach_Themengebiet {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_fach")
    @JsonManagedReference
    private Fach fach;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_themengebiet")
    @JsonManagedReference
    private Themengebiet themengebiet;
}
