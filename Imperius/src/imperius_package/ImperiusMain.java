package imperius_package;

class ImperiusMain {
	
  public static void main(String[] args) {
	 VariablesClass Variables = new VariablesClass();
	 VariablesClass.TurnNum = 0;
	 VariablesClass.Number2 = 0;
	 VariablesClass.StartA = "a";
	 VariablesClass.StartB = "b";
	 VariablesClass.StartC = "c";
	 VariablesClass.StartD = "d";
	 VariablesClass.NastaveníZákladnyAI = 0;
	 VariablesClass.NastaveníArmádaAI = 0;
	 VariablesClass.NastaveníTechAI = 0;
    System.out.println("    Imperius\nVítejte v této naové a napínavé strategické høe!\n - V této høe musíte ovládat alespoò jednu základnu, jinak prohrajete\n - Zvítìzíte tehdy, kdy znièíte všechny základny nepøítele\n\n");
    
    Variables.StartChoice();
  }
  
}
