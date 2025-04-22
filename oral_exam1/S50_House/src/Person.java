/**
 * A person with a name
 */
public class Person {
    private String name;

    /**
     * get the name of the person
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the person
     * @param name the name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * create a person with a name
     * @param name the name of the person
     */
    Person(String name) {
        this.name = name;
    }

    /**
     * To String method
     * @return name of the person
     */
    @Override
    public String toString() {
        return name;
    }

}
