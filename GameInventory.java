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
      int index = 0;
      try {
        File myObj = new File("localFiles/" + categories[i] + ".txt");//henter info fra relevant fil
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          switch(categories[i]){//lager objects av all infoen i filen
            case "weapon": Weapons.add(new Weapon(myReader.nextLine(), myReader.nextLine(),Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), index)); break;
            case "item": Items.add(new Item(myReader.nextLine(), myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), index)); break;
            // case "armor": Armors.add(new Armor(myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
            // case "blessing": Blessings.add(new Blessing(myReader.nextLine(), Integer.parseInt(myReader.nextLine()))); break;
            case "enemy": Monsters.add(new Monster(this, myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
          }
          index++;
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
    if (i == -1){//hvis du kaller med en annen verdi får du våpenet med den indexen
      // this.floor = Room.getDungeonFloor();
      Weapon w = Weapons.get((int)(Math.floor(Math.random() * Weapons.size())));
      while(w.getTier() > this.floor){//passer på at funkjsonen ikke returnerer et våpen men forhøy tier
        w = Weapons.get((int)(Math.floor(Math.random() * Weapons.size())));
      }
      return w;
    } else {
      return Weapons.get(i);
    }
  }

  public Item getItem(int i) {
    if (i == -1){//hvis du kaller med en annen verdi får du våpenet med den indexen
      // this.floor = Room.getDungeonFloor();
      Item it = Items.get((int)(Math.floor(Math.random() * Items.size())));
      while(it.getTier() > this.floor){//passer på at funkjsonen ikke returnerer et våpen men forhøy tier
        it = Items.get((int)(Math.floor(Math.random() * Items.size())));
      }
      return it;
    } else {
      return Items.get(i);
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
