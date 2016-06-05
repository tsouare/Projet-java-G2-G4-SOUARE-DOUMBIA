package com.lyriaaw.SixColors.Gui;

import com.lyriaaw.SixColors.GameLogic.Case;
import com.lyriaaw.SixColors.GameLogic.CaseColor;

import javax.swing.*;
import java.awt.*;

import static com.lyriaaw.SixColors.GameLogic.CaseColor.*;

/**
 * (commentaires)
 *
 * @author Amalric Lombard de Buffières <amalric.debuffieres@icloud.com>
 * @version 1.0.0
 */
public class CaseButton extends JButton{

    private static final int GRID_CASE_SIZE = 50;

    private Case caseRepresented;
    private CaseColor currentColor;

    private int y, x;

    public CaseButton() {

    }

    public CaseButton(Case caseRepresented, int x, int y) {
        this.caseRepresented = caseRepresented;
        currentColor = caseRepresented.getColor();

        this.x = x;
        this.y = y;
    }

    public CaseButton(CaseColor currentColor) {
        this.currentColor = currentColor;

        caseRepresented = new Case();
    }

    public CaseButton(int y, int x) {
        this.y = y;
        this.x = x;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(getGuiColorFromGameColor(currentColor));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());


        // Drawing the case border
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, this.getWidth(), 0);
        g.drawLine(0,  this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
        g.drawLine(0, 0, 0, this.getHeight());
        g.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight());



       // System.out.println(this.getWidth());


    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GRID_CASE_SIZE, GRID_CASE_SIZE);
    }

    public static Color getGuiColorFromGameColor(CaseColor caseColor) {


        switch (caseColor) {
            case ORANGE:
                return new Color(255, 122, 14);
            case YELLOW:
                return Color.YELLOW;
            case GREEN:
                return Color.GREEN;
            case BLUE:
                return Color.BLUE;
            case PURPLE:
                return new Color(128, 0, 128);
            case RED:
                return Color.RED;

        }

        return Color.WHITE;
    }

    public void addCaseRepresented(Case caseRepresented) {
        this.caseRepresented = caseRepresented;
        currentColor = caseRepresented.getColor();
    }

}
