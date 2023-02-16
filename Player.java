import java.util.ArrayList;

public class Player{
    private String name;
    private int 
    maxHealth = 100,
    health = 100,
    strength = 25,
    Speed = 15,
    endurance = 20,
    luck = 1,
    gold = 0;
    private Weapon myWeapon;
    private Armor myArmor;
    // private Blessing blessing;

    ArrayList<Object> inventory = new ArrayList<Object>();
  
    public String getInventoryString(int i) {
      if(inventory.get(i).getClass() == Weapon.class){
        Weapon weapon = (Weapon) inventory.get(i);
        return weapon.getType();
      }
      if(inventory.get(i).getClass() == Armor.class){
        Armor armor = (Armor) inventory.get(i);
        return armor.getType();
      }
      if(inventory.get(i).getClass() == Item.class){
        Item item = (Item) inventory.get(i);
        return item.getName();
      }
      return null;
    }

    public Player(String name){
      this.name = name;
    }
    public String getName(){
      return this.name;
    }
    public int getMaxHealth(){
      return this.maxHealth;
    }
    public void changeMaxHealth(int upMaxHealth){
      this.maxHealth += upMaxHealth;
      this.health = this.maxHealth;
    }
    public void changeHealth(int health){
      this.health = health;
    }
  
    public int getHealth(){
      return this.health;
    }
    // public void heal(){
    //   this.potions -= 1;
    //   this.health = this.maxHealth;
    // }
    // public int getPotions(){
    //   return this.potions;
    // }
    // public void addPotions(int potinon){
    //   if(this.potions < 5){
    //     this.potions += potinon;
    //   }
    // }
    
    public void addGold(int gold){
      this.gold += gold;
    }
    public void subtractGold(int gold){
      this.gold -= gold;
    }
    public int getGold(){
      return this.gold;
    }
    public int getEndurance() {
      return endurance;
    }
    public int getLuck() {
      return luck;
    }
    public int getSpeed() {
      return Speed;
    }
    public int getStrength() {
      return strength;
    }
  
    public void setMyWeapon(Weapon weapon){
      this.myWeapon = weapon;
    }
    public Weapon getMyWeapon(){
      if (this.myWeapon != null){
        return this.myWeapon;
      }
      else{
        return null;
      }
    }
    
    // public int getBaseDamage(){
    //   return this.basedamage;
    // }
    // public int getTotalDamage(){
    //   if (this.weapon != null){
    //     return this.weapon.getDamage() + this.basedamage;
    //   }
    //   return this.basedamage;
    // }
  
    public void setMyArmor(Armor armor){
      this.myArmor = armor;
    }
    public Armor getMyArmor(){
      if (this.myArmor != null){
        return this.myArmor;
      }
      else{
        return null;
      }
    }

    // public int getTotalDefence(){
    //   if (this.armor != null){
    //     return this.armor.getDefence() + this.baseDefence;
    //   }
    //   return this.baseDefence;
    // }
  
    // public int getCritChance() {
    //   return critChance;
    // }

    // public double getCritMultiplier() {
    //   return critMultiplier;
    // }

    // public void setBlessing(Blessing blessing){//måten jeg setter blessings gjør at kun disse 5 typer blessings vil ha noen effect. det er egt ikke optimalt, men jeg ser ingen annen måte og gjøre det på pga hver blessing gjør noe helt forskjeldig
    //   this.blessing = blessing;
    //   if(this.blessing.getType().equals("power")){
    //     this.basedamage += this.blessing.getEffect();
    //   }else if(this.blessing.getType().equals("luck")){
    //     this.critChance = this.blessing.getEffect() + 1;// 1/3
    //   }else if(blessing.getType().equals("destruction")){
    //   this.critMultiplier = (double)this.blessing.getEffect()/100;
    //   }else if(this.blessing.getType().equals("health")){
    //     changeMaxHealth(this.blessing.getEffect());
    //   }else if(this.blessing.getType().equals("protection")){
    //     this.baseDefence += this.blessing.getEffect();
    //   }
    // }
  
    // public Blessing getBlessing(){
    //   return this.blessing;
    // }
  }

  //dept???
  //progresjon^?