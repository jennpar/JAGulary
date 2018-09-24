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
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.FileController;
import jagulary.JAGgui;

public class Jeopardy {
	private static ArrayList<String> array = new ArrayList<String>();
	private static ArrayList<String> definitions = new ArrayList<String>();
	private static ArrayList<String> words = new ArrayList<String>();
	private static ArrayList<Integer> indexNumbers = new ArrayList<Integer>();
	private static int index = 0;
    private static int score = 0;
    private static final boolean RIGHT_TO_LEFT = false;

	public static JPanel addComponentsToJeopardy(String library, String chapter) {
		JPanel pane = new JPanel();
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setBackground(new Color(0, 32, 91));
        
        score = 0;
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
        
        int max = definitions.size()-1;
		int numbersNeeded = 25;
		if (max < numbersNeeded)
		{
		    throw new IllegalArgumentException("Can't ask for more numbers than are available");
		}
		Random rng = new Random(); // Ideally just create one instance globally
		// Note: use LinkedHashSet to maintain insertion order
		Set<Integer> generated = new LinkedHashSet<Integer>();
		while (generated.size() < numbersNeeded)
		{
		    Integer next = rng.nextInt(max) + 1;
		    // As we're adding to a set, this will automatically do a containment check
		    generated.add(next);
		}
		 			
		indexNumbers.addAll(0, generated);
        
        //JButton btn = new JButton();
        JButton backButton, btn;
        JLabel label, jeopardyLabel, scoreTitleLabel, scoreLabel;
        pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		jeopardyLabel = new JLabel("Jeopardy");
		jeopardyLabel.setHorizontalAlignment(JLabel.CENTER);
		jeopardyLabel.setFont(new Font("Snap ITC", Font.PLAIN, 30));
		jeopardyLabel.setForeground(Color.WHITE);
		jeopardyLabel.setBackground(new Color(191, 13, 62));
		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
		//c.ipady = 10;      //makes label taller
		//c.ipadx = 200;		//makes label wider
		c.gridx = 3;	
		c.gridy = 0;	//row 1
		//c.weightx = 1.0;
  		//c.weighty = 1.0;
		pane.add(jeopardyLabel, c); 
		
		scoreTitleLabel = new JLabel("Score:");
		scoreTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreTitleLabel.setFont(new Font("Snap ITC", Font.PLAIN, 30));
		scoreTitleLabel.setForeground(Color.WHITE);
		scoreTitleLabel.setBackground(new Color(191, 13, 62));
		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
		//c.ipady = 10;      //makes label taller
		//c.ipadx = 200;		//makes label wider
		c.gridx = 0;	
		c.gridy = 3;	//row 1
		//c.weightx = 1.0;
  		//c.weighty = 1.0;
		pane.add(scoreTitleLabel, c); 
		
		scoreLabel = new JLabel(score + "");
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setFont(new Font("Snap ITC", Font.PLAIN, 30));
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setBackground(new Color(191, 13, 62));
		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
		//c.ipady = 10;      //makes label taller
		//c.ipadx = 200;		//makes label wider
		c.gridx = 0;	
		c.gridy = 4;	//row 1
		//c.weightx = 1.0;
  		//c.weighty = 1.0;
		pane.add(scoreLabel, c); 

		backButton = new JButton("Back");
  		createRedButton(backButton);
  		//c.ipady = 30;       //reset to default
  		c.gridy = 9;       //row 2
  		c.gridx = 0;
  		//c.weightx = 1.0;
  		//c.weighty = 1.0;
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

		
//		String [] array = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};
//		 int x = 1;
//		 for (int i = 0; i < array.length; i++)
//		 {
//			 	label = new JLabel(array[i]);
//				createLabel(label);
//				c.gridy = 1;       //row 2
//				c.gridx = x;
//				//c.weightx = 1.0;
//		  		//c.weighty = 1.0;
//				pane.add(label, c);
//				x++;
//		 }
		
		for (int column = 1; column < 6; column++ )
		{
				 btn = new JButton("10");
				 createRedButton(btn);
				 c.gridx = column;
				 c.gridy = 2;
				 //c.weightx = 1.0;
			  	 //c.weighty = 1.0;
				 pane.add(btn, c);
				 
				 btn.addActionListener( new ActionListener()
					{
					    @Override
					    public void actionPerformed(ActionEvent ae)
					    {	    	
					    	int answer = addComponentsToPlayJeopardy();
					    	if (answer ==1) {
					    		score += 10;
					    		scoreLabel.setText(score + "");
					    	}
					    	else {
					    		score += 0;
					    	}
					    	JButton source = (JButton) ae.getSource();
			                source.setEnabled(false);
			                source.setBackground(Color.GRAY);
					    }
					});
		 }
		
		for (int column = 1; column < 6; column++ )
		{
				 btn = new JButton("20");
				 createWhiteButton(btn);
				 c.gridx = column;
				 c.gridy = 3;
				 //c.weightx = 1.0;
			  	 //c.weighty = 1.0;
				 pane.add(btn, c);
				 
				 btn.addActionListener( new ActionListener()
					{
					    @Override
					    public void actionPerformed(ActionEvent ae)
					    {	    	
					    	int answer = addComponentsToPlayJeopardy();
					    	if (answer ==1) {
					    		score += 20;
					    		scoreLabel.setText(score + "");
					    	}
					    	else {
					    		score += 0;
					    	}
					    	JButton source = (JButton) ae.getSource();
			                source.setEnabled(false);
			                source.setBackground(Color.GRAY);
					    }
					});
		 }
		
		for (int column = 1; column < 6; column++ )
		{
				 btn = new JButton("30");
				 createRedButton(btn);
				 c.gridx = column;
				 c.gridy = 4;
				 //c.weightx = 1.0;
			  	 //c.weighty = 1.0;
				 pane.add(btn, c);
				 
				 btn.addActionListener( new ActionListener()
					{
					    @Override
					    public void actionPerformed(ActionEvent ae)
					    {	    	
					    	int answer = addComponentsToPlayJeopardy();
					    	if (answer ==1) {
					    		score += 30;
					    		scoreLabel.setText(score + "");
					    	}
					    	else {
					    		score += 0;
					    	}
					    	JButton source = (JButton) ae.getSource();
			                source.setEnabled(false);
			                source.setBackground(Color.GRAY);
					    }
					});
		 }
		
		for (int column = 1; column < 6; column++ )
		{
				 btn = new JButton("40");
				 createWhiteButton(btn);
				 c.gridx = column;
				 c.gridy = 5;
				 //c.weightx = 1.0;
			  	 //c.weighty = 1.0;
				 pane.add(btn, c);
				 
				 btn.addActionListener( new ActionListener()
					{
					    @Override
					    public void actionPerformed(ActionEvent ae)
					    {	    	
					    	int answer = addComponentsToPlayJeopardy();
					    	if (answer ==1) {
					    		score += 40;
					    		scoreLabel.setText(score + "");
					    	}
					    	else {
					    		score += 0;
					    	}
					    	JButton source = (JButton) ae.getSource();
			                source.setEnabled(false);
			                source.setBackground(Color.GRAY);
					    }
					    
					});
		 }
		
		for (int column = 1; column < 6; column++ )
		{
				 btn = new JButton("50");
				 createRedButton(btn);
				 c.gridx = column;
				 c.gridy = 6;
				 //c.weightx = 1.0;
			  	 //c.weighty = 1.0;
				 pane.add(btn, c);
	
		
				btn.addActionListener( new ActionListener()
				{
				    @Override
				    public void actionPerformed(ActionEvent ae)
				    {	
				    	int answer = addComponentsToPlayJeopardy();
				    	if (answer ==1) {
				    		score += 50;
				    		scoreLabel.setText(score + "");
				    	}
				    	else {
				    		score += 0;
				    	}
				    	JButton source = (JButton) ae.getSource();
		                source.setEnabled(false);
		                source.setBackground(Color.GRAY);
				    }
				});
		}
		return pane;
	 }	
    
    public static int addComponentsToPlayJeopardy() {
        
//        UIManager UI = new UIManager();
        UIManager.put("OptionPane.background", new Color(0, 32, 91));
        UIManager.put("Panel.background", Color.WHITE);
        
        JOptionPane option = new JOptionPane();
        option.setFont(new Font("Snap ITC", Font.PLAIN, 20));
        String answer = JOptionPane.showInputDialog(null, definitions.get(indexNumbers.get(index)));
        if (answer.equalsIgnoreCase(words.get(indexNumbers.get(index)))) {
			index++;
        	return 1;
		}
		else {
			index++;
			return 0;
		}
        
//        if (answer.equalsIgnoreCase(words.get(index)))
//    	{
//    		label.setText("Correct Answer");
//    	}
//    	else
//    	{
//    		System.out.print(wordTextBox.getText());
//    		System.out.print("\n" + words.get(index));
////    		label.setText("Incorrect Answer");
//    		wordTextBox.setEnabled(false);
//    	}
		
        	
        	
        	
        	
//  		answerLabel = new JLabel("<html><center>" + "Enter Answer");
//  		answerLabel.setFont(new Font("Snap ITC", Font.CENTER_BASELINE, 50));
//  		answerLabel.setForeground(new Color(191, 13, 62));
//  		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
//  		//c.ipady = 75;      //makes label taller
//  		//c.ipadx = 75;		//makes label wider
//  		c.gridx = 1;	
//  		c.gridy = 3;	//row 1
//  		c.gridwidth =  2;
//		c.gridheight = 1;
//  		pane.add(answerLabel, c);
//  		
//  		index += 1;
//  		String def = definitions.get(index);
//		defLabel = new JLabel("<html><center>" + def);
//		defLabel.setHorizontalAlignment(JLabel.CENTER);
//		defLabel.setFont(new Font("Snap ITC", Font.PLAIN, 50));
//		defLabel.setForeground(Color.WHITE);
//		defLabel.setBackground(new Color(191, 13, 62));
//		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
//		//c.ipady = 10;      //makes label taller
//		//c.ipadx = 200;		//makes label wider
//		c.gridx = 1;	
//		c.gridy = 2;	//row 1
//		c.gridwidth =  4;
//		c.gridheight = 1;
//		c.weightx = 1.0;
//	  	c.weighty = 1.0;
//		pane.add(defLabel, c); 
//
//  		wordTextBox = new JTextField();
//  		wordTextBox.setFont(new Font("Julius Sans One", Font.PLAIN, 50));
//  		wordTextBox.setForeground(new Color(191, 13, 62));
//  		wordTextBox.setBackground(Color.WHITE);
//  		c.fill = GridBagConstraints.HORIZONTAL;
//  		//c.ipadx = 100;       //reset to default
//  		c.gridy = 4;       //row 2
//  		c.gridx = 2;
//  		c.gridwidth =  2;
//		c.gridheight = 2;
//  		pane.add(wordTextBox, c);
//  		
//  		
//  		JLabel label = new JLabel();
//		label.setHorizontalAlignment(JLabel.CENTER);
//		label.setFont(new Font("Snap ITC", Font.PLAIN, 50));
//		label.setForeground(Color.WHITE);
//		label.setBackground(new Color(191, 13, 62));
//		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
//		//c.ipady = 10;      //makes label taller
//		//c.ipadx = 200;		//makes label wider
//		c.gridx = 2;	
//		c.gridy = 6;	//row 1
//		c.gridwidth =  4;
//		c.gridheight = 1;
//		c.weightx = 1.0;
//	  	c.weighty = 1.0;
//	  	pane.add(label, c);
//	  	
//  		wordTextBox.addActionListener( new ActionListener()
//		{
//		    @Override
//		    public void actionPerformed(ActionEvent ae)
//		    {	
//		    	
//		    	if (wordTextBox.getText().equalsIgnoreCase(words.get(index)))
//		    	{
//		    		label.setText("Correct Answer");
//		    	}
//		    	else
//		    	{
//		    		System.out.print(wordTextBox.getText());
//		    		System.out.print("\n" + words.get(index));
//		    		label.setText("Incorrect Answer");
//		    		wordTextBox.setEnabled(false);
//		    	}
//		    }
//		});
//  		
//  		submitButton = new JButton("<html><center>" + "Next");
//  		submitButton.setFont(new Font("Snap ITC", Font.PLAIN, 50));
//  		submitButton.setForeground(new Color(0, 32, 91));
//  		submitButton.setBackground(new Color(191, 13, 62));
//  		c.fill = GridBagConstraints.HORIZONTAL;
//  		//c.ipady = 40;       //reset to default
//  		c.gridy = 9;       //row 2
//  		c.gridx = 2;
//  		c.gridwidth =  1;
//		c.gridheight = 1;
//  		pane.add(submitButton, c);
//  		
//
//  		
//  		submitButton.addActionListener( new ActionListener()
//		{
//		    @Override
//		    public void actionPerformed(ActionEvent ae)
//		    {	
//		    	
//		    }
//		});


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
