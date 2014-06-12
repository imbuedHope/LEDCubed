int const SIDE_LENGTH = 8;
int const BIT_LENGTH = 3; // = log(SIDE_LENGTH)/log(2)

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

void setup() {
  for(int i = 2; i < 14; i++)
    pinMode(i, OUTPUT);
}

void loop() {
  
}

//x = row; y = layer; z = row select
void set(int x, int y, int z, int r, int g, int b) {
  clearAllOutputs();  
  
  boolean val[3][BIT_LENGTH];
  
  for(int j = 0; j < BIT_LENGTH; j++) {
    int tmp = x%2;
    val[1][j] = (tmp==1);
    x /= 2;
  }
  
  for(int j = 0; j < BIT_LENGTH; j++) {
    int tmp = y%2;
    val[1][j] = (tmp==1);
    y /= 2;
  }
  
  for(int j = 0; j < BIT_LENGTH; j++) {
    int tmp = z%2;
    val[1][j] = (tmp==1);
    z /= 2;
  }
  
  analogWrite(PIN_R, r);
  analogWrite(PIN_G, g);
  analogWrite(PIN_B, b);
}

void clearAllOutputs() {
  for(int i = 2; i < 14; i++)
    digitalWrite(i, LOW);
}
