package model;

public class Prato {
    private final int id;
    private final String nome;
    private final double tempoMinimo;
    private final double tempoMaximo;
    private double tempoCozimento;
    
    public Prato(int id, String nome, double tempoMinimo, double tempoMaximo) {
        this.id = id;
        this.nome = nome;
        this.tempoMinimo = tempoMinimo;
        this.tempoMaximo = tempoMaximo;
        this.tempoCozimento = calcularTempoCozimento();
    }
    
    private double calcularTempoCozimento() {
        return tempoMinimo + Math.random() * (tempoMaximo - tempoMinimo);
    }
    
    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getTempoCozimento() { return tempoCozimento; }
    public double getTempoMinimo() { return tempoMinimo; }
    public double getTempoMaximo() { return tempoMaximo; }
    
    public boolean isImpar() {
        return id % 2 != 0;
    }
}