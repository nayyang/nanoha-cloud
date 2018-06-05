message = "卧槽强总不服咯"
message2 = "nihaoya"
# fuck you
message3 = 223
print(message + str(message3) + message2)

import this

list_qiang = ["1", "2", "3"]
print(list_qiang[1])
list_qiang.append("卧槽强总还不搞Python？")
print(list_qiang)

for qiang in list_qiang:
    print(qiang)
    print("呵呵")

for value in range(1, 7):
    print(value)

# list_longs=range(1,1000000001)
# print(min(list_longs))
# print(max(list_longs))
# print(sum(list_longs

print(True)

alien_0 = {"color": "green", "points": 5}
alien_0["x_position"] = 0
alien_0["y_position"] = 25
print(alien_0)
alien_0["speed"] = "medium"
print(alien_0)
if alien_0["speed"] == "slow":
    x_increment = 1
elif alien_0["speed"] == "medium":
    x_increment = 2
else:
    x_increment = 3

alien_0["x_position"] = alien_0["x_position"] + x_increment
print(alien_0)

for key, value in alien_0.items():
    print(key + " === " + str(value))

import matplotlib
print(matplotlib.matplotlib_fname())

print(pow(25,0.5))

list1=[123]
list2=[456]
list1=list1+list2
print(list1)
