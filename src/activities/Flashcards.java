package activities;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.FileController;
import jagulary.JAGgui;

public class Flashcards {
	private static final boolean RIGHT_TO_LEFT = false;
	private static ArrayList<String> array = null;
	private static ArrayList<String> definitions = new ArrayList<String>();
	private static ArrayList<String> words = new ArrayList<String>();
	private static int index = 0;

	public static JPanel addComponentsToFlashcardsPane(String library, String chapter) {
		JPanel pane = new JPanel();
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setBackground(new Color(0, 32, 91));
        JButton flashcardsButton = new JButton();
        JButton hintButton, backButton, nextButton, prevButton;        
        JLabel classLabel, chapterLabel;
        
        index = 0;

        ArrayList<String> wordArray = new ArrayList<String>();
		try {
			wordArray = FileController.scanFileToArray(library + "\\" + chapter);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        definitions = FileController.getDefinition(wordArray);
        words = FileController.getWords(wordArray);
        int count = 0;
        
        pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
	
//		String[] className = FileController.printDirectory("");			//getSelectedLibrary(classList);
//		int x = classList.getSelectedIndex();
//		classLabel = new JLabel(className[x]);
//		//classLabel = new JLabel("Class Name:");
//		classLabel.setFont(new Font("Snap ITC", Font.PLAIN, 50));
//		classLabel.setForeground(Color.WHITE);
//		classLabel.setBackground(new Color(191, 13, 62));
//		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
//		c.ipady = 100;      //makes label taller
//		c.ipadx = 75;		//makes label wider
//		c.gridx = 10;	
//		c.gridy = 1;	//row 1
//		c.weightx = 1.0;
//  		c.weighty = 1.0;
//		pane.add(classLabel, c);
		
//		classList.addListSelectionListener(new ListSelectionListener() {
//		      public void valueChanged(ListSelectionEvent evt) {
//		       int x = classList.getSelectedIndex();
//		       String[] newListChapters = FileController.printDirectory(className[x]);		//getGroupNames(x);
//		       chapterList.setListData(newListChapters);
//		      }
//		    });
		

		
//		String[] chapterName = FileController.printDirectory(chapterList);	//getSelectedGroup(chapterList);
//		x = classList.getSelectedIndex();
//		String[] newListChapters = FileController.getGroupNames(x);
//		chapterList.setListData(newListChapters);
//		chapterLabel = new JLabel(newListChapters[x]);
//		chapterLabel = new JLabel();
//		chapterLabel.setFont(new Font("Snap ITC", Font.PLAIN, 50));
//		chapterLabel.setForeground(Color.WHITE);
//		chapterLabel.setBackground(new Color(191, 13, 62));
//		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
//		//c.ipady = 100;      //makes label taller
//		//c.ipadx = 75;		//makes label wider
//		c.gridx = 20;	
//		c.gridy = 1;	//row 1
//		//c.weightx = 1.0;
//  		//c.weighty = 1.0;
//		pane.add(chapterLabel, c);
		
//		chapterList.addListSelectionListener(new ListSelectionListener() {
//		      public void valueChanged(ListSelectionEvent evt) {
//		       int x = chapterList.getSelectedIndex();
//		       FileController.scanNewFileToArray(printDirectory[x]);
//		      }
//		    });

		createRedButton(flashcardsButton);
		//flashcardsButton.setText("<html><center>" + def);
		//c.ipady = 100;       //reset to default
		c.gridy = 3;       //row 2
		c.gridx = 10;
		
		c.weightx = 0.5;
	  	c.weighty = 0.5;
		flashcardsButton.setPreferredSize(new Dimension(400,400));
		pane.add(flashcardsButton, c);
	
		
//		flashcardsButton.addActionListener( new ActionListener()
//		{
//		    @Override
//		    public void actionPerformed(ActionEvent ae)
//		    {	    	
//			    if (flashcardsButton.getText().equals("<html><center>" + def)) {
//			          flashcardsButton.setText("<html><center>" + word);
//			    }
//			    else if(flashcardsButton.getText().equals("<html><center>" + word)) {
//			          flashcardsButton.setText("<html><center>" + def);
//			       }
//		    }
//		});
		
		
		
		
		hintButton = new JButton("HINT");
		createWhiteButton(hintButton);
		//c.ipady = 40;       //reset to default
		c.gridy = 2;       //row 2
		c.gridx = 5;
		c.weightx = 0.5;
  		c.weighty = 0.5;
		pane.add(hintButton, c);
		
		hintButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					hintButton.setText("THIS IS WHERE A BEAUTIFUL HINT GOES");
				}
			});
		
		backButton = new JButton("Back");
		createWhiteButton(backButton);
		//c.ipady = 40;       //reset to default
		c.gridy = 4;       //row 2
		c.gridx = 0;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.weightx = 0.5;
  		c.weighty = 0.5;
		pane.add(backButton, c);
		
		backButton.addActionListener( new ActionListener()
			{
			    @Override
			    public void actionPerformed(ActionEvent e)
			    {	    	
			    	JAGgui.frame.setContentPane(JAGgui.addComponentsToClassPane());
			    	JAGgui.frame.revalidate();
			    	
			    }
			});
		
		nextButton = new JButton("Next");
		createRedButton(nextButton);
		//c.ipady = 300;       //reset to default
		c.gridy = 4;       //row 2
		c.gridx = 5;
		c.weightx = 0.5;
  		c.weighty = 0.5;
		pane.add(nextButton, c);
		
		prevButton = new JButton("Previous");
		createRedButton(prevButton);
		//c.ipady = 300;       //reset to default
		c.gridy = 4;       //row 2
		c.gridx = 4;
		c.weightx = 0.5;
  		c.weighty = 0.5;
		pane.add(prevButton, c);
		
		
//		String def = definitions.get(0);
//		String word = words.get(0);
		createRedButton(flashcardsButton);
		flashcardsButton.setText("<html><center>" + definitions.get(0));
//		c.ipady = 100;       //reset to default
		c.fill = GridBagConstraints.CENTER;
		c.gridy = 1;       //row 2
		c.gridx = 1;
		c.gridwidth =  3;//GridBagConstraints.RELATIVE;
		c.gridheight = 2;//GridBagConstraints.RELATIVE;
		c.weightx = 1.0;
	  	c.weighty = 1.0;
		flashcardsButton.setPreferredSize(new Dimension(400,400));
		pane.add(flashcardsButton, c);
		
		flashcardsButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent ae)
		    {	    	
			    if (flashcardsButton.getText().equals("<html><center>" + definitions.get(index))) {
			          flashcardsButton.setText("<html><center>" + words.get(index));
			    }
			    else if(flashcardsButton.getText().equals("<html><center>" + words.get(index))) {
			          flashcardsButton.setText("<html><center>" + definitions.get(index));
			       }
		    }
		});
		
		nextButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent ae)
		    {	//for (int i = 1; i<definitions.size(); i++)
//		    	{
			    	index += 1;
			    	flashcardsButton.setText("<html><center>" + definitions.get(index));
//		    	}
		    }
		});
		
		prevButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent ae)
		    {	//for (int i = 1; i<definitions.size(); i++)
//		    	{
			    	index -= 1;
			    	flashcardsButton.setText("<html><center>" + definitions.get(index));
//		    	}
		    }
		});
		
		return pane;
		
    }
	
	public static void createWhiteButton(JButton button){
    	
        GridBagConstraints c = new GridBagConstraints(); 
		    	 
		button.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		button.setBackground(Color.WHITE);
		button.setForeground(new Color(191, 13, 62));
		button.setBorder(BorderFactory.createLineBorder(new Color(191, 13, 62)));
		button.setPreferredSize(new Dimension(200, 100));
		 		
	}
	  
	public static void createRedButton(JButton button) {	   
		
		GridBagConstraints c = new GridBagConstraints(); 
		    	 
		button.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(191, 13, 62));
		button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		button.setPreferredSize(new Dimension(200, 100));
		    	 
     }
	
	public static void createLabel(JLabel label) {
    	 
    	 GridBagConstraints c = new GridBagConstraints(); 
    	 label.setHorizontalAlignment(JLabel.CENTER);
		 label.setFont(new Font("Snap ITC", Font.PLAIN, 40));
		 label.setForeground(new Color(191, 13, 62));
		 label.setPreferredSize(new Dimension(200, 100));

     }

   

}
