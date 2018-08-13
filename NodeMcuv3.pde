#include <ESP8266WiFi.h>
#include <PubSubClient.h>
//wifi
const char* ssid = "nennee";
const char* password = "iovUr8rN";

const char* mqttServer = "10.42.0.1";
const int mqttPort = 1883;
char* currentState = "0";
const char* topic = "light";
const int pin1 = 5;

WiFiClient wifiClient;
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
  if(currentState == "0"){
    //light on
    digitalWrite(pin1, LOW);
  }else{
    //light off
    digitalWrite(pin1, HIGH);
  }
  char* subTopic;
  sprintf(subTopic, "%s%s", topic,"/change");
  client.subscribe(subTopic,0);
}

void loop(){
  client.publish(topic, currentState);
  delay(2000);
}

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message: ");
  currentState = (char*)payload[0];
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
