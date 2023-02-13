public class Item {
  private String name, path;
  private int tier, value;

  public Item(String n, String p, int v, int t){
    name = n;
    path = p;
    value = v;
    tier = t;
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
