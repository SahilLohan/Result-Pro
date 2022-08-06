import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.*;
import static java.awt.Color.*;

// to add panel on Login Frame where gif will be shown
class Graphicsdraw extends JPanel
{
        public void paint(Graphics g){

            Toolkit t=Toolkit.getDefaultToolkit();
            Image logo=t.getImage("/home/stifler/Pictures/Java-project-images/man walking.gif");
            g.drawImage(logo, 200, 240,this);

        }
}


// main screen where we Login
class logInFrame extends JFrame
{
    // Get a database connection
    public boolean get_a_connection_and_create_next_frame(String username,String password)
    {
        return username.equals("sahil") && password.equals("#Stiflers07");
    }

    //constructor
    public logInFrame()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("Welcome To GJUS&T Result Downloader");

        // add Graphicsdraw panel to our frame
        Graphicsdraw x = new Graphicsdraw();
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

        // setting forground color for all labels
        usernameLabel.setForeground(WHITE);
        passwordLabel.setForeground(WHITE);
        note.setForeground(LIGHT_GRAY);

        // adding labels to logInFrame
        add(usernameLabel);
        add(passwordLabel);
        add(note);


        //text fields

        // 1. usernameTextField
        JTextField usernameTextField = new JTextField("eg: sahil");
        usernameTextField.setBounds((screenSize.width/2) + 250,screenSize.height/3 ,160,23);
        usernameTextField.setCaretColor(lightGray);
        usernameTextField.setVisible(true);
        add(usernameTextField);
        // 2. passwordTextField
        JTextField passwordTextField = new JTextField();
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
                    if(get_a_connection_and_create_next_frame(username,password))
                    {
                        note.setText("Connection granted");

                         //******************************************************************************
                         //                     wite a code for next frame
                         //********************************************************************************
                        // setVisible(false);
                    }
                    else
                    {
                        passwordTextField.setText("");
                        note.setText("Internet is connected but Password or username are incorrect ");
                    }
                } catch (MalformedURLException e) {
                    note.setText("Internet is not connected");
                } catch (IOException e) {
                    note.setText("Internet is not connected,sir");
                }


            });


        // some configuration in logInFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width, screenSize.height);
        getContentPane().setBackground(BLACK);
        setVisible(true);
    }


}
public class Main
{
    public static void main(String[] args)
    {
        new logInFrame();
    }
}
