import paho.mqtt.client as mqtt

class MyMQTTClass(mqtt.Client):

    def on_connect(self, client, flags, userdata, rc):
        print("Connected with Result Code: "+str(rc))
        client.subscribe("house/room1/temperature")

    def on_message(self, client, userdata, msg):
        print(msg.topic+" "+str(msg.payload.decode("utf-8")))

    def on_publish(self, client, userdata, mid):
        print("mid: "+str(mid))

    def on_subscribe(self, client, userdata, mid, granted_qos):
        print("Subscribed: "+str(mid)+" "+str(granted_qos))

    def on_log(self, client, userdata, level, log_msg):
        print(log_msg)

    def run(self):
        self.connect("localhost", 1883, 60)
        self.subscribe("house/room1/temperature", 1)
        rc = 0
        while rc==0:
            rc=self.loop()
        return rc

client = MyMQTTClass()
rc = client.run()

print("rc: "+str(rc))
