import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements KeyListener, ActionListener {

    private Background bg;
    private Floor floor;
    private Pipe pipe;
    private Bird bird;
    private Score score;
    private SoundPlayer soundPlayer;

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
        
        int randomY = (int) (pipe.getY() - pipe.getHeight()/5 - Math.random() * (pipe.getHeight()/2));
        int space = pipe.getHeight()/4;

        Pipe topPipe = new Pipe("src/asset/sprite/toppipe.png");
        topPipe.setY(randomY);
        obstacles.add(topPipe);

        Pipe bottomPipe = new Pipe("src/asset/sprite/bottompipe.png");
       // bottomPipe.y = topPipe.y + pipe.height + space;
       int tpy = topPipe.getY();
       bottomPipe.setY(tpy + pipe.getHeight() + space);
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
            g.drawImage(pipe.rImage(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
         }

        /* 
        g.drawImage(bottomPipe, 288, 325, 50, 188, null);
        g.drawImage(topPipe, 288, 0, 50, 188, null);
        */

        // bird
        g.drawImage(bird.getImage(), bird.getX(), bird.getY(), null);

        // score

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score.getPlayerScore()), 10, 35);
            if (score.getPlayerScore() > 0) {
            g.drawString("Last Game: " + String.valueOf((int) score.getPlayerLastScore()), 10, 65);    
            }
        } else {
            g.drawString((String.valueOf((int) score.getPlayerScore())), 10, 35);
        }

    }

    public void gravity() {
        
        //bird
        if (bird.getY() > 488) {
            soundPlayer.playSound("src/asset/sound/hit.wav");
            gameOver = true;
        } else if (bird.getY() < 0) {
            bird.setY(0);
        }
        else {
            //bird.velocityY += bird.gravity;
            //bird.y += bird.velocityY;

            int bvg = bird.getVelocityY();
            int byv = bird.getY();

            bird.setVelocityY(bvg += bird.getGravity());
            bird.setY(byv += bvg);
        }
        
        //pipe
        for (int i = 0; i < obstacles.size(); i++) {
            Pipe pipe = obstacles.get(i);
           // pipe.x += pipe.velocityX;
           int px = pipe.getX();
           pipe.setX(px += pipe.getVelocityX());

            if (!pipe.isPassed() && bird.getX() > pipe.getX() + pipe.getWidth()) {
                pipe.setPassed(true);
                //score.playerScore += 0.5;
                double psc = score.getPlayerScore();
                score.setPlayerScore(psc += 0.5);
                soundPlayer.playSound("src/asset/sound/point.wav");
            }

            if (collision(bird, pipe)) {
                soundPlayer.playSound("src/asset/sound/hit.wav");
                gameOver = true;
            }
        }
    }

    public boolean collision(Bird a, Pipe b) {
        return a.getX() < b.getX() + b.getWidth() &&
        a.getX() + a.getWidth() > b.getX() &&
        a.getY() < b.getY() + b.getHeight() &&
        a.getY() + a.getHeight() > b.getY();
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
            //bird.velocityY = -9;
            bird.setVelocityY(-9);
            // sound wings
            soundPlayer.playSound("src/asset/sound/wing.wav");
            
            if (gameOver) {
                //restart
                //bird.y = 200;
                //bird.velocityY = 0;
                
                bird.setY(200);
                bird.setVelocityY(0);
                
                obstacles.clear();
                //score.playerLastScore = score.playerScore;
                //score.playerScore = 0;
                score.setPlayerLastScore(score.getPlayerScore());
                score.setPlayerScore(0);
                gameOver = false;
                gameLoop.start();
                
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    
}
