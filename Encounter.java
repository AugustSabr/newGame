public class Encounter {
  UI ui;
  Item i;
  _KeyListener key;
  Map map;
  Player player;
  int randomEncounter;
  
  public Encounter(UI u, Item it, Player p) {
    ui = u;
    i = it;
    player = p;
  }
  public void addKeyListener(_KeyListener k) {
    key = k;
  }
  public void addMap(Map m){
    map = m;
  }

  public void selectPosition(String nextPosition){
    switch(nextPosition){
      case "getItem": getItem(); break;
      case "check door": checkDoor(); break;
      case "open door": openDoor(); break;
      case "go thru door": goThruDoor(); break;
      // case "new floor": newFloor(); break;
      // case "save": save(); break;
      // case "stats": stats(); break;
      // case "heal": heal(); break;
      // case "upgrade": upgrade(); break;
      // case "upgradeWeapon": upgradeWeapon(); break;
      // case "upgradeArmor": upgradeArmor(); break;
      // case "getHealingPotion": getHealingPotion(); break;
      // case "getWeapon": getWeapon(); break;
      // case "dontGetWeapon": dontGetWeapon(); break;
      // case "getArmor": getArmor(); break;
      // case "dontGetArmor": dontGetArmor(); break;
      // case "attackRoll": attackRoll(); break;
      // case "attackPlayer": attackPlayer(); break;
      // case "attackEnemy": attackEnemy(); break;
      // case "lootEnemy": lootEnemy(); break;
      // case "lootWeapon": lootWeapon(); break;
      // case "lootArmor": lootArmor(); break;
      // case "escapeRoll": escapeRoll(); break;
      // case "run": run(); break;
      // case "DrinkMaxHealthPotion": DrinkMaxHealthPotion(); break;
      // case "acceptBlessing": acceptBlessing(); break;
    }
  }

  public void newEncounter(){
    key.z = "";
    key.x = "";
    key.c = "";
    this.randomEncounter = (int)Math.floor(Math.random()*10);

    ui.drawEncounter("","");
    if(randomEncounter == 0){
      foundItem();
    }
    if(randomEncounter == 1){
      ui.drawEncounter("monsters", "me");
    }
  }

  private void foundItem(){
    i.newItem();
    ui.mainTextArea.setText("key?");

    key.z = "getItem";
  }

  private void getItem(){
    System.out.println(player.getInventory());
    player.inventory.add(i.name);
    ui.mainTextArea.setText("key in inventory");
    ui.drawEncounter("","");
    System.out.println(player.getInventory());
  }
  private void checkDoor(){
    ui.mainTextArea.setText("You try to open the door\nIt is locked");
    if(player.inventory.contains("key")){
      key.z = "open door";
    }
  }

  private void openDoor(){
    ui.drawStructure("openDoor");
    map.structureLayout[map.y][map.x] = "q";
    ui.mainTextArea.setText("You open door yousing a key");
    key.z = "go thru door";
  }

  private void goThruDoor(){
    switch(map.facing){
      case "north": map.y=map.y-1; map.draw(); break;
      case "south": map.y++; map.draw(); break;
      case "east": map.x++; map.draw(); break;
      case "west": map.x=map.x-1; map.draw(); break;
    }
  }
}
