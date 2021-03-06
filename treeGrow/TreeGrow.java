//package treeGrow;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.*;
import java.util.*;

public class TreeGrow {
	static long startTime = 0;
	static int frameX;
	static int frameY;
	static int count;
	static ForestPanel fp;
	static SunData sundata = new SunData();
	static Thread[] threads = new Thread[10];
	static ArrayList<Tree> threadtrees = new ArrayList<Tree>();
	static int countYear;
	static JLabel label = new JLabel();
	static boolean pauseCondition = true;
	//static Tree [] convertArray;
	// start timer
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	
	// stop timer, return time elapsed in seconds
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	
	public static Thread[] createThreads(Tree []trees)
	{
		
		float minh = 18.0f;
		float maxh = 20.0f;
		
		for(int layer = 0; layer < 10; layer++)
		{
			
			for (int tree = 0 ; tree<trees.length; tree++ )
			{
				if(trees[tree].getExt() >= minh && trees[tree].getExt() < maxh) 
				{
					threadtrees.add(trees[tree]);
				}
				 //convertArray = new Tree[threadtrees.size()];
				//Tree [] convertArray =  threadtrees.toArray(new Tree[threadtrees.size()]);
				threads[layer] = new Thread (new Stimulate(sundata.trees,sundata.sunmap));
			}
			threadtrees.clear();
			minh -= 2.0f;  // next band of trees
			maxh -= 2.0f;
		}
		return threads;
	}
	
	public static void setupGUI(int frameX,int frameY,Tree [] trees) {
		
		Dimension fsize = new Dimension(800, 800);
		createThreads(trees);
		// Frame init and dimensions
    	JFrame frame = new JFrame("Photosynthesis"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setPreferredSize(fsize);
    	frame.setSize(800, 800);
    	
      	JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      	g.setPreferredSize(fsize);
 
		fp = new ForestPanel(trees);
		fp.setPreferredSize(new Dimension(frameX,frameY));
		JScrollPane scrollFrame = new JScrollPane(fp);
		fp.setAutoscrolls(true);
		scrollFrame.setPreferredSize(fsize);
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		
		
		/** This button resets the extent of all trees to 0.4 and the year count = 0
		 */ 
		JButton reset = new JButton("Reset");
      	reset.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Button Reset was clicked.");
					for(int t = 0; t < trees.length; t++)
					{
						countYear = 0;
						trees[t].setExt(0.4f);
						label.setText("Year "+countYear);
					}
				}
				});
		
		/** Button play starts simulation or continues simulation if paused was used.
		 */ 
		JButton play = new JButton("Play");
		play.addActionListener(
			new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					pauseCondition = true;
					System.out.println("Button Play was clicked.");
	
					for(int i = 0; i<threads.length;i++)
					{
						threads[i].start();
					}
				}
			});
			
		/**Button pause temporarily stops the simulation
		 */ 
		JButton pause = new JButton("Pause");
		pause.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Button pause was clicked.");
					pauseCondition = false;
				}
				
				});
		
		/** Button end closes window and exits program.
		 */ 
		JButton end = new JButton("End");
		end.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Button End was clicked.");
					System.exit(0);
				}
				
				}); 
				
		
		/** This textField updates the current year. 
		 */ 
		//JTextField textField = new JTextField("Year");
		//textField.setText(""+countYear/10+"");
		
		/** This Label shows the year. 
		 */ 
		//label = new JLabel();
		//label.setText("Year "+countYear);
			
	    g.add(scrollFrame);
	    p.add(reset);
	    p.add(pause);
	    p.add(play);
	    p.add(end);	
	    p.add(label);
	   // p.add(textField);
    	
      	frame.setLocationRelativeTo(null);  // Center window on screen.
      	g.add(p);
      	frame.add(g);
        frame.setContentPane(g);      
        frame.setVisible(true);
        Thread fpt = new Thread(fp);
        fpt.start();
	}
		
	public static void main(String[] args) {
		
		// check that number of command line arguments is correct
		if(args.length != 1)
		{
			System.out.println("Incorrect number of command line arguments. Should have form: java treeGrow.java intputfilename");
			System.exit(0);
		}
				
		// read in forest and landscape information from file supplied as argument
		sundata.readData(args[0]);
		System.out.println("Data loaded");
		
		//createThreads(sundata.trees);
		frameX = sundata.sunmap.getDimX();
		frameY = sundata.sunmap.getDimY();
		setupGUI(frameX, frameY, sundata.trees);
		
		//create and start simulation loop here as separate thread
		

	}
}
