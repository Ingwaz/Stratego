package main;


import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
/**
 * Interface graphique du plateau, utilise classe Plateau pour d�finir le visuel
 * +comportement des diff�rents �l�ments
 *     
 * @author Kraken
 *
 */
public class PlateauGUI extends JFrame
{

	private Plateau plateau;
	//!\\ ajouter tableaux rouge et bleu de d�part
	private JLabel result;
	private caseButton [][] Cb;

	public PlateauGUI (Plateau plateau)
	{
		this.setTitle("Stratego");//titre fen�tre
		this.setLocation(200,200);//positionnement lors du lancement depuis bord sup�rieur gauche
		this.plateau = plateau;//voir classe Plateau
		int size=plateau.size();
		Box hbAll=Box.createHorizontalBox();//SCHEMA A FOURNIR (modulable pour ajout ult�rieurs)
		Box vbPlateau=Box.createVerticalBox();
		Box vb1 = Box.createVerticalBox(); // boite pour les boutons

		JPanel jp = new JPanel (new GridLayout(size, size)); // panneau pour le dessin de la matrice
		JPanel jpP1 = new JPanel (new GridLayout(5, 8)); //pions P1
		JPanel jpP2 = new JPanel (new GridLayout(5, 8));//pions P2

		JButton launch = new JButton("START");
		launch.addActionListener (new LaunchButtonListener()); // bouton "Launch" avec �couteur d'action
		//CONTENU des boutons des Grid (exemple basique � remplacer par les objets UNIT)
		caseButton [][] cb = new caseButton [size][size]; 
		for(int i=0; i < size; i++)
		{
			for(int j=0; j < size; j++)
			{
				cb [i][j] = new caseButton(i,j,null);
				cb [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/01 ("+(j+i*10+1)+").gif")));//AJOUT TEMP
				cb [i][j].setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));//AJOUT TEMP
				cb [i][j].setBackground (Color.WHITE);
				cb [i][j].setPreferredSize (new Dimension (50, 50));             // on cree les boutons et on les met tous blancs
				jp.add (cb[i][j]);
				cb [i][j].addActionListener (new ColorButtonListener());
			}
		}

		caseButton [][] cb1 = new caseButton [5][8]; 
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 8; j++)
			{	
				Unit pion=new Unit("Colonel",8,"img/black/colonel.jpg");
				cb1 [i][j] = new caseButton(i,j,pion);
				cb1 [i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource(pion.getImagePath())));
				cb1 [i][j].setBackground (Color.RED);
				cb1 [i][j].setPreferredSize (new Dimension (50, 50));             // on cree les boutons et on les met tous blancs
				jpP1.add (cb1[i][j]);
				cb1 [i][j].addActionListener (new ColorButtonListener());
			}
		}

		caseButton [][] cb2 = new caseButton [5][8]; 
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 8; j++)
			{
				cb2 [i][j] = new caseButton(i,j, null);
				cb2 [i][j].setBackground (Color.BLUE);
				cb2 [i][j].setPreferredSize (new Dimension (50, 50));             // on cree les boutons et on les met tous blancs
				jpP2.add (cb2[i][j]);
				cb2 [i][j].addActionListener (new ColorButtonListener());
			}
		}

		//ADD element to the box
		//vb1.add (vb1.createVerticalGlue());//utile si il y a pls boutons
		vb1.add (launch);        

		vbPlateau.add(jpP1);
		vbPlateau.add(vb1);
		vbPlateau.add(jpP2);
		hbAll.add(jp);
		hbAll.add(vbPlateau);

		getContentPane().add (hbAll); // on ajoute le tout au contenu et on positionne 
		this.setResizable(false);//d�sactive redimensionnement
		//hbAll.setPreferredSize(new java.awt.Dimension(830, 420));
		//OBLIGATOIRE
		this.pack();
		this.setVisible(true); // on rend visible la fenetre
	}


	//!\\ Ajouter images/cases
	private class caseButton extends JButton
	{
		private int i; //ligne du bouton
		private int j; //colonne du bouton
		private Unit pion;
		
		public caseButton (int i,int j, Unit pion) 
		{
			super();
			this.i=i;
			this.j=j;
			this.pion=pion;
		}

		public int getI()
		{
			return i;
		}

		public int getJ()
		{
			return j;
		}
	}

	private class LaunchButtonListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//TO DO
		}   
	}
	//A r�impl�menter
	private class ColorButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e)
		{
			boolean color;

			caseButton button = (caseButton) e.getSource();
			color = button.getBackground().equals(Color.BLACK);
			plateau.set(button.getI(), button.getJ(), !color);

			if (color) 
			{
				button.setBackground(Color.WHITE);
			}
			else
			{
				button.setBackground(Color.BLACK);
			}
		}
	}
}   


