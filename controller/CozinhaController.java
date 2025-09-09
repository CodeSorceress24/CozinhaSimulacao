package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import model.Prato;
import view.CozinhaView;

public class CozinhaController {
    private static final int TOTAL_PRATOS = 5;
    private static final double TEMPO_ENTREGA = 0.5;
    private static final double INTERVALO_ATUALIZACAO = 0.1;
    
    private final Semaphore semaforoEntrega;
    private final CozinhaView view;
    private final Random random;
    
    public CozinhaController() {
        this.semaforoEntrega = new Semaphore(1);
        this.view = new CozinhaView();
        this.random = new Random();
    }
    
    public void iniciarSimulacao() {
        view.mostrarCabecalho(TOTAL_PRATOS);
        
        List<Thread> threadsPratos = new ArrayList<>();
        
        // Criar e iniciar threads para cada prato
        for (int i = 1; i <= TOTAL_PRATOS; i++) {
            final int pratoId = i;
            Thread thread = new Thread(() -> cozinharPrato(pratoId));
            threadsPratos.add(thread);
            thread.start();
            
            aguardar(100); // Pequeno intervalo entre início dos pratos
        }
        
        // Aguardar todos os pratos terminarem
        for (Thread thread : threadsPratos) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                view.mostrarErro("Erro ao aguardar thread: " + e.getMessage());
            }
        }
    }
    
    private void cozinharPrato(int pratoId) {
        Prato prato = criarPrato(pratoId);
        view.mostrarInicioCozimento(prato);
        
        try {
            simularCozimento(prato);
            entregarPrato(prato);
        } catch (InterruptedException e) {
            view.mostrarErro("Interrupção no cozimento do prato " + pratoId);
        }
    }
    
    private Prato criarPrato(int pratoId) {
        if (pratoId % 2 != 0) {
            return new Prato(pratoId, "Sopa de Cebola", 0.5, 0.8);
        } else {
            return new Prato(pratoId, "Lasanha a Bolonhesa", 0.6, 1.2);
        }
    }
    
    private void simularCozimento(Prato prato) throws InterruptedException {
        double tempoTotal = prato.getTempoCozimento();
        int totalIntervalos = (int) (tempoTotal / INTERVALO_ATUALIZACAO);
        
        for (int intervalo = 1; intervalo <= totalIntervalos; intervalo++) {
            double percentual = (intervalo * 100.0) / totalIntervalos;
            view.mostrarProgressoCozimento(prato, percentual);
            aguardar((int)(INTERVALO_ATUALIZACAO * 1000));
        }
        
        view.mostrarCozimentoConcluido(prato);
    }
    
    private void entregarPrato(Prato prato) throws InterruptedException {
        view.mostrarAguardandoEntrega(prato);
        
        semaforoEntrega.acquire();
        try {
            view.mostrarInicioEntrega(prato);
            aguardar((int)(TEMPO_ENTREGA * 1000));
            view.mostrarEntregaConcluida(prato);
        } finally {
            semaforoEntrega.release();
        }
    }
    
    private void aguardar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}