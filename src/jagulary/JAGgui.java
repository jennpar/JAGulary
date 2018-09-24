package jagulary;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import activities.Bingo;
import activities.Flashcards;
import activities.Jeopardy;
import activities.Quiz;
import controller.FileController;

public class JAGgui {
	
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    public static JList classList = new JList();
    public static JList chapterList = new JList();
    public static ArrayList<String> array = null;
    public static ArrayList<String> definitions = new ArrayList<String>();
    public static ArrayList<String> words = new ArrayList<String>();
    public static int index = 0;
    public static String library = "";
    public static String chapter = "";
	
	public static JFrame frame;
	private JPanel p;
	private JButton b1;
	private JLabel lab;
	
	public JAGgui() {
		gui(p);
	}
	
	public void gui(JPanel p) {
		frame = new JFrame("JAGulary");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 32, 91));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // get 2/3 of the height, and 2/3 of the width
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;

        // set the jframe height and width
        frame.setPreferredSize(new Dimension(width, height));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setVisible(true);
//        
//        p = new JPanel();
//        p.setBackground(new Color(0, 32, 91));
//        
//        b1 = new JButton("Start");
//        lab = new JLabel("JAGulary");
//        
//        p.add(b1);
//        p.add(lab);
        
        frame.getContentPane().add(new JPanel());
        frame.getContentPane().add(addComponentsToTitlePane());
	}
	
	public static Component addComponentsToTitlePane() {
		JPanel pane = new JPanel();
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setBackground(new Color(0, 32, 91));
        
        
        JButton startButton;
        JLabel titleLabel;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
	
		titleLabel = new JLabel("  Welcome to JAGulary!");
		titleLabel.setFont(new Font("Snap ITC", Font.PLAIN, 75));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(191, 13, 62));
		titleLabel.validate();
		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
		//c.ipady = 300;      //makes label taller
		//c.ipadx = 100;		//makes label wider
		c.gridx = 10;	
		c.gridy = 1;	//row 1
		c.weightx = 1.0;
  		c.weighty = 1.0;
		pane.add(titleLabel, c);
	
		startButton = new JButton("Start");
		startButton.setFont(new Font("Snap ITC", Font.PLAIN, 75));
		startButton.setForeground(new Color(0, 32, 91));
		startButton.setBackground(new Color(191, 13, 62));
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipady = 40;       //reset to default
		//c.gridy = 2;       //row 2
		c.gridx = 10;
		c.gridy = 2;
		c.weightx = 0.5;
  		c.weighty = 0.5;
		pane.add(startButton, c);
//		frame.add(pane);
		
		startButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {	 
		    	try {
					FileController.createDirectory();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	frame.setContentPane(addComponentsToClassPane());
		    	frame.revalidate();
		    }
		});
		
		
		return pane;
    }
	
/***************************************************** C L A S S *******************************************/    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
  	public static JPanel addComponentsToClassPane() {
    	JPanel pane = new JPanel();
          if (RIGHT_TO_LEFT) {
              pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
          }
          pane.setBackground(new Color(0, 32, 91));
          JButton loadButton;
          JLabel helloLabel, classeslabel, chapterslabel;
          //JTextArea classTextArea;
          //JList classList, chapterList;
  		pane.setLayout(new GridBagLayout());
  		GridBagConstraints c = new GridBagConstraints();
  	
  		helloLabel = new JLabel("Hello!");
  		helloLabel.setFont(new Font("Julius Sans One", Font.PLAIN, 100));
  		helloLabel.setForeground(Color.WHITE);
  		helloLabel.setBackground(new Color(191, 13, 62));
  		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
  		//c.ipady = 20;      //makes label taller
  		//c.ipadx = 200;		//makes label wider
  		c.gridx = 0;	
  		c.gridy = 1;	//row 1
  		//c.weightx = 1.0;
  		//c.weighty = 1.0;
  		pane.add(helloLabel, c);
  		
  		classeslabel = new JLabel("Classes: ");
  		classeslabel.setFont(new Font("Julius Sans One", Font.PLAIN, 50));
  		classeslabel.setForeground(Color.WHITE);
  		classeslabel.setBackground(new Color(191, 13, 62));
  		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
  		//c.ipady = 10;      //makes label taller
  		//c.ipadx = 200;		//makes label wider
  		c.gridx = 0;	
  		c.gridy = 2;	//row 1
  		//c.weightx = 1.0;
  		//c.weighty = 1.0;
  		pane.add(classeslabel, c);
  		
  		chapterslabel = new JLabel("Chapters: ");
  		chapterslabel.setFont(new Font("Julius Sans One", Font.PLAIN, 50));
  		chapterslabel.setForeground(Color.WHITE);
  		chapterslabel.setBackground(new Color(191, 13, 62));
  		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
  		//c.ipady = 10;      //makes label taller
  		//c.ipadx = 200;		//makes label wider
  		c.gridx = 2;	
  		c.gridy = 2;	//row 1
  		//c.weightx = 1.0;
  		//c.weighty = 1.0;
  		pane.add(chapterslabel, c);
  		
  		String[] listClasses = FileController.printDirectory("");
  		classList = new JList(listClasses);
  		classList.setSelectedIndex(0);
  		classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classList.setFont(new Font("Snap ITC", Font.PLAIN, 30));
  		classList.setForeground(new Color(0, 32, 91));
  		classList.setBackground(new Color(191, 13, 62));
  		c.fill = GridBagConstraints.HORIZONTAL;
  		//c.ipady = 40;       //reset to default
  		c.gridy = 3;       //row 2
  		c.gridx = 0;
  		pane.add(classList, c);
  		pane.add(new JScrollPane(classList), c);
  		library = listClasses[classList.getSelectedIndex()];

  		
  		//classList = new JList();		//EDIT THIS TO ACTUAL INSTANTIATE JLIST!!!!!!!!
  		String[] listChapters = FileController.printDirectory(listClasses[0]);
  		chapterList = new JList(listChapters);
  		chapterList.setSelectedIndex(0);
  		chapterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  		chapterList.setFont(new Font("Snap ITC", Font.PLAIN, 30));
  		chapterList.setForeground(new Color(0, 32, 91));
  		chapterList.setBackground(new Color(191, 13, 62));
  		c.fill = GridBagConstraints.HORIZONTAL;
  		//c.ipady = 40;       //reset to default
  		c.gridy = 3;       //row 2
  		c.gridx = 2;
  		pane.add(chapterList, c);
  		pane.add(new JScrollPane(chapterList), c);
  		chapter = listChapters[chapterList.getSelectedIndex()];

  		
  		classList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		       int x = classList.getSelectedIndex();
		       String[] newListChapters = FileController.printDirectory(listClasses[x]);
		       chapterList.setListData(newListChapters);
		       
		      }
		    });  		
  		
  		chapterList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		       int x = chapterList.getSelectedIndex();
		       chapter = listChapters[x];
		       
		      }
		    });  	

  		loadButton = new JButton("Add Words");
		createWhiteButton(loadButton);
		c.gridy = 1;       //row 2
		c.gridx = 3;
		//c.weightx = 1.0;
  		//c.weighty = 1.0;
		pane.add(loadButton, c);
  		
  		JButton flashcardButton = new JButton("Flashcards");
		createWhiteButton(flashcardButton);
		c.gridy = 6;       //row 2
		c.gridx = 0;
		//c.weightx = 1.0;
  		//c.weighty = 1.0;
		pane.add(flashcardButton, c);
		
		flashcardButton.addActionListener( new ActionListener()
			{
			    @Override
			    public void actionPerformed(ActionEvent e)
			    {	 
			    	frame.setContentPane(Flashcards.addComponentsToFlashcardsPane(library, chapter));
			    	frame.revalidate();	
			    	
			    }
			});
		
		
  		JButton bingoButton = new JButton("BINGO");
		createWhiteButton(bingoButton);
		c.gridy = 6;       //row 2
		c.gridx = 1;
		//c.weightx = 1.0;
  		//c.weighty = 1.0;
		pane.add(bingoButton, c);
		
		bingoButton.addActionListener( new ActionListener()
			{
			    @Override
			    public void actionPerformed(ActionEvent e)
			    {	    	
			    	try {
						frame.setContentPane(Bingo.addComponentsToBingoAuto(library, chapter));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	frame.revalidate();	
			    	
			    }
			});
		
  		JButton jeopardyButton = new JButton("Jeopardy");
		createWhiteButton(jeopardyButton);
		c.gridy = 6;       //row 2
		c.gridx = 2;
		//c.weightx = 1.0;
  		//c.weighty = 1.0;
		pane.add(jeopardyButton, c);
		
		jeopardyButton.addActionListener( new ActionListener()
			{
			    @Override
			    public void actionPerformed(ActionEvent e)
			    {	    	
			       	frame.setContentPane(Jeopardy.addComponentsToJeopardy(library, chapter));
			       	frame.revalidate();
			    	
			    }
			});
		 
		JButton quizButton = new JButton("Quiz");
		createWhiteButton(quizButton);
		c.gridy = 6;       //row 2
		c.gridx = 3;
		//c.weightx = 1.0;
  		//c.weighty = 1.0;
		pane.add(quizButton, c);
		
		
		quizButton.addActionListener( new ActionListener()
			{
			    @Override
			    public void actionPerformed(ActionEvent e)
			    {	    	
			    	frame.setContentPane(Quiz.addComponentsToQuiz(library, chapter));
			       	frame.revalidate();
			    	
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
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JAGgui();
		
	}

}
