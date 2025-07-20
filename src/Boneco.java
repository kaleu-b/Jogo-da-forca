public class Boneco {
    
    /**
     * Exibe o boneco do jogo da forca com base no número de erros.
     * Esse método funciona bem com 2 jogadores, quando um advinha e o outro escolhe a palavra.
     * Mas mudanças terão que ser feitas caso rodadas e mais jogadores sejam implementados.
     */
    public static void exibirBoneco(int erros) {
        switch (erros) {
            case 0:
                System.out.println("  +---+"); //nenhum erro, boneco não aparece
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 1:
                System.out.println("  +---+"); //1 erro, cabeça do boneco aparece
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 2:
                System.out.println("  +---+"); //2 erros, tronco do boneco aparece
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 3:
                System.out.println("  +---+"); //3 erros, braço do boneco aparecem
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|   |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 4:
                System.out.println("  +---+");
                System.out.println("  |   |"); //4 erros, o outro braço do boneco aparecem
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 5:
                System.out.println("  +---+"); //5 erros, perna do boneco aparece
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" /    |");
                System.out.println("=========");
                break;
            case 6:
                System.out.println("  +---+"); //6 erros, o outro pé do boneco aparece
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" / \\  |");
                System.out.println("=========");
                break;
            default:
                System.out.println("Número de erros inválido. Deve ser entre 0 e 6."); //não deve acontecer. mas se acontecer, informa o erro
                break;

}
    }
}