import br.com.dio.desafio.dominio.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Curso — usando construtor completo
        Curso curso1 = new Curso("Curso Java", "Descrição curso Java", 8);
        Curso curso2 = new Curso("Curso JS", "Descrição curso JS", 4);

        // Mentoria — ainda usa setters (não tem construtor completo)
        Mentoria mentoria = new Mentoria();
        mentoria.setTitulo("Mentoria de Java");
        mentoria.setDescricao("Descrição mentoria Java");
        mentoria.setData(LocalDate.now());

        // Desafio — ainda usa setters (não tem construtor completo)
        Desafio desafio = new Desafio();
        desafio.setTitulo("Desafio POO");
        desafio.setDescricao("Implementar sistema bancário");
        desafio.setNivel("dificil");
        desafio.setDuracaoEmDias(7);

        // Bootcamp — usando construtor com sets vazios e adicionarConteudo()
        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Descrição Bootcamp Java Developer");
        bootcamp.adicionarConteudo(curso1);
        bootcamp.adicionarConteudo(curso2);
        bootcamp.adicionarConteudo(mentoria);
        bootcamp.adicionarConteudo(desafio);

        // --- Camila ---
        Dev devCamila = new Dev();
        devCamila.setNome("Camila");
        devCamila.inscreverBootcamp(bootcamp);

        System.out.println("=== Camila ===");
        System.out.println("Conteúdos Inscritos: " + devCamila.getConteudosInscritos());

        devCamila.progredir();
        devCamila.progredir();

        System.out.println("-");
        System.out.println("Conteúdos Inscritos: "  + devCamila.getConteudosInscritos());
        System.out.println("Conteúdos Concluídos: " + devCamila.getConteudosConcluidos());
        System.out.println("XP Total: "             + devCamila.calcularTotalXp());
        System.out.println("Nível: "                + devCamila.getNivel().getNivel());
        System.out.println("XP atual no nível: "    + devCamila.getNivel().getXpAtual());
        System.out.println("XP p/ próximo nível: "  + devCamila.getNivel().getXpParaProximoNivel());

        System.out.println();

        // --- João ---
        Dev devJoao = new Dev();
        devJoao.setNome("Joao");
        devJoao.inscreverBootcamp(bootcamp);

        System.out.println("=== João ===");
        System.out.println("Conteúdos Inscritos: " + devJoao.getConteudosInscritos());

        devJoao.progredir();
        devJoao.progredir();
        devJoao.progredir();
        devJoao.progredir(); // completa o desafio também

        System.out.println("-");
        System.out.println("Conteúdos Inscritos: "  + devJoao.getConteudosInscritos());
        System.out.println("Conteúdos Concluídos: " + devJoao.getConteudosConcluidos());
        System.out.println("XP Total: "             + devJoao.calcularTotalXp());
        System.out.println("Nível: "                + devJoao.getNivel().getNivel());
        System.out.println("XP atual no nível: "    + devJoao.getNivel().getXpAtual());
        System.out.println("XP p/ próximo nível: "  + devJoao.getNivel().getXpParaProximoNivel());
    }
}