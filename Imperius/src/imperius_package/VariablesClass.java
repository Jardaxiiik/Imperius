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
		public static int Z�kladny1;
		public static int Z�kladny2;
		public static int Arm�da1;
		public static int Arm�da2;
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
		public static int Nastaven�ZdrojeAI;
		public static int Nastaven�Z�kladnyAI;
		public static int Nastaven�Arm�daAI;
		public static int Nastaven�TechAI;
		public static int Arm�da�tok1a;
		public static int Arm�da�tok1b;
		public static int Arm�da�tok1c;
		public static int Arm�da�tok2a;
		public static int Arm�da�tok2b;
		public static int Arm�da�tok2c;
		public static int Arm�daUNep��tele1;
		public static int Arm�daUNep��tele2;
		public static int �tokD�l1;
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
				System.out.println("Pravidla: \n- Na za��tku m� jednu z�kladnu. Z�kladna ti d� ka�d� kolo 1 zdroj. \n- Za sv� zdroje si m��e� ka�d� kolo koupit arm�du, technologii nebo dal�� z�kladnu.\n- Technologie zvy�uje v�kon tv� arm�dy, hr��, kter� m� 2 arm�dy a 2 technologie m� s�lu rovnou 4, hr�� kter� m� 1 arm�du a technologii 3 m� s�lu t�i, atd... \n- Ka�d� kolo m��ete ��ci sv� arm�d�, co m� d�lat. �pion� v�m d� informace o nep��tely. Nep��tel za��n� norm�ln� s jednou z�kladnou jako vy (to se d� zm�nit v nastaven�). \n- �kol hry je zni�it v�echny nep��telsk� z�kladny, to se d� ud�lat tak, �e po�lete proti nep��tely svoj� arm�du - to ale trv� dv� kola.\n- Arm�da norm�ln� hl�d� va�e z�kladny, tak�e mus�te p�em��let kdy s arm�dou vyraz�te na �tok.\n a)Vra� se zp�t do menu");
				  String Typing = NewObj.nextLine();
				  boolean JeA = Typing.equals(StartA);
				  if (JeA == true) {
					  StartChoice();
				  } else {
					  System.out.println("!!! �patn� vstup !!!");
					  Rules();
				  }
			}
			  ;
		  }
		  
		  public void StartHry() {
			Zdroje1 = 0;
			Zdroje2 = 0 + Nastaven�ZdrojeAI;
			Z�kladny1 = 1;
			Z�kladny2 = 1 + Nastaven�Z�kladnyAI;
			Arm�da1 = 0;
			Arm�da2 = 0 + Nastaven�Arm�daAI;
			Technologie1 = 1;
			Technologie2 = 1 + Nastaven�TechAI;
			SpyBase1a = 0;
			SpyBase1b = 0;
			SpyTech1a =0;
			SpyTech1b =0;
			SpyArmy1a =0;
			SpyArmy1b =0;
			TurnNum = 0;
			Arm�da�tok1a = 0;
			Arm�da�tok1b = 0;
			Arm�da�tok1c = 0;
			Arm�da�tok2a = 0;
			Arm�da�tok2b = 0;
			Arm�da�tok2c = 0;
			�tokD�l1 = 0;
			Arm�daUNep��tele1 = 0;
			Arm�daUNep��tele2 = 0;
			ArmyGoBack1 = 0;
			NextTimeAI = 0;
			
			
			System.out.println("----------------\nHRA ZA��N�!\n");
			AkceNaZa��tkuKola();
		  };
		  
		  public void SpyIter() {
			  if (SpyBase1b > 0) {
				  Arm�da1 = Arm�da1 + SpyBase1b;
				  System.out.println("Tv�j �peh zjistil, �e nep��tel m� " + Z�kladny2 + " Z�kladen");
			  };
					SpyBase1b = SpyBase1a;
					SpyBase1a = 0;
					
					if (SpyTech1b > 0) {
						  Arm�da1 = Arm�da1 + SpyTech1b;
						  System.out.println("Tv�j �peh zjistil, �e nep��tel m� " + Technologie2 + " V��ku Technologie");
					  };
					SpyTech1b = SpyTech1a;
					SpyTech1a = 0;
							
					if (SpyArmy1b > 0) {
						  Arm�da1 = Arm�da1 + SpyArmy1b;
						  System.out.println("Tv�j �peh zjistil, �e nep��tel m� " + Arm�da2 + " Velikou arm�du");
					  };
					SpyArmy1b = SpyArmy1a;
					SpyArmy1a = 0;
							
		  };
		  
		  
		  
		  public void CheckForWin () {
			  if (Z�kladny1 == 0) {
				  int armos = Arm�daUNep��tele2 + Arm�da2 + Arm�da�tok2a + Arm�da�tok2b + Arm�da�tok2c;
				  System.out.println("Konec Hry!\n Prohr�l si na pln� ���e\n\nTv�j nep��tel m�l "+ armos +" velikou celou arm�du, m�l "+ Z�kladny2 +" Z�kladen, a jeho Technologie byla na �rovni " + Technologie2 + "\nTv�j nep��tel m�l " + Zdroje2 + " Zdroj�");
				  StartChoice();
			  }
			  if (Z�kladny2 == 0) {
				  int armos = Arm�daUNep��tele1 + Arm�da1 + Arm�da�tok1a + Arm�da�tok1b + Arm�da�tok1c;
				  int armos2 = Arm�daUNep��tele2 + Arm�da2 + Arm�da�tok2a + Arm�da�tok2b + Arm�da�tok2c;
				  System.out.println("Konec Hry!\n Vyhr�l jsi na pln� ���e\n\nM�l jsi "+ armos +" velikou arm�du, m�l jsi "+ Z�kladny1 +" Z�kladny, a tvoje technologie byla na �rovni " + Technologie1 + "\nTv�j nep��tel m�l "+ armos2 +" velikou celou arm�du, m�l "+ Z�kladny2 +" Z�kladen, a jeho Technologie byla na �rovni " + Technologie2);
				  StartChoice();
			  }
		  };
		  public void AkceNaZa��tkuKola() {
			  
			  if (Arm�daUNep��tele1 == 0) {
				if (Arm�da�tok1c >0) {
					�tokD�l1 = 1;
				};
			  };
			  
			  Arm�daUNep��tele1 = Arm�da�tok1c + Arm�daUNep��tele1;
			  Arm�da�tok1c = Arm�da�tok1b;
			  Arm�da�tok1b = Arm�da�tok1a;
			  Arm�da�tok1a = 0;
			  Arm�daUNep��tele2 =  Arm�daUNep��tele2 + Arm�da�tok2c;
			  Arm�da�tok2c = Arm�da�tok2b;
			  Arm�da�tok2b = Arm�da�tok2a;
			  Arm�da�tok2a = 0;
			  Arm�da1 = Arm�da1 + ArmyGoBack1;
			  ArmyGoBack1 = 0;
			  
			  if (Arm�daUNep��tele1 > 0) {
				  if (�tokD�l1 == 1) {
					  int x = Arm�daUNep��tele1*Technologie1;
					  int y = Arm�da2 * Technologie2;
					  if (x>y) {
						  Arm�da2 = 0;
						  int ztr�ty = Arm�daUNep��tele1; 
						  Arm�daUNep��tele1 = Math.round((x - y)/ Technologie1);
						  Z�kladny2 = Z�kladny2 - 1;
						  int Ztr�tyFull = ztr�ty - Arm�daUNep��tele1;
						  System.out.println("Tv� �to�n� Arm�da pr�v� vyhr�la boj a zni�ila nep��telskou z�kladnu. Ztr�ty: " + Ztr�tyFull);
					  } else {
						  Arm�daUNep��tele1 = 0;
						  Arm�da2 = Math.round((y - x)/Technologie2);
						  System.out.println("Tv� �to�n� Arm�da pr�v� prohr�la boj o nep��telskou z�kladnu a byla zni�ena");
					  };
				  };
			  };
			 if (Arm�daUNep��tele2 > 0) {
					  int x = Arm�da1*Technologie1;
					  int y = Arm�daUNep��tele2 * Technologie2;
					  if (x>=y) {
						  Arm�daUNep��tele2 = 0;
						  int ztr�ty = Arm�da1; 
						  Arm�da1 = Math.round((x - y)/ Technologie1);
						  int Ztr�tyFull = ztr�ty - Arm�da1;
						  System.out.println("Tv� Obrann� Arm�da pr�v� �sp�n� odrazila nep��telsk� �tok! Tv� Ztr�ty: " + Ztr�tyFull);
					  } else {
						  Arm�da1 = 0;
						  Z�kladny1 = Z�kladny1 - 1;
						  Arm�daUNep��tele2 = Math.round((y - x)/Technologie2);
						  System.out.println("Tv� Obrann� Arm�da pr�v� prohr�la boj o tvoji z�kladnu. Z�kladna byla zni�ena a tvoje obrana t�.");
				};
			};
				CheckForWin();
				
				Zdroje1 = Z�kladny1 + Zdroje1;
				Zdroje2 = Z�kladny2 + Zdroje2;
				
				SpyIter();
				TurnNum = TurnNum + 1;
				System.out.println("---------- \nZa��n� "+ TurnNum + " Kolo");
				N�kup();
			  };
			  
			  public void N�kupArm�dy() {
				  Scanner NewObj = new Scanner(System.in);
					System.out.println("Tv� zdroje: " + Zdroje1 + "\n Pokud nechce� kupovat, zadej ��slo 0 \nNapi� po�et Obrann� Arm�dy, co chce� koupit (1 Zdroj = 1 Arm�da)");
					  String StringS = NewObj.nextLine();
					  if(StringS.isEmpty()==true) {
						  System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
					    	N�kup();
					  };
					    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
					    if(isNumeric == false) {
					    	System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
					    	N�kup();
					    } else {
					    int num = Integer.parseInt(StringS);
					    
					  if (num > Zdroje1) {
						  System.out.println("!!! M�lo Zdroj� !!!");
						  N�kup();
					  } else if (num < 1){
						  System.out.println("!!! Chybn� ��slo !!!");
						  N�kup();
						  
					  } else {
						Arm�da1 = Arm�da1 + num;
						Zdroje1 = Zdroje1 - num;
						N�kup();
					  }
					  NewObj.close();
					    }
			  };
			  public void N�kupZ�kladny() {
				  int Cost = Z�kladny1*2;
		            int num = 1;
			        int k = num*Cost;
				  if (k > Zdroje1) {
					  System.out.println("!!! M�lo Zdroj� !!!");
					  N�kup();
				  } else if (num < 1){
					  System.out.println("!!! Chybn� ��slo !!!");
					  N�kup();
					  
				  } else {
					Zdroje1 = Zdroje1 - num*Cost;
					Z�kladny1 = Z�kladny1 + num;
					
					N�kup();
				  };
			  };
			  public void N�kupTechnologie() {
				  Scanner NewObj = new Scanner(System.in);
					System.out.println("Tv� zdroje: " + Zdroje1 + "\nTv� technologie: "+ Technologie1 +"\n Pokud nechce� kupovat, zadej ��slo 0\n Napi� po�et Technologii, co chce� koupit (1 technologie = 2 Zdroje) ");
					  String StringS = NewObj.nextLine();
					  if(StringS.isEmpty()==true) {
						  System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
					    	N�kup();
					  };
					    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
					    if(isNumeric == false) {
					    	System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
					    	N�kup();
					    };
					    int num = Integer.parseInt(StringS);
					    int Cost = num*2;
					  if (Cost > Zdroje1) {
						  System.out.println("!!! M�lo Zdroj� !!!");
						  N�kup();
					  } else if (num < 1){
						  System.out.println("!!! Chybn� ��slo !!!");
						  N�kup();
						  
					  } else {
						Technologie1 = Technologie1 + num;
						Zdroje1 = Zdroje1 - Cost;
						N�kup();
					  }
				NewObj.close();
			  };
			  
			  public void N�kup() {
				 Scanner NewObj = new Scanner(System.in);
					int Cost = Z�kladny1*2;
					  System.out.println("\n- F�ze N�kup� \nZdroje: "+ Zdroje1 +"     \na) N�kup Arm�dy (Tv� arm�da:"+ Arm�da1 +") \nb) N�kup jedn� z�kladny za "+ Cost +" Zdroj� (Tv� z�kladny: "+ Z�kladny1 +") \nc) N�kup technologie (tv� technologie: "+ Technologie1 +")\nd) u� ��dn� n�kupy");
					  String Typing = NewObj.nextLine();
					  boolean JeA = Typing.equals(StartA);
					  boolean JeB = Typing.equals(StartB);
					  boolean JeC = Typing.equals(StartC);
					  boolean JeD = Typing.equals(StartD);
					  if (JeA == true) {
						  N�kupArm�dy();
					  } else if (JeB == true) {
						  N�kupZ�kladny();
					  } else if(JeC == true) {
						  N�kupTechnologie();
					  } else if(JeD == true) {
							 ArmyMovement();	  
					  } else {
						  System.out.println("!!! �patn� vstup !!!");
						  N�kup();
					  }
					  NewObj.close();
			  };
			  public void Vy�liArm�du() {
				  Scanner NewObj = new Scanner(System.in);
					System.out.println("Tv� Obrann� Arm�da: " + Arm�da1 + " \n Pokud nechce� pos�lat nic, zadej ��slo 0 \nNapi� po�et Obrann� Arm�dy, co chce� poslat k nep��teli (arm�da se tam ob�v� a� za 2 kola) ");
					  String StringS = NewObj.nextLine();
					  if(StringS.isEmpty()==true) {
						  System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
						  ArmyMovement();
					  };
					    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
					    if(isNumeric == false) {
					    	System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
					    	ArmyMovement();
					    };
					    int num = Integer.parseInt(StringS);
					    
					  if (num < 1) {
						   System.out.println("!!! �patn� vstup !!!");
							  ArmyMovement();
					  } else if (num > Arm�da1) {
						  System.out.println("!!! Nedostatek arm�dy !!!");
						  ArmyMovement();
					  } else {
						  System.out.println("Vyslal jsi "+ num + "Arm�dy na �tok");
						  Arm�da�tok1a = Arm�da�tok1a + num;
						  Arm�da1 = Arm�da1 - num;
						  ArmyMovement();
					  }
					  NewObj.close();
			  };
			  
			  	public void SpyBaseSent() {
			  		Scanner NewObj = new Scanner(System.in);
						System.out.println("Tv� Obrann� Arm�da: " + Arm�da1 + "\nNapi� po�et �pion� Z�kladen, kolik jich chce� vyslat (1 �pion = 1 Arm�da)");
						  String StringS = NewObj.nextLine();
						  if(StringS.isEmpty()==true) {
							  System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
							  SentSpyes();
						  };
						    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
						    if(isNumeric == false) {
						    	System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
						    	SentSpyes();
						    };
						    int num = Integer.parseInt(StringS);
						   
						  if (num < 0 && num <= 1000000) {
							  System.out.println("!!! �patn� vstup !!!");
							  SentSpyes();
						  } else if (num > Arm�da1) {
							  System.out.println("!!! Moc vysok� ��slo !!!");
							  SpyBaseSent();
						  } else {
							  SpyBase1a = SpyBase1a + 1;
							  Arm�da1 = Arm�da1 - num;
							  SentSpyes();
								  }
						  NewObj.close();
						  
					  
			  		};
			  	public void SpyTechSent() {
			  		Scanner NewObj = new Scanner(System.in);
						System.out.println("Tv� Obrann� Arm�da: " + Arm�da1 + " \nNapi� po�et �pion� Technologi�, kolik jich chce� vyslat (1 �pion = 1 Arm�da)");
						  String StringS = NewObj.nextLine();
						  if(StringS.isEmpty()==true) {
							  System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
							  SentSpyes();
						  };
						    boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
						    if(isNumeric == false) {
						    	System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
						    	SentSpyes();
						    };
						    int num = Integer.parseInt(StringS);
						   
						  if (num < 0 && num <= 1000000) {
							  System.out.println("!!! �patn� vstup !!!");
							  SentSpyes();
						  } else if (num > Arm�da1) {
							  System.out.println("!!! Moc vysok� ��slo !!!");
							  SpyTechSent();
						  } else {
							  SpyTech1a = SpyTech1a + 1;
							  Arm�da1 = Arm�da1 - num;
							  SentSpyes();
								  }
						  NewObj.close();
			  };
			  	public void SpyArmySent() {
			  			Scanner NewObj = new Scanner(System.in);
							System.out.println("Tv� Obrann� Arm�da: " + Arm�da1 + " \nNapi� po�et �pion� Arm�d, kolik jich chce� vyslat (1 �pion = 1 Arm�da)");
							String StringS = NewObj.nextLine();
							if(StringS.isEmpty()==true) {
								  System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
								  SentSpyes();
							  };
							boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
							if(isNumeric == false) {
								System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
								SentSpyes();
							};
							int num = Integer.parseInt(StringS);
								
  if (num < 0 && num <= 1000000) {
							  System.out.println("!!! "+ num +" vrac�te se zp�t !!!");
							  SentSpyes();
  } else if (num > Arm�da1) {
							  System.out.println("!!! Nedostatek Arm�dy !!!");
							  SpyTechSent();
  } else {
							  SpyArmy1a = SpyArmy1a + 1;
							  Arm�da1 = Arm�da1 - num;
							  SentSpyes();
								  }
  NewObj.close();
			  };
			  
			  public void SentSpyes() {
				  Scanner NewObj = new Scanner(System.in);
					int SpyesZ�kladny = SpyBase1a + SpyBase1b;
					 int SpyesTech = SpyTech1a + SpyTech1b;
					 int SpyesArmy = SpyArmy1a + SpyArmy1b;
					  System.out.println("\n- �pioni \n�pioni Z�kladen: " + SpyesZ�kladny + " \n�pioni Technologi�: " + SpyesTech + " \n�pioni nep��telsk� Arm�dy: "+ SpyesArmy +" \na) Vy�li �piony Z�kladen \nb) Vy�li �piony technologi� \nc) Vy�li �piony Arm�d \nd) Vra� se zp�tky");
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
						  System.out.println("!!! �patn� vstup !!!");
						  SentSpyes();
					  }
					  NewObj.close();
			  };
			  
		 public void ArmyMovement() {
			 Scanner NewObj = new Scanner(System.in);
				int Arm�dak = Arm�da�tok1a + Arm�da�tok1b + Arm�da�tok1c;
				  System.out.println("\n- Pohyb Arm�dy \nObrann� Arm�da doma: "+ Arm�da1 +"\n�to�n� Arm�da u nep��tele: "+ Arm�daUNep��tele1 +"\n�to�n� Arm�da m���c� k nep��tely: "+ Arm�dak +" \na) po�li Obrannou Arm�du k nep��tely \nb) Vy�li �piony \nc) Nechce� nic d�lat");
				  String Typing = NewObj.nextLine();
				  boolean JeA = Typing.equals(StartA);
				  boolean JeB = Typing.equals(StartB);
				  boolean JeC = Typing.equals(StartC);
				  if (JeA == true) {
					  Vy�liArm�du();
				  } else if (JeB == true) {
					  SentSpyes();
				  } else if(JeC == true) {
					  ArmyInEnemyBase();
				  } else {
					  System.out.println("!!! �patn� vstup !!!");
					  ArmyMovement();
				  }
				  NewObj.close();
		 };
		 
		  public void ArmyInEnemyBase() {
			  int armada = Arm�daUNep��tele1 + Arm�da�tok1a + Arm�da�tok1b; 
			  if (Arm�daUNep��tele1 > armada) {
				  	Scanner NewObj = new Scanner(System.in);
					 int Arm�dak = Arm�da�tok1a + Arm�da�tok1b + Arm�da�tok1c;
					  System.out.println("\n-  �to�n� Arm�da U nep��tele: "+ Arm�daUNep��tele1 +"\n- �to�n� Arm�da m���c� k nep��tely: "+ Arm�dak +" \na) Po�li v�echnu Arm�du Zp�tky Dom� \nb) Za�to� na dal�� z�kladnu \nc) nechce� nic d�lat");
					  String Typing = NewObj.nextLine();
					  boolean JeA = Typing.equals(StartA);
					  boolean JeB = Typing.equals(StartB);
					  boolean JeC = Typing.equals(StartC);
					  
					  if (JeA == true) {
						  ArmyGoBack1 = Arm�dak + Arm�daUNep��tele1;
						  Arm�daUNep��tele1 = 0;
						  Arm�da�tok1a = 0;
						  Arm�da�tok1b = 0;
						  
						  �tokD�l1 = 0;
						  AIPlay();
					  } else if (JeB == true) {
						  �tokD�l1 = 1;
						  AIPlay();
					  } else if(JeC == true) {
						  �tokD�l1 = 0;
						  AIPlay();
					  } else {
						  System.out.println("�patn�");
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
			int Cost = Z�kladny2*2;
			int roundedNumber = (int) ((int) (Z�kladny2-1)*0.5);
			if (Arm�daUNep��tele1 == 0) {
				if ( Zdroje2 >= Cost) {
					Zdroje2 = Zdroje2 - Cost;
					Z�kladny2 = Z�kladny2 + 1;
				} else {
				int Z = getRandomNumberInRange(roundedNumber, Zdroje2);
				int a = getRandomNumberInRange(1, 10);
				Zdroje2 = Zdroje2 - Z;
				if (a >= 4) {
					Arm�da2 = Arm�da2 + Z;
						
				} else {
					Technologie2 = Technologie2 + Math.round(Z/2);
					};
				};
					
				} else {
					int Z = getRandomNumberInRange(roundedNumber, Zdroje2);
					Zdroje2 = Zdroje2 - Z;
					Arm�da2 = Arm�da2 + Z;
					
			};
			AIMove();
		  };

		public void AIMove() {
			int a = getRandomNumberInRange(1, 5);
			if (a == 5 && Arm�daUNep��tele1 == 0) {
				Arm�da�tok2a = Arm�da2;
				Arm�da2 = 0;
			};
			AkceNaZa��tkuKola();
		};
		  
		  public void Zm��Z�kladny() {
			 Scanner NewObj = new Scanner(System.in);
				System.out.println("Napi� po�et z�kladen, o kolik by m�l m�t nep��tel v�c, ne� vy na za��tku hry (" + Nastaven�Z�kladnyAI + ")");
				String StringS = NewObj.nextLine();
				  if(StringS.isEmpty()==true) {
					  System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
					  Nastaven�();
				  };
				   boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
				   if(isNumeric == false) {
				    	System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
				    	Nastaven�();
				    }
				   int num = Integer.parseInt(StringS);
				   
				  if (num > 0 && num <= 1000000) {
					   Nastaven�Z�kladnyAI = num;
					   Nastaven�();
				  } else {
					  System.out.println("!!! �patn� vstup !!!");
					  Nastaven�();
				  }
				  NewObj.close();
		  };
		  public void Zm��Arm�du() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Napi� po�et z�kladen, o kolik by m�l m�t nep��tel v�c, ne� vy na za��tku hry (" + Nastaven�Arm�daAI + ")");
				String StringS = NewObj.nextLine();
				  if(StringS.isEmpty()==true) {
					  System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
					  Nastaven�();
				  };
				   boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
				   if(isNumeric == false) {
				    	System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
				    	Nastaven�();
				    }
				   int num = Integer.parseInt(StringS);
				    
				  if (num >= 0) {
					   Nastaven�Arm�daAI = num;
					   Nastaven�();
				  } else {
					  System.out.println("!!! �patn� vstup !!!");
					  Nastaven�();
				  }
				  NewObj.close();
			  
		  };
		  public void Zm��Tech() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Napi� v��i technologie, o kterou bude m�t nep��tel v�c ne� vy na za��tku hry (" + Nastaven�TechAI + ")");
				String StringS = NewObj.nextLine();
				  if(StringS.isEmpty()==true) {
					  System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
					  Nastaven�();
				  };
				   boolean isNumeric = StringS.chars().allMatch( Character::isDigit );
				   if(isNumeric == false) {
				    	System.out.println("!!! "+ StringS +" vrac�te se zp�t !!!");
				    	Nastaven�();
				    }
				   int num = Integer.parseInt(StringS);

				  if (num > 0 && num <= 1000000) {
					   Nastaven�TechAI = num;
					   Nastaven�();
				  } else {
					  System.out.println("!!! �patn� vstup !!!");
					  Zm��Tech();
				  }
				  NewObj.close();
			};
			  

		  
		  public void Nastaven�() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Nastaven�:\na)Zm�� po�et z�kladen protivn�ka na za��tku (" + Nastaven�Z�kladnyAI + ") \nb)Zm�� arm�du nep��tele na za��tku (" + Nastaven�Arm�daAI + ") \nc)Zm�� Technologii nep��tele na za��tku (" + Nastaven�TechAI + ") \nd) B� zp�tky do menu");
				  String Typing = NewObj.nextLine();
				  
				  boolean JeA = Typing.equals(StartA);
				  boolean JeB = Typing.equals(StartB);
				  boolean JeC = Typing.equals(StartC);
				  boolean JeD = Typing.equals(StartD);
				  if (JeA == true) {
					   Zm��Z�kladny();
				  } else if (JeB == true) {
					  Zm��Arm�du();
				  } else if(JeC == true) {
					  Zm��Tech();
					  }
					else if(JeD == true) {
						StartChoice();
				  } else {
					  System.out.println("!!! �patn� vstup !!!");
					  Nastaven�();
				  }
				  NewObj.close();
			  
			  ;
		  }
		  
		  public void StartChoice() {
			  Scanner NewObj = new Scanner(System.in);
				System.out.println("Chcete za��t hru?\na)Za��t   b)Pravidla hry   c)Nastaven�");
				  String Typing = NewObj.nextLine();
				  boolean JeA = Typing.equals(StartA);
				  boolean JeB = Typing.equals(StartB);
				  boolean JeC = Typing.equals(StartC);
				  if (JeA == true) {
					  StartHry();
					  
				  } else if (JeB == true) {
					  Rules();
				  } else if(JeC == true) {
					  Nastaven�();
				  } else {
					  System.out.println("!!! �patn� vstup !!!");
					  StartChoice();
				  }
				  NewObj.close();
			  
			  
		  };
}
