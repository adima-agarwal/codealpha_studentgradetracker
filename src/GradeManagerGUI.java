// GradeManagerGUI.java
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GradeManagerGUI extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();
    private JTextField nameF = new JTextField(10), gradeF = new JTextField(5);
    private JTextArea area = new JTextArea(10, 30);

    public GradeManagerGUI() {
        setTitle("Grade Manager");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton addBtn = new JButton("Add"), reportBtn = new JButton("Summary");
        add(new JLabel("Name:")); add(nameF);
        add(new JLabel("Grade:")); add(gradeF);
        add(addBtn); add(reportBtn); add(new JScrollPane(area));

        addBtn.addActionListener(e -> {
            students.add(new Student(nameF.getText(), Double.parseDouble(gradeF.getText())));
            area.append("Added: " + nameF.getText() + "\n");
        });

        reportBtn.addActionListener(e -> {
            double sum = 0, h = students.get(0).getGrade(), l = students.get(0).getGrade();
            for (Student s : students) { sum += s.getGrade(); if(s.getGrade() > h) h = s.getGrade(); if(s.getGrade() < l) l = s.getGrade(); }
            area.append("Avg: " + (sum/students.size()) + " High: " + h + " Low: " + l + "\n");
        });
        setVisible(true);
    }

    public static void main(String[] args) { new GradeManagerGUI(); }
}