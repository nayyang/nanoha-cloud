package subscribe;

import java.util.ArrayList;
import java.util.List;

public class ConcreteWatched implements Watched {
    // 存放观察者
    private List<Watcher> list = new ArrayList<Watcher>();

    @Override
    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    @Override
    public void notifyWatchers(String str) {
        // 自动调用实际上是主题进行调用的
        for (Watcher watcher : list) {
            watcher.update(str);
        }
    }

    public static void main(String[] args) {
        Watched girl = new ConcreteWatched();

        Watcher watcher1 = new ConcreteWatcher();
        Watcher watcher2 = new ConcreteWatcher();
        Watcher watcher3 = new ConcreteWatcher();

        girl.addWatcher(watcher1);
        girl.addWatcher(watcher2);
        girl.addWatcher(watcher3);

        girl.notifyWatchers("开心");
    }
}
