

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private static final String FILENAME = "Jogo.txt";
    private static List<Jogo> Jogos = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        loadJogosFromFile();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Listar Jogos");
            System.out.println("2. Adicionar Jogo");
            System.out.println("3. Atualizar Jogo");
            System.out.println("4. Deletar Jogo");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

        switch (choice) {
                case 1:
                    listarJogos();
                    break;
                case 2:
                    adicionarJogo(scanner);
                    break;
                case 3:
                    atualizarJogo(scanner);
                    break;
                case 4:
                    deletarJogo(scanner);
                    break;
                case 5:
                    salvarJogosToFile();
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void loadJogosFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Jogo jogo = Jogo.fromString(line);
                Jogos.add(jogo);
                if (jogo.getId() >= nextId) {
                    nextId = jogo.getId() + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Não foi possível carregar os Jogos do arquivo. Iniciando com uma lista vazia.");
        }
    }

    private static void salvarJogosToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Jogo Jogo : Jogos) {
                bw.write(Jogo.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os Jogos no arquivo.");
        }
    }

    private static void listarJogos() {
        if (Jogos.isEmpty()) {
            System.out.println("Não há Jogos para listar.");
        } else {
            System.out.println("Jogos:");
            for (Jogo Jogo : Jogos) {
                System.out.println(Jogo);
            }
        }
    }

    private static void adicionarJogo(Scanner scanner) {
        System.out.print("Título do Jogo: ");
        String nomejg = scanner.nextLine();
        System.out.print("empresa do Jogo: ");
        String empresa = scanner.nextLine();
        System.out.print("Ano de publicação do Jogo: ");
        int nota = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        Jogo Jogo = new Jogo(nextId++, nomejg, empresa, nota);
        Jogos.add(Jogo);
        salvarJogosToFile();
        System.out.println("Jogo adicionado com sucesso.");
    }

    private static void atualizarJogo(Scanner scanner) {
        System.out.print("ID do Jogo a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        Jogo Jogo = procurarJogoPorId(id);
        if (Jogo != null) {
            System.out.print("Novo título do Jogo: ");
            String novonomejg = scanner.nextLine();
            System.out.print("Novo empresa do Jogo: ");
            String novoempresa = scanner.nextLine();
            System.out.print("Novo ano de publicação do Jogo: ");
            int novonota = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

            Jogo.setnomejg(novonomejg);
            Jogo.setempresa(novoempresa);
            Jogo.setnota(novonota);
            salvarJogosToFile();
            System.out.println("Jogo atualizado com sucesso.");
        } else {
            System.out.println("Jogo não encontrado.");
        }
    }

    private static void deletarJogo(Scanner scanner) {
        System.out.print("ID do Jogo a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        Jogo Jogo = procurarJogoPorId(id);
        if (Jogo != null) {
            Jogos.remove(Jogo);
            salvarJogosToFile();
            System.out.println("Jogo deletado com sucesso.");
        } else {
            System.out.println("Jogo não encontrado.");
        }
    }

    private static Jogo procurarJogoPorId(int id) {
        for (Jogo Jogo : Jogos) {
            if (Jogo.getId() == id) {
                return Jogo;
            }
        }
        return null;
    }
}