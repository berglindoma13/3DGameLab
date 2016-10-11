package com.ru.tgra.berglindoma13_Haukura14;

/**
 * Created by Lenny on 10.10.2016.
 */
public class Cell {

    public boolean northwall;
    public boolean westwall;
    public boolean object;

    Cell(boolean northwall, boolean westwall){
        this.northwall = northwall;
        this.westwall = westwall;
        this.object = false;
    }
}
