import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by droydi on 06/02/15.
 */
public class GUI extends JFrame implements ActionListener {
    //Main frame
    JList l = new JList();
    JButton b1 = new JButton("Run Calculation");
    ArrayList al = new ArrayList();

    //Menu
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem newCalc = new JMenuItem("New Calculation");
    JMenuItem saveCalc = new JMenuItem("Save");
    JMenuItem exitProg = new JMenuItem("Exit");
    JMenu edit = new JMenu("Edit");
    JMenuItem freq = new JMenuItem("Frequency");
    JMenuItem diod = new JMenuItem("Diodeloss");
    final JFileChooser fc = new JFileChooser();

    Inputs input = new Inputs();
    double URMS;
    double UsAC;
    double Iload;
    double f = input.getF();
    double diode = input.getDiode();

    GUI(){
        //this.setLayout(new FlowLayout());
        this.setLayout(new GridLayout());
        this.setSize(700,600);
        this.setLocationRelativeTo(null);

        //Menubar in GUI
        this.setJMenuBar(menuBar);
            //File
            menuBar.add(file);
                file.add(newCalc);
            newCalc.addActionListener(this);
                file.add(saveCalc);
            saveCalc.addActionListener(this);
                file.add(exitProg);
            exitProg.addActionListener(this);
            //Edit
            menuBar.add(edit);
                edit.add(freq);
            freq.addActionListener(this);
                edit.add(diod);
            diod.addActionListener(this);

        this.add(b1);
            b1.addActionListener(this);
        this.add(l);
        start();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void start(){
        setStatic();
        ratioCalc();
        nominellCalc();
        worstCase1();
        worstCase2();
    }

    public void setStatic(){
        al.clear();
        al.add("Input data:\n");
        al.add(input);
        al.add("Frequency: "+f+", Diodeloss: "+diode);
        al.add("\n\n");
        updateGUI(this.l, al.toArray());
    }

    public void updateGUI(JList l, Object[] data){
        DefaultListModel m = new DefaultListModel();
        for(Object o: data){
            m.addElement(o);
        }
        l.setModel(m);
    }

    public void newRun()    {
        al.clear();

        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JTextField field3 = new JTextField();

        Object[] objectInput = {
                "Urms", field1,
                "UsAC", field2,
                "I-load", field3
        };

        JOptionPane.showConfirmDialog(null, objectInput, "Input fields to calculate.", JOptionPane.OK_CANCEL_OPTION);

        input.setURMS(Double.parseDouble(field1.getText()));
        input.setUsAC(Double.parseDouble(field2.getText()));
        input.setIload(Double.parseDouble(field3.getText()));

        setStatic();

        URMS = input.getURMS();
        UsAC = input.getUsAC();
        Iload = input.getIload();
        diode = input.getDiode();
        f = input.getF();
    }

    //Calculation methods
    public void ratioCalc(){
        double ratio = URMS/UsAC;
        ratio = Math.floor(ratio*100)/100;

        if(ratio==1){
            al.add("Winding ratio");
            al.add("Winding ratio: 1:1");
            al.add("\n\n");
            updateGUI(this.l, al.toArray());
        }
        else if(ratio>1){
            al.add("Ratio: "+"1:"+ratio);
            al.add("\n\n");
            updateGUI(this.l,al.toArray());
        }
        else if (ratio<1){
            ratio = Math.round(UsAC/URMS);
            al.add("Windingratio");
            al.add("Ratio: "+ ratio +":1");
            al.add("\n\n");
            updateGUI(this.l,al.toArray());
        }
    }

    public void nominellCalc(){
        double AUsAC						= (UsAC*(Math.sqrt(2)));
        double Umax							= AUsAC-(2*diode);
        double Ur							= Umax-UsAC;
        double C							= Iload/(2*f*Ur);

        al.add("Nominell: \n");
        al.add("Voltage inn: " + UsAC +"V\n");
        al.add("Amplitude voltage: " + AUsAC +"V\n");
        al.add("Voltage after diodes: " + Umax + "V\n");
        al.add("Frequency: " + f+"Hz\n");
        al.add("Rippel: " + Ur +"V\n");
        al.add("Load: " + Iload + "A\n");
        al.add("Smoothing capacitor: " + C*1000 + "microfarad");
        al.add("\n\n");
        updateGUI(this.l,al.toArray());

    }

    public void worstCase1() {
        double W1UsAC 						= UsAC*0.9;
        double W1AUsAC						= (W1UsAC*(Math.sqrt(2)));
        double W1Umax						= W1AUsAC-(2*diode);
        double W1Ur							= W1Umax-W1UsAC;
        double W1C							= Iload/(2*f*W1Ur);

        al.add("Worstcase: -10% on inn voltage\n");
        al.add("Voltage inn: " + W1UsAC +"V\n");
        al.add("Amplitude voltage: " + W1AUsAC +"V\n" );
        al.add("Voltage after diodes: " + W1Umax + "V\n");
        al.add("Frequency: " + f +"Hz\n");
        al.add("Rippel: " + W1Ur +"V\n");
        al.add("Load: " + Iload + "A\n");
        al.add("Smoothing capacitor: " + W1C * 1000 + "microfarad");
        al.add("\n\n");
        updateGUI(this.l,al.toArray());

    }

    public void worstCase2() {
        double W2UsAC 						= UsAC*1.1;
        double W2AUsAC						= (W2UsAC*(Math.sqrt(2)));
        double W2Umax						= W2AUsAC-(2*0.7);
        double W2Ur							= W2Umax-W2UsAC;
        double W2C							= Iload/(2*f*W2Ur);

        al.add("Worstcase: +10% on inn voltage\n");
        al.add("Voltage inn: " + W2UsAC +"V\n");
        al.add("Amplitude voltage: " + W2AUsAC +"V\n" );
        al.add("Voltage after diodes: " + W2Umax + "V\n");
        al.add("Frequency: " + f +"Hz\n");
        al.add("Rippel: " + W2Ur +"V\n");
        al.add("Load: " + Iload + "A\n");
        al.add("Smoothing capacitor: " + W2C*1000 + "microfarad");
        al.add("\n\n");
        updateGUI(this.l,al.toArray());

    }

    //Edit menu methods
    public void editFreq(){
        double freq = Double.parseDouble(JOptionPane.showInputDialog(null, "Input Frequency"));
        input.setF(freq);
        f = input.getF();
        setStatic();
    }
    public void editDiod(){
        double diod = Double.parseDouble(JOptionPane.showInputDialog(null, "Input Diodeloss"));
        input.setDiode(diod);
        diode = input.getDiode();
        setStatic();
    }

    //Save results to file
    public void saveIt(){

        int saveLocation = fc.showSaveDialog(null);
        if (saveLocation == JFileChooser.APPROVE_OPTION){
            File saveto = fc.getSelectedFile();

            try {
                FileWriter fw = new FileWriter(saveto);
                BufferedWriter bfw = new BufferedWriter(fw);

                for(int i=0; i<al.size();i++){
                    bfw.write(String.valueOf(al.get(i)));
                }

                bfw.close();
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Saved to file successfully");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(b1)){
            start();
        }

        if (e.getSource().equals(newCalc)){
            newRun();
        }

        if (e.getSource().equals(saveCalc)){
            saveIt();
        }

        if (e.getSource().equals(exitProg)){
            System.exit(0);

        }

        if (e.getSource().equals(freq)){
            editFreq();
        }

        if (e.getSource().equals(diod)){
            editDiod();
        }
    }
}