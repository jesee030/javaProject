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
 * @creat 2021-04-18-下午 5:45
 */
import java.io.*;
import java .net.*;
import java. awt.event.*;
import java.awt.*;
import javax. swing.*;
/**
 *
 *
 *   █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 *  ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 *  ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 *  ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 *           ░     ░ ░      ░  ░

 @author JESEE030
 @ClassName javaProject.java
 @Description TODE
 @webadress https://github.com/jesee030
 *@Date 2021/4/18 下午 5:45
 */
public class ChatClient implements ActionListener,Runnable{
    JTextArea showArea;
    JTextField msgText;
    JFrame mainJframe;
    JButton sentBtn;
    JScrollPane JSPane;//滚动面板。JScrollPane 支持水平和垂直滚动视图。文本区域、表格等需要显示较多数据而空间又有限时，通常使用 JScrollPane 进行包裹以实现滚动显示。
    JPanel pane;
    Container con;//容器： 用来组织界面上的组件或者单元
    Thread thread=null;
    Socket connectToServer;
    DataInputStream inFromServer;
    DataOutputStream outToServer;

    public ChatClient(){
        mainJframe=new JFrame("聊天——客户端");
        con=mainJframe.getContentPane();
        showArea=new JTextArea();
        showArea.setEditable(false);
        showArea.setLineWrap(true);//激活自动换行功能
        JSPane=new JScrollPane(showArea);
        msgText=new JTextField();
        msgText.setColumns(30);
        msgText.addActionListener(this);
        sentBtn=new JButton("发送");
        sentBtn.addActionListener(this);

        pane=new JPanel();
        pane.setLayout(new FlowLayout());
        pane.add(msgText);
        pane.add(sentBtn);

        con.add(JSPane, BorderLayout.CENTER);
        con.add(pane, BorderLayout.SOUTH);
        mainJframe.setSize (500 ,400);
        mainJframe.setVisible (true);
        mainJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建套接字连接到服务器
        try{
            connectToServer=new Socket("localhost",5500);
            inFromServer=new DataInputStream(connectToServer.getInputStream());
            outToServer=new DataOutputStream(connectToServer .getOutputStream());
            showArea.append("连接成功，请说话...\n");

            //创建线程在后台处理对方的消息
            thread=new Thread(this);
            //setPriority:更改线程优先级，MIN_PRIORITY为最大优先级--1
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        } catch (UnknownHostException e1){
            e1.printStackTrace();
        } catch (IOException e1){
            showArea.append("抱歉，未能连接到服务器！\n");
            msgText.setEditable(false);
            sentBtn.setEnabled(false);
        }
    }

    public static void main(String[] args){
        new ChatClient();
    }


    @Override
    //
    public void actionPerformed(ActionEvent e){
        String s=msgText.getText();
        if (s.length()>0){
            try{
                outToServer.writeUTF(s);
                outToServer.flush();
                showArea.append("[客户端]： "+msgText. getText()+"\n");
                if (msgText. getText().equals("end")){
                    showArea.append("[服务端]："+"end"+"\n");
                    connectToServer.close();
                    showArea.append("client disconnected\n");
                }
            } catch (IOException e1){
                showArea.append("你的消息：“"+msgText.getText()+"”未能发送出去！\n");
            }
        }

    }

    //本线程负责将服务器传来的消息显示在对话区域
    public void run(){
        try{
            while (true){
                showArea.append("[服务端]："+inFromServer.readUTF()+"\n");
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
