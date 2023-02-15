public class Item {
  private String name, path;
  private int tier, value, index;

  public Item(String n, String p, int t, int v){
    name = n;
    path = p;
    tier = t;
    value = v;

  }

  public String getName() {
    return name;
  }
  public String getPath() {
    return path;
  }
  public int getValue() {
    return value;
  }
  public int getTier() {
    return tier;
  }
}
