import java.io.Serializable;
public class Weapon implements Serializable {
  private String weaponType;
  private int tier, weaponDamage, value, index;

  public Weapon(String wt, int tr, int wd, int va, int index) {// tar inn tre verdier og lager et våpen
    weaponType = wt;
    tier = tr;
    weaponDamage = wd;
    value = va;
  }

  public Weapon reinforceWeapon() {// oppgraderer våpene med 30 skade
    weaponType = "reinforced " + weaponType;
    weaponDamage = weaponDamage + 30;
    return null;
  }

  public int getTier() {
    return this.tier;
  }

  public String getType() {
    return this.weaponType;
  }

  public int getDamage() {
    return this.weaponDamage;
  }

  public int getValue() {
    return value;
  }
  public int getIndex() {
    return index;
  }
}