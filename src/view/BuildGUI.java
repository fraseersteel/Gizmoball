package view;

import controller.*;
import controller.build.BuildKeyListener;
import controller.build.edit.ClearListener;
import controller.build.edit.SliderListener;
import controller.build.gizmo.BuildButtonListener;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Hashtable;

public class BuildGUI extends GUI {

    private final static Dimension WINDOW_SIZE = new Dimension(750, 675);

    private BuildBoard buildBoard;
    private JPanel gizmoButtons;
    private JPanel editButtons;
    private JPanel sliders;
    private FlowLayout flow;
    private JLabel status;
    private BuildButtonListener buildButtonListener;
    private RunListener runListener;
    private KeyListener keyListener;
    private SliderListener sliderListener;

    static final int gravMin = 0;
    static final int gravMax = 5;
    static final int gravInit = 1;



    public BuildGUI(Model model) {
        super(model);
        runListener = new RunListener(model,this);
        keyListener = null;
        sliderListener = new SliderListener(model,this);
        status = new JLabel("Welcome to Build Mode");

        initilise();
        buildMenuBar();
        leftZone();
        buildEditButtons();
        setSliders();

        jframe.setVisible(true);
    }

    private void initilise() {

        jframe = new JFrame();
        flow = new FlowLayout();
        jframe.setLayout(flow);

        //jframe.addKeyListener(new BuildKeyListener(model));

        jframe.setTitle("Gizmoball - BUILD MODE");

        jframe.setSize(WINDOW_SIZE);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gizmoButtons = new JPanel();
        editButtons = new JPanel();
        sliders = new JPanel();


        buildBoard = new BuildBoard(500, 500, model);

        // Gizmo board is 25x25 pixels each zone (doesnt work atm)
        Dimension runDimension = new Dimension(buildBoard.getWidth(), buildBoard.getHeight());
        buildBoard.setPreferredSize(runDimension);

        gizmoButtons.setLayout(new GridLayout(0, 1));
        editButtons.setLayout(new GridLayout(0, 1));
        sliders.setLayout(new GridLayout(0,1));
        //playZone.setLayout(new GridLayout(21,21));
        editButtons.setPreferredSize(new Dimension(100, 600));
        sliders.setPreferredSize(new Dimension(200,100));
        editButtons.setPreferredSize(new Dimension(100, 400));

        Container pane1 = jframe.getContentPane();
        buildButtonListener = new BuildButtonListener(model, buildBoard, this);

        pane1.add(gizmoButtons,BorderLayout.LINE_START);
        pane1.add(buildBoard,BorderLayout.CENTER);
        pane1.add(editButtons,BorderLayout.LINE_END);
        pane1.add(status,BorderLayout.PAGE_END);
        pane1.add(sliders,BorderLayout.PAGE_END);




        jframe.setResizable(false);

    }

    private void buildEditButtons() {

        //Create grid and populate with buttons
        JButton move = new JButton("Move");
        move.setFocusable(false);
        JButton rotate = new JButton("Rotate");
        rotate.setFocusable(false);
        JButton delete = new JButton("Delete");
        delete.setFocusable(false);
        JButton connect = new JButton("Connect");
        connect.setFocusable(false);
        JButton disconnect =  new JButton("Disconnect");
        disconnect.setFocusable(false);

        connect.addActionListener(buildButtonListener);
        disconnect.addActionListener(buildButtonListener);
        rotate.addActionListener(buildButtonListener);
        delete.addActionListener(buildButtonListener);
        move.addActionListener(buildButtonListener);

        editButtons.add(move);
        editButtons.add(rotate);
        editButtons.add(delete);
        editButtons.add(connect);
        editButtons.add(disconnect);

    }

    private void leftZone() {

        //Create grid and populate with buttons
        JButton ball = new JButton("Ball");
        ball.setFocusable(false);
        JButton square = new JButton("SquareGizmo");
        square.setFocusable(false);
        JButton triangle = new JButton("TriangleGizmo");
        triangle.setFocusable(false);
        JButton circle = new JButton("Circle");
        circle.setFocusable(false);
        JButton leftFlipper = new JButton("Left Flipper");
        leftFlipper.setFocusable(false);
        JButton rightFlipper = new JButton("Right Flipper");
        rightFlipper.setFocusable(false);
        JButton absorber = new JButton("Absorber");
        absorber.setFocusable(false);



        ball.addActionListener(buildButtonListener);
        square.addActionListener(buildButtonListener);
        triangle.addActionListener(buildButtonListener);
        circle.addActionListener(buildButtonListener);
        leftFlipper.addActionListener(buildButtonListener);
        rightFlipper.addActionListener(buildButtonListener);
        absorber.addActionListener(buildButtonListener);



        gizmoButtons.add(ball);
        gizmoButtons.add(square);
        gizmoButtons.add(triangle);
        gizmoButtons.add(circle);
        gizmoButtons.add(leftFlipper);
        gizmoButtons.add(rightFlipper);
        gizmoButtons.add(absorber);


        gizmoButtons.setPreferredSize(new Dimension(120, 400));

    }

    public JLabel getLabel(){
        return status;
    }

    private void buildMenuBar(){

        JMenuBar buildMenu = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new SaveListener(model,buildBoard,this));

        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new LoadListener(model, buildBoard));

        JMenuItem clear = new JMenuItem("Clear Map");
        clear.addActionListener(new ClearListener(model,buildBoard));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(runListener);

        jframe.setJMenuBar(buildMenu);

        buildMenu.add(menu);
        menu.add(save);
        menu.add(load);
        menu.add(clear);
        menu.addSeparator();

        menu.add(exit);

        JMenu switch_mode = new JMenu("Switch Mode");
        buildMenu.add(switch_mode);
        JMenuItem runMode = new JMenuItem("Run Mode");
        switch_mode.add(runMode);
        runMode.addActionListener(runListener);
    }


    private void setSliders(){
        JSlider grav = new JSlider(JSlider.HORIZONTAL, gravMin,gravMax,gravInit);
        grav.setFocusable(false);
        grav.setName("gravity");
        grav.setMinorTickSpacing(1);
        grav.setMajorTickSpacing(5);
        grav.setPaintTicks(true);
        grav.setPaintLabels(true);
        Hashtable gravLabel = new Hashtable();
        gravLabel.put( new Integer( 0 ), new JLabel("Gravity") );
        grav.setLabelTable(gravLabel);


        JSlider fric = new JSlider(JSlider.HORIZONTAL,gravMin,gravMax,gravInit);
        fric.setFocusable(false);
        fric.setName("friction");
        fric.setMinorTickSpacing(1);
        fric.setMajorTickSpacing(5);
        fric.setPaintTicks(true);
        fric.setPaintLabels(true);
        Hashtable fricLabel = new Hashtable();
        fricLabel.put( new Integer( 0 ), new JLabel("Friction") );
        fric.setLabelTable(fricLabel);

        grav.addChangeListener(sliderListener);
        fric.addChangeListener(sliderListener);
        sliders.add(grav);
        sliders.add(fric);

    }

    public void setKeyListener(KeyListener listener) {
        jframe.removeKeyListener(keyListener);
        keyListener = listener;
        jframe.addKeyListener(keyListener);
    }


    public void showError(Exception ex){
        JOptionPane.showMessageDialog(null,ex);
    }

}

