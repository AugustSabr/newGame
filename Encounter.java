public class Encounter {
  UI ui;
  int randomEncounter;
  
  public Encounter(UI u) {
    ui = u;
  }

  public void newMonster(){
    this.randomEncounter = (int)Math.floor(Math.random()*10);

    if(randomEncounter == 0){
      ui.drawMonster("me");
    } else {
      ui.drawMonster("");
    }
  }
}
