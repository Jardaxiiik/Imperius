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
		public static int ArmádaUNepøítele1;
		public static int ArmádaUNepøítele2;
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
				System.out.println("Pravidla: \n- Na zaèátku máš jednu základnu. Základna ti dá každé kolo 1 zdroj. \n- Za své zdroje si mùžeš každé kolo koupit armádu, technologii nebo další základnu.\n- Technologie zvyšuje výkon tvé armády, hráè, který má 2 armády a 2 technologie má sílu rovnou 4, hráè který má 1 armádu a technologii 3 má sílu tøi, atd... \n- Každé kolo mùžete øíci své armádì, co má dìlat. Špionáš vám dá informace o nepøítely. Nepøítel zaèíná normálnì s jednou základnou jako vy (to se dá zmìnit v nastavení). \n- Úkol hry je znièit všechny nepøátelské základny, to se dá udìlat tak, že pošlete proti nepøítely svojí armádu - to ale trvá dvì kola.\n- Armáda normálnì hlídá vaše základny, takže musíte pøemýšlet kdy s armádou vyrazíte na útok.\n a)Vra se zpìt do menu");
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
			ArmádaUNepøítele1 = 0;
			ArmádaUNepøítele2 = 0;
			ArmyGoBack1 = 0;
			NextTimeAI = 0;
			
			
			System.out.println("----------------\nHRA ZAÈÍNÁ!\n");
			AkceNaZaèátkuKola();
		  };
		  
		  public void SpyIter() {
			  if (SpyBase1b > 0) {
				  Armáda1 = Armáda1 + SpyBase1b;
				  System.out.println("Tvùj špeh zjistil, že nepøítel má " + Základny2 + " Základen");
			  };
					SpyBase1b = SpyBase1a;
					SpyBase1a = 0;
					
					if (SpyTech1b > 0) {
						  Armáda1 = Armáda1 + SpyTech1b;
						  System.out.println("Tvùj špeh zjistil, že nepøítel má " + Technologie2 + " Výšku Technologie");
					  };
					SpyTech1b = SpyTech1a;
					SpyTech1a = 0;
							
					if (SpyArmy1b > 0) {
						  Armáda1 = Armáda1 + SpyArmy1b;
						  System.out.println("Tvùj špeh zjistil, že nepøítel má " + Armáda2 + " Velikou armádu");
					  };
					SpyArmy1b = SpyArmy1a;
					SpyArmy1a = 0;
							
		  };
		  
		  
		  
		  public void CheckForWin () {
			  if (Základny1 == 0) {
				  int armos = ArmádaUNepøítele2 + Armáda2 + ArmádaÚtok2a + ArmádaÚtok2b + ArmádaÚtok2c;
				  System.out.println("Konec Hry!\n Prohrál si na plné èáøe\n\nTvùj nepøítel mìl "+ armos +" velikou celou armádu, mìl "+ Základny2 +" Základen, a jeho Technologie byla na úrovni " + Technologie2 + "\nTvùj nepøítel mìl " + Zdroje2 + " Zdrojù");
				  StartChoice();
			  }
			  if (Základny2 == 0) {
				  int armos = ArmádaUNepøítele1 + Armáda1 + ArmádaÚtok1a + ArmádaÚtok1b + ArmádaÚtok1c;
				  int armos2 = ArmádaUNepøítele2 + Armáda2 + ArmádaÚtok2a + ArmádaÚtok2b + ArmádaÚtok2c;
				  System.out.println("Konec Hry!\n Vyhrál jsi na plné èáøe\n\nMìl jsi "+ armos +" velikou armádu, mìl jsi "+ Základny1 +" Základny, a tvoje technologie byla na úrovni " + Technologie1 + "\nTvùj nepøítel mìl "+ armos2 +" velikou celou armádu, mìl "+ Základny2 +" Základen, a jeho Technologie byla na úrovni " + Technologie2);
				  StartChoice();
			  }
		  };
		  public void AkceNaZaèátkuKola() {
			  
			  if (ArmádaUNepøítele1 == 0) {
				if (ArmádaÚtok1c >0) {
					ÚtokDál1 = 1;
				};
			  };
			  
			  ArmádaUNepøítele1 = ArmádaÚtok1c + ArmádaUNepøítele1;
			  ArmádaÚtok1c = ArmádaÚtok1b;
			  ArmádaÚtok1b = ArmádaÚtok1a;
			  ArmádaÚtok1a = 0;
			  ArmádaUNepøítele2 =  ArmádaUNepøítele2 + ArmádaÚtok2c;
			  ArmádaÚtok2c = ArmádaÚtok2b;
			  ArmádaÚtok2b = ArmádaÚtok2a;
			  ArmádaÚtok2a = 0;
			  Armáda1 = Armáda1 + ArmyGoBack1;
			  ArmyGoBack1 = 0;
			  
			  if (ArmádaUNepøítele1 > 0) {
				  if (ÚtokDál1 == 1) {
					  int x = ArmádaUNepøítele1*Technologie1;
					  int y = Armáda2 * Technologie2;
					  if (x>y) {
						  Armáda2 = 0;
						  int ztráty = ArmádaUNepøítele1; 
						  ArmádaUNepøítele1 = Math.round((x - y)/ Technologie1);
						  Základny2 = Základny2 - 1;
						  int ZtrátyFull = ztráty - ArmádaUNepøítele1;
						  System.out.println("Tvá Útoèná Armáda právì vyhrála boj a znièila nepøátelskou základnu. Ztráty: " + ZtrátyFull);
					  } else {
						  ArmádaUNepøítele1 = 0;
						  Armáda2 = Math.round((y - x)/Technologie2);
						  System.out.println("Tvá Útoèná Armáda právì prohrála boj o nepøátelskou základnu a byla znièena");
					  };
				  };
			  };
			 if (ArmádaUNepøítele2 > 0) {
					  int x = Armáda1*Technologie1;
					  int y = ArmádaUNepøítele2 * Technologie2;
					  if (x>=y) {
						  ArmádaUNepøítele2 = 0;
						  int ztráty = Armáda1; 
						  Armáda1 = Math.round((x - y)/ Technologie1);
						  int ZtrátyFull = ztráty - Armáda1;
						  System.out.println("Tvá Obranná Armáda právì úspìšnì odrazila nepøátelský útok! Tvé Ztráty: " + ZtrátyFull);
					  } else {
						  Armáda1 = 0;
						  Základny1 = Základny1 - 1;
						  ArmádaUNepøítele2 = Math.round((y - x)/Technologie2);
						  System.out.println("Tvá Obranná Armáda právì prohrála boj o tvoji základnu. Základna byla znièena a tvoje obrana též.");
				};
			};
				CheckForWin();
				
				Zdroje1 = Základny1 + Zdroje1;
				Zdroje2 = Základny2 + Zdroje2;
				
				SpyIter();
				TurnNum = TurnNum + 1;
				System.out.println("---------- \nZaèíná "+ TurnNum + " Kolo");
				Nákup();
			  };
			  
			  public void NákupArmády() {
				  Scanner NewObj = new Scanner(System.in);
					System.out.println("Tvé zdroje: " + Zdroje1 + "\n Pokud nechceš kupovat, zadej èíslo 0 \nNapiš poèet Obranné Armády, co chceš koupit (1 Zdroj = 1 Armáda)");
					  String StringS = NewObj.nextLine();
					  if(StringS.isEmpty()==true) {
						  System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
					    	Nákup();
					  };
					    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
					    if(isNumeric == false) {
					    	System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
					    	Nákup();
					    } else {
					    int num = Integer.parseInt(StringS);
					    
					  if (num > Zdroje1) {
						  System.out.println("!!! Málo Zdrojù !!!");
						  Nákup();
					  } else if (num < 1){
						  System.out.println("!!! Chybné èíslo !!!");
						  Nákup();
						  
					  } else {
						Armáda1 = Armáda1 + num;
						Zdroje1 = Zdroje1 - num;
						Nákup();
					  }
					  NewObj.close();
					    }
			  };
			  public void NákupZákladny() {
				  int Cost = Základny1*2;
		            int num = 1;
			        int k = num*Cost;
				  if (k > Zdroje1) {
					  System.out.println("!!! Málo Zdrojù !!!");
					  Nákup();
				  } else if (num < 1){
					  System.out.println("!!! Chybné èíslo !!!");
					  Nákup();
					  
				  } else {
					Zdroje1 = Zdroje1 - num*Cost;
					Základny1 = Základny1 + num;
					
					Nákup();
				  };
			  };
			  public void NákupTechnologie() {
				  Scanner NewObj = new Scanner(System.in);
					System.out.println("Tvé zdroje: " + Zdroje1 + "\nTvé technologie: "+ Technologie1 +"\n Pokud nechceš kupovat, zadej èíslo 0\n Napiš poèet Technologii, co chceš koupit (1 technologie = 2 Zdroje) ");
					  String StringS = NewObj.nextLine();
					  if(StringS.isEmpty()==true) {
						  System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
					    	Nákup();
					  };
					    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
					    if(isNumeric == false) {
					    	System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
					    	Nákup();
					    };
					    int num = Integer.parseInt(StringS);
					    int Cost = num*2;
					  if (Cost > Zdroje1) {
						  System.out.println("!!! Málo Zdrojù !!!");
						  Nákup();
					  } else if (num < 1){
						  System.out.println("!!! Chybné èíslo !!!");
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
					  System.out.println("\n- Fáze Nákupù \nZdroje: "+ Zdroje1 +"     \na) Nákup Armády (Tvá armáda:"+ Armáda1 +") \nb) Nákup jedné základny za "+ Cost +" Zdrojù (Tvé základny: "+ Základny1 +") \nc) Nákup technologie (tvá technologie: "+ Technologie1 +")\nd) už žádné nákupy");
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
					System.out.println("Tvá Obranná Armáda: " + Armáda1 + " \n Pokud nechceš posílat nic, zadej èíslo 0 \nNapiš poèet Obranné Armády, co chceš poslat k nepøíteli (armáda se tam obìví až za 2 kola) ");
					  String StringS = NewObj.nextLine();
					  if(StringS.isEmpty()==true) {
						  System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
						  ArmyMovement();
					  };
					    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
					    if(isNumeric == false) {
					    	System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
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
						System.out.println("Tvá Obranná Armáda: " + Armáda1 + "\nNapiš poèet špionù Základen, kolik jich chceš vyslat (1 Špion = 1 Armáda)");
						  String StringS = NewObj.nextLine();
						  if(StringS.isEmpty()==true) {
							  System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
							  SentSpyes();
						  };
						    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
						    if(isNumeric == false) {
						    	System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
						    	SentSpyes();
						    };
						    int num = Integer.parseInt(StringS);
						   
						  if (num < 0 && num <= 1000000) {
							  System.out.println("!!! Špatný vstup !!!");
							  SentSpyes();
						  } else if (num > Armáda1) {
							  System.out.println("!!! Moc vysoké èíslo !!!");
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
						System.out.println("Tvá Obranná Armáda: " + Armáda1 + " \nNapiš poèet špionù Technologií, kolik jich chceš vyslat (1 Špion = 1 Armáda)");
						  String StringS = NewObj.nextLine();
						  if(StringS.isEmpty()==true) {
							  System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
							  SentSpyes();
						  };
						    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
						    if(isNumeric == false) {
						    	System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
						    	SentSpyes();
						    };
						    int num = Integer.parseInt(StringS);
						   
						  if (num < 0 && num <= 1000000) {
							  System.out.println("!!! Špatný vstup !!!");
							  SentSpyes();
						  } else if (num > Armáda1) {
							  System.out.println("!!! Moc vysoké èíslo !!!");
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
							System.out.println("Tvá Obranná Armáda: " + Armáda1 + " \nNapiš poèet špionù Armád, kolik jich chceš vyslat (1 Špion = 1 Armáda)");
							String StringS = NewObj.nextLine();
							if(StringS.isEmpty()==true) {
								  System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
								  SentSpyes();
							  };
							boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
							if(isNumeric == false) {
								System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
								SentSpyes();
							};
							int num = Integer.parseInt(StringS);
								
  if (num < 0 && num <= 1000000) {
							  System.out.println("!!! "+ num +" vracíte se zpìt !!!");
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
					  System.out.println("\n- Špioni \nŠpioni Základen: " + SpyesZákladny + " \nŠpioni Technologií: " + SpyesTech + " \nŠpioni nepøátelské Armády: "+ SpyesArmy +" \na) Vyšli Špiony Základen \nb) Vyšli špiony technologií \nc) Vyšli špiony Armád \nd) Vra se zpátky");
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
				  System.out.println("\n- Pohyb Armády \nObranná Armáda doma: "+ Armáda1 +"\nÚtoèná Armáda u nepøítele: "+ ArmádaUNepøítele1 +"\nÚtoèná Armáda míøící k nepøítely: "+ Armádak +" \na) pošli Obrannou Armádu k nepøítely \nb) Vyšli špiony \nc) Nechceš nic dìlat");
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
			  int armada = ArmádaUNepøítele1 + ArmádaÚtok1a + ArmádaÚtok1b; 
			  if (ArmádaUNepøítele1 > armada) {
				  	Scanner NewObj = new Scanner(System.in);
					 int Armádak = ArmádaÚtok1a + ArmádaÚtok1b + ArmádaÚtok1c;
					  System.out.println("\n-  Útoèná Armáda U nepøítele: "+ ArmádaUNepøítele1 +"\n- Útoèná Armáda míøící k nepøítely: "+ Armádak +" \na) Pošli všechnu Armádu Zpátky Domù \nb) Zaútoè na další základnu \nc) nechceš nic dìlat");
					  String Typing = NewObj.nextLine();
					  boolean JeA = Typing.equals(StartA);
					  boolean JeB = Typing.equals(StartB);
					  boolean JeC = Typing.equals(StartC);
					  
					  if (JeA == true) {
						  ArmyGoBack1 = Armádak + ArmádaUNepøítele1;
						  ArmádaUNepøítele1 = 0;
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
						  System.out.println("Špatnì");
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
			if (ArmádaUNepøítele1 == 0) {
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
			if (a == 5 && ArmádaUNepøítele1 == 0) {
				ArmádaÚtok2a = Armáda2;
				Armáda2 = 0;
			};
			AkceNaZaèátkuKola();
		};
		  
		  public void ZmìòZákladny() {
			 Scanner NewObj = new Scanner(System.in);
				System.out.println("Napiš poèet základen, o kolik by mìl mít nepøítel víc, než vy na zaèátku hry (" + NastaveníZákladnyAI + ")");
				String StringS = NewObj.nextLine();
				  if(StringS.isEmpty()==true) {
					  System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
					  Nastavení();
				  };
				   boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
				   if(isNumeric == false) {
				    	System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
				    	Nastavení();
				    }
				   int num = Integer.parseInt(StringS);
				   
				  if (num > 0 && num <= 1000000) {
					   NastaveníZákladnyAI = num;
					   Nastavení();
				  } else {
					  System.out.println("!!! Špatný vstup !!!");
					  Nastavení();
				  }
				  NewObj.close();
		  };
		  public void ZmìòArmádu() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Napiš poèet základen, o kolik by mìl mít nepøítel víc, než vy na zaèátku hry (" + NastaveníArmádaAI + ")");
				String StringS = NewObj.nextLine();
				  if(StringS.isEmpty()==true) {
					  System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
					  Nastavení();
				  };
				   boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
				   if(isNumeric == false) {
				    	System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
				    	Nastavení();
				    }
				   int num = Integer.parseInt(StringS);
				    
				  if (num >= 0) {
					   NastaveníArmádaAI = num;
					   Nastavení();
				  } else {
					  System.out.println("!!! Špatný vstup !!!");
					  Nastavení();
				  }
				  NewObj.close();
			  
		  };
		  public void ZmìòTech() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Napiš výši technologie, o kterou bude mít nepøítel víc než vy na zaèátku hry (" + NastaveníTechAI + ")");
				String StringS = NewObj.nextLine();
				  if(StringS.isEmpty()==true) {
					  System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
					  Nastavení();
				  };
				   boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
				   if(isNumeric == false) {
				    	System.out.println("!!! "+ StringS +" vracíte se zpìt !!!");
				    	Nastavení();
				    }
				   int num = Integer.parseInt(StringS);

				  if (num > 0 && num <= 1000000) {
					   NastaveníTechAI = num;
					   Nastavení();
				  } else {
					  System.out.println("!!! Špatný vstup !!!");
					  ZmìòTech();
				  }
				  NewObj.close();
			};
			  

		  
		  public void Nastavení() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Nastavení:\na)Zmìò poèet základen protivníka na zaèátku (" + NastaveníZákladnyAI + ") \nb)Zmìò armádu nepøítele na zaèátku (" + NastaveníArmádaAI + ") \nc)Zmìò Technologii nepøítele na zaèátku (" + NastaveníTechAI + ") \nd) Bìž zpátky do menu");
				  String Typing = NewObj.nextLine();
				  
				  boolean JeA = Typing.equals(StartA);
				  boolean JeB = Typing.equals(StartB);
				  boolean JeC = Typing.equals(StartC);
				  boolean JeD = Typing.equals(StartD);
				  if (JeA == true) {
					   ZmìòZákladny();
				  } else if (JeB == true) {
					  ZmìòArmádu();
				  } else if(JeC == true) {
					  ZmìòTech();
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
				System.out.println("Chcete zaèít hru?\na)Zaèít   b)Pravidla hry   c)Nastavení");
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
