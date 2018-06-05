import matplotlib.pyplot as plt

plt.rcParams['font.sans-serif']=['DengXian'] #用来正常显示中文标签
plt.rcParams['axes.unicode_minus']=False #用来正常显示负号

#s尺寸
#plt.scatter(2,4,s=20000)

xs=[1,2,3,4,5]
ys=[]
for x in xs:
    ys.append(pow(x,2))
print(ys)

xs=list(range(-100,101))

#ys=[x**3 for x in xs]
ys=[]
for x in xs:
    ys.append(pow(pow(100,2)-pow(x,2),0.5))
for x in xs:
    ys.append(0-pow(pow(100,2)-pow(x,2),0.5))

xs_temp=xs[:]
xs_temp.reverse()
xs=xs+xs_temp
#plt.scatter(xs,ys,s=40,edgecolor="none",c=ys,cmap=plt.cm.Blues)

print(len(xs)==len(ys))

plt.scatter(xs,ys,s=40,c="Red")

plt.title("强总姿势",fontsize=24)
plt.xlabel("收入（10K）",fontsize=14)
plt.ylabel("工作年限",fontsize=14)

plt.tick_params(axis="both",which="major",labelsize=14)

plt.show()