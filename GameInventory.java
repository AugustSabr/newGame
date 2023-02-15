import java.io.File;  // Import the File class
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class GameInventory {
  //arrays med nesten alle objects spillet vil trenge
  ArrayList<Weapon> Weapons = new ArrayList<Weapon>();
  ArrayList<Item> Items = new ArrayList<Item>();
  // ArrayList<Armor> Armors = new ArrayList<Armor>();
  // ArrayList<Blessing> Blessings = new ArrayList<Blessing>();
  ArrayList<Monster> Monsters = new ArrayList<Monster>();

  private String[] categories = {"weapon", "item","armor", "blessing", "enemy"};
  private int floor = 1;

  public GameInventory(){
    for (int i = 0; i < categories.length; i++){
      try {
        File myObj = new File("localFiles/" + categories[i] + ".txt");//henter info fra relevant fil
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          switch(categories[i]){//lager objects av all infoen i filen
            case "weapon": Weapons.add(new Weapon(myReader.nextLine(), myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
            case "item": Items.add(new Item(myReader.nextLine(), myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
            // case "armor": Armors.add(new Armor(myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
            // case "blessing": Blessings.add(new Blessing(myReader.nextLine(), Integer.parseInt(myReader.nextLine()))); break;
            case "enemy": Monsters.add(new Monster(this, myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
          }
        }
        myReader.close();
      } catch (Exception e) {
        System.err.println("Couldnt read locale files. They are either incompatible with the games software, or non existent");
        System.err.println("Error message: " + e + "\n");
        // e.printStackTrace();
      }
    }
  }
  
  public Weapon getWeapon(int i) {
    Weapon w1;
    Weapon w2;
    if (i == -1){//hvis du kaller med en annen verdi får du våpenet med den indexen
      // this.floor = Room.getDungeonFloor();
      w1 = Weapons.get((int)(Math.floor(Math.random() * Weapons.size())));
      while(w1.getTier() > this.floor){//passer på at funkjsonen ikke returnerer et våpen men forhøy tier
        w1 = Weapons.get((int)(Math.floor(Math.random() * Weapons.size())));
      }
      w2 = new Weapon(w1.getType(), w1.getPath(), w1.getTier(), w1.getDamage(), w1.getValue());
      return w2;
    } else {
      w1 = Weapons.get(i);
      w2 = new Weapon(w1.getType(), w1.getPath(), w1.getTier(), w1.getDamage(), w1.getValue());
      return w2;
    }
  }

  public Item getItem(int i) {
    Item i1;
    Item i2;
    if (i == -1){//hvis du kaller med en annen verdi får du våpenet med den indexen
      // this.floor = Room.getDungeonFloor();
      i1 = Items.get((int)(Math.floor(Math.random() * Items.size())));
      i2 = new Item(i1.getName(), i1.getPath(), i1.getTier(), i1.getValue());
      return i2;
    } else {
      i1 = Items.get(i);
      i2 = new Item(i1.getName(), i1.getPath(), i1.getTier(), i1.getValue());
      return i2;
    }
  }

  // public Armor getArmor(int i) {
  //   if (i == -1){
  //     this.floor = Room.getDungeonFloor();
  //     Armor a = Armors.get((int)(Math.floor(Math.random() * Armors.size())));
  //     while(a.getTier() > this.floor){
  //       a = Armors.get((int)(Math.floor(Math.random() * Armors.size())));
  //     }
  //     return a;
  //   } else {
  //     return Armors.get(i);
  //   }
  // }

  // public Blessing getBlessing(int i) {
  //   if (i == -1){
  //     return Blessings.get((int)(Math.floor(Math.random() * Blessings.size())));
  //   } else{
  //     return Blessings.get(i);
  //   }
  // }

  public Monster getMonster(int i) {
    if (i == -1){
      // this.floor = Room.getDungeonFloor();
      Monster e = Monsters.get((int)(Math.floor(Math.random() * Monsters.size())));
      while(e.getTier() > this.floor){
        e = Monsters.get((int)(Math.floor(Math.random() * Monsters.size())));
      }
      return e;
    } else {
      return Monsters.get(i);
    }
  }
}
