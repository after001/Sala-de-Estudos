
/*
 * @author Arthur Baltar Eler
 */
import java.util.ArrayList; //Para o histórico

public class HashingOpenAdd {
    private static String horas[] = { "07:00", "08:40", "10:35", "13:00", "14:40", "16:35" };
    private static TabelaHash sala = new TabelaHash(6, 5); // 6 horários; 5 pessoas
    private static ArrayList<String> historico = new ArrayList<>();

    public static void main(String[] args) {

        exibeReservas();
        reserva("07:00", "Zeus");
        exibeReservas();
        reserva("10:35", "Oner");
        exibeReservas();
        reserva("07:00", "Faker");
        exibeReservas();
        reserva("08:40", "Gumayusi");
        exibeReservas();
        reserva("10:35", "Keria");
        exibeReservas();
        cancela("07:00");
        exibeReservas();

        imprime();
    }

    public static void exibeReservas() {
        System.out.println("");
        for (int i = 0; i < 6; i++) {
            String situacao = sala.pesquisa(horas[i]) == null ? "Disponível"
                    : "Reservada para " + sala.pesquisa(horas[i]);
            System.out.println(horas[i] + " " + situacao);
        }
        System.out.println("");
    }

    public static void reserva(String hora, String pessoa) {
        // Salva a reserva no histórico
        String acao = sala.pesquisa(hora) == null ? " reservou " : " aguarda ";
        String msg = pessoa + acao + hora;
        historico.add(msg);
        System.out.println(msg);

        // Faz a reserva
        sala.insere(hora, pessoa);
    }

    public static void cancela(String hora) {
        try {
            // Cancela a reserva
            Object pessoa = sala.pesquisa(hora);
            sala.retira(hora);

            // Salva o cancelamento no histórico
            String msg = pessoa + " cancelou " + hora;
            historico.add(msg);
            System.out.println(msg);
            if (sala.pesquisa(hora) != null) {
                historico.add(sala.pesquisa(hora) + " entrou em " + hora);
            }
        } catch (Exception e) {
            System.err.println("Erro ao cancelar a reserva: " + e.getMessage());
            e.printStackTrace(); // Isso imprime o stack trace para diagnóstico
        }
    }

    public static void imprime() {
        System.out.println("Histórico:");
        for (String v : historico) {
            System.out.println(v);
        }
    }
}

class Celula {
    String chave;
    Object item;
    boolean retirado;

    public Celula(String chave, Object item) {
        this.chave = chave;
        this.item = item;
        this.retirado = false;
    }

    public boolean equals(Object obj) {
        Celula cel = (Celula) obj;
        return chave.equals(cel.chave);
    }
}

class TabelaHash {
    private int M; // tamanho da tabela
    private Celula tabela[];
    private int pesos[];

    public TabelaHash(int m, int maxTamChave) {
        this.M = m;
        this.tabela = new Celula[this.M];
        for (int i = 0; i < this.M; i++)
            this.tabela[i] = null; // vazio
        this.pesos = this.geraPesos(maxTamChave);
    }

    private int[] geraPesos(int n) {
        int[] p = new int[n];
        java.util.Random rand = new java.util.Random();

        for (int i = 0; i < n; i++) {
            p[i] = rand.nextInt(M) + 1;
        }

        return p;
    }

    public Object pesquisa(String chave) {
        int indice = this.pesquisaIndice(chave);
        if (indice < this.M)
            return this.tabela[indice].item;
        else
            return null; // pesquisa sem sucesso
    }

    public void insere(String chave, Object item) {
        if (this.pesquisa(chave) == null) {
            int inicial = this.h(chave, this.pesos);
            int indice = inicial;
            int i = 0;
            while (this.tabela[indice] != null && !this.tabela[indice].retirado && i < this.M)
                indice = (inicial + (++i)) % this.M;
            if (i < this.M)
                this.tabela[indice] = new Celula(chave, item);
            else
                System.out.println("Tabela cheia");
        } else
            System.out.println("Registro já está presente");
    }

    private int pesquisaIndice(String chave) {
        int inicial = this.h(chave, this.pesos);
        int indice = inicial, i = 0;
        while (this.tabela[indice] != null && !chave.equals(this.tabela[indice].chave) && i < this.M)
            indice = (inicial + (++i)) % this.M;
        if (this.tabela[indice] != null && chave.equals(this.tabela[indice].chave))
            return indice;
        else
            return this.M; // pesquisa sem sucesso
    }

    public void retira(String chave) throws Exception {
        int i = this.pesquisaIndice(chave);
        if (i < this.M) {
            this.tabela[i].retirado = true;
            this.tabela[i].chave = null;
        } else
            System.out.println("Registro não está presente");
    }

    private int h(String chave, int[] pesos) {
        int soma = 0;

        for (int i = 0; i < chave.length(); i++) {
            soma = soma + ((int) chave.charAt(i)) * pesos[i];
        }

        return soma % this.M;
    }

}
