import javax.swing.JFrame;

public class Window extends JFrame {

    int windowWidth = 288 + 288;
    int windowHeight = 480 + 112;

 
    
    public Window() {

        init();
        
    }

    public void init() {
        JFrame frame = new JFrame("Flappy Bird Clone");

        frame.setResizable(false);
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

        Board board = new Board();
        frame.add(board);
       //frame.pack();
        board.requestFocus();
        frame.setVisible(true);
    }
}

