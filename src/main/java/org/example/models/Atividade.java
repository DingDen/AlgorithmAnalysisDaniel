package org.example.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Atividade {
    private String nome;
    private int duracao;
    private int numero;
    @Builder.Default
    private List<Atividade> dependecias = new ArrayList<>();
    private int valorMaisCedo;
    @Builder.Default
    private int valorMaisTarde = Integer.MAX_VALUE;
}
