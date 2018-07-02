import paho.mqtt.client as mqtt
import random as rand
import time
import _thread
import threading

status = 0

def on_connect(mqttc, userdata, flags, rc):
    print("Connected with Result Code: "+str(rc))
    client.subscribe("house/light/2/change",0)

def on_publish(userdata, mid):
    print("mid: "+str(mid))
    pass

def on_message(client, userdata, msg):
    print("Message Fetched")
    global status
    status = int(msg.payload.decode("utf-8"))
    print(status)

def on_subscribe(client, userdata, mid, granted_qos):
    print("Subscribed: "+str(mid)+" "+str(granted_qos))

def on_log(client, userdata, level, log_msg):
    print(log_msg)

def startPublish(client):
    while(True):
        global status
        infoLight = client.publish("house/light/2", status, qos=0)
        infoLight.wait_for_publish()
        time.sleep(5)


client = mqtt.Client(client_id="light2")
client.on_message = on_message
client.on_log = on_log
client.on_connect = on_connect
client.on_subscribe = on_subscribe

client.connect("localhost", 1883, 60)
thread1 = threading.Thread(target=startPublish, args=(client, ), name="thread-4")
thread1.start()

client.loop_forever()
