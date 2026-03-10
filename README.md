# 🚀 Bootcamp POO — Melhorias Aplicadas

Sistema de gerenciamento de Bootcamp desenvolvido em Java, evoluído com boas práticas de Programação Orientada a Objetos.

---

## 📦 Estrutura do Projeto

```
src/
└── br/com/dio/desafio/dominio/
    ├── Conteudo.java      # Classe abstrata base
    ├── Curso.java         # Herda Conteudo
    ├── Mentoria.java      # Herda Conteudo
    ├── Desafio.java       # Herda Conteudo (novo)
    ├── Bootcamp.java      # Agrega Conteudos e Devs
    ├── Dev.java           # Representa o desenvolvedor
    └── Nivel.java         # Gerencia XP e level-up (novo)
```

---

## ✅ Melhorias Implementadas

### 1. 🔒 Encapsulamento — Proteção das Coleções Internas

**Onde:** `Bootcamp.java`

**O que foi feito:** O getter `getConteudos()` passou a retornar uma cópia imutável com `Collections.unmodifiableSet()`. A adição de conteúdos foi delegada a um método com responsabilidade clara.

```java
public Set<Conteudo> getConteudos() {
    return Collections.unmodifiableSet(conteudos);
}

public void adicionarConteudo(Conteudo conteudo) {
    this.conteudos.add(conteudo);
}
```

**Por quê:** Encapsulamento real não é só ter getter/setter — é controlar *como* o estado interno muda. Sem isso, qualquer classe externa poderia modificar a coleção livremente.

---

### 2. 🏗️ Construtores — Objetos Válidos desde a Criação

**Onde:** `Curso.java`, `Bootcamp.java`

**O que foi feito:** Adicionados construtores que recebem os campos obrigatórios, eliminando a necessidade de setters linha a linha no `Main`.

```java
// Antes
Curso curso1 = new Curso();
curso1.setTitulo("curso java");
curso1.setDescricao("descrição curso java");
curso1.setCargaHoraria(8);

// Depois
Curso curso1 = new Curso("Curso Java", "Descrição curso Java", 8);
```

**Por quê:** Um objeto deve nascer já em estado válido. Evita NullPointerException e uso indevido por esquecer de setar algum campo.

---

### 3. 🎲 Nova Classe — `Desafio`

**Onde:** `Desafio.java` (classe nova)

**O que foi feito:** Criado um novo tipo de `Conteudo` com campo `nivel` (fácil/médio/difícil) e `duracaoEmDias`, com XP variável de acordo com a dificuldade.

```java
public double calcularXp() {
    switch (nivel) {
        case "facil":   return XP_PADRAO * 1.5;
        case "medio":   return XP_PADRAO * 2.5;
        case "dificil": return XP_PADRAO * 4;
        default:        return XP_PADRAO;
    }
}
```

**Por quê:** Exercita **herança** e **polimorfismo** — `Dev.progredir()` não precisa saber o tipo do conteúdo, apenas chama `calcularXp()` e o comportamento correto é executado automaticamente.

---

### 4. 🏆 Nova Classe — `Nivel`

**Onde:** `Nivel.java` (classe nova)

**O que foi feito:** Criada classe responsável por gerenciar o nível do Dev. A cada XP ganho, verifica se houve level-up. O XP necessário para subir de nível cresce exponencialmente a cada nível.

```java
// Fórmula de progressão
xpParaProximoNivel = XP_BASE * Math.pow(MULTIPLICADOR, nivel - 1);
// Nível 1 → 100 XP | Nível 2 → 150 XP | Nível 3 → 225 XP ...
```

**Por quê:** Aplica o princípio **SRP (Single Responsibility Principle)** — `Nivel` é responsável *apenas* por saber quando e como subir de nível. `Dev` não precisa conhecer essa lógica.

---

### 5. 🔗 Composição em `Dev` — Usando `Nivel`

**Onde:** `Dev.java`

**O que foi feito:** `Dev` passou a ter um objeto `Nivel` por composição, e um campo `xpTotal` separado para acumular o histórico total de XP.

```java
private Nivel nivel = new Nivel();
private double xpTotal = 0;

public void progredir() {
    // ...
    this.xpTotal += xpGanho;        // histórico total
    this.nivel.adicionarXp(xpGanho); // progressão de nível
}
```

**Por quê:** `Dev` *tem um* nível, não *é um* nível — **composição** é mais flexível que herança aqui. Além disso, XP total (histórico) e XP do nível atual são conceitos distintos e ficam em lugares distintos.

---

### 6. ✅ Validação nos Setters

**Onde:** `Curso.java`, `Bootcamp.java`

**O que foi feito:** Setters passaram a lançar exceção para valores inválidos.

```java
public void setCargaHoraria(int cargaHoraria) {
    if (cargaHoraria <= 0) {
        throw new IllegalArgumentException("Carga horária deve ser positiva.");
    }
    this.cargaHoraria = cargaHoraria;
}
```

**Por quê:** A classe deve ser responsável pela sua própria integridade de dados — ninguém de fora deveria conseguir criar um `Curso` com carga horária negativa.

---

## 📐 Conceitos de POO Aplicados

| Melhoria | Conceito |
|---|---|
| `Collections.unmodifiableSet` + `adicionarConteudo` | Encapsulamento |
| Construtores obrigatórios | Encapsulamento + Design por Contrato |
| Validação nos setters | Encapsulamento + SRP |
| Classe `Desafio` herdando `Conteudo` | Herança + Polimorfismo |
| `calcularXp()` diferente por tipo | Polimorfismo |
| `Dev` possui `Nivel` | Composição |
| `Nivel` isolado em sua própria classe | SRP (SOLID) |

---

## ▶️ Exemplo de Saída

```
=== João ===
Conteúdos Inscritos: [Curso Java, Curso JS, Mentoria de Java, Desafio POO]
Level up! Agora você é nível 2 | Próximo nível: 150.0 XP
-
Conteúdos Inscritos: []
Conteúdos Concluídos: [Curso Java, Curso JS, Mentoria de Java, Desafio POO]
XP Total: 210.0
Nível: 2
XP atual no nível: 110.0
XP p/ próximo nível: 150.0
```