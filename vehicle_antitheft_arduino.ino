5#include <SoftwareSerial.h>
#include <TinyGPS++.h>

float lattitude,longitude; // create variable for latitude and longitude object 
SoftwareSerial gpsSerial(8,9);//tx,rx
SoftwareSerial gsmm(2,3);//tx,rx
TinyGPSPlus gps;// create gps object

int led = 7;
String message;

void setup(){
   Serial.println("Module started");  
  gsmm.begin(9600);   // Setting the baud rate of GSM Module  
  gpsSerial.begin(9600);    // Setting the baud rate of Serial Monitor (Arduino)
  Serial.begin(9600);    // Setting the baud rate of Serial Monitor (Arduino)
  delay(100);

  gsmm.println("AT+CNMI=2,2,0,0,0"); // AT Command to receive a live SMS
  delay(1000);
 
  pinMode(led,OUTPUT);
  digitalWrite(led,LOW);     
  Serial.println("Module started");  
}

void loop(){
 Serial.println("Checking SMS");  
 gsmm.listen();
 if (gsmm.available()>0){
  Serial.println(gsmm.available());
  message = gsmm.readString();
 }
 Serial.println(message);
 
  if (message.indexOf("GPS") >= 0) {
    textgps();
  gsmm.print("\r");
    delay(1000);                  
    gsmm.print("AT+CMGF=1\r");    
    delay(1000);
  
  gsmm.print("AT+CMGS=\"+918735004766\"\r");    //Replace XXXXXXXXXX to 10 digit mobile number &  ZZ to 2 digit country code/
  delay(1000);
 
  gsmm.print("https://www.google.com/maps/place/");
  gsmm.print(lattitude, 6);
  gsmm.print(",");
  gsmm.print(longitude, 6);
  
  delay(1000);
  gsmm.write(0x1A);
  delay(1000); 
  message="";
  }
  else if(message.indexOf("ON") > -1) {
  Serial.println("LED ON");
  digitalWrite(led,HIGH);
  }
  else if(message.indexOf("OFF") > -1) {
  Serial.println("LED OFF");
  digitalWrite(led,LOW);
  }

  delay(1000); 
}


void textgps()
{
   while (gpsSerial.available() > 0)
   { gps.encode(gpsSerial.read()); }

  if (gps.location.isUpdated())
  {
   Serial.print("LAT=");  Serial.println(gps.location.lat(), 6);
   Serial.print("LONG="); Serial.println(gps.location.lng(), 6);
   lattitude=gps.location.lat();
   longitude=gps.location.lng();
  }

 Serial.print("LATTITUDE="); Serial.println(lattitude,6);
 Serial.print("LONGITUDE="); Serial.println(longitude,6);
 
 delay(1000);
}
