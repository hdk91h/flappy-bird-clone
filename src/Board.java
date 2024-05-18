import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements KeyListener, ActionListener {

    public Background bg;
    public Floor floor;
    public Pipe pipe;
    public Bird bird;
    public Score score;
    public SoundPlayer soundPlayer;

    ArrayList<Pipe> obstacles;

    Timer gameLoop;
    Timer pipePlace;

    boolean gameOver = false;

    Board() {
        bg = new Background();
        floor = new Floor();
        pipe = new Pipe();
        bird = new Bird();
        score = new Score();
        soundPlayer = new SoundPlayer();

        obstacles = new ArrayList<>();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        pipePlace = new Timer(1800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipe();
            }
        });

        pipePlace.start();

        setFocusable(true);
        addKeyListener(this);
    }

    public void placePipe() {  
        
        int randomY = (int) (pipe.y - pipe.height/5 - Math.random() * (pipe.height/2));
        int space = pipe.height/4;

        Pipe topPipe = new Pipe("src/asset/sprite/toppipe.png");
        topPipe.y = randomY;
        obstacles.add(topPipe);

        Pipe bottomPipe = new Pipe("src/asset/sprite/bottompipe.png");
        bottomPipe.y = topPipe.y + pipe.height + space;
        obstacles.add(bottomPipe);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        // draw bg
        g.drawImage(bg.getImage(), 0, 0, null);
        g.drawImage(bg.getImage(), 288, 0, null);
        // draw floor
        g.drawImage(floor.getImage(), 0, 512, null);
        g.drawImage(floor.getImage(), 288, 512, null);

        // pipe

        
        for(int i = 0; i < obstacles.size(); i++) {
            Pipe pipe = obstacles.get(i);
            g.drawImage(pipe.image, pipe.x, pipe.y, pipe.width, pipe.height, null);
         }

        /* 
        g.drawImage(bottomPipe, 288, 325, 50, 188, null);
        g.drawImage(topPipe, 288, 0, 50, 188, null);
        */

        // bird
        g.drawImage(bird.getImage(), bird.x, bird.y, null);

        // score

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score.playerScore), 10, 35);
            if (score.playerLastScore > 0) {
            g.drawString("Last Game: " + String.valueOf((int) score.playerLastScore), 10, 65);    
            }
        } else {
            g.drawString((String.valueOf((int) score.playerScore)), 10, 35);
        }

    }

    public void gravity() {
        
        //bird
        if (bird.y > 488) {
            soundPlayer.playSound("src/asset/sound/hit.wav");
            gameOver = true;
        } else if (bird.y < 0) {
            bird.y = 0;
        }
        else {
            bird.velocityY += bird.gravity;
            bird.y += bird.velocityY;
        }
        
        //pipe
        for (int i = 0; i < obstacles.size(); i++) {
            Pipe pipe = obstacles.get(i);
            pipe.x += pipe.velocityX;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                pipe.passed = true;
                score.playerScore += 0.5;
                soundPlayer.playSound("src/asset/sound/point.wav");
            }

            if (collision(bird, pipe)) {
                soundPlayer.playSound("src/asset/sound/hit.wav");
                gameOver = true;
            }
        }
    }

    public boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&
        a.x + a.width > b.x &&
        a.y < b.y + b.height &&
        a.y + a.height > b.y;
    }

    public void update() {
        gravity();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        update();
        
        if(gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.velocityY = -9;
            // sound wings
            soundPlayer.playSound("src/asset/sound/wing.wav");
            
            if (gameOver) {
                //restart
                bird.y = 200;
                bird.velocityY = 0;
                obstacles.clear();
                score.playerLastScore = score.playerScore;
                score.playerScore = 0;
                gameOver = false;
                gameLoop.start();
                
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    
}
