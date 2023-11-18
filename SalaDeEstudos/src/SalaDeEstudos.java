import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SalaDeEstudos {
    private static String horas[] = { "07:00", "08:40", "10:35", "13:00", "14:40", "16:35" };
    private static ArrayList<Queue<String>> sala = new ArrayList<>();
    private static ArrayList<String> historico = new ArrayList<>();

    public static void main(String[] args) {
        // Inicializa o arrayList com as filas
        for (int i = 0; i < 6; i++) {
            sala.add(new LinkedList<>());
        }

        exibeReservas();
        reserva(1, "Zeus");
        exibeReservas();
        reserva(3, "Oner");
        exibeReservas();
        reserva(1, "Faker");
        exibeReservas();
        reserva(2, "Gumayusi");
        exibeReservas();
        reserva(3, "Keria");
        exibeReservas();
        cancela(1);
        exibeReservas();

        imprime();
    }

    public static void exibeReservas() {
        System.out.println("");
        for (int i = 0; i < 6; i++) {
            String situacao = sala.get(i).isEmpty() ? "Disponível" : "Reservada para " + sala.get(i).element();
            System.out.println(horas[i] + " " + situacao);
        }
        System.out.println("");
    }

    public static void reserva(int hora, String pessoa) {
        // Salva a reserva no histórico
        String acao = sala.get(hora).isEmpty() ? " reservou " : " aguarda ";
        String msg = pessoa + acao + horas[hora];
        historico.add(msg);
        System.out.println(msg);

        // Faz a reserva
        sala.get(hora).add(pessoa);
    }

    public static void cancela(int hora) {
        // Cancela a reserva
        String pessoa = sala.get(hora).remove();

        // Salva o cancelamento no histórico
        String msg = pessoa + " cancelou " + horas[hora];
        historico.add(msg);
        System.out.println(msg);
        if (!sala.get(hora).isEmpty()) {
            historico.add(sala.get(hora).element() + " entrou em " + horas[hora]);
        }
    }

    public static void imprime() {
        System.out.println("Histórico:");
        for (String v : historico) {
            System.out.println(v);
        }
    }

}
