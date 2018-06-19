package prize;

public class Employee {
    CallBack callBack;

    public Employee(CallBack callBack) {
        this.callBack = callBack;
    }

    public void doWork() throws Exception {
        System.out.println("员工：老子TMD正在干。。。");
        System.out.println("员工：老子休息3秒抽根烟。。。");
        Thread.sleep(3000);
        System.out.println("员工：老子干完了。。。");
        callBack.doEvent();
    }
}
