package fUNcgrp;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class FunctionGrapher extends JPanel {

	public static void main(String[] args) {

		new FunctionGrapher();
	}

	public static JTextField textField_1;
	public static JTextField textField_2;
	public static double Z;
	JLabel lblArea;
	public static double a;
	public static double b;
	double h;
	public static double ll=0,up=100;



	private GraphPanel graph;

	private JTextField functionInput;

	public FunctionGrapher() {

		graph = new GraphPanel();

		JFrame frame = new JFrame("Function & Integration Plotter");
		frame.setSize(550, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 540, 500, 100);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblfx = new JLabel("f(x) :");
		lblfx.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblfx.setBounds(10, 11, 56, 22);
		panel.add(lblfx);

		JLabel lblUpperLimit = new JLabel("Upper Limit :");
		lblUpperLimit.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUpperLimit.setBounds(10, 44, 74, 14);
		panel.add(lblUpperLimit);

		JLabel lblLowerLimit = new JLabel("Lower Limit :");
		lblLowerLimit.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLowerLimit.setBounds(10, 66, 74, 14);
		panel.add(lblLowerLimit);

		functionInput = new JTextField();
		functionInput.setBounds(85, 11, 395, 24);
		panel.add(functionInput);
		functionInput.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(85, 41, 29, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(85, 63, 29, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		graph.setBorder(new LineBorder(new Color(0, 0, 0)));
		graph.setBounds(10, 30, 500, 500);
		frame.getContentPane().add(graph);
		
		lblArea = new JLabel();
		lblArea.setForeground(new Color(0, 0, 128));
		lblArea.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblArea.setBounds(286, 57, 177, 26);
		panel.add(lblArea);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 32));
		lblNewLabel.setBounds(269, 44, 24, 42);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(286, 44, 40, 14);
		panel.add(lblNewLabel_1);
		JLabel label = new JLabel();
		label.setBounds(279, 78, 40, 14);
		panel.add(label);

		JButton btnNewButton = new JButton("Integrate!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MathExpress function;
				try {
					String def = functionInput.getText();
					function = new MathExpress(def);
					graph.setFunction(function);
				} catch (IllegalArgumentException e) {
					graph.clearFunction();

				}
				functionInput.selectAll();
				functionInput.requestFocus();
				
				
				a = Double.parseDouble(textField_2.getText());
				b = Double.parseDouble(textField_1.getText());
				ll=a;
				up=b;
				h = (b - a) / 100;
				Trapezoidal t = new Trapezoidal();
				System.out.println(a + " " + b + " " + h);
				h = t.trap1(a, b, 100, h);
				

				System.out.println(" SG "+ h);
				h=new BigDecimal(h ).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
				
				lblArea.setText("f(x)= " + h);
				lblNewLabel.setText("\u222B");
				lblNewLabel_1.setText(""+b);
				label.setText(""+a);
				
			

			}
		});

		btnNewButton.setBounds(134, 44, 89, 36);
		panel.add(btnNewButton);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmPlotFunction = new JMenuItem("Plot function");
		mnFile.add(mntmPlotFunction);

		JMenuItem mntmPlotIntegration = new JMenuItem("Plot Integration");
		mnFile.add(mntmPlotIntegration);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame abt = new JFrame();
				abt.setSize(300, 100);
				abt.setVisible(true);
				abt.add(new JLabel("Created by Oronno & Prodhi"));
			}
		});

		// functionInput = new JTextField();
		// JTextField uplimit = new JTextField(3);
		// JTextField lowlimit = new JTextField(3);
		//
		// JPanel subpanel = new JPanel();
		// subpanel.setLayout(new BorderLayout());
		// subpanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		// subpanel.add(new JLabel("Enter a function ∫f(x) : "),
		// BorderLayout.WEST);
		// subpanel.add(new JLabel("Upper limit : "), BorderLayout.WEST);
		// subpanel.add(uplimit, BorderLayout.CENTER);
		// subpanel.add(new JLabel("Lower limit : "), BorderLayout.WEST);
		// subpanel.add(lowlimit, BorderLayout.CENTER);
		// subpanel.add(functionInput, BorderLayout.CENTER);
		// add(subpanel, BorderLayout.SOUTH);

//		functionInput.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//
//				
//			}
//		});

	} // end constructor

	// -------------------------- Painting Habijabi ----------------------------

	public static class GraphPanel extends JPanel {

		public static MathExpress func;

		GraphPanel() {

			func = null;
		}

		public void setFunction(MathExpress exp) {

			func = exp;
			repaint();
		}

		public void clearFunction() {

			func = null;
			repaint();
		}

		public void paintComponent(Graphics g) {

			super.paintComponent(g);

			

			if (func != null) {

				drawFunction(g);
			}
			drawAxes(g);
			DrawArea(g);
		}

		void drawAxes(Graphics g) {

			// g.setColor(Color.gray);
			// for(int i=0;i<=500;i+=10)
			// {
			// g.drawLine(i, 0, i, 600);
			// g.drawLine(0, i, 600, i);
			//
			// }

			int j=-5;
			g.setColor(Color.black);
			for (int i = 0; i <= 500; i += 50) {
				g.drawLine(i, 0, i, 500);
				g.drawLine(0, i, 500, i);
				
			
				g.drawString(Integer.toString(j), i, 250);
				g.drawString(Integer.toString(j), 250, i);
				j++;
				
				

			}

			
			
			g.setColor(Color.RED);
			g.drawLine(0, 250, 500, 250);
			g.drawLine(250, 0, 250, 500);
			
//			g.setColor(Color.LIGHT_GRAY);
//			g.drawLine(a1,b1,a1,250);
		}
		

		void drawFunction(Graphics g) {

			double x, y;
			double prevx, prevy;

			double dx;

			dx = 10.0 / 500;

			g.setColor(Color.BLUE);

			x = -5;
			y = func.value(x);
			
			
			Z = y;

			for (int i = 1; i <= 500; i++) {

				prevx = x;
				prevy = y;

				x += dx;

				y = func.value(x);

				if ((!Double.isNaN(y)) && (!Double.isNaN(prevy))) {

					putLine(g, prevx, prevy, x, y);
				}

			}

		}
		
		 void DrawArea(Graphics g)
		{
//			int Al = (int)a;
//			int B ;
//			int Ah = (int)b;
//			
//			Al = (Al+5)*50;
//			Ah = (Ah+5)*50;
			
			
			
//			g.setColor(Color.orange);
//				for(int i=Al;i<=Ah;i++)
//				{
//					B = (int)GraphPanel.func.value(i);
//					B  = (5-B)*50;
//					g.drawLine(i, B, i, 250);
//					
//					
//				}
			
		}

		public static void putLine(Graphics g, double x1, double y1, double x2, double y2) {
		
			int a1,b1,a2,b2;

			int width = 500;
			int height = 500;

			a1 = (int) ((x1 + 5) / 10 * width);
			b1 = (int) ((5 - y1) / 10 * height);
			a2 = (int) ((x2 + 5) / 10 * width);
			b2 = (int) ((5 - y2) / 10 * height);

			// g.drawLine(parr[i][0] * 10 + 300, 300 - parr[i][1] * 10,
			// parr[i + 1][0] * 10 + 300, 300 - parr[i + 1][1] * 10);

			if (Math.abs(y1) < 30000 && Math.abs(y2) < 30000) {

				g.setColor(Color.BLUE);
				g.drawLine(a1, b1, a2, b2);
				
			}
			
			int Al ;
			int B ;
			int Ah ;
			if(a>b)
			{
				double temp;
				temp = b;
				b = a; 
				a = temp;
				
				
			}
			
			Al = (int) ((a+5)*50);
			Ah = (int) ((b+5)*50);
			g.setColor(Color.orange);
			
			if(a1>=Al && a1<=Ah)
			{
				g.drawLine(a1, b1, a1, 250);
			}
			
			
//			int A1,A2,B1,B2;
//			A1 = ((2 + 5) / 10 * width);
//			B1 = (int) GraphPanel.func.value(A1);
//			A2 = ((4 + 5) / 10 * width);
//			
//			while(A1!=A2)
//			{
//				g.setColor(Color.orange);
//				g.drawLine(A1,B1,A1,250);
//				A1++;
//				
//			}
			
			
			
			
			

		} 

	} 

} 
