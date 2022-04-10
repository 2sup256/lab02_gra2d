package game2d;

import javax.swing.*;

public class Main {
    public static JFrame window;

    public static void main(String[] args) {
        System.out.println("Loading...");

        window = new JFrame("2D");
        window.setSize(800, 600);
        window.setIgnoreRepaint(true);
        window.setResizable(false);



        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Game game = new Game();
        window.add(game);
        window.pack();

        GameLogic gl = new GameLogic();
        gl.init();
        Thread glth = new Thread(gl);
        glth.run();




    }
}
