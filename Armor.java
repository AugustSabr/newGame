import java.io.Serializable;
public class Armor extends objectSuperClass implements Serializable{
  private int armorDefence;

  public Armor(String ty, String p, int ad, int va, int ti) {// tar inn tre verdier og lager en rusning
    type = ty;
    path = p;
    tier = ti;
    armorDefence = ad;
    value = va;
  }

  // public Armor reinforceArmor() {// oppgraderer rustningen med 20 defence
  //   type = "reinforced " + type;
  //   armorDefence = armorDefence + 20;
  //   return null;
  // }

  public int getDefence(){
    return armorDefence;
  }
}