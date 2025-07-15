import java.util.Scanner;

public class Forca {
    // Scanner instance for reading user input throughout the class
    public Scanner scanner = new Scanner(System.in);

    public static void iniciarJogo(boolean multiplayer) {
        // Start the game by selecting a word based on the game mode
        escolhaPalavra(multiplayer);
        // Note: Additional game initialization logic would go here
    }

    /**
     * Handles word selection based on the game mode
     */
    public static void escolhaPalavra(boolean multiplayer) {
         Scanner scanner = new Scanner(System.in);
        String palavra; // Variable to hold the selected word
         if (multiplayer == true) {  // Multiplayer mode - one player chooses the word

            System.out.println("Escolha uma palavra para o amigo adivinhar:");
            // TODO: Implement actual word input logic
            // Currently just clears the input buffer
            //Scanner scanner = new Scanner(System.in);
            palavra = scanner.nextLine(); // Clears the input buffer

            // Implementation notes:
            // 1. Should validate the input (letters only, no special characters)
            // 2. Should handle word hiding (convert to underscores)
            // 3. Should clear the screen after word input to prevent cheating
        } else {  // Single player mode - computer selects random word
            System.out.println("A palavra será escolhida pelo computador.");
            palavra = "exemplo"; // Placeholder for the word selected by the computer
            // Implementation notes:
            // 1. Should integrate with a word dictionary/API
            // 2. Should select random word of appropriate difficulty
            // 3. Should handle word categories/themes
        }
        scanner.close(); // Close the scanner to prevent resource leaks
    }

    public static void adivinharPalavra(String palavra) {
        // Logic for guessing the word goes here
        // This would include:
        // 1. Displaying the current state of the word (with underscores for unguessed letters)
        // 2. Handling user input for guesses
        // 3. Checking if the guess is correct and updating the state accordingly
        // 4. Implementing win/loss conditions based on guesses
        // Note: This is a placeholder method and needs to be implemented fully.
        int erros = 0; // Initialize error count
        String palavra_minusculas = palavra.toLowerCase(); // Convert the word to lowercase for consistency
        StringBuilder estadoPalavra = new StringBuilder("_".repeat(palavra.length())); //

    }

}
