package org.example;

import org.example.models.Atividade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnaliseAlg {
    private static final List<Atividade> atividadeList = new ArrayList<>();

    public static void inserirDados() {
        atividadeList.add(
                Atividade.builder()
                        .nome("Preparar os tornos")
                        .duracao(8)
                        .numero(1)
                        .build());
        atividadeList.add(
                Atividade.builder()
                        .nome("Preparar as embalagens")
                        .duracao(8)
                        .numero(2)
                        .build()
        );
        atividadeList.add(
                Atividade.builder()
                        .nome("Cortar e distribuir o PVC")
                        .duracao(10)
                        .numero(3)
                        .build()
        );
        atividadeList.add(
                Atividade.builder()
                        .nome("Cortar e distribuir o aço")
                        .duracao(12)
                        .numero(4)
                        .build()
        );
        atividadeList.add(
                Atividade.builder()
                        .nome("Tornear a peça A")
                        .dependecias(Arrays.asList(atividadeList.get(0), atividadeList.get(2)))
                        .duracao(8)
                        .numero(5)
                        .build()
        );
        atividadeList.add(
                Atividade.builder()
                        .nome("Tornear a peça B")
                        .dependecias(Arrays.asList(atividadeList.get(0), atividadeList.get(2)))
                        .duracao(11)
                        .numero(6)
                        .build()
        );
        atividadeList.add(
                Atividade.builder()
                        .nome("Tornear a peça C")
                        .dependecias(Arrays.asList(atividadeList.get(0), atividadeList.get(3)))
                        .duracao(15)
                        .numero(7)
                        .build()
        );
        atividadeList.add(
                Atividade.builder()
                        .nome("Rosquear a peça A")
                        .dependecias(Collections.singletonList(atividadeList.get(4)))
                        .duracao(9)
                        .numero(8)
                        .build()
        );
        atividadeList.add(
                Atividade.builder()
                        .nome("Rosquear a peça B")
                        .dependecias(Collections.singletonList(atividadeList.get(5)))
                        .duracao(7)
                        .numero(9)
                        .build()
        );
        atividadeList.add(
                Atividade.builder()
                        .nome("Montar as peças A e C")
                        .dependecias(Arrays.asList(atividadeList.get(6), atividadeList.get(7)))
                        .duracao(4)
                        .numero(10)
                        .build()
        );
        atividadeList.add(
                Atividade.builder()
                        .nome("Montar a peça B")
                        .dependecias(Arrays.asList(atividadeList.get(8), atividadeList.get(9)))
                        .duracao(6)
                        .numero(11)
                        .build()
        );
        atividadeList.add(
                Atividade.builder()
                        .nome("Embalar e armazenar")
                        .dependecias(Arrays.asList(atividadeList.get(1), atividadeList.get(10)))
                        .duracao(7)
                        .numero(12)
                        .build()
        );
    }

    public static void main(String[] args) {
        inserirDados();

        // calcular valor mais Cedo -> O(n^2)
        for (Atividade atividade : atividadeList) // n
            for (Atividade atividadeDependente : atividade.getDependecias()) // n - 1
                atividade.setValorMaisCedo(Math.max(atividade.getValorMaisCedo(), atividadeDependente.getDuracao() + atividadeDependente.getValorMaisCedo()));

        // calcular valor mais tarde -> O(1)
        Atividade ativ = atividadeList.get(atividadeList.size() - 1); // CONSTANTE
        ativ.setValorMaisTarde(ativ.getValorMaisCedo()); // CONSTANTE
        
        // O(n^2)
        for (int i = atividadeList.size() - 1; i >= 0; i--) { // n
                Atividade atividade = atividadeList.get(i);
        
                for (Atividade atividadeDependente : atividade.getDependecias()) // n - 1
                        atividadeDependente.setValorMaisTarde(Math.min(atividade.getValorMaisTarde() - atividadeDependente.getDuracao(), atividadeDependente.getValorMaisTarde()));
        }
        // Tempo minímo
        System.out.println("Tempo mínimo: " + (atividadeList.get(atividadeList.size() - 1).getValorMaisCedo() +  atividadeList.get(atividadeList.size() - 1).getDuracao()));
        System.out.println();
        
        // Encontrar tarefas criticas
        System.out.println("Tarefas críticas: ");
        for (Atividade atividade : atividadeList)
                if (atividade.getValorMaisTarde() - atividade.getValorMaisCedo() == 0)
                        System.out.println(atividade.getNumero() + ". " + atividade.getNome() + " - Duração: " + atividade.getDuracao());

        // Encontrar tarefas não criticas
        System.out.println("\nTarefas não críticas: ");
        for (Atividade atividade : atividadeList)
            if (atividade.getValorMaisTarde() - atividade.getValorMaisCedo() != 0)
                System.out.println(atividade.getNumero() + ". " + atividade.getNome() + " - Folga: " + (atividade.getValorMaisTarde() - atividade.getValorMaisCedo()));
        
    }
}
