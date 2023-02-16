import java.io.Serializable;
public class Armor implements Serializable{
  private String armorType, path;
  private int tier, armorDefence, value;

  public Armor(String at, String ph, int tr, int ad, int va) {// tar inn tre verdier og lager en rusning
    armorType = at;
    path = ph;
    tier = tr;
    armorDefence = ad;
    value = va;
  }

  public Armor reinforceArmor() {// oppgraderer rustningen med 20 defence
    armorType = "reinforced " + armorType;
    armorDefence = armorDefence + 20;
    return null;
  }

  public int getTier() {
    return tier;
  }
  public String getType(){
    return armorType;
  }
  public int getDefence(){
    return armorDefence;
  }
  public String getPath() {
    return path;
  }
  public int getValue() {
    return value;
  }
}