package subscribe;

/**
 * 
 * @Title: 观察者
 * @Description:
 * @Author:nayyang
 * @Since:2016年11月29日
 * @Version:1.0
 */
public class ConcreteWatcher implements Watcher {

    @Override
    public void update(String str) {
        System.out.println(str);
    }

}