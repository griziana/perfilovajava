package s06.z04;


public class User {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String city;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

/*    public User(int userid, String username, String usersurname, int userage, String usercity) {
        id = userid;
        name = username;
        surname = usersurname;
        age = userage;
        city = usercity;

    }*/

    public String toString() {
        return "User{" + "id:" + id + ", name:" + name + ", surname:" + surname + ", age=" + age + ", city:" + city + '}';

    }
}