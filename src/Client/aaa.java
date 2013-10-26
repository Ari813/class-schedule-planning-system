package Client;


import java.awt.*;
import java.util.*;
import javax.swing.*;

public class aaa {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JSpinner Demo");

        // Spinner with number
        SpinnerNumberModel snm = new SpinnerNumberModel(
                new Integer(1),
                new Integer(1),
                new Integer(8),
                new Integer(1)
        );
        JSpinner spnNumber = new JSpinner(snm);

        // Spinner with Dates
        SpinnerModel snd = new SpinnerDateModel(
                new Date(),
                null,
                null,
                Calendar.DAY_OF_MONTH
        );
        JSpinner spnDate = new JSpinner(snd);

        // Spinner with List
        String[] colors = {"Red","Green","Blue"};
        SpinnerModel snl = new SpinnerListModel(colors);
        JSpinner spnList = new JSpinner(snl);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 100);

        Container cont = frame.getContentPane();

        cont.setLayout(new FlowLayout());
        cont.add(new JLabel("Select Number:"));
        cont.add(spnNumber);

        cont.add(new JLabel("Select Date:"));
        cont.add(spnDate);

        cont.add(new JLabel("Select Color:"));
        cont.add(spnList);

        frame.setVisible(true);
    }
}
