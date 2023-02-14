import java.util.ArrayList;

public class Encounter {
  private Game game;
  private UI ui;
  private Item i;
  private _KeyListener key;
  private Map map;
  private GameInventory in;
  private VisibilityManager vm;

  private Player player;
  private int randomEncounter;
  private Item newItem;

  ArrayList<Integer> shopInventory = new ArrayList<>();
  // private int shopWeapons = (int)Math.floor(Math.random()*3) + 1;
  private int shopWeapons = 7;
  private String[] shopSmalltalk = {"<shopkeeper>\nCan I help you with something specific, or are you just here to waste my time?", "<shopkeeper>\nIf you're not going to buy anything, please don't touch the merchandise.", "<shopkeeper>\nI don't have all day to stand here and chat. If you're not going to make a purchase, I suggest you leave.", "<shopkeeper>\nIf you don't know what you want, maybe you should come back when you do.", "<shopkeeper>\nI'm sorry, but I'm not in the mood for small talk right now. Is there something specific you're looking for?"};
  
  public Encounter(UI u, Player p, GameInventory gi, VisibilityManager v, Game g) {
    game = g;
    ui = u;
    in = gi;
    vm = v;

    player = p;
    updateShop();
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
      case "buyFromShop": buyFromShop(); break;
      case "selectItemsToSell": selectItemsToSell(); break;
      case "leaveShop": leaveShop(); break;
      case "sellToShop": sellToShop(); break;
      case "selectItemsToBuy": selectItemsToBuy(); break;
      case "shopSmalltalk": shopSmalltalk(); break;
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
    this.randomEncounter = (int)Math.floor(Math.random()*1);

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
    // player.inventory.add(in.getItem(0).getName());
    // player.inventory.add(in.getItem(1).getName());

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
    ui.mainTextArea.setText("You try to open the door\nIt is locked. Maybe you can open it using a key?");
    if(player.inventory.contains("key")){
      key.z = "openDoor";
    }
  }

  private void openDoor(){
    ui.drawStructure("openDoor");
    map.structureLayout[map.y][map.x] = "q";
    ui.mainTextArea.setText("You open door using a key");
    for (int i = 0; i < player.inventory.size(); i++) {
      if(player.inventory.get(i).equals("key")){
        player.inventory.remove(i);
        break;
      }
    }

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

  private void updateShop(){
    for(int i = 0; i < shopWeapons; i++){
      shopInventory.add(in.getWeapon(-1).getIndex());
    }
  }
  private void talkToShopkeeper(){
    System.out.println(player.getGold());
    ui.mainTextArea.setText("<shopkeeper>\nWhat do you want?\n\n[z]: buy     [x]: sell     [c]: small talk");
    key.z = "selectItemsToBuy";
    key.x = "selectItemsToSell";
    key.c = "shopSmalltalk";
  }
  private void shopSmalltalk(){
    ui.mainTextArea.setText(shopSmalltalk[(int)Math.floor(Math.random()*shopSmalltalk.length)]);
  }

  private void selectItemsToBuy(){
    ui.mainTextArea.setText("<shopkeeper>\nEverything has a price. I can't just give things away for free?");
    game.pIn = "buyFromShop";
    ui.selectedButtons.clear();
    ui.chageButton(null);
    vm.showchoiceButtons();
    for(int i = 0; i < ui.buttonList.size()-2; i++){
      ui.buttonList.get(i).setText(null);
    }
    for(int i = 0; i < ui.buttonList.size(); i++){
      if(i < shopWeapons){
        ui.buttonList.get(i).setText(in.Weapons.get(shopInventory.get(i)).getType());
      }
    }
    ui.buttonList.get(10).setText("buy");
    ui.buttonList.get(11).setText("Leave shop");
    key.z = "";
    key.x = "";
  }

  private void buyFromShop(){
    key.z = "";
    key.x = "";
    if(player.getGold() >= Integer.parseInt(ui.intSellCounterLabel.getText())){
      if(player.inventory.size() + ui.selectedButtons.size() <= 10){
        // ui.mainTextArea.setText("<shopkeeper>\nIts a good price, don't you think?");
        for(int i = ui.selectedButtons.size()-1; i >= 0 ; i--){
          if(ui.selectedButtons.get(i) <= shopWeapons){
            shopWeapons--;
          }
          System.out.println("wewa");
          player.inventory.add(ui.buttonList.get(ui.selectedButtons.get(i)).getText());
          shopInventory.remove(ui.selectedButtons.get(i));
          ui.selectedButtons.remove(i);
        }
        player.subtractGold(Integer.parseInt(ui.intSellCounterLabel.getText()));
        selectItemsToBuy();
      } else {
        ui.mainTextArea.setText("<shopkeeper>\nYou can't carry all this stuff, maybe you want to sell me sothing?");
      }
    } else {
      ui.mainTextArea.setText("<shopkeeper>\nLooks like youre a little short on coin. Don't waste my time");
    }
    System.out.println(player.getGold());
  }

  private void selectItemsToSell(){
    ui.mainTextArea.setText("<shopkeeper>\nWhat do you want to sell?");
    game.pIn = "sellToShop";
    vm.showchoiceButtons();
    ui.selectedButtons.clear();
    ui.chageButton(null);
    for(int i = 0; i < ui.buttonList.size()-2; i++){
      ui.buttonList.get(i).setText(null);
    }
    for(int i = 0; i < player.inventory.size(); i++){
      ui.buttonList.get(i).setText(player.inventory.get(i));
    }
    
    ui.buttonList.get(10).setText("Sell");
    ui.buttonList.get(11).setText("Leave shop");
    key.z = "";
    key.x = "";
  }

  private void sellToShop(){
    ui.mainTextArea.setText("<shopkeeper>\nWhat a steal.");

    for (int i = player.inventory.size()-1; i >= 0; i--) {
      for(int j = 0; j < ui.selectedButtons.size(); j++){
        if(player.inventory.get(i).equals(ui.buttonList.get(ui.selectedButtons.get(j)).getText())){
          ui.selectedButtons.remove(j);
          player.inventory.remove(i);
          player.addGold(Integer.parseInt(ui.intSellCounterLabel.getText()));
          break;
        }
      }
    }
    System.out.println(player.getGold());
    selectItemsToSell();
  }

  private void leaveShop(){
    ui.mainTextArea.setText("<shopkeeper>\nbye");
    vm.dontShowchoiceButtons();
    for(int i = 0; i < player.inventory.size(); i++){
      ui.buttonList.get(i).setText(player.inventory.get(i));
    }
    key.z = "selectItemsToBuy";
    key.x = "selectItemsToSell";
  }
}
