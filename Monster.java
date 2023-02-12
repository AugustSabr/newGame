public class Monster {
  private String enemyType;

  private int tier, enemyHealth, enemyDamage;

  // private Weapon weapon;
  // private Armor armor;
  private GameInventory in;

  public Monster (GameInventory gi, String et, int tr, int ed, int eh) {
    in = gi;
    enemyType = et;
    tier = tr;
    enemyDamage = ed;
    enemyHealth = eh;
  }

  // public Enemy giveGear() {
  //   int gear = (int)(Math.floor(Math.random()*4));
  //   switch (gear){
  //     case 0: weapon = in.getWeapon(-1); break;
  //     case 1: armor = in.getArmor(-1); break;
  //     case 2: weapon = in.getWeapon(-1); armor = in.getArmor(-1); break;
  //   }
  //   return this;
  // }

  public int getTier() {
    return tier;
  }

  public String getType(){
    return this.enemyType;
  }

  public int getHealth(){
    return this.enemyHealth;
  }

  // public Weapon getWeapon(){
  //   if (this.weapon != null){
  //     return this.weapon;
  //   }
  //   else{
  //     return null;
  //   }
  // }

  public int getBaseDamage(){
    return this.enemyDamage;
  }

  // public Armor getArmor(){
  //   if (this.armor != null){
  //     return this.armor;
  //   }
  //   else{
  //     return null;
  //   }
  // }
}