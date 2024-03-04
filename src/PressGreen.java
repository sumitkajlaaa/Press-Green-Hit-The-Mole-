import java.awt.*;
import java.awt.Desktop.Action;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class PressGreen {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Press Green");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton []board = new JButton[9];
    ImageIcon greenIcon;
    ImageIcon redIcon;

    JButton currGreenTile;
    JButton currRedTile;

    Random random = new Random();
    Timer setGreenTimer;
    Timer setRedTimer;

    int score;

    PressGreen(){
        
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Aerial" , Font.PLAIN , 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Score: 0");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.black);
        frame.add(boardPanel);

        //greenIcon = new ImageIcon(getClass().getResource("./Green.png"));
        Image greenImg = new ImageIcon(getClass().getResource("./Green.png")).getImage();
        greenIcon = new ImageIcon(greenImg.getScaledInstance(150,150,java.awt.Image.SCALE_SMOOTH));

        Image redImg = new ImageIcon(getClass().getResource("./Red.png")).getImage();
        redIcon = new ImageIcon(redImg.getScaledInstance(150,150,java.awt.Image.SCALE_SMOOTH));


        score = 0;
        for(int i=0; i < 9  ; i++){
            JButton tile = new JButton();
            board[i] = tile;
            boardPanel.add(tile);
            tile.setFocusable(false);
           // tile.setIcon(greenIcon);
         //   tile.setIcon(redIcon);

            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton tile = (JButton) e.getSource();
                    if(tile == currGreenTile){
                        score += 10;
                        textLabel.setText("Score: " + score);
                    }
                    else if(tile == currRedTile){
                        textLabel.setText("Game Over " + score);
                        setGreenTimer.stop();
                        setRedTimer.stop();
                        for(int i = 0 ; i< 9 ; i++){
                            board[i].setEnabled(false);
                        }
                    }
                }
            });
        }

        setGreenTimer = new Timer(800 , new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(currGreenTile != null){
                    currGreenTile.setIcon(null);
                    currGreenTile = null;
                }

                int num = random.nextInt(9);
                JButton tile = board[num];

                //if tile is occupied
                if(currRedTile == tile) return;

                currGreenTile = tile;
                currGreenTile.setIcon(greenIcon);
            }
        });

        

        setRedTimer = new Timer(1200 , new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(currRedTile != null){
                    currRedTile.setIcon(null);
                    currRedTile = null;
                }

                int num = random.nextInt(9);
                JButton tile = board[num];

                if(currGreenTile == tile) return;

                currRedTile = tile;
                currRedTile.setIcon(redIcon);
            }
        });

        setGreenTimer.start();
        setRedTimer.start();
        frame.setVisible(true);
    }
}
