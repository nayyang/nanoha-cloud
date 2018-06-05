# 随机漫步
import matplotlib.pyplot as plt
from random_walk import RandomWalk
import time

while True:

    rw = RandomWalk()
    rw.fill_walk()
    plt.scatter(rw.x, rw.y, s=15)
    plt.rcParams['font.sans-serif'] = ['DengXian']  # 用来正常显示中文标签
    plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号

    plt.title("强总姿势", fontsize=24)
    plt.xlabel("大保健位置", fontsize=14)
    plt.ylabel("按摩次数", fontsize=14)

    plt.show()

    time.sleep(3)
