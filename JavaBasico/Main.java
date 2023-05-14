package JavaBasico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import JavaBasico.entities.Studant;
import JavaBasico.builders.StudentsBuilder;

public class Main {

    public static void main(String[] args) {
        var allStudents = StudentsBuilder.getAllStudents();

        // Agora vamos as atividades
        /*

        1. Recupere da lista os alunos que passaram de ano (nota minima 7.0).
            - Exiba os dados nesse formato: <código> - <nome> : Média = <nota>
        2. Recupere da lista os alunos que não passaram de ano.
            - Exiba os dados nesse formato: <código> - <nome> : Média = <media> (Faltou = <nota_faltante>)
        3. Traga os alunos que tiraram a nota máxima (nota 10).
            - Exiba os dados nesse formato: <código> - <nome>
        4. Traga o aluno que tirou a menor nota, em caso de notas iguais, traga ambos os alunos.
            - Exiba os dados nesse formato: <código> - <nome> : Nota = <nota>
        5. Faça uma lista com top 3 notas de alunos. Em caso de notas iguais coloque todos na mesma posição.
            - Ex:
                1º - Fulano : Nota = 10.0;
                   - Beltrano : Nota = 10.0;
                2º - Joãozinho : Nota = 9.0;
                3º - Mariazinha : Nota = 8.9;
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>
        6. Faça uma lista com as 3 menores notas de alunos. Em caso de notas iguais coloque todos na mesma posição. Exemplo igual a anterior
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>
        7. Monte a média de todos os alunos e exiba em tela ordenando da maior para a menor nota.
            - Exiba os dados nesse formato: <posicao> - <código> - <nome> : Média = <nota>

         */

        Scanner java = new Scanner(System.in);

        System.out.println("Digite qual das operações deseja acionar: 1- Alunos que passaram; 2- Alunos que não passaram; 3- Alunos que tiraram a nota máxima; 4- Aluno que tirou a menor nota; 5- Top 3 notas de alunos; 6- As 3 menores notas de alunos; 7- Média de todos os alunos");
        int resp = java.nextInt();

        switch(resp) {
            case 1:

                List<Studant> listaAprovados = new ArrayList<>();
            
                for (Studant studant : allStudents) {
                    if (((studant.getTestOne() + studant.getTestTwo() + studant.getTestThree()) / 3) >= 7) {
                        listaAprovados.add(studant);
                    }
                }
            
                for (Studant studantA : listaAprovados) {
                    System.out.println("<" + studantA.getCode() + "> - <" + studantA.getName() + "> : Média = <" + ((studantA.getTestOne() + studantA.getTestTwo() + studantA.getTestThree()) / 3) + ">");
                }
                break;

            case 2:
                List<Studant> listaReprovados = new ArrayList<>();
        
                for (Studant studant : allStudents) {
                    if (((studant.getTestOne() + studant.getTestTwo() + studant.getTestThree()) / 3) < 7) {
                        listaReprovados.add(studant);
                    }
                }
        
                for (Studant studantA : listaReprovados) {
                    System.out.println("<" + studantA.getCode() + "> - <" + studantA.getName() + "> : Média = <" + ((studantA.getTestOne() + studantA.getTestTwo() + studantA.getTestThree()) / 3) + "> (Faltou = " + (7 - ((studantA.getTestOne() + studantA.getTestTwo() + studantA.getTestThree()) / 3))+ " )");
                }
                break;
                
            case 3:
                List<Studant> lista10 = new ArrayList<>();
        
                for (Studant studant : allStudents) {
                    if (((studant.getTestOne() + studant.getTestTwo() + studant.getTestThree()) / 3) == 10) {
                        lista10.add(studant);
                    }
                }
        
                for (Studant studantA : lista10) {
                    System.out.println("<" + studantA.getCode() + "> - <" + studantA.getName() + ">");
                }
                break;

            case 4:
                List<Studant> alunoComMenorNota = new ArrayList<>();
                float minGrade = Float.MAX_VALUE;
        
                for (Studant studant : allStudents) {
                    
                    Float studentMinGrade = Math.min(studant.getTestOne(), Math.min(studant.getTestTwo(), studant.getTestThree()));
        
                    if (studentMinGrade < minGrade) {
                        minGrade = studentMinGrade;
                        alunoComMenorNota.clear();
                        alunoComMenorNota.add(studant);
                    }
                    else if (studentMinGrade == minGrade) {
                        alunoComMenorNota.add(studant);
                    }
        
                }
                for (Studant student : alunoComMenorNota) {
                    System.out.println(student.getCode() + " - " + student.getName() + " : Nota = " + minGrade);
                }
                    break;

            case 5:
                List<Studant> sortedStudents = allStudents.stream()
                .sorted(Comparator.comparingDouble(Studant::average).reversed())
                .toList();
        
                System.out.println("Top 3 notas:");
                double lastGrade = Double.POSITIVE_INFINITY;
        
                for (int i = 0; i < 3 && i < sortedStudents.size(); i++) {
                    
                    Studant student = sortedStudents.get(i);
                    double grade = student.average();
                    String position = (grade < lastGrade) ? (i + 1) + "º" : "=";
                    
                    System.out.printf("%s - %s: Nota = %.1f\n", position, student.getName(), grade);
                    lastGrade = grade;
                }
                break;

            case 6:
                    List<Studant> lowestGrades = new ArrayList<>(allStudents);
                    Collections.sort(lowestGrades, Comparator.comparing(Studant::average));
            
                    System.out.println("As 3 menores notas são:");
                    for (int i = 0; i < 3 && i < lowestGrades.size(); i++) {
                        Studant student = lowestGrades.get(i);
                        System.out.printf("%dº - %s: Nota = %.1f%n", i + 1, student.getName(), student.average());
                    }
                    break;
                    
                case 7:
                    allStudents.stream()
                            .sorted(Comparator.comparingDouble(Studant::average).reversed())
                            .forEach(student -> System.out.printf("%d - %s - %s : Média = %.2f%n",
                                    allStudents.indexOf(student) + 1, student.getCode(),
                                    student.getName(), student.average()));
                    break;
            default: 
                System.out.println("Opção inválida");
        }
    }
}