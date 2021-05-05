package imperius_package;

import java.util.Scanner;

public class VariablesClass {

		public static int TurnNum;
		public static int Number2;
		public static String StartA;
		public static String StartB;
		public static String StartC;
		public static String StartD;
		public static int Zdroje1;
		public static int Zdroje2;
		public static int Základny1;
		public static int Základny2;
		public static int Armáda1;
		public static int Armáda2;
		public static int Technologie1;
		public static int Technologie2;
		public static int SpyBase1a;
		public static int SpyBase1b;
		public static int SpyBase2a;
		public static int SpyBase2b;
		public static int SpyTech1a;
		public static int SpyTech1b;
		public static int SpyTech1c;
		public static int SpyArmy1a;
		public static int SpyArmy1b;
		public static int NastaveníZdrojeAI;
		public static int NastaveníZákladnyAI;
		public static int NastaveníArmádaAI;
		public static int NastaveníTechAI;
		public static int ArmádaÚtok1a;
		public static int ArmádaÚtok1b;
		public static int ArmádaÚtok1c;
		public static int ArmádaÚtok2a;
		public static int ArmádaÚtok2b;
		public static int ArmádaÚtok2c;
		public static int ArmádaUNepřítele1;
		public static int ArmádaUNepřítele2;
		public static int ÚtokDál1;
		public static int ArmyGoBack1;
		public static int NextTimeAI;
		
		 public void Endless() {
			  try (Scanner NewObj = new Scanner(System.in)) {
			
			  System.out.println("asf " + TurnNum);
			  TurnNum = TurnNum + 1;
			 
			  Endless();
			  }
		  };
		  
		  public void SetResource(int num) {
			VariablesClass.Zdroje1 = num; 
		  };
		  
		  public void Rules() {
			  try (Scanner NewObj = new Scanner(System.in)) {
				System.out.println("PRAVIDLA: \n- Na začátku máš jednu základnu. Základna ti dá každé kolo 1 zdroj. \n- Za své zdroje si můžeš každé kolo koupit armádu, technologii nebo další základnu.\n- Technologie zvyšuje výkon tvé armády, hráč, který má 2 armády a 2 technologie má sílu rovnou 4, hráč který má 1 armádu a technologii 3 má sílu tři, atd... \n- Každé kolo můžete říci své armádě, co má dělat. Špionáš vám dá informace o nepřítely. Nepřítel začíná normálně s jednou základnou jako vy (to se dá změnit v nastavení). \n- Úkol hry je zničit všechny nepřátelské základny, to se dá udělat tak, že pošlete proti nepřítely svojí armádu - to ale trvá dvě kola.\n- Armáda normálně hlídá vaše základny, takže musíte přemýšlet kdy s armádou vyrazíte na útok.\n a)Vrať se zpět do menu");
				  String Typing = NewObj.nextLine();
				  boolean JeA = Typing.equals(StartA);
				  if (JeA == true) {
					  StartChoice();
				  } else {
					  System.out.println("!!! Špatný vstup !!!");
					  Rules();
				  }
			}
			  ;
		  }
		  
		  public void StartHry() {
			Zdroje1 = 0;
			Zdroje2 = 0 + NastaveníZdrojeAI;
			Základny1 = 1;
			Základny2 = 1 + NastaveníZákladnyAI;
			Armáda1 = 0;
			Armáda2 = 0 + NastaveníArmádaAI;
			Technologie1 = 1;
			Technologie2 = 1 + NastaveníTechAI;
			SpyBase1a = 0;
			SpyBase1b = 0;
			SpyTech1a =0;
			SpyTech1b =0;
			SpyArmy1a =0;
			SpyArmy1b =0;
			TurnNum = 0;
			ArmádaÚtok1a = 0;
			ArmádaÚtok1b = 0;
			ArmádaÚtok1c = 0;
			ArmádaÚtok2a = 0;
			ArmádaÚtok2b = 0;
			ArmádaÚtok2c = 0;
			ÚtokDál1 = 0;
			ArmádaUNepřítele1 = 0;
			ArmádaUNepřítele2 = 0;
			ArmyGoBack1 = 0;
			NextTimeAI = 0;
			
			
			System.out.println("----------------\nHRA ZAČÍNÁ!\n");
			AkceNaZačátkuKola();
		  };
		  
		  public void SpyIter() {
			  if (SpyBase1b > 0) {
				  Armáda1 = Armáda1 + SpyBase1b;
				  System.out.println("Tvůj špeh zjistil, že nepřítel má " + Základny2 + " Základen");
			  };
					SpyBase1b = SpyBase1a;
					SpyBase1a = 0;
					
					if (SpyTech1b > 0) {
						  Armáda1 = Armáda1 + SpyTech1b;
						  System.out.println("Tvůj špeh zjistil, že nepřítel má " + Technologie2 + " Výšku Technologie");
					  };
					SpyTech1b = SpyTech1a;
					SpyTech1a = 0;
							
					if (SpyArmy1b > 0) {
						  Armáda1 = Armáda1 + SpyArmy1b;
						  HUD.StartInfoLabel.setText("Tvůj špeh zjistil, že nepřítel má " + Armáda2 + " Velikou armádu");
					  };
					SpyArmy1b = SpyArmy1a;
					SpyArmy1a = 0;
							
		  };
		  
		  
		  
		  public void CheckForWin () {
			  if (Základny1 == 0) {
				  int armos = ArmádaUNepřítele2 + Armáda2 + ArmádaÚtok2a + ArmádaÚtok2b + ArmádaÚtok2c;
				  System.out.println("Konec Hry!\n Prohrál si na plné čáře\n\nTvůj nepřítel měl "+ armos +" velikou celou armádu, měl "+ Základny2 +" Základen, a jeho Technologie byla na úrovni " + Technologie2 + "\nTvůj nepřítel měl " + Zdroje2 + " Zdrojů");
				  StartChoice();
			  }
			  if (Základny2 == 0) {
				  int armos = ArmádaUNepřítele1 + Armáda1 + ArmádaÚtok1a + ArmádaÚtok1b + ArmádaÚtok1c;
				  int armos2 = ArmádaUNepřítele2 + Armáda2 + ArmádaÚtok2a + ArmádaÚtok2b + ArmádaÚtok2c;
				  System.out.println("Konec Hry!\n Vyhrál jsi na plné čáře\n\nMěl jsi "+ armos +" velikou armádu, měl jsi "+ Základny1 +" Základny, a tvoje technologie byla na úrovni " + Technologie1 + "\nTvůj nepřítel měl "+ armos2 +" velikou celou armádu, měl "+ Základny2 +" Základen, a jeho Technologie byla na úrovni " + Technologie2);
				  StartChoice();
			  }
		  };
		  public void AkceNaZačátkuKola() {
			  
			  if (ArmádaUNepřítele1 == 0) {
				if (ArmádaÚtok1c >0) {
					ÚtokDál1 = 1;
				};
			  };
			  
			  ArmádaUNepřítele1 = ArmádaÚtok1c + ArmádaUNepřítele1;
			  ArmádaÚtok1c = ArmádaÚtok1b;
			  ArmádaÚtok1b = ArmádaÚtok1a;
			  ArmádaÚtok1a = 0;
			  ArmádaUNepřítele2 =  ArmádaUNepřítele2 + ArmádaÚtok2c;
			  ArmádaÚtok2c = ArmádaÚtok2b;
			  ArmádaÚtok2b = ArmádaÚtok2a;
			  ArmádaÚtok2a = 0;
			  Armáda1 = Armáda1 + ArmyGoBack1;
			  ArmyGoBack1 = 0;
			  
			  if (ArmádaUNepřítele1 > 0) {
				  if (ÚtokDál1 == 1) {
					  int x = ArmádaUNepřítele1*Technologie1;
					  int y = Armáda2 * Technologie2;
					  if (x>y) {
						  Armáda2 = 0;
						  int ztráty = ArmádaUNepřítele1; 
						  ArmádaUNepřítele1 = Math.round((x - y)/ Technologie1);
						  Základny2 = Základny2 - 1;
						  int ZtrátyFull = ztráty - ArmádaUNepřítele1;
						  System.out.println("Tvá Útočná Armáda právě vyhrála boj a zničila nepřátelskou základnu. Ztráty: " + ZtrátyFull);
					  } else {
						  ArmádaUNepřítele1 = 0;
						  Armáda2 = Math.round((y - x)/Technologie2);
						  System.out.println("Tvá Útočná Armáda právě prohrála boj o nepřátelskou základnu a byla zničena");
					  };
				  };
			  };
			 if (ArmádaUNepřítele2 > 0) {
					  int x = Armáda1*Technologie1;
					  int y = ArmádaUNepřítele2 * Technologie2;
					  if (x>=y) {
						  ArmádaUNepřítele2 = 0;
						  int ztráty = Armáda1; 
						  Armáda1 = Math.round((x - y)/ Technologie1);
						  int ZtrátyFull = ztráty - Armáda1;
						  System.out.println("Tvá Obranná Armáda právě úspěšně odrazila nepřátelský útok! Tvé Ztráty: " + ZtrátyFull);
					  } else {
						  Armáda1 = 0;
						  Základny1 = Základny1 - 1;
						  ArmádaUNepřítele2 = Math.round((y - x)/Technologie2);
						  System.out.println("Tvá Obranná Armáda právě prohrála boj o tvoji základnu. Základna byla zničena a tvoje obrana též.");
				};
			};
				CheckForWin();
				
				Zdroje1 = Základny1 + Zdroje1;
				Zdroje2 = Základny2 + Zdroje2;
				
				SpyIter();
				TurnNum = TurnNum + 1;
				System.out.println("---------- \nZačíná "+ TurnNum + " Kolo");
				Nákup();
			  };
			  
			  public void NákupArmády() {
				  Scanner NewObj = new Scanner(System.in);
					System.out.println("Tvé zdroje: " + Zdroje1 + "\n Pokud nechceš kupovat, zadej číslo 0 \nNapiš počet Obranné Armády, co chceš koupit (1 Zdroj = 1 Armáda)");
					  String StringS = NewObj.nextLine();
					    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
					    if(isNumeric == false) {
					    	System.out.println("!!! "+ StringS +" vracíte se zpět !!!");
					    	Nákup();
					    };
					    int num = Integer.parseInt(StringS);
					    
					  if (num > Zdroje1) {
						  System.out.println("!!! Málo Zdrojů !!!");
						  Nákup();
					  } else if (num < 1){
						  System.out.println("!!! Chybné číslo !!!");
						  Nákup();
						  
					  } else {
						Armáda1 = Armáda1 + num;
						Zdroje1 = Zdroje1 - num;
						Nákup();
					  }
					  NewObj.close();
			  };
			  public void NákupZákladny() {
				  int Cost = Základny1*2;
		            int num = 1;
			        int k = num*Cost;
				  if (k > Zdroje1) {
					  System.out.println("!!! Málo Zdrojů !!!");
					  Nákup();
				  } else if (num < 1){
					  System.out.println("!!! Chybné číslo !!!");
					  Nákup();
					  
				  } else {
					Zdroje1 = Zdroje1 - num*Cost;
					Základny1 = Základny1 + num;
					
					Nákup();
				  };
			  };
			  public void NákupTechnologie() {
				  Scanner NewObj = new Scanner(System.in);
					System.out.println("Tvé zdroje: " + Zdroje1 + "\nTvé technologie: "+ Technologie1 +"\n Pokud nechceš kupovat, zadej číslo 0\n Napiš počet Technologii, co chceš koupit (1 technologie = 2 Zdroje) ");
					  String StringS = NewObj.nextLine();
					    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
					    if(isNumeric == false) {
					    	System.out.println("!!! "+ StringS +" vracíte se zpět !!!");
					    	Nákup();
					    };
					    int num = Integer.parseInt(StringS);
					    int Cost = num*2;
					  if (Cost > Zdroje1) {
						  System.out.println("!!! Málo Zdrojů !!!");
						  Nákup();
					  } else if (num < 1){
						  System.out.println("!!! Chybné číslo !!!");
						  Nákup();
						  
					  } else {
						Technologie1 = Technologie1 + num;
						Zdroje1 = Zdroje1 - Cost;
						Nákup();
					  }
				NewObj.close();
			  };
			  
			  public void Nákup() {
				 Scanner NewObj = new Scanner(System.in);
					int Cost = Základny1*2;
					  System.out.println("\n- Fáze Nákupů \nZdroje: "+ Zdroje1 +"     \na) Nákup Armády (Tvá armáda:"+ Armáda1 +") \nb) Nákup jedné základny za "+ Cost +" Zdrojů (Tvé základny: "+ Základny1 +") \nc) Nákup technologie (tvá technologie: "+ Technologie1 +")\nd) už žádné nákupy");
					  String Typing = NewObj.nextLine();
					  boolean JeA = Typing.equals(StartA);
					  boolean JeB = Typing.equals(StartB);
					  boolean JeC = Typing.equals(StartC);
					  boolean JeD = Typing.equals(StartD);
					  if (JeA == true) {
						  NákupArmády();
					  } else if (JeB == true) {
						  NákupZákladny();
					  } else if(JeC == true) {
						  NákupTechnologie();
					  } else if(JeD == true) {
							 ArmyMovement();	  
					  } else {
						  System.out.println("!!! Špatný vstup !!!");
						  Nákup();
					  }
					  NewObj.close();
			  };
			  public void VyšliArmádu() {
				  Scanner NewObj = new Scanner(System.in);
					System.out.println("Tvá Obranná Armáda: " + Armáda1 + " \n Pokud nechceš posílat nic, zadej číslo 0 \nNapiš počet Obranné Armády, co chceš poslat k nepříteli (armáda se tam oběví až za 2 kola) ");
					  String StringS = NewObj.nextLine();
					    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
					    if(isNumeric == false) {
					    	System.out.println("!!! "+ StringS +" vracíte se zpět !!!");
					    	ArmyMovement();
					    };
					    int num = Integer.parseInt(StringS);
					    
					  if (num < 1) {
						   System.out.println("!!! Špatný vstup !!!");
							  ArmyMovement();
					  } else if (num > Armáda1) {
						  System.out.println("!!! Nedostatek armády !!!");
						  ArmyMovement();
					  } else {
						  System.out.println("Vyslal jsi "+ num + "Armády na útok");
						  ArmádaÚtok1a = ArmádaÚtok1a + num;
						  Armáda1 = Armáda1 - num;
						  ArmyMovement();
					  }
					  NewObj.close();
			  };
			  
			  	public void SpyBaseSent() {
			  		Scanner NewObj = new Scanner(System.in);
						System.out.println("Tvá Obranná Armáda: " + Armáda1 + "\nNapiš počet špionů Základen, kolik jich chceš vyslat (1 Špion = 1 Armáda)");
						  String StringS = NewObj.nextLine();
						    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
						    if(isNumeric == false) {
						    	System.out.println("!!! "+ StringS +" vracíte se zpět !!!");
						    	SentSpyes();
						    };
						    int num = Integer.parseInt(StringS);
						   
						  if (num < 0) {
							  System.out.println("!!! Špatný vstup !!!");
							  SentSpyes();
						  } else if (num > Armáda1) {
							  System.out.println("!!! Moc vysoké číslo !!!");
							  SpyBaseSent();
						  } else {
							  SpyBase1a = SpyBase1a + 1;
							  Armáda1 = Armáda1 - num;
							  SentSpyes();
								  }
						  NewObj.close();
						  
					  
			  		};
			  	public void SpyTechSent() {
			  		Scanner NewObj = new Scanner(System.in);
						System.out.println("Tvá Obranná Armáda: " + Armáda1 + " \nNapiš počet špionů Technologií, kolik jich chceš vyslat (1 Špion = 1 Armáda)");
						  String StringS = NewObj.nextLine();
						    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
						    if(isNumeric == false) {
						    	System.out.println("!!! "+ StringS +" vracíte se zpět !!!");
						    	SentSpyes();
						    };
						    int num = Integer.parseInt(StringS);
						   
						  if (num < 0) {
							  System.out.println("!!! Špatný vstup !!!");
							  SentSpyes();
						  } else if (num > Armáda1) {
							  System.out.println("!!! Moc vysoké číslo !!!");
							  SpyTechSent();
						  } else {
							  SpyTech1a = SpyTech1a + 1;
							  Armáda1 = Armáda1 - num;
							  SentSpyes();
								  }
						  NewObj.close();
			  };
			  	public void SpyArmySent() {
			  			Scanner NewObj = new Scanner(System.in);
							System.out.println("Tvá Obranná Armáda: " + Armáda1 + " \nNapiš počet špionů Armád, kolik jich chceš vyslat (1 Špion = 1 Armáda)");
							String StringS = NewObj.nextLine();
							boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
							if(isNumeric == false) {
								System.out.println("!!! "+ StringS +" vracíte se zpět !!!");
								SentSpyes();
							};
							int num = Integer.parseInt(StringS);
								
  if (num < 0) {
							  System.out.println("!!! "+ num +" vracíte se zpět !!!");
							  SentSpyes();
  } else if (num > Armáda1) {
							  System.out.println("!!! Nedostatek Armády !!!");
							  SpyTechSent();
  } else {
							  SpyArmy1a = SpyArmy1a + 1;
							  Armáda1 = Armáda1 - num;
							  SentSpyes();
								  }
  NewObj.close();
			  };
			  
			  public void SentSpyes() {
				  Scanner NewObj = new Scanner(System.in);
					int SpyesZákladny = SpyBase1a + SpyBase1b;
					 int SpyesTech = SpyTech1a + SpyTech1b;
					 int SpyesArmy = SpyArmy1a + SpyArmy1b;
					  System.out.println("\n- Špioni \nŠpioni Základen: " + SpyesZákladny + " \nŠpioni Technologií: " + SpyesTech + " \nŠpioni nepřátelské Armády: "+ SpyesArmy +" \na) Vyšli Špiony Základen \nb) Vyšli špiony technologií \nc) Vyšli špiony Armád \nd) Vrať se zpátky");
					  String Typing = NewObj.nextLine();
					  boolean JeA = Typing.equals(StartA);
					  boolean JeB = Typing.equals(StartB);
					  boolean JeC = Typing.equals(StartC);
					  boolean JeD = Typing.equals(StartD);
					  if (JeA == true) {
						  SpyBaseSent();
					  } else if (JeB == true) {
						  SpyTechSent();
					  } else if(JeC == true) {
						  SpyArmySent();
					  }	else if(JeD == true) {
							ArmyMovement();
					  } else {
						  System.out.println("!!! Špatný vstup !!!");
						  SentSpyes();
					  }
					  NewObj.close();
			  };
			  
		 public void ArmyMovement() {
			 Scanner NewObj = new Scanner(System.in);
				int Armádak = ArmádaÚtok1a + ArmádaÚtok1b + ArmádaÚtok1c;
				  System.out.println("\n- Pohyb Armády \nObranná Armáda doma: "+ Armáda1 +"\nÚtočná Armáda u nepřítele: "+ ArmádaUNepřítele1 +"\nÚtočná Armáda mířící k nepřítely: "+ Armádak +" \na) pošli Obrannou Armádu k nepřítely \nb) Vyšli špiony \nc) Nechceš nic dělat");
				  String Typing = NewObj.nextLine();
				  boolean JeA = Typing.equals(StartA);
				  boolean JeB = Typing.equals(StartB);
				  boolean JeC = Typing.equals(StartC);
				  if (JeA == true) {
					  VyšliArmádu();
				  } else if (JeB == true) {
					  SentSpyes();
				  } else if(JeC == true) {
					  ArmyInEnemyBase();
				  } else {
					  System.out.println("!!! Špatný vstup !!!");
					  ArmyMovement();
				  }
				  NewObj.close();
		 };
		 
		  public void ArmyInEnemyBase() {
			  int armada = ArmádaUNepřítele1 + ArmádaÚtok1a + ArmádaÚtok1b; 
			  if (ArmádaUNepřítele1 > armada) {
				  	Scanner NewObj = new Scanner(System.in);
					 int Armádak = ArmádaÚtok1a + ArmádaÚtok1b + ArmádaÚtok1c;
					  System.out.println("\n-  Útočná Armáda U nepřítele: "+ ArmádaUNepřítele1 +"\n- Útočná Armáda mířící k nepřítely: "+ Armádak +" \na) Pošli všechnu Armádu Zpátky Domů \nb) Zaútoč na další základnu \nc) nechceš nic dělat");
					  String Typing = NewObj.nextLine();
					  boolean JeA = Typing.equals(StartA);
					  boolean JeB = Typing.equals(StartB);
					  boolean JeC = Typing.equals(StartC);
					  
					  if (JeA == true) {
						  ArmyGoBack1 = Armádak + ArmádaUNepřítele1;
						  ArmádaUNepřítele1 = 0;
						  ArmádaÚtok1a = 0;
						  ArmádaÚtok1b = 0;
						  
						  ÚtokDál1 = 0;
						  AIPlay();
					  } else if (JeB == true) {
						  ÚtokDál1 = 1;
						  AIPlay();
					  } else if(JeC == true) {
						  ÚtokDál1 = 0;
						  AIPlay();
					  } else {
						  System.out.println("Špatně");
						  ArmyInEnemyBase();
					  };
					  NewObj.close();
			  };
			  
			 AIPlay();
		  };
		  
		  private static int getRandomNumberInRange(int min, int max) {

		        if (min >= max) {
		            throw new IllegalArgumentException("max must be greater than min");
		        }

		        return (int)(Math.random() * ((max - min) + 1)) + min;
		    }


		public void AIPlay() {
			int Cost = Základny2*2;
			int roundedNumber = (int) ((int) (Základny2-1)*0.5);
			if (ArmádaUNepřítele1 == 0) {
				if ( Zdroje2 >= Cost) {
					Zdroje2 = Zdroje2 - Cost;
					Základny2 = Základny2 + 1;
				} else {
				int Z = getRandomNumberInRange(roundedNumber, Zdroje2);
				int a = getRandomNumberInRange(1, 10);
				Zdroje2 = Zdroje2 - Z;
				if (a >= 4) {
					Armáda2 = Armáda2 + Z;
						
				} else {
					Technologie2 = Technologie2 + Math.round(Z/2);
					};
				};
					
				} else {
					int Z = getRandomNumberInRange(roundedNumber, Zdroje2);
					Zdroje2 = Zdroje2 - Z;
					Armáda2 = Armáda2 + Z;
					
			};
			AIMove();
		  };

		public void AIMove() {
			int a = getRandomNumberInRange(1, 5);
			if (a == 5 && ArmádaUNepřítele1 == 0) {
				ArmádaÚtok2a = Armáda2;
				Armáda2 = 0;
			};
			AkceNaZačátkuKola();
		};
		  
		  public void ZměňZákladny() {
			 Scanner NewObj = new Scanner(System.in);
				System.out.println("Napiš počet základen, o kolik by měl mít nepřítel víc, než vy na začátku hry (" + NastaveníZákladnyAI + ")");
				  int num = NewObj.nextInt();
				   
				  if (num >= 0) {
					   NastaveníZákladnyAI = num;
					   Nastavení();
				  } else {
					  System.out.println("!!! Špatný vstup !!!");
					  Nastavení();
				  }
				  NewObj.close();
		  };
		  public void ZměňArmádu() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Napiš počet základen, o kolik by měl mít nepřítel víc, než vy na začátku hry (" + NastaveníArmádaAI + ")");
				  int num = NewObj.nextInt();
				    
				  if (num >= 0) {
					   NastaveníArmádaAI = num;
					   Nastavení();
				  } else {
					  System.out.println("!!! Špatný vstup !!!");
					  Nastavení();
				  }
				  NewObj.close();
			  
		  };
		  public void ZměňTech() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Napiš výši technologie, o kterou bude mít nepřítel víc než vy na začátku hry (" + NastaveníTechAI + ")");
				  int num = NewObj.nextInt();
				    
				  if (num >= 0) {
					   NastaveníTechAI = num;
					   Nastavení();
				  } else {
					  System.out.println("!!! Špatný vstup !!!");
					  ZměňTech();
				  }
				  NewObj.close();
			};
			  

		  
		  public void Nastavení() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Nastavení:\na)Změň počet základen protivníka na začátku (" + NastaveníZákladnyAI + ") \nb)Změň armádu nepřítele na začátku (" + NastaveníArmádaAI + ") \nc)Změň Technologii nepřítele na začátku (" + NastaveníTechAI + ") \nd) Běž zpátky do menu");
				  String Typing = NewObj.nextLine();
				  
				  boolean JeA = Typing.equals(StartA);
				  boolean JeB = Typing.equals(StartB);
				  boolean JeC = Typing.equals(StartC);
				  boolean JeD = Typing.equals(StartD);
				  if (JeA == true) {
					   ZměňZákladny();
				  } else if (JeB == true) {
					  ZměňArmádu();
				  } else if(JeC == true) {
					  ZměňTech();
					  }
					else if(JeD == true) {
						StartChoice();
				  } else {
					  System.out.println("!!! Špatný vstup !!!");
					  Nastavení();
				  }
				  NewObj.close();
			  
			  ;
		  }
		  
		  public void StartChoice() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Chcete začít hru?\na)Začít   b)Pravidla hry   c)Nastavení");
				  String Typing = NewObj.nextLine();
				  boolean JeA = Typing.equals(StartA);
				  boolean JeB = Typing.equals(StartB);
				  boolean JeC = Typing.equals(StartC);
				  if (JeA == true) {
					  StartHry();
					  
				  } else if (JeB == true) {
					  Rules();
				  } else if(JeC == true) {
					  Nastavení();
				  } else {
					  System.out.println("!!! Špatný vstup !!!");
					  StartChoice();
				  }
				  NewObj.close();
			  
			  
		  };
}
