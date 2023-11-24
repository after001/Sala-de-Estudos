/*
 * 
 * @author Arthur
 */
public class ReservaLabs {
    public static void main(String[] args) {
        String[][] Labs = new String[7][3];
        Labs[1][0] = "07:00";
        Labs[2][0] = "08:40";
        Labs[3][0] = "10:35";
        Labs[4][0] = "13:00";
        Labs[5][0] = "14:40";
        Labs[6][0] = "16:35";
        Labs[0][1] = "L3";
        Labs[0][2] = "LES";

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

    }

    public static void desfazerReserva(String matricula) {

    }

    public static void salvaReservas(String matricula) {

    }
}

class Pilha {   // Com estruturas auto-referenciadas
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

