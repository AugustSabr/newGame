public class Encounter {
  private UI ui;
  private Item i;
  private _KeyListener key;
  private Map map;
  private GameInventory in;
  private VisibilityManager vm;

  private Player player;
  private int randomEncounter;
  private Item newItem;
  // Weapon
  
  public Encounter(UI u, Player p, GameInventory gi, VisibilityManager v) {
    ui = u;
    in = gi;
    vm = v;

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
      case "checkDoor": checkDoor(); break;
      case "openDoor": openDoor(); break;
      case "goThruDoor": goThruDoor(); break;
      case "talkToShopkeeper": talkToShopkeeper(); break;
      case "buyFormShop": buyFormShop(); break;
      case "selectItemsToSell": selectItemsToSell(); break;
      case "leaveShop": leaveShop(); break;
      case "sellItemsToShop": sellItemsToShop(); break;
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
    this.randomEncounter = (int)Math.floor(Math.random()*5);

    ui.drawEncounter("","");
    if(randomEncounter == 0){
      foundItem();
    }
    if(randomEncounter == 1){
      ui.drawEncounter("monsters", "me");
    }
  }

  public void noEncounter(){
    ui.drawEncounter("","");
  }

  private void foundItem(){
    newItem = in.getItem(-1);
    ui.drawEncounter("items", newItem.getPath());
    ui.mainTextArea.setText("You see a "+newItem.getName());

    key.z = "getItem";
  }

  private void getItem(){
    if(player.inventory.size() >= 10){
      ui.mainTextArea.setText("you carry to many items");
    } else {
      player.inventory.add(newItem.getName());
      ui.mainTextArea.setText(newItem.getName()+" is now in youre inventory");
      ui.drawEncounter("","");
      key.z = "";
    }
  }
  private void checkDoor(){
    ui.mainTextArea.setText("You try to open the door\nIt is locked");
    if(player.inventory.contains("key")){
      key.z = "openDoor";
    }
  }

  private void openDoor(){
    ui.drawStructure("openDoor");
    map.structureLayout[map.y][map.x] = "q";
    ui.mainTextArea.setText("You open door using a key");
    key.z = "goThruDoor";
  }

  private void goThruDoor(){
    switch(map.getFacing()){
      case "north": map.y=map.y-1; map.move("0"); break;
      case "south": map.y++; map.move("0"); break;
      case "east": map.x++; map.move("0"); break;
      case "west": map.x=map.x-1; map.move("0"); break;
    }
  }

  private void talkToShopkeeper(){
    ui.mainTextArea.setText("<shopkeeper>\nBuy or sell?");
    key.z = "buyFormShop";
    key.x = "selectItemsToSell";
  }

  private void buyFormShop(){
    ui.mainTextArea.setText("<shopkeeper>\nI don't have anything to sell you");
    key.z = "";
    key.x = "selectItemsToSell";
  }

  private void selectItemsToSell(){
    ui.mainTextArea.setText("<shopkeeper>\nWhat do you want to sell?");
    vm.showchoiceButtons();
    ui.selectedButtons.clear();
    ui.chageButton(null);
    for(int i = 0; i < ui.buttonList.size()-1; i++){
      ui.buttonList.get(i).setText(null);
    }
    for(int i = 0; i < player.inventory.size(); i++){
      ui.buttonList.get(i).setText(player.inventory.get(i));
    }
    
    ui.buttonList.get(10).setText("Sell");
    key.z = "";
    key.x = "";
  }

  private void sellItemsToShop(){
    ui.mainTextArea.setText("<shopkeeper>\nWhat a steal.");

    for(int i = 0; i < player.inventory.size(); i++){
      for(int j = 0; j < ui.selectedButtons.size(); j++){

        if(player.inventory.get(i) == ui.buttonList.get(ui.selectedButtons.get(j)).getText()){
          player.inventory.remove(i);
          System.out.println(player.inventory);
        }
      }
    }
    player.addGold(Integer.parseInt(ui.intSellCounterLabel.getText()));
    System.out.println(player.getGold());
    selectItemsToSell();
  }

  private void leaveShop(){
    ui.mainTextArea.setText("<shopkeeper>\nbye");
    vm.dontShowchoiceButtons();
    for(int i = 0; i < player.inventory.size(); i++){
      ui.buttonList.get(i).setText(player.inventory.get(i));
    }
    key.z = "buyFormShop";
    key.x = "selectItemsToSell";
  }
}
