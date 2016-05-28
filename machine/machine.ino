#include <SimpleDHT.h>

// Cooling

const int CoolingButton = 10;    
const int Cooling =  11;
int ledState = LOW;
boolean buttonState = LOW; 
int pressed=0;

// sensor de temperatura dht11
const byte TemperatureSensor = 12;

// Active Motors
const byte BottleMotor1 = 2;
const byte BottleMotor2 = 3;
const byte BottleMotor3 = 4;
const byte BottleMotor4 = 5;
const byte BottleMotors[4] = { BottleMotor1, BottleMotor2, BottleMotor3, BottleMotor4 };

// Water sensors
const byte BottleSensor1 = A0;
const byte BottleSensor2 = A1;
const byte BottleSensor3 = A2;
const byte BottleSensor4 = A3;
const byte BottleSensors[4] = { BottleSensor1, BottleSensor2, BottleSensor3, BottleSensor4 };

char ActiveMotors[4] = {'I', 'I', 'I','I'};

void setup() {

  Serial.begin(9600); 
  Serial.println("*******************************************************");
  Serial.println("**                     DrinkMixer                    **");
  Serial.println("**                                                   **");
  Serial.println("**    Alberto Flores S.         <Alberto@Flores.cf>  **");
  Serial.println("*******************************************************");
  
  Serial.println(" ");
  Serial.println("# Arduino Started");
  Serial.println("# Serial started at 9600 Bauds");
  Serial.println(" ");
  CheckTemperature();
  
  pinMode(BottleMotor1,OUTPUT);
  pinMode(BottleMotor2,OUTPUT);
  pinMode(BottleMotor3,OUTPUT);
  pinMode(BottleMotor4,OUTPUT);

//  pinMode(BottleSensor1,INPUT);
//  pinMode(BottleSensor2,INPUT);
//  pinMode(BottleSensor3,INPUT);
//  pinMode(BottleSensor4,INPUT);

  pinMode(Cooling, OUTPUT);
  pinMode(CoolingButton, INPUT);
  
}

void loop() {

  ActiveBottles();
  //CheckTemperature();
  TurnCooling();
}

void ActiveBottles(){

  int MotorNumber = 0;
  char CheckingMotors[4];
  boolean flag = false;
  
  for (int Pin = 0; Pin < 4; Pin++) {

    MotorNumber++;
    String MotorString = "# Motor ";
    MotorString.concat(MotorNumber);

    if( analogRead(BottleSensors[Pin]) != 0 ){
      digitalWrite(BottleMotors[Pin],HIGH);
      MotorString.concat(": Active");
      CheckingMotors[Pin] = 'A';
    }
    else{
      digitalWrite(BottleMotors[Pin],LOW);
      MotorString.concat(": Inactive");
      CheckingMotors[Pin] = 'I';
    }

        if ( CheckingMotors[Pin] !=  ActiveMotors[Pin]){
      flag = true;
      ActiveMotors[Pin] = CheckingMotors[Pin];
    }
    
    if (flag){
      Serial.println(MotorString);
      
    }
//flag = false;
  }

  //delay(1000);

}

void CheckTemperature(){

  byte temperature = 0;
  byte humidity = 0;
  if (simple_dht11_read(TemperatureSensor, &temperature, &humidity, NULL)) {
    Serial.print("Read temperature failed.");
    return;
  }
  Serial.print("# Temperature: "); Serial.print((int)temperature); Serial.println(" C"); 
  Serial.print("# Humidity: "); Serial.print((int)humidity); Serial.println(" %");
  
  delay(1000);
}

boolean debounceButton(boolean state)
{
  boolean stateNow = digitalRead(CoolingButton);
  if(state!=stateNow)
  {
    delay(10);
    stateNow = digitalRead(CoolingButton);
  }
  return stateNow;
  
}

void TurnCooling(){
    if(debounceButton(buttonState) == HIGH && buttonState == LOW)
  {
    pressed++;
    buttonState = HIGH;
  }
  else if(debounceButton(buttonState) == LOW && buttonState == HIGH)
  {
       buttonState = LOW;
  }
   if(pressed == 1)
  {
    digitalWrite(Cooling,HIGH);
  }
  else{
    digitalWrite(Cooling,LOW);
    pressed = 0;
  }
}


