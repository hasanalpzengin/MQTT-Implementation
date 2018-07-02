import paho.mqtt.client as mqtt
import random as rand
import time

def on_connect(self, flags, userdata, rc):
    print("Connected with Result Code: "+str(rc))
    client.subscribe("house/bulb/bulb1")

def on_message(self, userdata, msg):
    print(msg.topic+" "+str(msg.payload))

def on_publish(self, userdata, mid):
    print("mid: "+str(mid))
    pass

def on_subscribe(self, userdata, mid, granted_qos):
    print("Subscribed: "+str(mid)+" "+str(granted_qos))

def on_log(self, userdata, level, log_msg):
    print(log_msg)

#dummy
def getHumidity():
    return rand.randrange(0, 90)

client = mqtt.Client()
client.on_message = on_message
client.on_publish = on_publish
client.on_connect = on_connect
client.on_subscribe = on_subscribe
client.on_log = on_log

client.connect("localhost", 1883, 60)
while True:
    client.loop_start()
    infoHumidity = client.publish("house/room1/humidity", getHumidity(), qos=2)
    infoHumidity.wait_for_publish()
    time.sleep(15)
