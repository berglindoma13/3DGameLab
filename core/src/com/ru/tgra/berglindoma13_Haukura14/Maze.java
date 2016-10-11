package com.ru.tgra.berglindoma13_Haukura14;

/**
 * Created by Berglind on 11.10.2016.
 */
public class Maze {

    public Cell[][] cells;

    public Maze() {
        cells = new Cell[11][11];

        cells[0][0] = new Cell(true,true);
        cells[0][1] = new Cell(false,true);
        cells[0][2] = new Cell(false,true);
        cells[0][3] = new Cell(false,true);
        cells[0][4] = new Cell(false,true);
        cells[0][5] = new Cell(false,true);
        cells[0][6] = new Cell(true,false);
        cells[0][7] = new Cell(true,true);
        cells[0][8] = new Cell(true,true);
        cells[0][9] = new Cell(false,true);
        cells[0][10] = new Cell(true,false);

        cells[1][0] = new Cell(true,false);
        cells[1][1] = new Cell(false,false);
        cells[1][2] = new Cell(false,false);
        cells[1][3] = new Cell(false,false);
        cells[1][4] = new Cell(false,false);
        cells[1][5] = new Cell(true,true);
        cells[1][6] = new Cell(false,false);
        cells[1][7] = new Cell(false,false);
        cells[1][8] = new Cell(true,false);
        cells[1][9] = new Cell(false,false);
        cells[1][10] = new Cell(true,false);

        cells[2][0] = new Cell(true,false);
        cells[2][1] = new Cell(true,true);
        cells[2][2] = new Cell(true,true);
        cells[2][3] = new Cell(true,false);
        cells[2][4] = new Cell(false,false);
        cells[2][5] = new Cell(true,false);
        cells[2][6] = new Cell(false,true);
        cells[2][7] = new Cell(true,false);
        cells[2][8] = new Cell(true,false);
        cells[2][9] = new Cell(false,false);
        cells[2][10] = new Cell(true,false);

        cells[3][0] = new Cell(true,false);
        cells[3][1] = new Cell(true,false);
        cells[3][2] = new Cell(false,false);
        cells[3][3] = new Cell(true,false);
        cells[3][4] = new Cell(false,false);
        cells[3][5] = new Cell(false,false);
        cells[3][6] = new Cell(false,true);
        cells[3][7] = new Cell(true,false);
        cells[3][8] = new Cell(false,true);
        cells[3][9] = new Cell(true,false);
        cells[3][10] = new Cell(true,false);

        cells[4][0] = new Cell(true,false);
        cells[4][1] = new Cell(true,false);
        cells[4][2] = new Cell(false,false);
        cells[4][3] = new Cell(false,true);
        cells[4][4] = new Cell(false,true);
        cells[4][5] = new Cell(false,true);
        cells[4][6] = new Cell(false,false);
        cells[4][7] = new Cell(false,false);
        cells[4][8] = new Cell(false,true);
        cells[4][9] = new Cell(true,false);
        cells[4][10] = new Cell(true,false);

        cells[5][0] = new Cell(true,false);
        cells[5][1] = new Cell(true,false);
        cells[5][2] = new Cell(true,true);
        cells[5][3] = new Cell(false,true);
        cells[5][4] = new Cell(false,true);
        cells[5][5] = new Cell(false,true);
        cells[5][6] = new Cell(false,true);
        cells[5][7] = new Cell(false,true);
        cells[5][8] = new Cell(false,true);
        cells[5][9] = new Cell(false,false);
        cells[5][10] = new Cell(true,false);

        cells[6][0] = new Cell(true,false);
        cells[6][1] = new Cell(true,false);
        cells[6][2] = new Cell(false,true);
        cells[6][3] = new Cell(false,true);
        cells[6][4] = new Cell(false,true);
        cells[6][5] = new Cell(false,true);
        cells[6][6] = new Cell(false,true);
        cells[6][7] = new Cell(false,true);
        cells[6][8] = new Cell(false,true);
        cells[6][9] = new Cell(true,false);
        cells[6][10] = new Cell(true,false);

        cells[7][0] = new Cell(true,false);
        cells[7][1] = new Cell(true,false);
        cells[7][2] = new Cell(true,true);
        cells[7][3] = new Cell(true,false);
        cells[7][4] = new Cell(true,true);
        cells[7][5] = new Cell(true,false);
        cells[7][6] = new Cell(true,true);
        cells[7][7] = new Cell(false,true);
        cells[7][8] = new Cell(false,true);
        cells[7][9] = new Cell(false,false);
        cells[7][10] = new Cell(true,false);

        cells[8][0] = new Cell(true,false);
        cells[8][1] = new Cell(true,false);
        cells[8][2] = new Cell(true,false);
        cells[8][3] = new Cell(false,true);
        cells[8][4] = new Cell(false,false);
        cells[8][5] = new Cell(true,false);
        cells[8][6] = new Cell(false,true);
        cells[8][7] = new Cell(false,true);
        cells[8][8] = new Cell(false,true);
        cells[8][9] = new Cell(true,false);
        cells[8][10] = new Cell(true,false);

        cells[9][0] = new Cell(true,false);
        cells[9][1] = new Cell(false,true);
        cells[9][2] = new Cell(false,false);
        cells[9][3] = new Cell(false,false);
        cells[9][4] = new Cell(false,false);
        cells[9][5] = new Cell(false,true);
        cells[9][6] = new Cell(false,true);
        cells[9][7] = new Cell(false,true);
        cells[9][8] = new Cell(true,false);
        cells[9][9] = new Cell(false,true);
        cells[9][10] = new Cell(true,false);

        cells[10][0] = new Cell(false,true);
        cells[10][1] = new Cell(false,true);
        cells[10][2] = new Cell(false,true);
        cells[10][3] = new Cell(false,true);
        cells[10][4] = new Cell(false,true);
        cells[10][5] = new Cell(false,true);
        cells[10][6] = new Cell(false,true);
        cells[10][7] = new Cell(false,true);
        cells[10][8] = new Cell(false,true);
        cells[10][9] = new Cell(false,false);
        cells[10][10] = new Cell(false,false);

    }
}
