
const byte BottleMotor1 = 2;
const byte BottleMotor2 = 3;
const byte BottleMotor3 = 4;
const byte BottleMotor4 = 5;
const byte BottleMotors[] = { BottleMotor1, BottleMotor2, BottleMotor3, BottleMotor4 };

const byte BottleSensor1 = A0;
const byte BottleSensor2 = A1;
const byte BottleSensor3 = A2;
const byte BottleSensor4 = A3;
const byte BottleSensors[] = { BottleSensor1, BottleSensor2, BottleSensor3, BottleSensor4 };

int activeMotors[4];

void setup() {

  Serial.begin(9600); 
  Serial.println("*******************************************************");
  Serial.println("**                     DrinkMixer                    **");
  Serial.println("**                                                   **");
  Serial.println("**    Alberto Flores S.         <Alberto@Flores.cf>  **");
  Serial.println("*******************************************************");
  
  Serial.println(" ");
  Serial.println("# Arduino Started");
  Serial.println("# Serial started at 9600");

  pinMode(BottleMotor1,OUTPUT);
  pinMode(BottleMotor2,OUTPUT);
  pinMode(BottleMotor3,OUTPUT);
  pinMode(BottleMotor4,OUTPUT);

}

void loop() {

  if (Serial.available()){
      
      ActiveBottles();
  }

}


void ActiveBottles(){

  char checkingMotors[4];
  int MotorNumber = 0;
  
  for (int Pin = 0; Pin < 4; Pin++) {

    MotorNumber++;
    String MotorString = "# Motor ";
    MotorString.concat(MotorNumber);

    if( analogRead(BottleSensors[Pin]) != 0 ){

      digitalWrite(BottleMotors[Pin],HIGH);
      MotorString.concat(": Active");
      checkingMotors[MotorNumber,'A'];
    
    }
    
    else{
      digitalWrite(BottleMotors[Pin],LOW);
      MotorString.concat(": Inactive");
      checkingMotors[MotorNumber,'I'];
    }
    Serial.println(checkingMotors[MotorNumber]);
    //Serial.println(MotorString);
          }

    delay(5000);
    Serial.println("  ");

}

