import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelpScreen extends JFrame implements ActionListener {
	JButton Return;
	  int X = 1410;
	  int Y = 200;


	public HelpScreen() {

		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setTitle("Warehouse Boss 2017-COMP2911");
	    setSize(1678,888);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setVisible(true);

	    setLayout(new BorderLayout());
	    setContentPane(new JLabel(new ImageIcon("MenuImage/bg_instructions menu.png")));
	    setLayout(null);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/box.png");
		setIconImage(image);

		Return = new JButton("Return");
		Return.setFont(new Font("Arial", Font.BOLD, 25));
		Return.setHorizontalTextPosition(JButton.CENTER);
		Return.setVerticalTextPosition(JButton.CENTER);
		try {
			Image img1 = ImageIO.read(getClass().getResource("MenuImage/GUI_01.png"));
			Image img2 = ImageIO.read(getClass().getResource("MenuImage/GUI_02.png"));
			Return.setIcon(new ImageIcon(img1));
			Return.setDisabledIcon(new ImageIcon(img2));
			Return.setPressedIcon(new ImageIcon(img2));
			Return.setSelectedIcon(new ImageIcon(img2));
			Return.setDisabledSelectedIcon(new ImageIcon(img2));
		} catch (Exception ex) {
			//System.out.println(ex);
		}
		Return.setOpaque(false);
		Return.setContentAreaFilled(false);
		Return.setBorderPainted(false);
		Return.setFocusPainted(false);
		Return.setBounds(X - 277, Y + 460, 200, 92);
		Return.addActionListener(this);
		add(Return);

		// Just for refresh :) Not optional!
		setSize(1677, 887);
		setSize(1678, 888);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Return){
			setVisible(false);
		}
	}
	
  }

