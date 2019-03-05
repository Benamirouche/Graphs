package mainPackage;

/**
 * Idea la classe qui represente l'idée qu'un noeud peut adopter
 */
public class Idea {

  private String name;
  private float index;

  private float value;

  public Idea(String name)
{
  this.name=name;	
}

  public Idea(String name, float index)
  {
    this.index=index;
    this.name=name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getIndex() {
    return index;
  }

  public void setIndex(float index) {
    this.index = index;
  }

  public String getName() {
    return name;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Idea{" +
            name  +
            " ->" + value +
            '}';
  }
}
