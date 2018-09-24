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
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FileController;
import jagulary.JAGgui;

public class Quiz {
	private static final boolean RIGHT_TO_LEFT = false;
	private static ArrayList<String> array = null;
	private static ArrayList<String> definitions = new ArrayList<String>();
	private static ArrayList<String> words = new ArrayList<String>();
	private static int index = 0;
	private static JLabel quizQuestionLabel = new JLabel("");
    private static int quizQuestionNumber = 1;

    private static int correctQuizResults = 0;
    private static int incorrectQuizResults = 0;

	public static void createButton_1(JButton button)
	{
		GridBagConstraints c = new GridBagConstraints(); 
    	button.setFont(new Font("SNAP ITC", Font.PLAIN, 25));
		button.setForeground(new Color(0, 33, 91));
		button.setBackground(new Color(191, 13, 62));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	//c.ipady = 100;
    	//c.ipadx = 200;
    	//c.weightx = 1.0;
  		//c.weighty = 1.0;
	}
	
    public static JPanel addComponentsToQuiz(String library, String chapters)
    {
    	JPanel pane = new JPanel();
        if (RIGHT_TO_LEFT) 
        {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setBackground(new Color(0, 32, 91));
    	
    	pane.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	
    	quizQuestionNumber = 1;
    	index = 0;
        correctQuizResults = 0;
        incorrectQuizResults = 0;

    	
    	try {
			array = FileController.scanFileToArray(library + "\\" + chapters);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		definitions = FileController.getDefinition(array);
		words = FileController.getWords(array);
		int defIndex = 0;
		int max = definitions.size()-1;
		int numbersNeeded = 10;
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
		 			
		ArrayList <Integer> defNumbers = new ArrayList<Integer>();
		defNumbers.addAll(0, generated);
    	
    	JButton backButton;
    	backButton = new JButton("Back");
  		createRedButton(backButton);
  		//c.ipady = 30;       //reset to default
  		c.gridy = 9;       //row 2
  		c.gridx = 0;
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
    
    	JLabel quizLabel = new JLabel("Quiz");
    	createLabel(quizLabel);
		quizLabel.setForeground(Color.WHITE);
		c.gridx = 0;	
		c.gridy = 0;
		c.weightx = 0.5;
  		c.weighty = 0.5;
		pane.add(quizLabel, c); 
		
		JLabel questionNumberLabel = new JLabel("1/10");
		createLabel(questionNumberLabel);
		questionNumberLabel.setForeground(Color.WHITE);
		c.gridx = 3;	
		c.gridy = 0;
		c.weightx = 0.5;
  		c.weighty = 0.5;
		pane.add(questionNumberLabel, c);
		
//		JLabel questionLabel = new JLabel("Question: ");
//		createLabel(questionLabel);
//		questionLabel.setForeground(new Color(191, 13, 62));
//		c.gridx = 0;	
//		c.gridy = 1;
//		c.gridheight = 2;
//		c.gridwidth = 2;
//		c.weightx = 1.0;
//  		c.weighty = 1.0;
//		pane.add(questionLabel, c);
		String def = definitions.get(defNumbers.get(index));
		createLabel(quizQuestionLabel);
		quizQuestionLabel = new JLabel("<html><center>" + def);
		quizQuestionLabel.setHorizontalAlignment(JLabel.CENTER);
		quizQuestionLabel.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		quizQuestionLabel.setForeground(Color.WHITE);
		quizQuestionLabel.setBackground(new Color(191, 13, 62));
		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
		//c.ipady = 10;      //makes label taller
		//c.ipadx = 200;		//makes label wider
		c.gridx = 1;	
		c.gridy = 1;
		c.gridwidth =  2;
		c.gridheight = 3;
		c.weightx = 1.0;
  		c.weighty = 1.0;
		pane.add(quizQuestionLabel, c);
				
//		JButton hintButton = new JButton("Hint");=[
//		createButton_1(hintButton);
//    	c.gridx = 2;
//    	c.gridy = 1;
//    	//c.weightx = 1.0;
//  		//c.weighty = 1.0;
//    	pane.add(hintButton, c);
		
		
//		JLabel answerLabel = new JLabel("Answer: ");
//		createLabel(answerLabel);
//		answerLabel.setForeground(new Color(191, 13, 62));
//		c.gridx = 0;	
//		c.gridy = 2;
//		//c.weightx = 1.0;
//  		//c.weighty = 1.0;
//		pane.add(answerLabel, c);
		
		JTextField answerTextArea = new JTextField();
		answerTextArea.setFont(new Font("SNAP ITC", Font.PLAIN, 25));
		answerTextArea.setForeground(new Color(191, 13, 62));
		answerTextArea.setBackground(Color.WHITE);
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipady = 100;
		//c.ipadx = 100;
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 0.5;
  		c.weighty = 0.5;
		pane.add(answerTextArea, c);
		

    	JButton answerButton =  new JButton("Continue");
    	createButton_1(answerButton);
    	c.gridx = 3;
    	c.gridy = 9;
    	c.weightx = 0.5;
  		c.weighty = 0.5;
    	pane.add(answerButton, c);

    	answerButton.addActionListener( new ActionListener()
    	{
    	    @Override
    	    public void actionPerformed(ActionEvent e)
    	    {	
    	    	String answer = answerTextArea.getText().replaceAll("\\s+", "");
    	    	answerTextArea.setText("");
    	    	if(quizQuestionNumber < 10)
    	    	{
		    		quizQuestionNumber++;
		    		questionNumberLabel.setText(quizQuestionNumber + "/10");
//			    	quizQuestionLabel.setText("<html><center>" + definitions.get(defNumbers.get(index)));
			    	if (answer.equalsIgnoreCase(words.get(defNumbers.get(index)).replaceAll("\\s+", ""))) {
						correctQuizResults ++;
						index += 1;
						quizQuestionLabel.setText("<html><center>" + definitions.get(defNumbers.get(index)));
				    	
					}
					else {
						incorrectQuizResults++;
						index += 1;
						quizQuestionLabel.setText("<html><center>" + definitions.get(defNumbers.get(index)));
				    	
					}

	    			if(quizQuestionNumber == 10) 
	    			{
	    				if (answer.equalsIgnoreCase(words.get(defNumbers.get(index)))) {
							correctQuizResults ++;
						}
						else {
							incorrectQuizResults++;
						}
	    				quizQuestionNumber++;
	    	    		quizQuestionLabel.setText("<html><center>" + definitions.get(defNumbers.get(index)));
	    	    		questionNumberLabel.setText("10/10");
	    	    		answerButton.setText("Finish");
	    			}
    	    	}
    	    	else
    	    	{
    	    		
    	    		JAGgui.frame.setContentPane(addComponentsToQuizResults());
    		    	JAGgui.frame.revalidate();
    	    	}

    	    }
    	});
		return pane;
    	
    }
	   
    
    public static void calculateCorrectQuizResults()
    {}


    /************************************* Q U I Z  R E S U L T S *******************************************/    

    public static JPanel addComponentsToQuizResults()
    {
    	JPanel pane = new JPanel();
        if (RIGHT_TO_LEFT) 
        {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setBackground(new Color(0, 32, 91));
    	pane.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	
    	JButton backButton = new JButton("Back");
  		createRedButton(backButton);
  		c.ipady = 30;       //reset to default
  		c.gridy = 3;       //row 2
  		c.gridx = 2;
  		c.weightx = 1.0;
  		c.weighty = 1.0;
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
  		
  		JLabel quizResultsLabel = new JLabel("Quiz Results");
		createLabel(quizResultsLabel);
    	quizResultsLabel.setFont(new Font("SNAP ITC", Font.PLAIN, 50));
		c.ipady = 200;      //makes label taller
		c.ipadx = 300;		//makes label wider
		c.gridx = 2;	
		c.gridy = 0;
		c.weightx = 1.0;
  		c.weighty = 1.0;
		pane.add(quizResultsLabel, c);
		
		JLabel correctQuizQuestionLabel = new JLabel("Correct: "  + correctQuizResults);
		createLabel(correctQuizQuestionLabel);
    	correctQuizQuestionLabel.setFont(new Font("SNAP ITC", Font.PLAIN, 30));
		correctQuizQuestionLabel.setForeground(Color.WHITE);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1.0;
  		c.weighty = 1.0;
		pane.add(correctQuizQuestionLabel, c);
		
		int correct = (correctQuizResults*100/(quizQuestionNumber-1 ));
		
		JLabel correctQuizResultLabel = new JLabel(correct + "% correct");
		createLabel(correctQuizResultLabel);
    	correctQuizResultLabel.setFont(new Font("SNAP ITC", Font.PLAIN, 30));
		correctQuizResultLabel.setForeground(Color.WHITE);
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 1.0;
  		c.weighty = 1.0;
		pane.add(correctQuizResultLabel, c);
  		
		JLabel incorrectQuizQuestionLabel = new JLabel("Incorrect: " + incorrectQuizResults);
		createLabel(incorrectQuizQuestionLabel);
    	incorrectQuizQuestionLabel.setFont(new Font("SNAP ITC", Font.PLAIN, 30));
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1.0;
  		c.weighty = 1.0;
		pane.add(incorrectQuizQuestionLabel, c);
		
		int incorrect = (incorrectQuizResults*100/(quizQuestionNumber-1 ));
		
		JLabel incorrectQuizResultLabel = new JLabel(incorrect + "% incorrect");
		createLabel(incorrectQuizResultLabel);
    	incorrectQuizResultLabel.setFont(new Font("SNAP ITC", Font.PLAIN, 30));
		c.gridx = 2;
		c.gridy = 2;
		c.weightx = 1.0;
  		c.weighty = 1.0;
		pane.add(incorrectQuizResultLabel, c);
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
