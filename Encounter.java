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
  private Weapon newWeapon;

  public String buttonPanalUse = "inventory";

  ArrayList<Integer> shopInventory = new ArrayList<>();
  private int shopWeapons;
  private int shopItems;


  private String[] shopSmalltalk = {"<shopkeeper>\nCan I help you with something specific, or are you just here to waste my time?", "<shopkeeper>\nIf you're not going to buy anything, please don't touch the merchandise.", "<shopkeeper>\nI don't have all day to stand here and chat. If you're not going to make a purchase, I suggest you leave.", "<shopkeeper>\nIf you don't know what you want, maybe you should come back when you do.", "<shopkeeper>\nI'm sorry, but I'm not in the mood for small talk right now. Is there something specific you're looking for?"};
  
  public Encounter(UI u, Player p, GameInventory gi, VisibilityManager v, Game g) {
    game = g;
    ui = u;
    in = gi;
    vm = v;

    player = p;
    updateShop();
    updateStats();
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
      case "openInventory": openInventory(); break;
      case "closeInventory": closeInventory(); break;
      case "getWeapon": getWeapon(); break;
      case "drop": drop(); break;
      case "equipWeapon": equipWeapon(); break;
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
    updateStats();
  }

  public void newEncounter(){
    key.c = "openInventory";
    this.randomEncounter = (int)Math.floor(Math.random()*3);

    ui.drawEncounter("","");
    if(randomEncounter == 0){
      foundItem();
    }
    if(randomEncounter == 1){
      foundWeapon();
    }
    if(randomEncounter == 2){
      ui.drawEncounter("monsters", "me");
    }
  }

  public void noEncounter(){
    ui.drawEncounter("","");
  }

  public void updateStats(){
    int i = 0;
    ui.statLabelList.get(i).setText("Username:");i++;
    ui.statLabelList.get(i).setText(player.getName());i++;
    ui.statLabelList.get(i).setText("HP:");i++;
    ui.statLabelList.get(i).setText(player.getHealth()+"/"+player.getMaxHealth());i++;
    ui.statLabelList.get(i).setText("Gold:");i++;
    ui.statLabelList.get(i).setText(player.getGold()+"");i++;
    ui.statLabelList.get(i).setText("Inventory:");i++;
    ui.statLabelList.get(i).setText(player.inventory.size()+"/10");i++;
    ui.statLabelList.get(i).setText("Strength:");i++;
    ui.statLabelList.get(i).setText(player.getStrength()+"");i++;
    ui.statLabelList.get(i).setText("Speed:");i++;
    ui.statLabelList.get(i).setText(player.getSpeed()+"");i++;
    ui.statLabelList.get(i).setText("Endurance:");i++;
    ui.statLabelList.get(i).setText(player.getEndurance()+"");i++;
    ui.statLabelList.get(i).setText("Luck:");i++;
    ui.statLabelList.get(i).setText(player.getLuck()+"");
  }

  private void openInventory(){
    vm.showchoiceButtons();
    ui.selectedButtons.clear();
    for(int i = 0; i < ui.buttonList.size()-2; i++){
      ui.buttonList.get(i).setText(null);
    }
    for(int i = 0; i < player.inventory.size(); i++){
      ui.buttonList.get(i).setText(player.inventory.get(i));
    }
    ui.drawButtons();
    ui.buttonList.get(11).setText("Drop");
    int i = 0;
    ui.itemInfoLabelList.get(i).setText("");i++;
    ui.itemInfoLabelList.get(i).setText("");i++;
    ui.itemInfoLabelList.get(i).setText("");i++;
    ui.itemInfoLabelList.get(i).setText("");i++;
    ui.itemInfoLabelList.get(i).setText("");i++;
    ui.itemInfoLabelList.get(i).setText("");
    game.c11 = "drop";
    key.c = "closeInventory";
  }

  private void closeInventory(){
    vm.dontShowchoiceButtons();
    game.c11 = "";
    key.c = "openInventory";
  }

  private void drop(){
    player.inventory.remove((int)ui.selectedButtons.get(0));
    System.out.println(player.inventory);
    openInventory();
  }

  private void equipWeapon(){
    for(int i = 0; i < in.Weapons.size(); i++){
      if(in.Weapons.get(i).getType() == ui.buttonList.get((int)ui.selectedButtons.get(0)).getText()){
        player.setWeapon(in.getWeapon(i));
        player.weaponIndex = 1;
        break;
      }
    }
    System.out.println(player.getWeapon());
    openInventory();
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

  private void foundWeapon(){
    newWeapon = in.getWeapon(-1);
    ui.drawEncounter("weapons", newWeapon.getPath());
    ui.mainTextArea.setText("You see a "+ newWeapon.getType());
    key.z = "getWeapon";
  }

  private void getWeapon(){
    if(player.inventory.size() >= 10){
      ui.mainTextArea.setText("you carry to many items");
    } else {
      player.inventory.add(newWeapon.getType());
      ui.mainTextArea.setText(newWeapon.getType()+" is now in youre inventory");
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
      case "north": map.y=map.y-1; map.draw(); break;
      case "south": map.y++; map.draw(); break;
      case "east": map.x++; map.draw(); break;
      case "west": map.x=map.x-1; map.draw(); break;
    }
  }

  private void updateShop(){
    shopWeapons = (int)Math.floor(Math.random()*3) + 1;
    shopItems = (int)Math.floor(Math.random()*3) + 1;
  
    for(int i = 0; i < shopWeapons; i++){
      shopInventory.add(in.getWeapon(-1).getIndex());
    }
    for(int i = 0; i < shopItems; i++){
      shopInventory.add(in.getItem(-1).getIndex());
    }
  }
  private void talkToShopkeeper(){
    System.out.println(player.getGold());
    ui.mainTextArea.setText("<shopkeeper>\nWhat do you want?\n\n[z]: buy     [x]: sell     [c]: small talk");
    key.z = "selectItemsToBuy";
    key.x = "selectItemsToSell";
    key.c = "shopSmalltalk";
    game.c11 = "leaveShop";
    buttonPanalUse = "shop";
    ui.buttonList.get(10).setText("");
  }
  private void shopSmalltalk(){
    ui.mainTextArea.setText(shopSmalltalk[(int)Math.floor(Math.random()*shopSmalltalk.length)]);
  }

  private void selectItemsToBuy(){
    ui.mainTextArea.setText("<shopkeeper>\nEverything has a price. I can't just give things away for free?");
    game.c10 = "buyFromShop";
    ui.selectedButtons.clear();
    ui.chageButton(null);
    vm.showchoiceButtons();
    for(int i = 0; i < ui.buttonList.size()-2; i++){
      ui.buttonList.get(i).setText(null);
    }
    for(int i = 0; i < ui.buttonList.size(); i++){
      if(i < shopWeapons){
        ui.buttonList.get(i).setText(in.Weapons.get(shopInventory.get(i)).getType());
      }else if(i < shopItems + shopWeapons){
        ui.buttonList.get(i).setText(in.Items.get(shopInventory.get(i)).getName());
      }
    }
    ui.stringShopCounterLabel.setText("Buy:");
    ui.updateSellCounter();
    ui.buttonList.get(11).setText("Leave shop");
    key.z = "";
    key.x = "";
  }

  private void buyFromShop(){
    key.z = "";
    key.x = "";
    if(player.getGold() >= Integer.parseInt(ui.intShopCounterLabel.getText())){
      if(player.inventory.size() + ui.selectedButtons.size() <= 10){
        // ui.mainTextArea.setText("<shopkeeper>\nIts a good price, don't you think?");
        for(int i = ui.selectedButtons.size()-1; i >= 0 ; i--){
          if(ui.selectedButtons.get(i) <= shopWeapons){
            shopWeapons--;
          }
          System.out.println("wewa");
          player.inventory.add(ui.buttonList.get(ui.selectedButtons.get(i)).getText());
          shopInventory.remove(ui.selectedButtons.get(i));
        }
        player.subtractGold(Integer.parseInt(ui.intShopCounterLabel.getText()));
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
    game.c10 = "sellToShop";
    vm.showchoiceButtons();
    ui.selectedButtons.clear();
    ui.chageButton(null);
    for(int i = 0; i < ui.buttonList.size()-2; i++){
      ui.buttonList.get(i).setText(null);
    }
    for(int i = 0; i < player.inventory.size(); i++){
      ui.buttonList.get(i).setText(player.inventory.get(i));
    }
    
    ui.stringShopCounterLabel.setText("Sell:");
    ui.updateSellCounter();
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
          break;
        }
      }
    }
    player.addGold(Integer.parseInt(ui.intShopCounterLabel.getText()));
    System.out.println(player.getGold());
    selectItemsToSell();
  }

  private void leaveShop(){
    ui.mainTextArea.setText("<shopkeeper>\nbye");
    vm.dontShowchoiceButtons();
    key.z = "talkToShopkeeper";
    key.x = "";
    game.c10 = "";
    game.c11 = "";
    buttonPanalUse = "inventory";
    ui.stringShopCounterLabel.setText("");
    ui.intShopCounterLabel.setText("");
  }
}
