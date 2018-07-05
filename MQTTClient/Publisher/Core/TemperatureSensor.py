import paho.mqtt.client as mqtt
import random as rand
import time

def on_connect(self, flags, userdata, rc):
    print("Connected with Result Code: "+str(rc))
    client.subscribe("house/room1/temperature")

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
def getTemperature():
    return rand.randrange(20, 40)

client = mqtt.Client()
client.on_message = on_message
client.on_publish = on_publish
client.on_connect = on_connect
client.on_subscribe = on_subscribe
client.on_log = on_log

client.connect("localhost", 1883, 60)

while True:
    client.loop_start()
    infoTemprature = client.publish("house/room1/temperature", getTemperature(), qos=2)
    infoTemprature.wait_for_publish()
    time.sleep(15)
