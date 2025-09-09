import controller.CozinhaController;

public class Main {
    public static void main(String[] args) {
        System.out.println("SIMULAÇÃO DE COZINHA PROFISSIONAL");
        System.out.println("=====================================\n");
        
        CozinhaController controller = new CozinhaController();
        controller.iniciarSimulacao();
        
        System.out.println("\nTodos os pratos foram entregues!");
    }
}