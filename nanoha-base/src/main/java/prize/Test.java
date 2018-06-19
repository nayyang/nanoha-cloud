package prize;

public class Test {
    public static void main(String[] args) throws Exception {
        Boss boss = new Boss();
        boss.call();
        Employee employee = new Employee(new Boss());
        employee.doWork();
    }
}
