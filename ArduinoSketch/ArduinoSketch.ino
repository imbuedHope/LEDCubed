#include <SD.h>


//int const SIDE_LENGTH = 8;
//int const BIT_LENGTH = 3; // = log(SIDE_LENGTH)/log(2)

//int const PIN_AR [3][BIT_LENGTH] = {{  2,  4,  7}, {  8,  9, 10}, { 11, 12, 13}};

/*variables for the makeArray function*/
int pin_array [6][2000]; // each member of "x" is the (x, y, z) coordinate. I put in 2000 LEDS just incase
int fps=0; 
int rerun=0;
int num_frames=0; //
int num_pixels=0; //number of the current led info passing in                                    
                                    
/*
int const PIN_X0 = 2;
int const PIN_X1 = 4;
int const PIN_X2 = 7;

int const PIN_Y0 = 8;
int const PIN_Y1 = 9;
int const PIN_Y2 = 10;

int const PIN_Z0 = 11;
int const PIN_Z1 = 12;
int const PIN_Z2 = 13;
*/

int const PIN_R = 3;
int const PIN_G = 5;
int const PIN_B = 6;

//////////////////////////////////////////////

File myFile;


void setup() {
  for(int i = 2; i < 14; i++)
    pinMode(i, OUTPUT)
  
  
  Serial.begin(9600);                      // Initializing the serial port
}


pinMode(10, OUTPUT);                       // for the sd library to work properly

if (!SD.begin(4)) {
    Serial.println("Failed to read SD-card!");
    return;
  }


int i=0;                                   // To be used for file name


void ReadFile(){

 while(1){
  string path = "A/%d.txt", i ;                  // Defines the path for the file to access in the SD

  myFile = SD.open(path);   // Open the i.txt in Animation A

  if(myFile){
      Serial.println("Reading file: %s", path);
    
      while (myFile.available()) {          // Reading from the file until there's nothing left in it
         Serial.write(myFile.read());
      }
    myFile.close();
    
    i=i+1;                                  // increment i for next file
    
   } 
  else{
    Serial.println("Error opening file: %s", path);
    break;                                 // Get out of the loop! We are done!
  }
 }
}
///////////////////////////////////////////////

void loop() {
  
  
 
}

//x = row; y = layer; z = row select
void set(int x, int y, int z, int r, int g, int b) {
  clearAllOutputs();  
  
  boolean val[3][BIT_LENGTH];
  
  for(int j = 0; j < BIT_LENGTH; j++) {
    int tmp = x%2;
    val[0][j] = (tmp==1);
    x /= 2;
  }
  
  for(int j = 0; j < BIT_LENGTH; j++) {
    int tmp = y%2;
    val[1][j] = (tmp==1);
    y /= 2;
  }
  
  for(int j = 0; j < BIT_LENGTH; j++) {
    int tmp = z%2;
    val[2][j] = (tmp==1);
    z /= 2;
  }
  
  for(int i = 0; i < BIT_LENGTH; i++) {
    for(int j = 0; j < BIT_LENGTH; j++) {
      if(val[i][j])
        digitalWrite(PIN_AR[i][j], HIGH);
    }
  }
  
  analogWrite(PIN_R, r);
  analogWrite(PIN_G, g);
  analogWrite(PIN_B, b);
}

void clearAllOutputs() {
  for(int i = 2; i < 14; i++)
    digitalWrite(i, LOW);
}
