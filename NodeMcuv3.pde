#include "ESP8266WiFi.h"
#include "PubSubClient.h"
//wifi
const char* ssid = "nennee";
const char* password = "iovUr8rN";

const char* mqttServer = "10.42.0.1";
const int mqttPort = 1883;
char* currentState = "0";
const int pin1 = 5;

WiFiClient net;
PubSubClient client(net);

unsigned long lastMillis = 0;

void reconnect() {
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    if (client.connect("ESP8266 Client")) {
      Serial.println("connected");
      // ... and subscribe to topic
      client.subscribe("light/change");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

void setup(){
  Serial.begin(9600);

  delay(100);
  WiFi.begin(ssid, password);

  Serial.print("Wifi Connecting.");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  randomSeed(micros());

  Serial.println("Wifi Connected");

  client.setServer(mqttServer, mqttPort);
  client.setCallback(callback);

  pinMode(pin1, OUTPUT);
  //change state
  if(currentState == "0"){
    //light on
    digitalWrite(pin1, LOW);
  }else{
    //light off
    digitalWrite(pin1, HIGH);
  }
}

void loop(){
    if (!client.connected()) {
      reconnect();
    }
    client.loop();
    delay(10);
    client.publish("light",currentState);
    delay(4000);
}

void callback(char* topic, byte* payload, unsigned int length) {
  currentState = (char*)payload;
  Serial.println(currentState);
  //change state
  if(currentState == "0"){
    //light on
    digitalWrite(pin1, LOW);
  }else{
    //light off
    digitalWrite(pin1, HIGH);
  }
}
