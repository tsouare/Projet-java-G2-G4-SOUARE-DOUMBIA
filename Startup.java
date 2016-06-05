import java.util.InputMismatchException;
import java.util.Scanner;


public class Startup{
	public static void main(String[] args){
		versionConsole();
	}
	public static void versionConsole() {
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		int nbrjoueur = 0;
		boolean continuer = true;
		boolean isdiamant=true; //true pour mode diamant et false pour mode carre
		System.out.println("******* Jeu des 6 couleurs *******\n");
		while(continuer){
			System.out.println("Choisissez le type de damier :");
			System.out.println("1. Pour diamant <>");
			System.out.println("2. Pour carre | |");
			int typedamier=sc.nextInt();
			try{
				if (typedamier==1 || typedamier==2){
					continuer=false;
					if(typedamier==2)
						isdiamant=false;
				}
			}
			catch (InputMismatchException e) {
			    System.out.println("You entered bad data." );
			}
		}
		continuer=true;
		while(continuer) {
			System.out.println("Choisissez le nombre de joueurs :");
			System.out.println("1. Deux joueurs");
			System.out.println("2. Trois joueurs");
			System.out.println("3. Quatre joueurs");

			nbrjoueur = sc.nextInt();
			try{
				if(nbrjoueur >= 1 && nbrjoueur <= 3)
				{
					continuer = false;
					nbrjoueur++;
				}
			} catch (InputMismatchException e) {
			      System.out.println("You entered bad data." );
			}
		}
		
		Joueur[] player = new Joueur [nbrjoueur];
		for (int i=0;i<nbrjoueur;i++){
			System.out.println("Nom du joueur "  + String.valueOf(i + 1));
			String nomJoueur = sc2.nextLine();
			player[i] = new Joueur(nomJoueur);
		}
		Grille grille1 = new Grille(player, 7, 7,isdiamant);
		//boolean premierTour = true;
		continuer = true;
		System.out.print("Debut de la partie!\n");
		while (continuer){
			for(int i = 0; i < nbrjoueur; i++) {		// Penser a inverser les deux lignes
				boolean correct;
				do {
					grille1.afficherGrille(i,isdiamant);
					System.out.print("Les couleurs disponibles sont : ");
					char [] couleursDispo = grille1.proposeCouleur();
					for(int k = 0; k < 6 - nbrjoueur; k++) {
						System.out.print(couleursDispo[k] + " ");
					}
					System.out.println("\nC est a " + player[i].getNom() + " de jouer :");
					//System.out.println(i);

					correct = grille1.setCoup((sc2.nextLine()).charAt(0), i,isdiamant);
					System.out.println(player[i].getNom() + " a " + player[i].getScoretotal());
					if(player[i].getScoretotal()>=(grille1.nombreDiamants()/nbrjoueur)-1){
						System.out.println(player[i].getNom() + " a gagne");
						i=nbrjoueur;
						continuer=false;
					}
					/*if(correct){
						if(i+1<nbrjoueur){
							grille1.afficherGrille(i + 1,isdiamant);
							//premierTour = false;
						}
						else {
							grille1.afficherGrille(0,isdiamant);
							//premierTour = true;
						}
					}*/
					
					if (!correct) {
						System.out.println("La couleur que vous avez tape n est pas correcte.");
					}
				} while(!correct);
			}			
		}
		System.out.println("Rejouer? (Y/N)");
		String choix = sc2.nextLine();
		
		if(choix == "Y" || choix == "y"){
			versionConsole();
		}
		else{
			System.out.println("Au revoir");
		}
		sc.close();
		sc2.close();
	}
}
