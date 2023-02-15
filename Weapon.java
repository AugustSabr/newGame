import java.io.Serializable;
public class Weapon implements Serializable {
  private String weaponType, path;
  private int tier, weaponDamage, value;

  public Weapon(String wt, String ph, int tr, int wd, int va) {// tar inn tre verdier og lager et våpen
    weaponType = wt;
    path = ph;
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
  public String getPath() {
    return path;
  }

  public int getDamage() {
    return this.weaponDamage;
  }

  public int getValue() {
    return value;
  }
}