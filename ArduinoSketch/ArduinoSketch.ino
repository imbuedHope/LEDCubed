int const SIDE_LENGTH = 8;

int const PIN_X0 = 2;
int const PIN_X1 = 4;
int const PIN_X2 = 7;

int const PIN_Y0 = 8;
int const PIN_Y1 = 9;
int const PIN_Y2 = 10;

int const PIN_Z0 = 11;
int const PIN_Z1 = 12;
int const PIN_Z2 = 13;

int const PIN_R = 3;
int const PIN_G = 5;
int const PIN_B = 6;

int xOut [SIDE_LENGTH];
int yOut [SIDE_LENGTH];
int zOut [SIDE_LENGTH];

void setup() {
  for(int i = 2; i < 14; i++)
    pinMode(i, OUTPUT);
}

void loop() {
  
}

//x = row; y = layer; z = row select
void set(int x, int y, int z, int r, int g, int b) {
  clearAllOutputs();  
  switch(x) {
    case 0:
      digitalWrite(PIN_X0, HIGH);
      break;
    case 1:
      digitalWrite(PIN_X1, HIGH);
      break;
    case 2:
      digitalWrite(PIN_X2, HIGH);
      break;
  }
  
  switch(y) {
    case 0:
      digitalWrite(PIN_Y0, HIGH);
      break;
    case 1:
      digitalWrite(PIN_Y1, HIGH);
      break;
    case 2:
      digitalWrite(PIN_Y2, HIGH);
      break;
  }
  
  switch(z) {
    case 0:
      digitalWrite(PIN_Z0, HIGH);
      break;
    case 1:
      digitalWrite(PIN_Z1, HIGH);
      break;
    case 2:
      digitalWrite(PIN_Z2, HIGH);
      break;
  }
  
  analogWrite(PIN_R, r);
  analogWrite(PIN_G, g);
  analogWrite(PIN_B, b);
}

void clearAllOutputs() {
  for(int i = 2; i < 14; i++)
    digitalWrite(i, LOW);
}
