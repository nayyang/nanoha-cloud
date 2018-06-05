import matplotlib.pyplot as plt

plt.rcParams['font.sans-serif']=['DengXian'] #用来正常显示中文标签
plt.rcParams['axes.unicode_minus']=False #用来正常显示负号

input_value=[1,2,3,4,5]

squares=[1,4,9,16,25]

#plt.plot(squares)

plt.plot(input_value,squares,linewidth=5)
#设置图表标题，给坐标轴加上标签
plt.title("强总姿势",fontsize=24)
plt.xlabel("收入（10K）",fontsize=14)
plt.ylabel("工作年限",fontsize=14)
#刻度
plt.tick_params(axis="both",labelsize=14)

plt.show()