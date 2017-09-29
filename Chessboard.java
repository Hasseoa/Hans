package com.company;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import java.nio.charset.Charset;
import java.util.Random;



import static java.lang.Math.random;

public class Chessboard {

    public static void main(String[] args) throws InterruptedException{
        /*

        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.applyBackgroundColor(Terminal.Color.BLACK);
        terminal.applyForegroundColor(Terminal.Color.GREEN);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                terminal.moveCursor(i,j);
                char sign = ((i+j)%2 == 0) ? 'O':'X';
                terminal.putCharacter(sign);
            }
        }
        terminal.moveCursor(10,10);
        terminal.putCharacter('\uF0C5');
        terminal.moveCursor(14,10);
        terminal.putCharacter('\u263a');
        */
        /*
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        int [][] board = new int[20][20];

        Random rand = new Random();
        for (int i = 0; i <20 ; i++) {
            for (int j = 0; j <20 ; j++) {
                if(rand.nextInt(400) < 100){
                    board[i][j]= rand.nextInt(255);
                }

            }
        }

        for (int i = 0; i <20 ; i++) {
            for (int j = 0; j <20 ; j++) {
                int color = board[i][j];
                terminal.moveCursor(i, j);
                terminal.applyForegroundColor(color, 0, color);
                terminal.putCharacter('\u2588');

            }

        }
        */
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();

        int [][] board = new int[80][20];
        Random rand = new Random();

        while(true) {

            int xx = rand.nextInt(80);//fyller slumpmässig "pixel"
            int yy = rand.nextInt(20);
            board[xx][yy]= 255;
            rand.nextInt(255);

            for (int i = 0; i <80 ; i++) {//ritar pixlar
                for (int j = 0; j <20 ; j++) {
                    int color = board[i][j];
                    terminal.moveCursor(i, j);
                    terminal.applyForegroundColor(color, 0, 0);
                    terminal.putCharacter('\u2588');

                }
            }

            // Formula to take the average value of the current cell
            // and all of its neighbors
            int [][] tmpboard = new int[80][20];
            for (int y = 0; y < 20; y++) {
                for (int x = 0; x < 80; x++) {
                    int color = 0;

                    if(x>0 && y>0){
                        color += board[x - 1][y - 1];
                    }
                    if(y>0){
                        color += board[x][y - 1];
                    }
                    if(x<19 && y >0){
                        color += board[x + 1][y - 1];
                    }
                    if(x>0){
                        color += board[x - 1][y];
                    }
                    color += board[x][y];
                    if(x<19){
                        color += board[x + 1][y];
                    }
                    if(x>0 && y<19){
                        color += board[x - 1][y + 1];
                    }
                    if(y<19){
                        color += board[x][y + 1];
                    }
                    if(x<19 && y<19){
                        color += board[x + 1][y + 1];
                    }


                    if (color > 100) color /= 10;
                    tmpboard[x][y] = color;
                }
            }

            board = tmpboard;
            //Wait for a key to be pressed
            Key key;
            do{ Thread.sleep(5);
            key =terminal.readInput();
            } while(key == null);
        }






    }
}
