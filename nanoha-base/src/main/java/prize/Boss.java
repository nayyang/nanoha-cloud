package prize;

public class Boss implements CallBack {

    @Override
    public void doEvent() {
        System.out.println("老板：那小子干完了，今晚加鸡腿！");
    }

    @Override
    public void call() {
        System.out.println("老板：小子，开始干活");
    }

}
