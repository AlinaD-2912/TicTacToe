import Games.game_engine.TicTacToe;

public class Main {
    public static void main(String[] args) {
        System.out.println("\u001B[31m Red line\u001B[0m");
        System.out.println("\u001B[32m Green line\u001B[0m");
        System.out.println("\u001B[33m Yellow line\u001B[0m");
        System.out.println("\u001B[34m Violet line\u001B[0m");
        System.out.println("\u001B[35m Rose line\u001B[0m");
        System.out.println("\u001B[36m Blue line\u001B[0m");

        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.play();
    }

}
