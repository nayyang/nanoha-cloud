from random import choice


class RandomWalk():
    """一个生成随机漫步数据的类"""

    def __init__(self, nums=5000):
        """初始化随机漫步的属性"""
        self.nums = nums

        # 所有随机漫步都始于(0,0)
        self.x = [0]
        self.y = [0]

    def fill_walk(self):
        """计算随机漫步包含的所有点"""

        # 不断漫步，直到列表达到指定的长度
        while len(self.x) < self.nums:
            # 决定前进方向以及沿这个方向前进的距离
            x_direction = choice([1, -1])
            x_distance = choice(range(0, 5))
            x_step = x_direction * x_distance

            y_direction = choice([1, -1])
            y_distance = choice(range(0, 5))
            y_step = y_direction * y_distance

            # 拒绝原地踏步
            if x_step == 0 and y_step == 0:
                continue

            # 计算下一个点的x和y值
            next_x = self.x[-1] + x_step
            next_y = self.y[-1] + y_step

            self.x.append(next_x)
            self.y.append(next_y)
