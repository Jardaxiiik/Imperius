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
	 VariablesClass.Nastaven�Z�kladnyAI = 0;
	 VariablesClass.Nastaven�Arm�daAI = 0;
	 VariablesClass.Nastaven�TechAI = 0;
    System.out.println("    Imperius\nV�tejte v t�to naov� a nap�nav� strategick� h�e!\n - V t�to h�e mus�te ovl�dat alespo� jednu z�kladnu, jinak prohrajete\n - Zv�t�z�te tehdy, kdy� zni��te v�echny z�kladny nep��tele\n\n");
    
    Variables.StartChoice();
  }
  
}
