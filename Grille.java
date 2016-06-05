import java.util.List;

public class Grille {
	private Diamant[][] diamants;
	private Joueur[] joueur;
	private boolean partieFinie;
	private char couleursDispo[];

	public Grille(Joueur[] joueur,int ligne, int colonne,boolean isdiamant){
		this.joueur 		 = joueur;
		this.diamants 		 = new Diamant[ligne][colonne];
		char[] tmpPosCouleur = new char[4];
		
		for (int i=0;i<ligne;i++){
			for (int j=0;j<colonne;j++){
				Diamant diamant1 = new Diamant(i,j);
				diamants[i][j] = diamant1;
			}
		}
		this.couleursDispo   = new char[diamants[0][0].RetourneTouteLesCouleurs().length - joueur.length];

		for (int i=0;i<joueur.length;i++){//corrige les angles
			int x=0;
			int x1=0;
			int y=0;
			int y1=0;
			switch (i){
				case 0:
					x=ligne-1;
					x1=ligne-2;
					y=0;
					y1=1;
					tmpPosCouleur[0] = diamants[x][y].getCouleur();
					break;
				case 1:
					x=0;
					x1=1;
					y=colonne-1;
					y1=colonne-2;
					tmpPosCouleur[1] = diamants[x][y].getCouleur();
					while (tmpPosCouleur[0] == tmpPosCouleur[1]){
						Diamant diamant=new Diamant(x,y);
						diamants[x][y]=diamant;
						tmpPosCouleur[1] = diamants[x][y].getCouleur();
					}
					break;
				case 2:
					x=ligne-1;
					x1=ligne-2;
					y=colonne-1;
					y1=colonne-2;
					tmpPosCouleur[2] = diamants[x][y].getCouleur();
					
					while ((tmpPosCouleur[0] == tmpPosCouleur[2]) || (tmpPosCouleur[1] == tmpPosCouleur[2])){
						Diamant diamant=new Diamant(x,y);
						diamants[x][y]=diamant;
						tmpPosCouleur[2] = diamants[x][y].getCouleur();
					}
					break;
				case 3:
					x=0;
					x1=1;
					y=0;
					y1=1;
					tmpPosCouleur[3] = diamants[x][y].getCouleur();
					
					while ((tmpPosCouleur[0] == tmpPosCouleur[3]) || (tmpPosCouleur[1] == tmpPosCouleur[3]) || (tmpPosCouleur[2] == tmpPosCouleur[3])){
						Diamant diamant=new Diamant(x,y);
						diamants[x][y]=diamant;
						tmpPosCouleur[3] = diamants[x][y].getCouleur();
					}
					break;
				default:
					break;
			}
			while (diamants[x][y].getCouleur()==diamants[x1][y1].getCouleur()){
				Diamant diamant1=new Diamant(x1,y1);
				diamants[x1][y1]=diamant1;
			}
			if (i<joueur.length){
				joueur[i].setControled(diamants[x][y]);
			}
			changecouleur(x,y,diamants[x][y].getCouleur(),i,isdiamant);
		}
		//afficherGrille(0,true); // Le premier affichage (basique) avec l eoile a cote du joueur 1 


		//afficherGrille(); // Le premier affichage (basique) avec l'etoile a cote du joueur 1 



		//isPartieFinie();
	}
	public void afficherGrille(int indexJoueurActuel,boolean isdiamant){
		String debutdia="<";
		String findia=">";
		if(!isdiamant){
			debutdia="|";
			findia="|";
		}
		for (int i=0;i<diamants.length;i++){
			for(int j=0;j<diamants[i].length;j++){
				char couleurdiamant=diamants[i][j].getCouleur();
				for (int k = 0; k < joueur.length; k++) {
					if (joueur[k].hasDiamant(diamants[i][j])){
						couleurdiamant=Character.toUpperCase(couleurdiamant);
					}
				}
					
				// Joueur 1
				if(indexJoueurActuel == 0 && i == diamants.length - 1 && j == 0) {
					System.out.print(" " + debutdia + couleurdiamant + findia +"1");
				}
				
				// Joueur 2
				else if(indexJoueurActuel == 1 && i == 0 && j == diamants[i].length - 1) {
					System.out.print(" " + debutdia + couleurdiamant + findia +"2");
				}
				
				// Joueur 3
				else if(indexJoueurActuel == 2 && i == diamants.length - 1 && j == diamants[i].length - 1) {
					System.out.print(" " + debutdia + couleurdiamant + findia +"3");
				}
				
				// Joueur 4	
				else if(indexJoueurActuel == 3 && i == 0 && j == 0) {
					System.out.print(" " + debutdia + couleurdiamant + findia +"4");
				}
				
				else {
					System.out.print(" " + debutdia + couleurdiamant + findia +" ");
				}
			}
			System.out.println("");
			
			
			/*for (int j=0;j<diamants.length;j++){
				System.out.print("_");
			}
			System.out.println("");*/
		}
	}
	public int nombreDiamants(){
		return (diamants.length * diamants[0].length);
	}
	public boolean isPartieFinie() {
		/*int nombreDiamants = diamants.length * diamants[0].length;
		
		for(int i = 0; i < joueur.length; i++) {
			switch(joueur.length) {
				case 2:
					break;
				
				case 3:
					break;
				
				case 4:
					break;
			}
		}
		
		return partieFinie;*/return false;
	}
	


	public char [] proposeCouleur(){
		char c0=diamants[diamants.length-1][0].getCouleur();
		char c1=diamants[0][diamants[0].length-1].getCouleur();
		
		char c2=diamants[diamants.length-1][diamants[1].length-1].getCouleur();
		
		char c3=diamants[0][0].getCouleur();	
		
		if (joueur.length<3) {
			c2=c1;	
		}
		if (joueur.length<4) {
			c3=c1;
		}	
		char tab[]=diamants[0][0].RetourneTouteLesCouleurs();
		int j = 0;
		
		for (int i=0; i<tab.length; i++){
			if(tab[i]!=c0 && tab[i]!=c1 && tab[i]!=c2  && tab[i]!=c3) {
				this.couleursDispo[j] = tab[i];
				j++;
			}
		}
		return this.couleursDispo;
	}

	
	public void setPartieFinie(boolean partieFinie) {
		this.partieFinie = partieFinie;
	}
	
	/**
	 * 
	 * Cette methode definit l'etat de la grille apres un coup du joueur
	 * 
	 * @param color Choix de la couleur a jouer
	 * @param indexJoueur le numero du joueur actuel 
	 * 
	 * @return True si le coup est correct, False sinon
	 * 
	 */
	
	public boolean setCoup(char color, int indexJoueur,boolean isdiamant) {
		boolean correct = false;
		for(int i = 0; i < couleursDispo.length; i++) {
			if(couleursDispo[i] == color) {
				correct = true;
			}
		}
		
		if(!correct){
			return correct;
		}
		else{
			nouvelleCouleur(color, indexJoueur,isdiamant);
			return correct;
		}		
	}
	private void changecouleur(int x,int y,char couleur,int indexJoueur,boolean isdiamant){
		char anciennecouleur=diamants[x][y].getCouleur();
		diamants[x][y].setCouleur(couleur);
		if(!joueur[indexJoueur].hasDiamant(diamants[x][y])) {//normalement inutile mais au cas ou
			joueur[indexJoueur].setControled(diamants[x][y]);
		}
		int x1;
		int y1;
		for (int i=-1;i<=1;i++){
			for (int j=-1;j<=1;j++){
				x1=x+i;
				y1=y+j;
				if(x1>=0 && x1<=diamants.length-1 && y1>=0 && y1<=diamants[0].length-1 && (((Math.abs(i)+Math.abs(j))==2 && isdiamant) ||((Math.abs(i)+Math.abs(j))==1 && !isdiamant)) ){
					if (anciennecouleur==diamants[x1][y1].getCouleur() && anciennecouleur!=couleur){
						changecouleur(x1,y1,couleur,indexJoueur,isdiamant);
					}
					else if (couleur==diamants[x1][y1].getCouleur()){
						if(!joueur[indexJoueur].hasDiamant(diamants[x1][y1])) {
							joueur[indexJoueur].setControled(diamants[x1][y1]);
							changecouleur(x1,y1,couleur,indexJoueur,isdiamant);
						}
					}
				}
			}
		}
		for (int x2=0;x2<=diamants.length-1;x2++){
			for(int y2=0;y2<=diamants[0].length-1;y2++){
				if(!joueur[indexJoueur].hasDiamant(diamants[x2][y2])) {
					int cpt=0;
					for (int i=-1;i<=1;i++){
						for (int j=-1;j<=1;j++){
							x1=x2+i;
							y1=y2+j;
							boolean poss=false;
							if (x1<diamants.length && y1<diamants[0].length && x1>=0 && y1>=0){
								if (joueur[indexJoueur].hasDiamant(diamants[x1][y1])){
									poss=true;
								}
							}
							else
								poss=true;
							if ((((Math.abs(i)+Math.abs(j))==2 && isdiamant) ||((Math.abs(i)+Math.abs(j))==1 && !isdiamant)) && poss) {
								cpt=cpt+1;
							}
						}
					}
					if (cpt==4){
						joueur[indexJoueur].setControled(diamants[x2][y2]);
						diamants[x2][y2].setCouleur(couleur);
					}
				}
			}
		}
	}

	private void nouvelleCouleur(char color, int indexJoueur,boolean isdiamant) {
		int x=0;
		int y=0;
		switch(indexJoueur){
			case 0:
				x=diamants.length-1;
				y=0;
				break;
			case 1:
				x=0;
				y=diamants[0].length-1;
				break;
			case 2:
				x=diamants.length-1;
				y=diamants[0].length-1;
				break;
			case 3:
				x=0;
				y=0;
				break;
			default:
				break;
		}
		changecouleur(x,y,color,indexJoueur,isdiamant);
		/*List<Diamant> diamantsControles = joueur[indexJoueur].getControledDiamants();
		
		// On change la couleur de tous les diamants contreles
		for(int i = 0; i < diamantsControles.size(); i++) {
			diamantsControles.get(i).setCouleur(color);
		}
		
		for(int i = 0; i < diamantsControles.size(); i++) {
			Diamant hautDroite;
			Diamant basDroite;
			Diamant basGauche;
			Diamant hautGauche;
			
			//System.out.println(diamantsControles[i].getPositionX() + " . y:" + diamantsControles[i].getPositionY());

			// Si on n'est pas dans le coin en bas a droite
			if (diamantsControles.get(i).getPositionX() == diamants.length - 1 && diamantsControles.get(i).getPositionY() == diamants.length - 1)
			{
				hautGauche = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() - 1];

				if(color == hautGauche.getCouleur()) {
					// Si le diamant n'est pas deja contrele
					if(!joueur[indexJoueur].hasDiamant(hautGauche)) {
						joueur[indexJoueur].setControled(hautGauche);
					}
				}
			}
			
			// Si on n'est pas dans le coin en bas a gauche
			else if (diamantsControles.get(i).getPositionX() == diamants.length - 1 && diamantsControles.get(i).getPositionY() == 0)
			{
				hautDroite = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() + 1];
				
				if(color == hautDroite.getCouleur()) {
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(hautDroite)) {
						joueur[indexJoueur].setControled(hautDroite);
					}
				}
			}
			
			// Si on n'est pas dans le coin en haut a droite
			else if (diamantsControles.get(i).getPositionX() == 0 && diamantsControles.get(i).getPositionY() == diamants.length - 1)
			{
				basGauche  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() - 1];

				if(color == basGauche.getCouleur()) {
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(basGauche)) {
						joueur[indexJoueur].setControled(basGauche);
					}
				}
			}
						

			// Si on n'est pas dans le coin en haut a gauche
			else if (diamantsControles.get(i).getPositionX() == 0 && diamantsControles.get(i).getPositionY() == 0)
			{
				basDroite  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() + 1];
										
				if(color == basDroite.getCouleur()) {
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(basDroite)) {
						joueur[indexJoueur].setControled(basDroite);
					}
				}
			}
				
			// La colonne de gauche sauf les coins
			else if(diamantsControles.get(i).getPositionY() == 0 && (diamantsControles.get(i).getPositionX() != 0 || diamantsControles.get(i).getPositionX() != diamants.length - 1)){
				hautDroite = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() + 1];
				basDroite  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() + 1];
					
				if(color == hautDroite.getCouleur()) { // Diagonale en haut a droite
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(hautDroite)) {
						joueur[indexJoueur].setControled(hautDroite);
					}
				}
					
				if(color == basDroite.getCouleur()) { // Diagonale en bas a droite
    					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(basDroite)) {
						joueur[indexJoueur].setControled(basDroite);
					}
				}
			}
				
			// La colonne de droite sauf les coins
			else if(diamantsControles.get(i).getPositionY() == diamants.length - 1  && (diamantsControles.get(i).getPositionX() != 0 || diamantsControles.get(i).getPositionX() != diamants.length - 1)){
				basGauche  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() - 1];
				hautGauche = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() - 1];
					
				if(color == hautGauche.getCouleur()) { // Diagonale en haut a droite
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(hautGauche)) {
						joueur[indexJoueur].setControled(hautGauche);
					}
				}
					
				if(color == basGauche.getCouleur()) { // Diagonale en bas a gauche
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(basGauche)) {
						joueur[indexJoueur].setControled(basGauche);
					}
				}
			}

			// La ligne du haut sauf les coins
			else if(diamantsControles.get(i).getPositionX() == 0 && (diamantsControles.get(i).getPositionY() != 0 || diamantsControles.get(i).getPositionY() != diamants.length - 1)){
				basGauche  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() - 1];
				basDroite  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() + 1];
				
				if(color == basDroite.getCouleur()) { // Diagonale en haut a droite
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(basDroite)) {
						joueur[indexJoueur].setControled(basDroite);
					}
				}
					
				if(color == basGauche.getCouleur()) { // Diagonale en haut a droite
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(basGauche)) {
						joueur[indexJoueur].setControled(basGauche);
					}
				}
			}
				
			// La ligne du bas sauf les coins
			else if(diamantsControles.get(i).getPositionX() == diamants[0].length - 1 && (diamantsControles.get(i).getPositionY() != 0 || diamantsControles.get(i).getPositionY() != diamants.length - 1)){
				hautDroite = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() + 1];
				hautGauche = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() - 1];
				
				if(color == hautGauche.getCouleur()) { // Diagonale en haut a droite
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(hautGauche)) {
						joueur[indexJoueur].setControled(hautGauche);
					}
				}
				
				if(color == hautDroite.getCouleur()) { // Diagonale en haut a droite
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(hautDroite)) {
						joueur[indexJoueur].setControled(hautDroite);
					}
				}
			}
			
			else {
				hautDroite = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() + 1];
				hautGauche = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() - 1];
				basGauche  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() - 1];
				basDroite  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() + 1];
				
				if(color == basDroite.getCouleur()) { // Diagonale en haut a droite
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(basDroite)) {
						joueur[indexJoueur].setControled(basDroite);
					}
				}
					
				if(color == basGauche.getCouleur()) { // Diagonale en haut a droite
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(basGauche)) {
						joueur[indexJoueur].setControled(basGauche);
					}
				}
				
				if(color == hautGauche.getCouleur()) { // Diagonale en haut a droite
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(hautGauche)) {
						joueur[indexJoueur].setControled(hautGauche);
					}
				}
				
				if(color == hautDroite.getCouleur()) { // Diagonale en haut a droite
					// Si le diamant n'est pas deja controle
					if(!joueur[indexJoueur].hasDiamant(hautDroite)) {
						joueur[indexJoueur].setControled(hautDroite);
					}
				}
			}
		}*/
	}


  }
	


