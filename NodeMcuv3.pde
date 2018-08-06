#include <ESP8266WiFi.h>
#include <PubSubClient.h>
//wifi
const char* ssid = "nennee";
const char* password = "123456";

const char* mqttServer = "192.168.1.1";
const int mqttPost = 1883;
const int currentState = 0;
const char* topic = "house/light/1";
const int pin1 = 5;

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

  while(!client.connected()){
    Serial.println("Connecting to MQTT...");
    if(client.connect("light1")){
      Serial.println("Client Connected");
    }else{
      Serial.print("failed with state ");
      Serial.print(client.state());
      delay(2000);
    }
  }
  //gpio 5 - d1
  pinMode(pin1, OUTPUT);
  //change state
  if(currentState == 1){
    //light on
    digitalWrite(pin1, LOW);
  }else{
    //light off
    digitalWrite(pin2, HIGH);
  }

  client.subscribe(topic+"/change");
}

void loop(){
  client.publish(topic, currentState);
  delay(2000);
}

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message: ");
  int currentState = (int)payload[0];
  Serial.println(currentState);
  //change state
  if(currentState == 1){
    //light on
    digitalWrite(pin1, LOW);
  }else{
    //light off
    digitalWrite(pin2, HIGH);
  }
}
