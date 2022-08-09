import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import static java.awt.Color.*;

// to add panel on Login Frame where gif will be shown
class GraphicsDraw extends JPanel
{
        public void paint(Graphics g){

            Toolkit t=Toolkit.getDefaultToolkit();
            Image logo=t.getImage("/home/stifler/Pictures/Java-project-images/man walking.gif");
            g.drawImage(logo, 200, 240,this);

        }
}


// main screen where we Log in
class logInFrame extends JFrame
{
    Connection conn = null;
    int count_conn =0;
    JFrame internalFrame;


    // Method to Get a database connection
    public int get_a_connection(String username,String password)
    {
        String url = "jdbc:mysql://localhost:3306/student_record";

        try
        {
            if(count_conn<1) {
                conn = DriverManager.getConnection(url, username, password);
                count_conn++;
                return 1;
            }
            else
            {
                return 2;
            }
        } catch (SQLException e) {
            return 0;
        }
    }

    //constructor where we create our login frame
    public logInFrame()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("Welcome To GJUS&T Result Downloader");

        // add GraphicsDraw panel to our frame
        GraphicsDraw x = new GraphicsDraw();
        x.setBounds(0,0, ((screenSize.width)/2), screenSize.height);
        add(x);
        setLayout(null);

         // creating labels
        JLabel usernameLabel = new JLabel("Enter User-name :");
        JLabel passwordLabel = new JLabel("Enter Password  :");
        JLabel note = new JLabel("Make Sure You are Connected to INTERNET");

        // setting labels as visible
        usernameLabel.setVisible(true);
        passwordLabel.setVisible(true);
        note.setVisible(true);

        // setting bounds for labels
        usernameLabel.setBounds((screenSize.width/2) + 50, screenSize.height/3, 160,20);
        passwordLabel.setBounds((screenSize.width/2) + 50,screenSize.height/3 + 40,160,20);
        note.setBounds((screenSize.width/2) + 50,screenSize.height -200 , screenSize.width/2, 20);

        // setting foreground color for all labels
        usernameLabel.setForeground(WHITE);
        passwordLabel.setForeground(WHITE);
        note.setForeground(LIGHT_GRAY);

        // adding labels to logInFrame
        add(usernameLabel);
        add(passwordLabel);
        add(note);


        //text fields

        // 1. usernameTextField
        JTextField usernameTextField = new JTextField("root");
        usernameTextField.setBounds((screenSize.width/2) + 250,screenSize.height/3 ,160,23);
        usernameTextField.setCaretColor(lightGray);
        usernameTextField.setVisible(true);
        add(usernameTextField);
        // 2. passwordTextField
        JTextField passwordTextField = new JTextField("#Stiflers07");
        passwordTextField.setBounds((screenSize.width/2) + 250,screenSize.height/3 + 40,160,23);
        passwordTextField.setCaretColor(lightGray);
        passwordTextField.setVisible(true);
        add(passwordTextField);



        // login button
        JButton logInButton = new JButton("Log in");
        logInButton.setBounds((screenSize.width/2) + 170,screenSize.height/3 + 100,160,23);
        add(logInButton);


            // function of logInButton
            logInButton.addActionListener(actionEvent -> {
                try {
                    URL url = new URL("http://www.google.com");
                    URLConnection connection = url.openConnection();
                    connection.connect();

                    String username = usernameTextField.getText();
                    String password = passwordTextField.getText();
                    note.setText("Getting a connection .... ");

                    int ret = get_a_connection(username,password);
                    if(ret == 1)
                    {
                        note.setText("Connection granted");

                         //******************************************************************************
                         //                     code for next frame
                        create_a_new_frame();
                         //********************************************************************************
                    }
                    else if(ret ==0)// warnings
                    {
                        passwordTextField.setText("");
                        note.setText("Internet is connected");
                        JOptionPane.showMessageDialog(null, "username or password is incorrect","Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        note.setText("");
                        JOptionPane.showMessageDialog(null, "Close next Window to Log in again","Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (MalformedURLException e) {
                    note.setText("");
                    JOptionPane.showMessageDialog(null, "Internet is not connected","Error", JOptionPane.ERROR_MESSAGE);

                } catch (IOException e) {
                    note.setText("");
                    JOptionPane.showMessageDialog(null, "Internet is not connected","Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            });


        // some configuration in logInFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(screenSize.width, screenSize.height);
        getContentPane().setBackground(BLACK);
        setVisible(true);
    }

    void create_a_new_frame() throws SQLException {


        internalFrame = new JFrame("Result Downloader");

        internalFrame.setLayout(null);
        internalFrame.getContentPane().setBackground(Color.BLACK);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        internalFrame.setSize(screenSize.width, screenSize.height);
        internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        // labels

        JLabel topLabel = new JLabel("Enter Following Details");
        topLabel.setBounds(screenSize.width / 2 -100, screenSize.height / 3, 250, 20);
        topLabel.setVisible(true);
        topLabel.setForeground(Color.WHITE);
        internalFrame.add(topLabel);

        JLabel deptLabel = new JLabel("Department :-");
        deptLabel.setBounds(screenSize.width / 2 - 200, screenSize.height / 3 + 100, 150, 20);
        deptLabel.setVisible(true);
        deptLabel.setForeground(Color.WHITE);
        internalFrame.add(deptLabel);

        JLabel semLabel = new JLabel("Semester   :-");
        semLabel.setBounds(screenSize.width / 2 - 200, screenSize.height / 3 + 150, 150, 20);
        semLabel.setVisible(true);
        semLabel.setForeground(Color.WHITE);
        internalFrame.add(semLabel);

        JLabel SIDLabel = new JLabel("Student ID  :-");
        SIDLabel.setBounds(screenSize.width / 2 - 200, screenSize.height / 3 + 200, 150, 20);
        SIDLabel.setVisible(true);
        SIDLabel.setForeground(Color.WHITE);
        internalFrame.add(SIDLabel);

        JLabel warningLabel = new JLabel("");
        warningLabel.setBounds(screenSize.width / 2 -100, screenSize.height - 200, 250, 20);
        warningLabel.setVisible(true);
        warningLabel.setForeground(Color.WHITE);
        internalFrame.add(warningLabel);

        // text fields

        JTextField tdeptLabel = new JTextField();
        tdeptLabel.setBounds(screenSize.width / 2 + 100, screenSize.height / 3 + 100, 150, 20);
        tdeptLabel.setVisible(true);
        internalFrame.add(tdeptLabel);

        JTextField tsemLabel = new JTextField();
        tsemLabel.setBounds(screenSize.width / 2 + 100, screenSize.height / 3 + 150, 150, 20);
        tsemLabel.setVisible(true);
        internalFrame.add(tsemLabel);

        JTextField tSIDLabel = new JTextField("");
        tSIDLabel.setBounds(screenSize.width / 2 + 100, screenSize.height / 3 + 200, 150, 20);
        tSIDLabel.setVisible(true);
        internalFrame.add(tSIDLabel);

        internalFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    count_conn =0;
                    conn.close();
                } catch (SQLException e) {
                    warningLabel.setText("Exit the Application and log in again");
                }
                //internalFrame.dispose();
            }
        });
        showDepartments();
        internalFrame.setVisible(true);
    }

    public void showDepartments() throws SQLException
    {

        JInternalFrame tableFrame = new JInternalFrame("Departments");
        tableFrame.setMaximizable(false);
        tableFrame.setClosable(true);
        tableFrame.setVisible(true);
        tableFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        tableFrame.setBounds(0,0,500,500);


        // show department ID and Deprtments
        Statement st = null;
        ResultSet rs1 = null;


        try { // create a statement object
            st = conn.createStatement();
        }
        catch (SQLException e)
        {
            count_conn =0;
            try {
                conn.close();
            } catch (SQLException ex) {
                System.exit(0);
            }
            internalFrame.dispose();
        }
        try {
            rs1 = st.executeQuery("select * from Departments");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"Department ID", "Department Name"};
        model.setColumnIdentifiers(columnNames);


        JTable table = new JTable();
        table.setForeground(Color.WHITE);
        table.setBackground(BLACK);
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setBounds(0,0,300,300);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setForeground(Color.WHITE);
        scroll.setBackground(BLACK);
        scroll.setBounds(0,0,300,300);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableFrame.add(scroll);

        String dep_ID = "";
        String DEPT = "";
        int i =0;
        while(rs1.next())
        {
            dep_ID = Integer.toString(rs1.getInt(1));
            DEPT = rs1.getString(2);
            model.addRow(new Object[]{dep_ID,DEPT});
            i++;
        }
        if(i <1)
        {
            JOptionPane.showMessageDialog(null, "No Record Found","Error", JOptionPane.ERROR_MESSAGE);
        }
        if(i ==1)
        {
            System.out.println(i+" Record Found");
        }
        else
        {
            System.out.println(i+" Records Found");
        }
        internalFrame.add(tableFrame);
    }
}
public class Main
{
    public static void main(String[] args)
    {
        new logInFrame();
    }
}
