#include <ESP8266WiFi.h>
#include <PubSubClient.h>
//wifi
const char* ssid = "nennee";
const char* password = "123456";

const char* mqttServer = "192.168.1.1";
const int mqttPost = 1883;

WifiClient WifiClient;
PubSubClient client(wifiClient);

void setup(){
  Serial.begin(9600);
  WiFi.begin(ssid, password);

  while(WiFi.status() != WL_CONNECTED){
    delay(500);
    Serial.print("Connecting to WiFi..");
  }
  Serial.println("Connected to the WiFi network");

  client.setServer(mqttServer, mqttPort);
  client.setCallback(callback);
}

void loop(){

}

void callback(char* topic, byte* payload, unsigned int length) {

  Serial.print("Message arrived in topic: ");
  Serial.println(topic);

  Serial.print("Message:");
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
  }

  Serial.println();
  Serial.println("-----------------------");

}
