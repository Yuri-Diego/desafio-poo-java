package br.com.dio.desafio.dominio;

public class Nivel {
    private int nivel;
    private double xpAtual;
    private double xpParaProximoNivel;

    private static final double XP_BASE = 100d;
    private static final double MULTIPLICADOR = 1.5d;

    public Nivel() {
        this.nivel = 1;
        this.xpAtual = 0;
        this.xpParaProximoNivel = XP_BASE;
    }

    public void adicionarXp(double xp) {
        this.xpAtual += xp;
        verificarLevelUp();
    }

    private void verificarLevelUp() {
        while (xpAtual >= xpParaProximoNivel) {
            xpAtual -= xpParaProximoNivel;
            nivel++;
            xpParaProximoNivel = XP_BASE * Math.pow(MULTIPLICADOR, nivel - 1);
            System.out.println("Level up! Agora você é nível " + nivel
                    + " | Próximo nível: " + String.format("%.1f", xpParaProximoNivel) + " XP");
        }
    }

    public int getNivel() { return nivel; }
    public double getXpAtual() { return xpAtual; }
    public double getXpParaProximoNivel() { return xpParaProximoNivel; }

    @Override
    public String toString() {
        return "Nivel{" +
                "nivel=" + nivel +
                ", xpAtual=" + xpAtual +
                ", xpParaProximoNivel=" + xpParaProximoNivel +
                '}';
    }
}