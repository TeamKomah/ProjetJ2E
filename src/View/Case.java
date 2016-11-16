package View;

import Model.Position;

import javax.swing.*;

/**
 * Created by root on 11/15/16.
 */
public class Case extends JButton {

    private PositionView pos;

    public Case(int x, int y){
        super();
        this.pos = new PositionView(x,y);
    }
}
