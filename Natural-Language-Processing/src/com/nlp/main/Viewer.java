package com.nlp.main;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.nlp.stanfordnlp.Annotate;

public class Viewer {
	JLabel labelFile;
	
	JTextField txtInput;
	JTextArea txtResults;
	JButton btnBrowse;
	
	JFrame mainFrame;
	JPanel mainPanel;
	
	public Viewer(){
		
		createGUI();
	}
	
	public void createGUI(){
	
		mainFrame = new JFrame("NLP Visualizer");
		mainFrame.setSize(new Dimension(400, 400));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel txtPanel = new JPanel(new FlowLayout());
		labelFile = new JLabel("Input File");
		
		txtInput = new JTextField();
		txtInput.setColumns(20);
		txtInput.setSize(new Dimension(200,40));
		
		txtResults = new JTextArea();
		txtResults.setSize(new Dimension(300, 300));
		
		btnBrowse = new JButton("Browse");
		btnBrowse.setSize(new Dimension(40,40));
		btnBrowse.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JFileChooser fileChooser = new JFileChooser();
					int returnValue = fileChooser.showOpenDialog(mainFrame);
					if(returnValue == JFileChooser.APPROVE_OPTION){
						File selectedFile = fileChooser.getSelectedFile();
						String content = "";
						try {
							BufferedReader br = new BufferedReader(
									new FileReader(selectedFile));
							String line = null;
							while((line = br.readLine())!=null){
								content = content + line + "\n";
							}
							txtResults.setText(content);
							
							Annotate ann = new Annotate();
							ann.generateAnnotators(content);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			
			
		});
		
		
				
		txtPanel.add(labelFile);
		txtPanel.add(txtInput);
		txtPanel.add(btnBrowse);
		txtPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		mainFrame.add(txtPanel, BorderLayout.NORTH);
		
		
		mainFrame.add(txtResults, BorderLayout.CENTER);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		
	}
	
	
	public static void main(String[] args){
		Viewer viewer = new Viewer();
	}
	
	

}
