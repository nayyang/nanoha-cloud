package redis.cluster;

import org.aspectj.lang.JoinPoint;

public class ShardedInterceptor {

    private ShardedInterceptor() {
        super();
    }

    public void beforeMethod(JoinPoint point) {
        // Integer count = ShardHolder.getCount();
        // if (count != null && count >= 1) {
        // ShardHolder.incrCount();
        // return;
        // }
        RedisAround annotation = getAnnotation(point);
        if (annotation != null) {
            ShardHolder.createShardedJedis(annotation.manager());
        }
    }

    public void finalMethod(JoinPoint point) {
        RedisAround annotation = getAnnotation(point);
        // ShardHolder.decrCount();
        // if (ShardHolder.getCount() == 0) {
        if (annotation != null) {
            ShardHolder.returnShardedJedis(annotation.manager());
        }
    }

    // }

    private RedisAround getAnnotation(JoinPoint point) {
        return point.getTarget().getClass().getAnnotation(RedisAround.class);
    }
}
