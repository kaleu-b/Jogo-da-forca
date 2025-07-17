import java.util.Scanner;

public class Menu {
    /**
     * Método principal que inicia a execução do programa.
     */
    public static void main(String[] args) {
        new Menu().verMenu(); // Cria uma nova instância de Menu e chama o método verMenu
    }

    /**
     * Exibe o menu principal e gerencia as opções selecionadas pelo usuário.
     * Controla o fluxo do programa com base nas escolhas do jogador.
     */
   

    public void verMenu() {
        char opcao; // Armazena a opção selecionada pelo usuário no menu principal
        Scanner input = new Scanner(System.in); // Objeto para leitura de entrada do usuário

        // Loop principal do menu - executa até que o usuário selecione a opção de sair (4)
        do {
            // Exibe o cabeçalho do menu com animação de pausa
            System.out.println("---------------Bem-vindo ao jogo da Forca!---------------");
            pausa(1000); // Pausa para melhor experiência do usuário

            // Exibe as opções do menu
            System.out.println("---------------MENU---------------");
            System.out.println("1 - Jogar");       // Inicia um novo jogo
            System.out.println("2 - Instruções");  // Mostra as regras do jogo
            System.out.println("3 - Créditos");     // Exibe informações sobre o desenvolvedor
            System.out.println("4 - Sair");        // Encerra o programa
            System.out.println("---------------------------------");
            System.out.print("Escolha uma opção:");

            opcao = input.next().charAt(0); // Lê apenas o primeiro caractere da entrada
            input.nextLine(); // Consome o restante da linha para evitar problemas de leitura futura
            // Processa a opção selecionada pelo usuário
            switch (opcao) {
                case '1': // Opção para iniciar um novo jogo
                    char modojogo;       // Armazena a escolha do modo de jogo
                    boolean multiplayer = true; // Define se o jogo será contra amigo (true) ou computador (false)

                    // Loop para garantir uma seleção válida do modo de jogo
                    do {
                        pausa(1000); // Pausa  antes de iniciar
                        System.out.println("Iniciando o jogo da Forca...");
                        pausa(1000);

                        // Solicita o modo de jogo desejado
                        System.out.print("Deseja jogar contra um amigo ou contra o computador? (1 - Amigo, 2 - Computador): ");
                        modojogo = input.next().charAt(0);
                        input.nextLine(); // Consome o restante da linha para evitar problemas de leitura futura
                        // Configura o modo de jogo com base na escolha
                        switch (modojogo) {
                            case '1': // Modo multiplayer (contra amigo)
                                multiplayer = true;
                                System.out.println("Modo selecionado: Jogar contra amigo");
                                break;
                            case '2': // Modo single player (contra computador)
                                multiplayer = false;
                                System.out.println("Modo selecionado: Jogar contra computador");
                                break;
                            default: // Tratamento de opção inválida
                                System.out.println("Opção de modo inválida! Por favor, escolha 1 ou 2.");
                                break;
                        }
                    } while (modojogo != '1' && modojogo != '2'); // Repete até seleção válida
                    //input.nextLine();
                    //scanner.close(); // Fecha o scanner para liberar recursos
                    new Forca(input).iniciarJogo(multiplayer); // Cria uma nova instância de Forca e inicia o jogo com o modo selecionado
                    break;

                case '2': // Opção para exibir as instruções do jogo
                    System.out.println("Instruções do jogo da Forca:");
                    System.out.println("1. O objetivo é adivinhar a(s) palavra(s) secreta(s).");
                    System.out.println("2. Você tem 6 tentativas.");
                    System.out.println("3. A cada erro, uma parte do boneco é desenhada.");
                    System.out.println("4. Se o boneco for completado, você perde.");
                    pausa(5000); // Pausa longa para leitura das instruções
                    break;

                case '3': // Opção para exibir os créditos
                    System.out.println("Créditos:");
                    System.out.println("Desenvolvido por Kaléu Borges");
                    pausa(2000); // Pausa para visualização dos créditos
                    break;

                case '4': // Opção para sair do programa
                    System.out.println("Saindo do jogo...");
                    pausa(1500); // Pausa antes de encerrar
                    input.close(); // Fecha o scanner para liberar recursos
                    System.exit(0); // Encerra o programa com código 0 (sucesso)
                    break;

                default: // Tratamento de opção inválida no menu principal
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                    System.out.flush(); // Limpa o buffer de saída
                    break;
            }
        } while (opcao != '4'); // Condição de saída do loop principal
    }

    /**
     * Pausa a execução do programa por um tempo determinado.
     * @param tempoPausa Tempo de pausa em milissegundos (ex: 1000 = 1 segundo)
     */
    public static void pausa(int tempoPausa) {
        try {
            Thread.sleep(tempoPausa); // Suspende a execução da thread atual
        } catch (InterruptedException e) {
            // Log da exceção caso a thread seja interrompida durante a pausa
            e.printStackTrace();
        }
    }
}
