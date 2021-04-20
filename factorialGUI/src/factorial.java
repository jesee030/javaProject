/**
 * █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @author JESEE030
 * @webadress https://github.com/jesee030
 * @Version 1.0
 * @creat 2021-04-20-上午 10:38
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @author JESEE030
 * @ClassName javaProject.java
 * @Description TODE
 * @webadress https://github.com/jesee030
 * @Date 2021/4/20 上午 10:38
 */
public class factorial implements ActionListener {
    JFrame myFrame;
    JPanel jPanel;
    JTextField msgText;
    JButton button;
    JTextArea jTextArea;
    JScrollPane jScrollPane;

    public factorial() {
        myFrame = new JFrame("FACTORIAL CALCULATER");
        myFrame.setSize(500,300);
        jTextArea = new JTextArea("F(n)=1!+...+n!\n");
        jTextArea.setSize(400,200);
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        jScrollPane = new JScrollPane(jTextArea);
        msgText = new JTextField(30);
        msgText.addActionListener(this);
        button = new JButton("Calculate");
        button.addActionListener(this);

        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.setSize(500, 300);
        jPanel.add(jTextArea);
        jPanel.add(msgText);
        jPanel.add(button);

        myFrame.add(jPanel);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        long sum=0, num=1;
        int i;
        i = Integer.valueOf(msgText.getText()).intValue();
        for (int j = 0; j < i ;j++){
            num*=i;
            sum+=num;
        }
        jTextArea.append(sum +"\n");
    }
}
