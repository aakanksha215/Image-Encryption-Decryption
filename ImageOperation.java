import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageOperation {

    public static void operate(int key) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        try {

            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for (byte b : data) {
                System.out.println(b);
                data[i] = (byte) (b ^ key);
                i++;
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        JFrame f = new JFrame();
        f.setTitle("Image Operation");
        f.setSize(400, 380);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Robto", Font.BOLD, 25);

        // Creating button
        JButton button = new JButton();
        button.setText("Open image");
        button.setFont(font);

        // Creating text field
        JTextField textField = new JTextField(10);
        textField.setFont(font);

        f.setLayout(new FlowLayout());
        f.add(button);
        f.add(textField);

        button.addActionListener(e -> {
            String text = textField.getText();
            int val = Integer.parseInt(text);
            operate(val);
        });

        f.setVisible(true);
    }
}