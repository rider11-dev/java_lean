package didemo;

public class Dog {
    private String name;
    private Integer age;
    private String call;

    public void setCall(String call) {
        System.out.println("Dog setCall：" + call);
        this.call = call;
    }

    public void setName(String name) {
        System.out.println("Dog setName：" + name);
        this.name = name;
    }

    public void setAge(Integer age) {
        System.out.println("Dog setAge：" + age);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", call='" + call + '\'' +
                '}';
    }
}
