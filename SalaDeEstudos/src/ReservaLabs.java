/*
 * 
 * @author Arthur
 */

import java.util.HashMap;
import java.util.Map;
/*
 * Para quê tantos códigos, 
 * Se a vida não é programada
 * E as melhores coisas não tem lógica?
 */
public class ReservaLabs {
    private static Map<String, Pilha> reservas = new HashMap<>();
    private static String[][] Labs = new String[7][3];

    public static void main(String[] args) {
        Labs[0][0] = "L/ H";
        Labs[1][0] = "07:00";
        Labs[2][0] = "08:40";
        Labs[3][0] = "10:35";
        Labs[4][0] = "13:00";
        Labs[5][0] = "14:40";
        Labs[6][0] = "16:35";
        Labs[0][1] = "L3";
        Labs[0][2] = "LES";

        imprimirMatriz(Labs);
        System.out.println();

        addReserva("M1", "L3", "08:40");
        addReserva("M1", "L3", "10:35");
        addReserva("M1", "LES", "13:00");
        addReserva("M2", "LES", "14:40");
        addReserva("M2", "L3", "16:35");

        desfazerReserva("M1");
        desfazerReserva("M2");

        addReserva("M2", "LES", "16:35");

        salvaReservas("M1");
        salvaReservas("M2");

        imprimirMatriz(Labs);
    }

    public static void imprimirMatriz(String[][] matriz) {
        final int linhas = matriz.length;
        final int colunas = matriz[0].length;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j] != null && !matriz[i][j].isEmpty()) {

                } else {
                    matriz[i][j] = "0";
                }
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void addReserva(String matricula, String lab, String horario) {
        // Verifica se o mapa já possui uma pilha para a matrícula
        if (!reservas.containsKey(matricula)) {
            reservas.put(matricula, new Pilha());
        }

        Pilha pilhaReservas = reservas.get(matricula);

        // Verifica se o monitor já tem uma reserva no mesmo horário
        if (!verificaHorarioDisponivel(pilhaReservas, horario)) {
            System.out.println("Erro: O monitor " + matricula + " já possui uma reserva para o horário " + horario);
            return;
        }

        // Adiciona a reserva à pilha correspondente
        pilhaReservas.empilha(lab + " " + horario);
    }

    private static boolean verificaHorarioDisponivel(Pilha pilhaReservas, String horario) {
        // Verifica se o monitor já tem uma reserva no mesmo horário
        for (int i = 0; i < pilhaReservas.tamanho(); i++) {
            try {
                String reserva = (String) pilhaReservas.desempilha();
                if (reserva.endsWith(horario)) {
                    // Devolve o valor desempilhado para manter a pilha inalterada
                    pilhaReservas.empilha(reserva);
                    return false;
                }
                pilhaReservas.empilha(reserva);
            } catch (Exception e) {
                System.err.println("Erro ao verificar horário disponível: " + e.getMessage());
            }
        }
        return true;
    }

    public static void desfazerReserva(String matricula) {
        if (reservas.containsKey(matricula) && !reservas.get(matricula).vazia()) {
            try {
                reservas.get(matricula).desempilha();
            } catch (Exception e) {
                System.err.println("Erro ao desempilhar: " + e.getMessage());
            }
        }
    }

    public static void salvaReservas(String matricula) {
        // Verifica se o monitor tem reservas
        if (reservas.containsKey(matricula)) {
            Pilha pilhaReservas = reservas.get(matricula);

            // Processa cada reserva na pilha
            while (!pilhaReservas.vazia()) {
                try {
                    String reserva = (String) pilhaReservas.desempilha();

                    // Extrai informações da reserva
                    String[] partes = reserva.split(" ");
                    String lab = partes[0];
                    String horario = partes[1];

                    // Encontra as posições na matriz Labs correspondentes ao laboratório e horário
                    int linha = encontraIndiceHorario(horario);
                    int coluna = encontraIndiceLaboratorio(lab);

                    // Salva o nome do monitor na matriz Labs
                    Labs[linha][coluna] = matricula;
                } catch (Exception e) {
                    System.err.println("Erro ao processar reserva: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Erro: O monitor " + matricula + " não possui reservas a serem salvas.");
        }
    }

    private static int encontraIndiceHorario(String horario) {
        switch (horario) {
            case "07:00":
                return 1;
            case "08:40":
                return 2;
            case "10:35":
                return 3;
            case "13:00":
                return 4;
            case "14:40":
                return 5;
            case "16:35":
                return 6;
            default:
                return -1; 
        }
    }

    private static int encontraIndiceLaboratorio(String lab) {
        switch (lab) {
            case "L3":
                return 1;
            case "LES":
                return 2;
            default:
                return -1;
        }
    }

    static class Pilha { // Com estruturas auto-referenciadas
        private static class Celula {
            Object item;
            Celula prox;
        }

        private Celula topo;
        private int tam;

        // Operações
        public Pilha() { // Cria uma Pilha vazia
            this.topo = null;
            this.tam = 0;
        }

        void empilha(Object x) {
            Celula aux = this.topo;
            this.topo = new Celula();
            this.topo.item = x;
            this.topo.prox = aux;
            this.tam++;
        }

        public boolean vazia() {
            return (this.topo == null);
        }

        public int tamanho() {
            return this.tam;
        }

        public Object desempilha() throws Exception {
            if (this.vazia())
                throw new Exception("Erro : A pilha esta vazia");

            Object item = this.topo.item;

            this.topo = this.topo.prox;
            this.tam--;
            return item;
        }
    }
}
