package lab0.P3;
import java.awt.*;

import javax.swing.JFrame;

// Note: you may need to close this application by typing Control-C at the
// console.

@SuppressWarnings("serial")
class MyFrame extends JFrame {

  Font f;
  boolean toggle;

  MyFrame(String label) {

    super(label);  // calls the constructor for Frame
    f = new Font ("SansSerif", Font.BOLD, 24);
    setBounds(0, 0, 450, 150);
  }

  public void paint(Graphics g) {
    g.setFont(f);
    toggle = !toggle;
    if (toggle)
      g.setColor(Color.blue);
    else g.setColor(Color.red);
    g.drawString("HELLO", 100, 100);
    g.drawOval(100,100,50,50);
    g.drawLine(100,100,400,100);
    g.drawRect(50,50,60,40);
    Math.abs(0);
  }

  public static void main(String[] args) {

    // create an instance of a Frame
    MyFrame myFrame = new MyFrame("Frame Example");
    myFrame.setVisible(true);  // needed for Frames, auto for applets
  }

}  // class FirstFrame

