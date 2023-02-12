public class Item {
  String name = "key";
  String path = "key";
  UI ui;
  
  public Item(UI u){
    ui = u;
  }

  public void newItem(){
    ui.drawEncounter("items", path);
  }
}
