package com.lyriaaw.SixColors.Gui;

import com.lyriaaw.SixColors.GameLogic.Case;
import com.lyriaaw.SixColors.GameLogic.CaseColor;
import com.lyriaaw.SixColors.GameLogic.Player;
import com.lyriaaw.SixColors.GameLogic.TableGame;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * (commentaires)
 *
 * @author Amalric Lombard de Buffières <amalric.debuffieres@icloud.com>
 * @version 1.0.0
 */
public class Frame extends JFrame implements ActionListener{

    TableGame gameLogic;
    Case[][] gameGrid;
    List<Player> playerList;
    Player currentPlayer;



    JPanel main, gridPanel, playersPanel, playerInterface;
    CaseButton[][] gameButtons;
    List<JLabel> playerPanelList;
    List<JLabel> playerLabelList;

    // Player interface
    JLabel currentPlayerLabel, playerInfos;
    JPanel colorSelectorPanel;
    List<CaseButton> colorSelector;




    private static final String COLOR_SELECTOR_BUTTON = "COLOR_BUTTON";



    public Frame() throws HeadlessException {
        System.out.println("Launching Frame(); Init game and Graphics");

        this.setTitle("Six Colors Game");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initGameComponent();
        initGraphicalComponents();



        this.setVisible(true);

    }


    /**
     * Graphical initiation
     */

    private void initGraphicalComponents() {

        //initing the main Label
        main = new JPanel();

        FlowLayout mainLayout = new FlowLayout();
        main.setLayout(mainLayout);

        this.setContentPane(main);



        // creating the grid panel
        gridPanel = new JPanel();
        displayGrid(gridPanel);


        // adding players panel
        playersPanel = new JPanel();
        displayPlayer(playersPanel);


        // adding player interface
        playerInterface = new JPanel();
        displayPlayerInterface(playerInterface);






        main.add(playerInterface);
        main.add(gridPanel);
        main.add(playersPanel);


    }


    private void displayGrid(JPanel myPanel) {

        int gridSize = gameLogic.getGridSize();
        gameGrid = gameLogic.getGrid();

        GridLayout gridPanelLayout = new GridLayout(gridSize, gridSize);
        myPanel.setLayout(gridPanelLayout);

        gameButtons = new CaseButton[gridSize][gridSize];


        // initing all the buttons
        for (int jt = 0; jt < gridSize; jt++) {
            for (int it = 0; it < gridSize; it++) {
                // Case currentCase = gameGrid[jt][it];

                CaseButton button = new CaseButton(jt, it);

                gameButtons[jt][it] = button;
                myPanel.add(button);


            }
        }

        updateGrid();

    }

    private void updateGrid() {
        int gridSize = gameLogic.getGridSize();

        // running throw all buttons and replacing the old representedCase by a new (taken from the gameLogic)
        for (int jt = 0; jt < gridSize; jt++) {
            for (int it = 0; it < gridSize; it++) {
                Case currentCase = gameGrid[jt][it];
                gameButtons[jt][it].addCaseRepresented(currentCase);
            }
        }



    }

    private void displayPlayer(JPanel myPanel) {

        LayoutManager playerPanelLayout = new FlowLayout();
        myPanel.setLayout(playerPanelLayout);

        playerPanelList = new ArrayList<>();
        playerLabelList = new ArrayList<>();

        for (Player player : playerList) {
            JPanel currentPLayerPanel = new JPanel();
            JLabel playerInfos = new JLabel();

            currentPLayerPanel.add(playerInfos);
            playerLabelList.add(playerInfos);
            myPanel.add(currentPLayerPanel);

        }

        updatePlayer();

    }

    private void updatePlayer() {

        for (int it = 0; it < playerList.size(); it++) {
            Player player = playerList.get(it);
            playerLabelList.get(it).setText(player.toString());
        }

    }

    private void displayPlayerInterface(JPanel myPanel) {

        FlowLayout playerInterfaceLayout = new FlowLayout();
        myPanel.setLayout(playerInterfaceLayout);


        currentPlayerLabel = new JLabel("Player Name");
        playerInfos = new JLabel("Vous possedez 12 cases");

        colorSelector = new ArrayList<>();
        colorSelectorPanel = new JPanel();
        for (CaseColor currentCaseColor : CaseColor.values()) {
            if (currentCaseColor != CaseColor.VOID) {
                CaseButton button = new CaseButton(currentCaseColor);

                button.addActionListener(this);
                button.setActionCommand("COLOR_BUTTON:CASECOLOR=" + currentCaseColor.toString());

                colorSelector.add(button);
                colorSelectorPanel.add(button);

            }
        }


        myPanel.add(currentPlayerLabel);
        myPanel.add(colorSelectorPanel);
        myPanel.add(playerInfos);

        updatePlayerInterface();


    }

    private void updatePlayerInterface() {
        currentPlayerLabel.setText("Player " + currentPlayer.getId());

        String infos = "You belong " + currentPlayer.getAmountOfBelongedCase() + " case(s).";
        playerInfos.setText(infos);


    }


    private void makeGuiChanges(){

        updatePlayerInterface();
        updatePlayer();
        updateGrid();

    }




    /**
     * Game Logic initiation
     */
    private void initGameComponent() {
        gameLogic = new TableGame();
        gameLogic.initGame();

        makeGameUpdates();

    }

    private void makeGameUpdates() {
        playerList = gameLogic.getPlayerList();
        currentPlayer = gameLogic.getCurrentPlayer();
        gameGrid = gameLogic.getGrid();

    }







    /**
     * Action performed
     */

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        if (actionCommand.contains(COLOR_SELECTOR_BUTTON)) {
            String colorSelection = actionCommand.split(":")[1];
            performColorSelectAction(colorSelection);
        }


    }

    private void performColorSelectAction(String colorSelection) {
        String selectedCaseColorName = colorSelection.split("=")[1];

        CaseColor selectedCaseColor = CaseColor.valueOf(selectedCaseColorName);
        if (selectedCaseColor == null) return;

        this.gameLogic.somebodyPlayed(selectedCaseColor);

        updateFromGameLogic();

    }

    private void updateFromGameLogic() {
        makeGameUpdates();
        makeGuiChanges();
    }
}
