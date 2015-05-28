package myClasses;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This program demonstrates how to capture screenshot of a specific GUI
 * component and save it to an image file.
 * 
 * @author www.codejava.net
 *
 */
public class ScreenShort extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button1 = new JButton("Capture Me!");
	private JButton button2 = new JButton("Capture This Frame!");

	public ScreenShort() {
		super("Component GUI Capture Demo");
		setLayout(new FlowLayout());

		button1.setName("button1");
		button1.addActionListener(this);

		button2.setName("button2");
		button2.addActionListener(this);

		add(button1);
		add(button2);

		setSize(400, 120);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void captureComponent(Component component) throws AWTException {
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit()
				.getScreenSize());// component.getBounds();

		try {
			String format = "png";
			String fileName = Paths.get("").toAbsolutePath()+"//send//send_file" + "." + format;
			Robot robot = new Robot();
			BufferedImage captureImage = robot.createScreenCapture(screenRect);
			ImageIO.write(captureImage, format, new File(fileName));

			System.out.printf("The screenshot of %s was saved!",
					component.getName());
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	public void actionPerformed(ActionEvent event) {
		JButton clickedButton = (JButton) event.getSource();
		if (clickedButton == button1) {
			// capture the button
			try {
				captureComponent(button1);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SendFiles sendFiles = new SendFiles();
			try {
				sendFiles.sendFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (clickedButton == button2) {
			// capture the frame
			try {
				captureComponent(this);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ScreenShort().setVisible(true);
			}
		});
	}
}