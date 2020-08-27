package core.gui;

import core.Node;
import core.SearchAlgorithm;
import core.algorithms.*;
import core.models.GUIGraph;
import core.models.GUINode;
import examples.traveller.GraphProblem;
import examples.traveller.GraphState;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainWindow extends JPanel {

    private GUIGraph graph;
    private GraphPanel graphPanel;

    public MainWindow() {
        super.setLayout(new BorderLayout());
        setGraphPanel();
    }

    private void setGraphPanel() {
        graph = new GUIGraph();
        graphPanel = new GraphPanel(graph);
        graphPanel.setPreferredSize(new Dimension(9000, 4096));

        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(graphPanel);
        scroll.setPreferredSize(new Dimension(750, 500));
        scroll.getViewport().setViewPosition(new Point(4100, 0));
        add(scroll, BorderLayout.CENTER);
        setTopPanel();
        setButtons();
    }

    private void setTopPanel() {
        JLabel info = new JLabel("Credit to iamareebjamal Edited by Hani Khatib");
        info.setForeground(new Color(230, 220, 250));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(130, 50, 250));
        panel.add(info);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(panel, BorderLayout.NORTH);
    }

    private void setButtons() {
        String[] algos = {"BFS", "DFS", "UCS", "Greedy", "Astar"};
        final JComboBox<String> algosCb = new JComboBox<String>(algos);
        String[] types = {"TreeSearch", "GraphSearch"};
        final JComboBox<String> typesCb = new JComboBox<String>(types);

        algosCb.setVisible(true);
        typesCb.setVisible(true);
        JButton run = new JButton();
        setupIcon(run, "run");
        JButton reset = new JButton();
        setupIcon(reset, "reset");
        final JButton info = new JButton();
        setupIcon(info, "info");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(DrawUtils.parseColor("#DDDDDD"));
        buttonPanel.add(reset);
        buttonPanel.add(run);
        buttonPanel.add(algosCb);
        buttonPanel.add(typesCb);
        buttonPanel.add(info);


        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.reset();
            }
        });

        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Click on empty space to create new node\n" +
                                "Drag from node to node to create an edge\n" +
                                "Click on edges to set the weight\n\n" +
                                "Combinations:\n" +
                                "Shift + Left Click       :    Set node as source\n" +
                                "Shift + Right Click     :    Set node as destination\n" +
                                "Ctrl  + Drag               :    Reposition Node\n" +
                                "Ctrl  + Click                :    Get Path of Node\n" +
                                "Alt  + Click                :    Set H for Node\n" +
                                "Ctrl  + Shift + Click   :    Delete Node/Edge\n");
            }
        });

        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphProblem p = new GraphProblem(graph);
                boolean useGraphSearch = typesCb.getSelectedIndex() == 1;
                SearchAlgorithm searchAlgo = new BFS(useGraphSearch);
                switch (algosCb.getSelectedIndex()) {
                    case 0:
                        searchAlgo = new BFS(useGraphSearch);
                        break;
                    case 1:
                        searchAlgo = new DFS(useGraphSearch);
                        break;
                    case 2:
                        searchAlgo = new UCS(useGraphSearch);
                        break;
                    case 3:
                        searchAlgo = new GreedyBestFisrtSeach(useGraphSearch);
                        break;
                    case 4:
                        searchAlgo = new Astar(useGraphSearch);
                        break;
                    default:
                        break;
                }

                Node sol = p.solve(searchAlgo);
                System.out.println(sol);
                List<GUINode> path = new ArrayList<>();
                searchAlgo.printSolution(sol);
                searchAlgo.printStates();
                graph.setSolved(true);
                while (sol != null) {
                    GUINode n = new GUINode();
                    for (GUINode nn : graph.getGUINodes()) {
                        if (nn.getId() == ((GraphState) sol.getState()).graphCurrentStateIndex) {
                            n = nn;
                            break;
                        }
                    }
                    path.add(n);
                    sol = sol.getParent();
                }
                Collections.reverse(path);
                graphPanel.setPath(path);
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupIcon(JButton button, String img) {
        try {
            Image icon = ImageIO.read(getClass().getResource(
                    "/core/resources/" + img + ".png"));
            ImageIcon imageIcon = new ImageIcon(icon);
            button.setIcon(imageIcon);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
