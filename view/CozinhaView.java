package view;

import model.Prato;

public class CozinhaView {
    
    public void mostrarCabecalho(int totalPratos) {
        System.out.println("COZINHA PROFISSIONAL EM AÇÃO!");
        System.out.println("=================================");
        System.out.println("Pratos a preparar: " + totalPratos);
        System.out.println("Regras:");
        System.out.println("• Pratos ÍMPARES: Sopa de Cebola (0.5s - 0.8s)");
        System.out.println("• Pratos PARES: Lasanha a Bolonhesa (0.6s - 1.2s)");
        System.out.println("• Entrega: Apenas 1 prato por vez (0.5s)");
        System.out.println("=================================\n");
    }
    
    public void mostrarInicioCozimento(Prato prato) {
        System.out.println("INÍCIO - Prato " + prato.getId() + 
                         " (" + prato.getNome() + ") - " +
                         String.format("%.2fs", prato.getTempoCozimento()));
    }
    
    public void mostrarProgressoCozimento(Prato prato, double percentual) {
        if (percentual % 25 == 0 || percentual >= 99) {
            System.out.println("Prato " + prato.getId() + " - " + 
                             String.format("%.0f%%", percentual) + " cozido");
        }
    }
    
    public void mostrarCozimentoConcluido(Prato prato) {
        System.out.println("PRONTO - Prato " + prato.getId() + 
                         " (" + prato.getNome() + ") finalizou o cozimento!");
    }
    
    public void mostrarAguardandoEntrega(Prato prato) {
        System.out.println("Prato " + prato.getId() + " aguardando para entrega...");
    }
    
    public void mostrarInicioEntrega(Prato prato) {
        System.out.println("ENTREGANDO - Prato " + prato.getId() + 
                         " (" + prato.getNome() + ") - 0.5s");
    }
    
    public void mostrarEntregaConcluida(Prato prato) {
        System.out.println("ENTREGUE - Prato " + prato.getId() + 
                         " (" + prato.getNome() + ") foi servido!");
        System.out.println("---------------------------------");
    }
    
    public void mostrarErro(String mensagem) {
        System.out.println("ERRO: " + mensagem);
    }
    
    public void mostrarEstatisticas() {
        System.out.println("\nESTATÍSTICAS DA COZINHA");
        System.out.println("• Pratos preparados: 5");
        System.out.println("• Sopas de Cebola: 3 (ímpares)");
        System.out.println("• Lasanhas: 2 (pares)");
        System.out.println("• Entregas: Sequenciais");
    }
}