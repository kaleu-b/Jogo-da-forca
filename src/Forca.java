import java.util.Scanner;
public class Forca {
    Scanner scanner = new Scanner(System.in);
    public void iniciarJogo() {
        // Lógica do jogo da Forca
        System.out.println("Iniciando o jogo da Forca...");
        System.exit(0);
        Menu.pausa(1000);
        System.out.println("Bem-vindo ao jogo da Forca!");
        System.out.println("Deseja jogar contra um amigo ou contra o computador? (1 - Amigo, 2 - Computador)");
        int modo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        System.out.println("Digite o número de rodadas: ");
        int rodadas = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        
        
    }

}
